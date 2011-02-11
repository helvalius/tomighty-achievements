package hel.achievements;

import net.sf.jachievement.Achievement;
import hel.bus.PomodoroTracking;

public class SecondsCompleteAchievement implements PomodoroAchievement {
	private  int step=1;
    private int exp=0;
    private int needed;
	@Override
	public boolean satisfied(PomodoroTracking model) {
		step = 1;
		needed = 1;
		int done = model.getInterruptsSession();
		while(needed < done){
			needed+=step;
			step++;
		}
		
		if(done > 10)
			return false;
		
		if(done >= needed){
			exp= step;
			model.addExp(exp);
			return true;
		}
		
		
		return false;
	}

	@Override
	public Achievement achievement() {
		Achievement ach=new Achievement(exp+"Exp -"+needed+" Interrupts this session");
		return ach;
	}
	
	
	
}
