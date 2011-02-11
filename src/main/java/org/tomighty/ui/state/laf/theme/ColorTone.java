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

package org.tomighty.ui.state.laf.theme;

import java.awt.Color;

import org.tomighty.ui.state.laf.theme.colors.Black;
import org.tomighty.ui.state.laf.theme.colors.Blue;
import org.tomighty.ui.state.laf.theme.colors.Green;
import org.tomighty.ui.state.laf.theme.colors.Red;

public interface ColorTone {

	ColorTone BLACK = new Black();
	ColorTone RED = new Red();
	ColorTone GREEN = new Green();
	ColorTone BLUE = new Blue();

	Color light();

	Color dark();

	Color lightBorder();

	Color shadowBorder();

	Color text();

}
