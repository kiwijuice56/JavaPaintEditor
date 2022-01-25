import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.image.BufferedImage;

/**
 * JPanel that draws the currentLayer of a PaintPanel at a smaller size every time the mouse is released
 */
public class ThumbnailPanel extends JPanel implements AWTEventListener {
    BufferedImage original;

    /**
     * Initialize ThumbnailPanel
     * @param width The width to copy the PaintPanel to
     * @param height The height to copy the PaintPanel to
     */
    public ThumbnailPanel(int width, int height) {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK);
        setMaximumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200,200,200)));
    }

    /**
     * Draws scaled image of PaintPanel's currentLayer
     * @param g
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        Image scaled = original.getScaledInstance(original.getWidth()/3,original.getHeight()/3, Image.SCALE_SMOOTH);
        g.drawImage(scaled, 0, 0, null);
    }

    public BufferedImage getOriginal() {
        return original;
    }

    public void setOriginal(BufferedImage original) {
        this.original = original;
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        repaint();
    }
}
