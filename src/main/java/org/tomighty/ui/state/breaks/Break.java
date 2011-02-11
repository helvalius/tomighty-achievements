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

package org.tomighty.ui.state.breaks;

import org.tomighty.ui.UiState;
import org.tomighty.ui.state.TimerSupport;

public abstract class Break extends TimerSupport {

	@Override
	protected Class<? extends UiState> finishedState() {
		return BreakFinished.class;
	}
	
	@Override
	protected Class<? extends UiState> interruptedState() {
		return BreakInterrupted.class;
	}
	
}
