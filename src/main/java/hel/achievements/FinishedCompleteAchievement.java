package hel.achievements;

import net.sf.jachievement.Achievement;
import hel.bus.PomodoroTracking;

public class FinishedCompleteAchievement implements PomodoroAchievement {
	private int exp=0;
	private long complete;
    
	@Override
	public boolean satisfied(PomodoroTracking model) {
		int level = model.getLevel();
		complete = model.getFinishedPomodorosComplete();
		if(complete%level==0){
			exp = level*2;
			model.addExp(exp);
			return true;
		}
		
		
		return false;
	}

	@Override
	public Achievement achievement() {
		Achievement ach=new Achievement(exp+"Exp -"+complete+ " pomodoros finished(Complete)");
		return ach;
	}
	
	
	
}
