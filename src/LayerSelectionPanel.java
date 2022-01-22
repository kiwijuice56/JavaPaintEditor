import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * JPanel with buttons that can control a PaintPanel's layer
 */
public class LayerSelectionPanel extends JPanel {

	private ArrayList<LayerButton> layerButtons;

	/**
	 * Initializes a LayerSelectionPanel
	 * @param paintPanel Reference to main PaintPanel in order to set currentColor
	 */
	public LayerSelectionPanel(PaintPanel paintPanel) {
		layerButtons = new ArrayList<>();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		ThumbnailPanel thumbnail = new ThumbnailPanel(266, 200);
		thumbnail.setMinimumSize(new Dimension(200, 500));
		thumbnail.setOriginal(paintPanel.getCurrentLayerImage());

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(e -> paintPanel.clearLayer());

		JButton addButton = new JButton("Add");
		JButton delButton = new JButton("Delete");

		JLabel currentLayer = new JLabel("Layer: 0");

		JPanel topRowPanel = new JPanel();
		topRowPanel.add(currentLayer);
		topRowPanel.add(clearButton);
		topRowPanel.add(addButton);
		topRowPanel.add(delButton);

		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new GridLayout(PaintPanel.MAX_LAYERS, 1));

		LayerButton firstLayerButton = new LayerButton(0, paintPanel, thumbnail, currentLayer);
		buttonContainer.add(firstLayerButton);
		layerButtons.add(firstLayerButton);

		addButton.addActionListener(e -> {
			int index = paintPanel.addLayer();
			if (index != -1) {
				LayerButton button = new LayerButton(index, paintPanel, thumbnail, currentLayer);
				buttonContainer.add(button, 0);
				buttonContainer.repaint();

				currentLayer.setText("Layer: " + index);
				thumbnail.setOriginal(paintPanel.getCurrentLayerImage());
				thumbnail.repaint();
				revalidate();
				layerButtons.add(button);
			}
		});
		delButton.addActionListener(e -> {
			int index = paintPanel.deleteLayer();
			if (index != -1) {
				buttonContainer.remove(layerButtons.get(index));
				buttonContainer.repaint();

				currentLayer.setText("Layer: " + paintPanel.getCurrentLayer());
				thumbnail.setOriginal(paintPanel.getCurrentLayerImage());
				thumbnail.repaint();
				revalidate();

				layerButtons.remove(index);
				for (int i = 0; i < layerButtons.size(); i++) {
					layerButtons.get(i).setLayer(i);
				}
			}
		});

		topRowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

		JPanel opacityPanel = new JPanel();
		JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		opacityPanel.add(new JLabel("Opacity"));
		opacityPanel.add(opacitySlider);
		opacityPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

		add(thumbnail);
		add(topRowPanel);
		add(buttonContainer);
	}
}
