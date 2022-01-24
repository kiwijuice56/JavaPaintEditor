import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PalettePanel extends JPanel {
	private PaintPanel paintPanel;
	private ColorSelectionPanel colorSelect;
	private HashMap<Color, JButton> paletteButtonMap;

	public final static int MAX_COLORS = 10;

	public PalettePanel(PaintPanel paintPanel, ColorSelectionPanel colorSelect) {
		this.paintPanel = paintPanel;
		this.colorSelect = colorSelect;
		paletteButtonMap = new HashMap<>();
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
	}

	public void addColor(Color color) {
		if (paletteButtonMap.containsKey(color) || paletteButtonMap.size() >= MAX_COLORS)
			return;
		JButton paletteButton = new JButton();
		paletteButton.addActionListener(e -> {
			colorSelect.updateColor(color);
		});

		paletteButton.setPreferredSize(new Dimension(16, 16));
		paletteButton.setBackground(color);
		paletteButton.setMargin(new Insets(0,0,0,0));
		paletteButton.setFocusPainted(false);
		paletteButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200,200,200)));

		add(paletteButton);
		revalidate();
		paletteButtonMap.put(color, paletteButton);
	}

	public void deleteColor(Color color) {
		if (!paletteButtonMap.containsKey(color))
			return;
		remove(paletteButtonMap.get(color));
		paletteButtonMap.remove(color);
		revalidate();
		repaint();
	}
}
