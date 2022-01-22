import javax.swing.*;
import java.awt.*;

/**
 * JPanel with controls to change the PaintPanel's brush size
 */
public class BrushSelectionPanel extends JPanel{

	/**
	 * Initializes a BrushSelectionPanel
	 * @param paintPanel Reference to main PaintPanel in order to set brushSize
	 */
	public BrushSelectionPanel(PaintPanel paintPanel){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JRadioButton precisionButton = new JRadioButton("Precision");
		JRadioButton pencilButton = new JRadioButton("Pencil");
		JRadioButton markerButton = new JRadioButton("Marker");
		ButtonGroup brushTypeGroup = new ButtonGroup();
		brushTypeGroup.add(precisionButton);
		brushTypeGroup.add(pencilButton); brushTypeGroup.add(markerButton);

		JPanel sizeSliderContainer = new JPanel();
		sizeSliderContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

		JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 64, 8);

		sizeSliderContainer.add(new JLabel("Size"));
		sizeSliderContainer.add(sizeSlider);

		// Radio buttons are deselected when the slider is changed, indicating a custom size to the user
		sizeSlider.addChangeListener( e -> {
			int newSize = sizeSlider.getValue();
			paintPanel.setBrushSize(newSize);
			if (newSize != PaintPanel.PRECISION_SIZE && newSize != PaintPanel.PENCIL_SIZE && newSize != PaintPanel.MARKER_SIZE)
				brushTypeGroup.clearSelection();
		});

		precisionButton.addActionListener(e -> sizeSlider.setValue(PaintPanel.PRECISION_SIZE));
		pencilButton.addActionListener(e -> sizeSlider.setValue(PaintPanel.PENCIL_SIZE) );
		markerButton.addActionListener(e -> sizeSlider.setValue(PaintPanel.MARKER_SIZE) );
		pencilButton.setSelected(true);

		add(precisionButton); add(pencilButton); add(markerButton);
		add(sizeSliderContainer);
	}
}
