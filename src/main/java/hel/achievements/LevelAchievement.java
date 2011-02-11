package hel.achievements;

import net.sf.jachievement.Achievement;
import hel.bus.PomodoroTracking;

public class LevelAchievement implements PomodoroAchievement {
	private final int step =5;
    private PomodoroTracking model;
	@Override
	public boolean satisfied(PomodoroTracking model) {
		this.model=model;
		int level=model.getLevel();
		int experience=model.getExperience();
		int needed=step;
		int complete=0;
		for(int i=0; i< level; i++){
			needed+=step;
			complete += needed;
		}
		if(experience>= complete){
			model.setLevel(model.getLevel()+1);
			return true;
		}
		return false;
	}

	@Override
	public Achievement achievement() {
		Achievement ach=new Achievement("You reached lvl "+model.getLevel()+".");
		return ach;
	}
	
	
	
}
