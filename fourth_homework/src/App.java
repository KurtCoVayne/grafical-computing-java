import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends JPanel implements KeyListener {
    private double angle = -Math.PI/2;
    private double cx, cy; // Current centroid
    private double initialCx, initialCy; // Initial centroid position
    private ArrayList<Puntos2> puntos = new ArrayList<>();
    private Map<Integer, ArrayList<Integer>> segmentos = new HashMap<>();

    private void addPunto(int id, Puntos2 p) {
        puntos.add(p);
        segmentos.put(id, new ArrayList<>());
    }

    private void addSegmento(int u, int v) {
        segmentos.get(u).add(v);
    }

    public App() {
        addPunto(0, new Puntos2(100, 50));
        addPunto(1, new Puntos2(150, 150));
        addPunto(2, new Puntos2(50, 150));
        addSegmento(0, 1);
        addSegmento(1, 2);
        addSegmento(2, 0);
        

        // Compute initial centroid
        for (Puntos2 p : puntos) {
            initialCx += p.x;
            initialCy += p.y;
        }
        initialCx /= puntos.size();
        initialCy /= puntos.size();
        
        // Set current centroid to initial position
        cx = initialCx;
        cy = initialCy;

        System.out.println("Initial Centroid: " + initialCx + ", " + initialCy);

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Compute rotation matrix around centroid
        Matrix3x3 t1 = Matrix3x3.translation(-cx, -cy);
        Matrix3x3 r = Matrix3x3.rotation(angle+Math.PI/2);
        Matrix3x3 t2 = Matrix3x3.translation(cx, cy);
        Matrix3x3 m = Matrix3x3.eye();
        m = Matrix3x3.times(t1.array, m.array);
        m = Matrix3x3.times(r.array, m.array);
        m = Matrix3x3.times(t2.array, m.array);

        g2d.setColor(Color.RED);

        // Draw segments
        for (Map.Entry<Integer, ArrayList<Integer>> entry : segmentos.entrySet()) {
            int u = entry.getKey();
            ArrayList<Integer> vs = entry.getValue();
            Puntos2 pu = puntos.get(u);
            for (int v : vs) {
                Puntos2 pv = puntos.get(v);
                Puntos2 puMoved = new Puntos2(pu.x + (cx - initialCx), pu.y + (cy - initialCy));
                Puntos2 pvMoved = new Puntos2(pv.x + (cx - initialCx), pv.y + (cy - initialCy));
                Puntos2 puRotated = puMoved.transform(m);
                Puntos2 pvRotated = pvMoved.transform(m);
                g2d.drawLine((int) puRotated.x, (int) puRotated.y, (int) pvRotated.x, (int) pvRotated.y);
            }
        }

        // Draw centroid
        g2d.setColor(Color.BLUE);
        g2d.fillOval((int) cx - 3, (int) cy - 3, 6, 6);

        // Draw angle vector
        g2d.setColor(Color.GREEN);
        g2d.drawLine((int) cx, (int) cy, 
                     (int) (cx + 50 * Math.cos(angle)), 
                     (int) (cy + 50 * Math.sin(angle)));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Puntos2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        frame.setBackground(Color.BLACK);

        App panel = new App();
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> angle = (angle - Config.ANGLE_SPEED + 2 * Math.PI) % (2 * Math.PI);
            case KeyEvent.VK_D -> angle = (angle + Config.ANGLE_SPEED) % (2 * Math.PI);
            case KeyEvent.VK_W -> moveForward();
            case KeyEvent.VK_S -> moveBackward();
        }
        repaint();
    }

    private void moveForward() {
        cx += Math.cos(angle) * Config.SPEED;
        cy += Math.sin(angle) * Config.SPEED;
    }

    private void moveBackward() {
        cx -= Math.cos(angle) * Config.SPEED;
        cy -= Math.sin(angle) * Config.SPEED;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
