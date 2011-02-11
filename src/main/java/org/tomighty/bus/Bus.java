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

package org.tomighty.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tomighty.ioc.Inject;
import org.tomighty.log.Log;

public class Bus {
	
	@Inject private Log log;
	private Map<Class<?>, List<Subscriber<?>>> subscribersByType;
	
	public Bus() {
		subscribersByType = Collections.synchronizedMap(new HashMap<Class<?>, List<Subscriber<?>>>());
	}

	public <T> void subscribe(Subscriber<T> subscriber, Class<T> messageType) {
		List<Subscriber<?>> list = subscribersByType.get(messageType);
		if(list == null) {
			list = new ArrayList<Subscriber<?>>();
			subscribersByType.put(messageType, list);
		}
		synchronized (list) {
			list.add(subscriber);
		}
	}
	
	public <T> void unsubscribe(Subscriber<T> subscriber, Class<T> messageType) {
		List<Subscriber<?>> list = subscribersByType.get(messageType);
		if(list != null) {
			synchronized (list) {
				list.remove(subscriber);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void publish(Object message) {
		Class<? extends Object> messageType = message.getClass();
		List<Subscriber<?>> list = safeListOfSubscribers(messageType);
		if(list == null) {
			return;
		}
		for(Subscriber subscriber : list) {
			try {
				subscriber.receive(message);
			} catch(Throwable error) {
				log.error("Error delivering message to subscriber: "+subscriber, error);
			}
		}
	}

	private <T> List<Subscriber<?>> safeListOfSubscribers(Class<T> messageType) {
		List<Subscriber<?>> list = subscribersByType.get(messageType);
		if(list != null) {
			synchronized (list) {
				return new ArrayList<Subscriber<?>>(list);
			}
		}
		return null;
	}

}
