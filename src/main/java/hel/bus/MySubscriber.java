package hel.bus;

import hel.achievements.FinishedCompleteAchievement;
import hel.achievements.FinishedSessionAchievement;
import hel.achievements.InterruptSessionAchievement;
import hel.achievements.LevelAchievement;
import hel.achievements.PomodoroAchievement;
import hel.achievements.SecondsCompleteAchievement;

import java.util.Arrays;
import java.util.List;

import net.sf.jachievement.AchievementQueue;

import org.tomighty.bus.Bus;
import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.ChangeUiState;
import org.tomighty.bus.messages.TimerEnd;
import org.tomighty.bus.messages.TimerTick;
import org.tomighty.bus.messages.TrayClick;
import org.tomighty.time.Time;

public class MySubscriber {
	private AchievementQueue queue = new AchievementQueue();
    public MySubscriber() {
    	model=XML.unmarshall();
    }

    private List<PomodoroAchievement> achievements = Arrays.asList(
    		new FinishedSessionAchievement(),
    		new InterruptSessionAchievement(),
    		new LevelAchievement(),
    		new FinishedCompleteAchievement());
//    		new SecondsCompleteAchievement());
    		

	private PomodoroTracking model = null;
    
	
	private class MyTimeTickSubscriber implements Subscriber<TimerTick>{
		@Override
		public void receive(TimerTick message) {
			Time time = message.time();
			
			model.incSeconds();
			
		}
	}
	
	private class MyTimeEndSubscriber implements Subscriber<TimerEnd>{
		@Override
		public void receive(TimerEnd message) {
			model.incFinishedPomodoros();
			updateModel();
		}
	}
	
	
	public void subscribe(Bus bus){
		bus.subscribe(new MyTimeTickSubscriber(), TimerTick.class);
		bus.subscribe(new MyTraySubscriber(), TrayClick.class);
		bus.subscribe(new MyUISubscriber(), ChangeUiState.class);
		bus.subscribe(new MyTimeEndSubscriber(), TimerEnd.class);
	}
	
	private class MyTraySubscriber implements Subscriber<TrayClick>{

		@Override
		public void receive(TrayClick message) {
			
			
		}
	}
	

	private class MyUISubscriber implements Subscriber<ChangeUiState>{
		@Override
		public void receive(ChangeUiState message) {
			Class clazz =message.getStateClass();
			if(clazz.equals(org.tomighty.ui.state.pomodoro.PomodoroInterrupted.class)){
				model.incInterrupts();
				updateModel();
			}
			if(clazz.equals(org.tomighty.ui.state.pomodoro.PomodoroFinished.class)){
				model.incInterrupts();
				updateModel();
			}
			if(clazz.equals(org.tomighty.ui.state.breaks.LongBreak.class)){
				model.incLongBreaks();
				updateModel();
			}
			if(clazz.equals(org.tomighty.ui.state.breaks.ShortBreak.class)){
				model.incSmallBreaks();
				updateModel();
			}
		}
	}
	
   private void updateModel(){
	   XML.marshall(model);
	   validateAchievements();
   }
	
   
   private void validateAchievements(){
	   for (PomodoroAchievement achi : achievements) {
		   if(achi.satisfied(model)){
			  queue.add(achi.achievement());   
		   }
	   }
   }
}
