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

package org.tomighty.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Properties;

import org.tomighty.ioc.Initializable;
import org.tomighty.ioc.Inject;
import org.tomighty.log.Log;

public class Messages implements Initializable {
	
	@Inject private Log log;
	private Properties messages = new Properties();
	
	@Override
	public void initialize() {
		Locale locale = Locale.getDefault();
		String localeId = locale.getLanguage()+"_"+locale.getCountry();
		log.info("Loading messages for locale "+localeId);
		String resourceName = "/messages_"+localeId+".properties";
		InputStream input = getClass().getResourceAsStream(resourceName);
		if(input == null) {
			log.info("Messages file not found: "+resourceName);
		} else {
			Charset charset = Charset.forName("utf-8");
			InputStreamReader reader = new InputStreamReader(input, charset);
			try {
				try {
					messages.load(reader);
				} finally {
					reader.close();
				}
			} catch (IOException e) {
				log.error("Error while loading resource: "+resourceName, e);
			}
		}
	}

	public String get(String name) {
		String key = name.replace(' ', '.');
		if(messages.containsKey(key)) {
			return messages.getProperty(key);
		}
		return name;
	}

}
