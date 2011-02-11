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

package org.tomighty.ioc;

import java.lang.reflect.Constructor;

public class Factory {
	
	private Injector injector;
	private Container container;
	
	void container(Container container) {
		this.container = container;
		this.injector = container.get(Injector.class);
	}
	
	public <T> T create(Class<T> clazz) {
		return create(clazz, null);
	}
	
	public <T> T create(Class<T> clazz, Object needer) {
		T instance;
		Provider<T> provider = providerFor(clazz);
		if(provider == null) {
			instance = newInstanceOf(clazz);
		} else {
			instance = provider.createFor(needer);
		}
		injector.inject(instance);
		if(instance instanceof Initializable) {
			((Initializable)instance).initialize();
		}
		return instance;

	}
	
	@SuppressWarnings("unchecked")
	private <T>	Provider<T> providerFor(Class<T> clazz) {
		String providerClassName = clazz.getName()+"Provider";
		try {
			Class<Provider<T>> providerClass = (Class<Provider<T>>) Class.forName(providerClassName);
			return create(providerClass);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	private <T> T newInstanceOf(Class<T> clazz) {
		try {
			Constructor<T> injectableConstructor = findInjectableConstructor(clazz);
			if(injectableConstructor == null) {
				return clazz.newInstance();
			}
			Object[] parameters = parametersFor(injectableConstructor);
			return injectableConstructor.newInstance(parameters);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create an instance of "+clazz, e);
		}
	}

	private <T> Object[] parametersFor(Constructor<T> constructor) {
		Class<?>[] types = constructor.getParameterTypes();
		Object[] parameters = new Object[types.length];
		for(int index = 0; index < types.length; index++) {
			Class<?> type = types[index];
			Object param = container.get(type);
			parameters[index] = param;
		}
		return parameters;
	}
	
	@SuppressWarnings("unchecked")
	private <T> Constructor<T> findInjectableConstructor(Class<T> clazz) {
		Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
		for(Constructor<T> constructor : constructors) {
			if(constructor.isAnnotationPresent(Inject.class)) {
				return constructor;
			}
		}
		return null;
	}

}
