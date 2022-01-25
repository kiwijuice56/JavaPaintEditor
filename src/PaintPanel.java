import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;

/**
 * JPanel that uses mouse events to draw with the cursor. Allows for multiple layers of drawings
 */
public class PaintPanel extends JPanel implements MouseMotionListener, MouseListener {
    private ArrayList<BufferedImage> drawingLayers;
    private int[] lastPoint;
    private int currentLayer;
    private int width, height;
    private int brushSize;
    private Color currentColor;

    public static final int MAX_LAYERS = 10;
    public static final int PRECISION_SIZE = 1;
    public static final int PENCIL_SIZE = 8;
    public static final int MARKER_SIZE = 24;

    BufferedImage canvasBackground;

    /**
     * Initializes a PaintPanel
     * @param width The width of the canvas that can be drawn on
     * @param height The height of the canvas that can be drawn on
     */
    public PaintPanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.brushSize = 8;
        this.currentLayer = 0;
        setSize(new Dimension(width, height));
        setBackground(new Color(170,180,200));

        drawingLayers = new ArrayList<>();
        addLayer();

        currentColor = Color.RED;
        currentLayer = 0;
        canvasBackground = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        canvasBackground.getGraphics().fillRect(0, 0, width, height);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("img/paintcursor.png");
        Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(),
                this.getY()), "img");
        this.setCursor(c);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * Draws the brush shape at given coordinate
     * @param x
     * @param y
     */
    private void drawBrush(int x, int y) {
        Graphics g = drawingLayers.get(currentLayer).getGraphics();
        g.setColor(currentColor);
        g.fillOval(x - brushSize/2, y - brushSize/2, brushSize, brushSize);
    }

    /**
     * Draws a line using the brush shape from the 1 coordinate to the 2 coordinate. Analogous to Graphics.drawLine()
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private void drawLine(int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) drawingLayers.get(currentLayer).getGraphics();
        g.setColor(currentColor);
        g.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g.drawLine(x1, y1, x2, y2);
    }

    /**
     * Replaces all drawings on the current layer with a color with 0 alpha
     */
    public void clearLayer() {
        BufferedImage layer = drawingLayers.get(currentLayer);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                layer.setRGB(j, i, 0);
        repaint();
    }

    /**
     * Adds a new blank layer to drawingLayers
     * @return The index of the new layer, unless drawingLayers is the maximum size, in which it returns -1
     */
    public int addLayer() {
        if (drawingLayers.size() < MAX_LAYERS) {
            drawingLayers.add(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
            currentLayer = drawingLayers.size()-1;
            repaint();
            return currentLayer;
        }
        return -1;
    }

    /**
     * Deletes the currentLayer of drawingLayers and returns the index removed, unless drawingLayers has only
     * one layer, in which it returns -1
     * @return The index of the new layer, unless drawingLayers is the maximum size, in which it returns -1
     */
    public int deleteLayer() {
        if (drawingLayers.size() > 1) {
            int oldLayer = currentLayer;
            drawingLayers.remove(oldLayer);
            currentLayer = Math.max(0, currentLayer-1);
            repaint();
            return oldLayer;
        }
        return -1;
    }

    /**
     * Repaints the images onto the PaintPanel
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvasBackground, 0, 0, null);
        for (BufferedImage image : drawingLayers)
            g.drawImage(image, 0, 0, null);
    }

    /* * * MouseMotion and MouseListener methods * * */

    /**
     * Draws lines from mouse points to create a smooth drawing
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastPoint != null)
            drawLine(e.getX(), e.getY(), lastPoint[0], lastPoint[1]);
        lastPoint = new int[] {e.getX(), e.getY()};
        repaint();
    }

    /**
     * Draws the brush on a singular mouse click
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        drawBrush(e.getX(), e.getY());
        repaint();
    }

    /**
     * Clears the lastPoint in memory to prevent mouseDragged from interpolating unconnected clicks
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        lastPoint = null;
    }

    @Override public void mouseMoved(MouseEvent e) { }
    @Override public void mousePressed(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }

    /* * * * * * Setter and Getter methods * * * * * */

    public BufferedImage getCurrentLayerImage() {
        return drawingLayers.get(currentLayer);
    }

    public void setCurrentLayer(int currentLayer) {
        this.currentLayer = Math.min(Math.max(0, currentLayer), currentLayer);
    }

    public int getCurrentLayer() {
        return currentLayer;
    }

    public BufferedImage getMerged(boolean drawBackground){
        BufferedImage merged = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = merged.getGraphics();
        if (drawBackground)
            g.drawImage(canvasBackground, 0, 0, null);
        for (BufferedImage image : drawingLayers)
            g.drawImage(image, 0, 0, null);
        return merged;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
