package ee.ut.math.tvt.includeName;

import org.apache.log4j.Logger;

public class Intro {	
	public static void main(String[] args){
		//Read log4j config from etc folder
		System.setProperty("log4j.configuration", Intro.class.getResource("/etc/log4j.xml").toString());
		Logger log = Logger.getLogger(Intro.class);
		
		log.info("Intro window opened");
		IntroUI ui = new IntroUI();
		ui.display();
	}
}
