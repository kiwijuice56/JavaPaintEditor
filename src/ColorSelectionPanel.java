import javax.swing.*;
import java.awt.*;

/**
 * JPanel with JSliders that can control a PaintPanel's current color
 */
public class ColorSelectionPanel extends JPanel {
	private JPanel colorHint;
	private PaintPanel paintPanel;
	private JSlider r, g, b, a;

	public static final int NO_CHANGE = -1;

	/**
	 * Initializes a BrushSelectionPanel
	 * @param paintPanel Reference to main PaintPanel in order to set currentColor
	 */
	public ColorSelectionPanel(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200,200,200)));

		colorHint = new JPanel();
		colorHint.setMaximumSize(new Dimension(150, 150));
		colorHint.setBackground(Color.RED);

		add(colorHint);

		PalettePanel palette = new PalettePanel(paintPanel, this);
		JButton addButton = new JButton("Save Color");
		JButton delButton = new JButton("Delete Color");
		addButton.addActionListener(e -> palette.addColor(colorHint.getBackground()));
		delButton.addActionListener(e -> palette.deleteColor(colorHint.getBackground()));
		JPanel paletteControl = new JPanel();
		paletteControl.add(addButton); paletteControl.add(delButton);

		add(palette);
		add(paletteControl);

		paletteControl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

	 	r = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	 	g = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	 	b = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	 	a = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		r.addChangeListener(e -> updateColor(r.getValue(), -1, -1, -1));
		g.addChangeListener(e -> updateColor(-1, g.getValue(), -1, -1));
		b.addChangeListener(e -> updateColor(-1, -1, b.getValue(), -1));
		a.addChangeListener(e -> updateColor(-1, -1, -1, a.getValue()));

		String[] labels = {"r", "g", "b", "a"}; JSlider[] sliders = {r, g, b, a};
		for (int i = 0; i < 4; i++){
			JPanel sliderContainer = new JPanel();
			sliderContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
			sliderContainer.add(new JLabel(labels[i]));
			sliderContainer.add(sliders[i]);
			add(sliderContainer);
		}
	}

	/**
	 * Updates the colorHint JPanel and the PaintPanel's currentColor
	 * @param rVal Red value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the red value unchanged
	 * @param gVal Green value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the green value unchanged
	 * @param bVal Blue value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the blue value unchanged
	 * @param aVal Alpha value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the alpha value unchanged
	 */
	public void updateColor(int rVal, int gVal, int bVal, int aVal){
		Color c = paintPanel.getCurrentColor();
		paintPanel.setCurrentColor(new Color(
				rVal >= 0 ? rVal : c.getRed(),
				gVal >= 0 ? gVal : c.getGreen(),
				bVal >= 0 ? bVal : c.getBlue(),
				aVal >= 0 ? aVal : c.getAlpha()));
		colorHint.setBackground(paintPanel.getCurrentColor());
		if (rVal >= 0) r.setValue(rVal);
		if (gVal >= 0) g.setValue(gVal);
		if (bVal >= 0) b.setValue(bVal);
		if (aVal >= 0) a.setValue(aVal);

		repaint();
	}

	public void updateColor(Color color) {
		updateColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
}
