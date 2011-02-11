package hel.achievements;

import net.sf.jachievement.Achievement;
import hel.bus.PomodoroTracking;

public class FinishedSessionAchievement implements PomodoroAchievement {
	private  int step=1;
    private int exp=0;
    private int needed;
	@Override
	public boolean satisfied(PomodoroTracking model) {
		step = 1;
		needed = 1;
		int done = model.getFinishedPomodorosThisSession();
		while(needed < done){
			needed+=needed;
		}
		
		if(done > 16)
			return false;
		
		if(done >= needed){
			exp= step+1;
			model.addExp(exp);
			return true;
		}
		
		
		return false;
	}

	@Override
	public Achievement achievement() {
		Achievement ach=new Achievement(exp+"Exp -"+needed+" Pomodoros finished(Session)");
		return ach;
	}
	
	
	
}
