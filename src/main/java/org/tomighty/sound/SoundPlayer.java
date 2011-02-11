/*
 * Copyright (c) 2010 Célio Cidral Junior
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.tomighty.sound;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import org.tomighty.ioc.Inject;
import org.tomighty.ioc.New;
import org.tomighty.log.Log;

public class SoundPlayer {
	
	@Inject @New private Log log;
	private Map<Sound, Clip> activeClips = Collections.synchronizedMap(new HashMap<Sound, Clip>());

	public SoundChain play(Sound sound) {
		return play(sound, false);
	}
	
	public SoundChain playRepeatedly(Sound sound) {
		return play(sound, true);
	}

	public void stop(Sound sound) {
		Clip clip = activeClips.get(sound);
		if(clip != null) {
			clip.stop();
		}
	}
	
	private SoundChain play(final Sound sound, boolean repeatedly) {
		return play(sound, repeatedly, new SoundChain());
	}

	private SoundChain play(final Sound sound, boolean repeatedly, final SoundChain chain) {
		stop(sound);
		if(sound.disabled()) {
			chain.takeOver();
			return chain;
		}
		try {
			InputStream stream = sound.inputStream();
			//wrap the stream in another one that supports mark/reset (see issue #31)
			stream = new BufferedInputStream(stream);
			AudioInputStream input = AudioSystem.getAudioInputStream(stream);
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(new ClipHandler(sound, chain));
			clip.open(input);
			if(repeatedly) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}
			activeClips.put(sound, clip);
		} catch (Exception e) {
			log.error("Error while playing sound: "+sound, e);
		}
		return chain;
	}
	
	public class SoundChain {

		private Sound sound;
		private SoundChain nextChain;
		private boolean repeatedly;
		private boolean takeOver = false;
		
		void playNextSound() {
			if(sound != null) {
				SoundPlayer.this.play(sound, repeatedly, nextChain);
			}
		}

		void takeOver() {
			this.takeOver = true;
		}

		public SoundChain playRepeatedly(Sound sound) {
			this.sound = sound;
			this.repeatedly = true;
			this.nextChain = new SoundChain();
			if(takeOver) {
				playNextSound();
			}
			return nextChain;
		}
		
	}
	
	private class ClipHandler implements LineListener {

		private final Sound sound;
		private final SoundChain chain;

		public ClipHandler(Sound sound, SoundChain chain) {
			this.sound = sound;
			this.chain = chain;
		}

		@Override
		public void update(LineEvent event) {
			if(event.getType().equals(LineEvent.Type.STOP)) {
				closeClip();
				chain.playNextSound();
			}
		}

		private void closeClip() {
			Clip clip = activeClips.get(sound);
			clip.close();
			activeClips.remove(sound);
		}
		
	}

}
