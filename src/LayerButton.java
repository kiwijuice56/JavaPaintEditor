import javax.swing.*;

/**
 * JButton that can set the currentLayer of a PaintPanel
 */
public class LayerButton extends JButton {

	private int layer;

	/**
	 * Initializes a LayerButton
	 * @param initialLayer The layer this button is set to upon creation
	 * @param paintPanel Reference to main PaintPanel in order to set currentLayer
	 * @param thumbnailPanel Reference to main ThumbnailPanel in order to set original
	 * @param currentLayer Reference to main JLabel used for currentLayer in order to set text
	 */
	public LayerButton(int initialLayer, PaintPanel paintPanel, ThumbnailPanel thumbnailPanel, JLabel currentLayer) {
		this.layer = initialLayer;
		setText("Layer %d".formatted(layer));
		addActionListener(e -> {
			paintPanel.setCurrentLayer(layer);
			currentLayer.setText("Layer: " + layer);
			thumbnailPanel.setOriginal(paintPanel.getCurrentLayerImage());
			thumbnailPanel.repaint();
		});
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
		setText("Layer %d".formatted(layer));
	}
}
