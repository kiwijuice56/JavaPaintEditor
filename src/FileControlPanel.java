import javax.swing.*;
import java.awt.*;

/**
 * JPanel with buttons to save and load images
 */

public class FileControlPanel extends JPanel {

	/**
	 * Initializes a FileControlPanel
	 */
	public FileControlPanel(){
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBackground(Color.WHITE);

		JButton saveAsButton = new JButton("Save as");
		saveAsButton.setMargin(new Insets(0,0,0,0));
		saveAsButton.setBackground(Color.WHITE);
		saveAsButton.setBorderPainted(false); saveAsButton.setFocusPainted(false);

		add(saveAsButton);
	}
}
