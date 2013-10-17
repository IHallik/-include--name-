package ee.ut.math.tvt.includeName;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

public class IntroUI extends JFrame {
	private static final Logger log = Logger.getLogger(IntroUI.class);	//For every "Catch" block, add a call to this
	//TODO Discuss what exactly do we want to catch with this logger.
	
	public IntroUI() {		
		setUndecorated(true);	//Remove borders
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int width = 420;
		int height = 200;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);
		setSize(width, height);
		
		Properties appProp = new Properties();
		Properties verProp = new Properties();
		
		try {
			appProp.load(getClass().getResourceAsStream("/application.properties"));
			verProp.load(getClass().getResourceAsStream("/version.properties"));
		} catch(Exception e) {
			log.error("Failed to load properties files!");
			log.error(e.getMessage());
		}

		URL logoURL = getClass().getResource("/etc/"+appProp.getProperty("team.logo.file"));
		String members[] = appProp.getProperty("team.members").split(",");
		
		//TODO: Replace hardcoded html with template reading from file
		String layout =
	"<html>"
+		"<table>"
+			"<tr>"
+				"<td>Team name</td>"
+				"<td>"+appProp.getProperty("team.name")+"</td>"
+				"<td rowspan=5><img height=128 width=128 src='"+logoURL+"'/></td>"
+			"</tr>"
+			"<tr>"
+				"<td>Team leader</td>"
+				"<td>"+appProp.getProperty("team.leader")+"</td>"
+			"</tr>"
+			"<tr>"
+				"<td>Team leader email</td>"
+				"<td>"+appProp.getProperty("team.leader.email")+"</td>"
+			"</tr>"
+			"<tr>"
+				"<td>Members</td>"
+				"<td>"+members[0]+"<br>"+members[1]+"<br>"+members[2]+"<br>"+members[3]+"</td>"
+			"</tr>"
+			"<tr>"
+				"<td>Version</td>"
+				"<td>"+verProp.getProperty("build.number")+"</td>"
+			"</tr>"
+		"</table>"
+	"</html>";
		
		add(new JLabel(layout));

		repaint();
	}
}
