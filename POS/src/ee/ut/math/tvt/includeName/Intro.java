package ee.ut.math.tvt.includeName;
import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;

public class Intro {
	private static final String MODE = "console";

	public static void main(String[] args) {
		// Read log4j config from etc folder
		System.setProperty("log4j.configuration",
				Intro.class.getResource("/etc/log4j.xml").toString());
		Logger log = Logger.getLogger(Intro.class);

		final SalesDomainController domainController = new SalesDomainControllerImpl();

		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {

			IntroUI introUI = new IntroUI();
			introUI.setVisible(true);
			introUI.setAlwaysOnTop(true);

			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);

			introUI.setAlwaysOnTop(false);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			introUI.setVisible(false);
		}
	}
}
