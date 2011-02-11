package hel.bus;


public class PomodoroTracking {
	
	private int finishedPomodorosThisSession = 0;
	private int finishedPomodorosComplete= 0;
	
	private long secondsComplete = 0;
	private long secondsSession = 0;
	
	private int interruptsSession = 0;
	private int interruptComplete = 0;
	
	private int smallBreaksSession = 0;
	private int smallBreaksComplete = 0;
	
	private int longBreaksSession = 0;
	private int longBreaksComplete = 0;
	
	private int pomodorosStartedSession = 0;
	private int pomodorosStartedComplete = 0;
	
	private int pomodorosRestartedSession = 0;
	private int pomodorosRestartedComplete = 0;
	
	private int experience =0;
	
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public void addExp(int exp){
		this.experience+=exp;
	}

	private int level = 0;
	

	
	
	public void incFinishedPomodoros(){
		finishedPomodorosComplete++;
		finishedPomodorosThisSession++;
	}
	
	public void incSeconds(){
		secondsComplete++;
		secondsSession++;
	}
	
	public void incInterrupts(){
		interruptComplete++;
		interruptsSession++;
	}
	
	public void incSmallBreaks(){
		smallBreaksComplete++;
		smallBreaksSession++;
	}
	
	public void incLongBreaks(){
		longBreaksComplete++;
		longBreaksSession++;
	}
	
	
	public void incStarts(){
		pomodorosStartedComplete++;
		pomodorosStartedSession++;
	}
	
	public void incRestarts(){
		pomodorosRestartedComplete++;
		pomodorosRestartedSession++;
	}
	

	
	public void resetSession(){
		finishedPomodorosThisSession=0;
		secondsSession=0;
		interruptsSession=0;
		smallBreaksSession=0;
		longBreaksSession=0;
		pomodorosStartedSession=0;
		pomodorosRestartedSession=0;
	}
	public int getFinishedPomodorosThisSession() {
		return finishedPomodorosThisSession;
	}

	public void setFinishedPomodorosThisSession(int finishedPomodorosThisSession) {
		this.finishedPomodorosThisSession = finishedPomodorosThisSession;
	}

	public int getFinishedPomodorosComplete() {
		return finishedPomodorosComplete;
	}

	public void setFinishedPomodorosComplete(int finishedPomodorosComplete) {
		this.finishedPomodorosComplete = finishedPomodorosComplete;
	}

	public long getSecondsComplete() {
		return secondsComplete;
	}

	public void setSecondsComplete(long secondsComplete) {
		this.secondsComplete = secondsComplete;
	}

	public long getSecondsSession() {
		return secondsSession;
	}

	public void setSecondsSession(long secondsSession) {
		this.secondsSession = secondsSession;
	}

	public int getInterruptsSession() {
		return interruptsSession;
	}

	public void setInterruptsSession(int interruptsSession) {
		this.interruptsSession = interruptsSession;
	}

	public int getInterruptComplete() {
		return interruptComplete;
	}

	public void setInterruptComplete(int interruptComplete) {
		this.interruptComplete = interruptComplete;
	}

	public int getSmallBreaksSession() {
		return smallBreaksSession;
	}

	public void setSmallBreaksSession(int smallBreaksSession) {
		this.smallBreaksSession = smallBreaksSession;
	}

	public int getSmallBreaksComplete() {
		return smallBreaksComplete;
	}

	public void setSmallBreaksComplete(int smallBreaksComplete) {
		this.smallBreaksComplete = smallBreaksComplete;
	}

	public int getLongBreaksSession() {
		return longBreaksSession;
	}

	public void setLongBreaksSession(int longBreaksSession) {
		this.longBreaksSession = longBreaksSession;
	}

	public int getLongBreaksComplete() {
		return longBreaksComplete;
	}

	public void setLongBreaksComplete(int longBreaksComplete) {
		this.longBreaksComplete = longBreaksComplete;
	}

	public int getPomodorosStartedSession() {
		return pomodorosStartedSession;
	}

	public void setPomodorosStartedSession(int pomodorosStartedSession) {
		this.pomodorosStartedSession = pomodorosStartedSession;
	}

	public int getPomodorosStartedComplete() {
		return pomodorosStartedComplete;
	}

	public void setPomodorosStartedComplete(int pomodorosStartedComplete) {
		this.pomodorosStartedComplete = pomodorosStartedComplete;
	}

	public int getPomodorosRestartedSession() {
		return pomodorosRestartedSession;
	}

	public void setPomodorosRestartedSession(int pomodorosRestartedSession) {
		this.pomodorosRestartedSession = pomodorosRestartedSession;
	}

	public int getPomodorosRestartedComplete() {
		return pomodorosRestartedComplete;
	}

	public void setPomodorosRestartedComplete(int pomodorosRestartedComplete) {
		this.pomodorosRestartedComplete = pomodorosRestartedComplete;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	

	
	
}
