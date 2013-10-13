package ee.ut.math.tvt.includeName;

import org.apache.log4j.Logger;

public class Intro {
	private static final Logger log = Logger.getLogger(Intro.class);
	
	public static void main(String[] args){
		log.info("Intro window opened");
		IntroUI ui = new IntroUI();
		ui.display();
	}
}
