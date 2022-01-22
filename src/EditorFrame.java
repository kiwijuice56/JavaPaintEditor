import javax.swing.*;
import java.awt.*;

/**
 * Main JPanel for paintEditor
 */
public class EditorFrame extends JFrame {
    PaintPanel paintPanel;

    public static void main(String[] args) { new EditorFrame(); }

    /**
     *  Initializes an EditorFrame
     */
    public EditorFrame(){
        /* * * * * * * FRAME * * * * * * * */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(1200, 700));
        setTitle("Paint Editor");
        paintPanel = new PaintPanel(800, 600);

        /* * * * * * * TOP PANEL * * * * * */
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240,240,240)));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(new FileControlPanel());

        /* * * * * * RIGHT PANEL * * * * * */
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(new LayerSelectionPanel(paintPanel));

        /* * * * * * LEFT PANEL * * * * * */
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(new ColorSelectionPanel(paintPanel));
        leftPanel.add(new BrushSelectionPanel(paintPanel));

        /* * * * * * ASSEMBLY * * * * * */
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(rightPanel, BorderLayout.EAST);
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(paintPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
