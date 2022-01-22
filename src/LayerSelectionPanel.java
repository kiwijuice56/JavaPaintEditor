import javax.swing.*;
import java.awt.*;

/**
 * JPanel with buttons that can control a PaintPanel's layer
 */
public class LayerSelectionPanel extends JPanel {

	/**
	 * Initializes a LayerSelectionPanel
	 * @param paintPanel Reference to main PaintPanel in order to set currentColor
	 */
	public LayerSelectionPanel(PaintPanel paintPanel) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		ThumbnailPanel thumbnail = new ThumbnailPanel(266, 200);
		thumbnail.setMinimumSize(new Dimension(200, 500));
		thumbnail.setOriginal(paintPanel.getCurrentLayer());

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(e -> paintPanel.clearLayer());

		JLabel currentLayer = new JLabel("Layer: 0");

		JPanel topRowPanel = new JPanel(); topRowPanel.add(currentLayer); topRowPanel.add(clearButton);
		topRowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

		JPanel opacityPanel = new JPanel();
		JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		opacityPanel.add(new JLabel("Opacity"));
		opacityPanel.add(opacitySlider);
		opacityPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

		add(thumbnail);
		add(topRowPanel);
		add(opacityPanel);

		JPanel layerButtons = new JPanel();
		layerButtons.setLayout(new GridLayout(PaintPanel.MAX_LAYERS, 1));
		for (int i = PaintPanel.MAX_LAYERS-1; i >= 0; i--) {
			JButton layerButton = new JButton("Layer %d".formatted(i));
			final int layer = i; // integers used in lambdas must be final
			layerButton.addActionListener(e -> {
				paintPanel.setCurrentLayer(layer);
				currentLayer.setText("Layer: " + layer);
				thumbnail.setOriginal(paintPanel.getCurrentLayer());
				thumbnail.repaint();
			});
			layerButtons.add(layerButton);
		}
		add(layerButtons);
	}
}
