package hel.achievements;

import net.sf.jachievement.Achievement;
import hel.bus.PomodoroTracking;

public interface PomodoroAchievement {

	public boolean satisfied(PomodoroTracking model);
	public Achievement achievement();
}
