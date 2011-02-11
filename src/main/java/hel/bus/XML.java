package hel.bus;

import java.io.File;

import javax.xml.bind.JAXB;

public class XML {
	private static String path=System.getProperty("user.dir");
	private static File file = new File(path+"/pomodoro.xml");
		
	
	public static void marshall(PomodoroTracking model){
		 JAXB.marshal(model, file);
//		 JAXB.marshal(model, System.out);
	}
	
	public static PomodoroTracking unmarshall(){
		PomodoroTracking model;
		try{
			 model = JAXB.unmarshal(file, PomodoroTracking.class);
		} catch(Exception e){
			model = new PomodoroTracking();
		}
		model.resetSession();
		return model;
	}
	
}
