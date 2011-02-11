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

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceSound implements Sound {

	private final String resourceName;

	public ResourceSound(String resourceName) {
		this.resourceName = resourceName;
	}
	
	@Override
	public InputStream inputStream() throws FileNotFoundException {
		InputStream stream = getClass().getResourceAsStream(resourceName);
		if(stream == null) {
			throw new FileNotFoundException("Resource file not found: "+resourceName);
		}
		return stream;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+": "+resourceName;
	}
	
	@Override
	public boolean disabled() {
		return false;
	}

}
