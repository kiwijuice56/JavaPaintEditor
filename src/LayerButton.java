import javax.swing.*;

public class LayerButton extends JButton {

	private int layer;

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
