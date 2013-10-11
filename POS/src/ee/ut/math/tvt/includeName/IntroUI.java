package ee.ut.math.tvt.includeName;

import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class IntroUI {
	static JFrame UIFrame;
	
	public void display() {
		Properties appProp = new Properties();
		Properties verProp = new Properties();
		
		try {
			appProp.load(new FileInputStream("application.properties"));
			verProp.load(new FileInputStream("version.properties"));
			
			UIFrame = new JFrame();
			UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			UIFrame.setSize(400, 600);
			UIFrame.setLayout(new GridLayout(10, 1));

			// Read params from application.properties

			// Count number of members
			int memCount = 4; // I just rolled a dice for a random number

			UIFrame.add(new JLabel("Team name: "+appProp.getProperty("team.name")));
			UIFrame.add(new JLabel("Team leader: "+appProp.getProperty("team.leader")));
			UIFrame.add(new JLabel("Team leader email: "+appProp.getProperty("team.leader.email")));
			UIFrame.add(new JLabel("Members:"));
			for (int i = 0; i < memCount; i++) {
				UIFrame.add(new JLabel("\t<includeteammemberfromproperties>"));
			}
			UIFrame.add(new JLabel("Version: "+verProp.getProperty("build.number")));

			UIFrame.setVisible(true);
			UIFrame.repaint();
	    } catch(Exception e) {
	    	System.err.println("Failed to read properties file.");
	    	e.printStackTrace();
	    }
	}
}
