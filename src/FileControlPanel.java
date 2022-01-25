import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * JPanel with buttons to save and load images
 */

public class FileControlPanel extends JPanel {

	/**
	 * Initializes a FileControlPanel
	 * @param paintPanel Reference to main PaintPanel in order to save current image
	 */
	public FileControlPanel(PaintPanel paintPanel){
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBackground(Color.WHITE);

		/* * * * * * * BUTTON STYLING * * * * * * */
		JButton saveAsButton = new JButton("Save as");
		saveAsButton.setMargin(new Insets(0,8,0,8));
		saveAsButton.setBackground(Color.WHITE);
		saveAsButton.setBorderPainted(false); saveAsButton.setFocusPainted(false);

		JButton sourceButton = new JButton("Source code");
		sourceButton.setMargin(new Insets(0,8,0,8));
		sourceButton.setBackground(Color.WHITE);
		sourceButton.setBorderPainted(false); saveAsButton.setFocusPainted(false);

		/* * * * * * * BUTTON FUNCTION * * * * * * */
		sourceButton.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(new URL("https://github.com/kiwijuice56/java-paint-editor").toURI());
			} catch (Exception f) {
				f.printStackTrace();
			}
		});

		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("untitled.png"));
		saveAsButton.addActionListener(e -> {
			int result = fc.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION){
				int drawBackground = JOptionPane.showConfirmDialog(null, "Save with transparent background?", "Saving image", JOptionPane.YES_NO_OPTION);
				File file = new File(fc.getSelectedFile().getAbsolutePath());
				try {
					ImageIO.write(paintPanel.getMerged(drawBackground == JOptionPane.NO_OPTION), "png", file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		add(saveAsButton);
		add(sourceButton);
	}
}
