import javax.swing.*;
import java.awt.*;

/**
 * JPanel with JSliders that can control a PaintPanel's current color
 */
public class ColorSelectionPanel extends JPanel {
	private JPanel colorHint;
	private PaintPanel paintPanel;

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

		JSlider r = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		JSlider g = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		JSlider b = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		JSlider a = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
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
	 * @param r Red value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the red value unchanged
	 * @param g Green value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the green value unchanged
	 * @param b Blue value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the blue value unchanged
	 * @param a Alpha value for new color; pass ColorSelectionPanel.NO_CHANGE to keep the alpha value unchanged
	 */
	private void updateColor(int r, int g, int b, int a){
		Color c = paintPanel.getCurrentColor();
		paintPanel.setCurrentColor(new Color(
				r >= 0 ? r : c.getRed(),
				g >= 0 ? g : c.getGreen(),
				b >= 0 ? b : c.getBlue(),
				a >= 0 ? a : c.getAlpha()));
		colorHint.setBackground(paintPanel.getCurrentColor());
		repaint();
	}
}
