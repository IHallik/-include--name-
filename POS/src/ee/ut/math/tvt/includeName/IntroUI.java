package ee.ut.math.tvt.includeName;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class IntroUI {
	static JFrame UIFrame;
	
	/*
		Team name 
		Team leader 
		Team leader email 
		Team members 
		Team logo (random image) 
		Your software version number
	 */
	
	public static void display() {
		UIFrame = new JFrame();
		UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIFrame.setSize(400,600);
		UIFrame.setLayout(new GridLayout(10,1));
		
		//Read params from application.properties
		
		//Count number of members
		int memCount = 4;	//I just rolled a dice for a random number
		
		UIFrame.add(new JLabel("Team name: <includeteamnamefromproperties>"));
		UIFrame.add(new JLabel("Team leader: <leader>"));
		UIFrame.add(new JLabel("Team leader email: <leader_email>"));
		UIFrame.add(new JLabel("Members:"));
		for(int i=0; i<memCount; i++) {
			UIFrame.add(new JLabel("\t<includeteammemberfromproperties>"));
		}
		UIFrame.add(new JLabel("Version: <version>"));
		
		UIFrame.setVisible(true);
		UIFrame.repaint();
	}
}
