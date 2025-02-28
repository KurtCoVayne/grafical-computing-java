import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Personaje {
    private double angle = -Math.PI / 2;
    private double cx, cy;
    private final double initialCx, initialCy;
    private final ArrayList<Puntos2> puntos = new ArrayList<>();
    private final Map<Integer, ArrayList<Integer>> segmentos = new HashMap<>();

    public Personaje(double initialRotation, double initialCx, double initialCy, ArrayList<Puntos2> puntos, Map<Integer, ArrayList<Integer>> segmentos) {
        this.angle = initialRotation;
        this.cx = initialCx;
        this.cy = initialCy;
        this.puntos.addAll(puntos);
        this.segmentos.putAll(segmentos);
        this.initialCx = initialCx;
        this.initialCy = initialCy;
    }

    
    public Personaje(double initialRotation, ArrayList<Puntos2> puntos, Map<Integer, ArrayList<Integer>> segmentos) {
        this.puntos.addAll(puntos);
        this.segmentos.putAll(segmentos);
        this.cx = 0;
        this.cy = 0;
        for (Puntos2 p : puntos) {
            this.cx += p.x;
            this.cy += p.y;
        }
        this.cx /= puntos.size();
        this.cy /= puntos.size();
        this.initialCx = this.cx;
        this.initialCy = this.cy;
    }

    public void moveForward() {
        cx += Math.cos(angle) * Config.SPEED;
        cy += Math.sin(angle) * Config.SPEED;
    }

    public void moveBackward() {
        cx -= Math.cos(angle) * Config.SPEED;
        cy -= Math.sin(angle) * Config.SPEED;
    }

    public void rotateLeft() {
        angle = (angle - Config.ANGLE_SPEED + 2 * Math.PI) % (2 * Math.PI);
    }

    public void rotateRight() {
        angle = (angle + Config.ANGLE_SPEED) % (2 * Math.PI);
    }

    public void draw(Graphics2D g2d) {
        Matrix3x3 t1 = Matrix3x3.translation(-cx, -cy);
        Matrix3x3 r = Matrix3x3.rotation(angle + Math.PI / 2);
        Matrix3x3 t2 = Matrix3x3.translation(cx, cy);
        Matrix3x3 m = Matrix3x3.times(t2.array, Matrix3x3.times(r.array, t1.array).array);

        g2d.setColor(Color.RED);
        double offsetX = cx - initialCx;
        double offsetY = cy - initialCy;
        m = Matrix3x3.times(m.array, Matrix3x3.translation(offsetX, offsetY).array);
        for (Map.Entry<Integer, ArrayList<Integer>> entry : segmentos.entrySet()) {
            Puntos2 pu = puntos.get(entry.getKey());
            for (int v : entry.getValue()) {
                Puntos2 pv = puntos.get(v);
                Puntos2 puTransformed = pu.transform(m);
                Puntos2 pvTransformed = pv.transform(m);
                g2d.drawLine((int) puTransformed.x, (int) puTransformed.y, (int) pvTransformed.x, (int) pvTransformed.y);
            }
        }

        g2d.setColor(Color.BLUE);
        g2d.fillOval((int) cx - 3, (int) cy - 3, 6, 6);

        g2d.setColor(Color.GREEN);
        g2d.drawLine((int) cx, (int) cy,
                     (int) (cx + 50 * Math.cos(angle)),
                     (int) (cy + 50 * Math.sin(angle)));
    }
}

public class App extends JPanel implements KeyListener {
    private final Personaje character;
    private final Set<Integer> pressedKeys = new HashSet<>();

    public App() {
        ArrayList<Puntos2> puntos = new ArrayList<>();
        puntos.add(new Puntos2(100, 50));
        puntos.add(new Puntos2(150, 150));
        puntos.add(new Puntos2(50, 150));
        Map<Integer, ArrayList<Integer>> segmentos = new HashMap<>();
        segmentos.put(0, new ArrayList<>());
        segmentos.get(0).add(1);
        segmentos.put(1, new ArrayList<>());
        segmentos.get(1).add(2);
        segmentos.put(2, new ArrayList<>());
        segmentos.get(2).add(0);
        character = new Personaje(0, 100, 100, puntos, segmentos);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        character.draw(g2d);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Triangulin Movindin");
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
        pressedKeys.add(e.getKeyCode());
        processKeys();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void processKeys() {
        if (pressedKeys.contains(KeyEvent.VK_A)) character.rotateLeft();
        if (pressedKeys.contains(KeyEvent.VK_D)) character.rotateRight();
        if (pressedKeys.contains(KeyEvent.VK_W)) character.moveForward();
        if (pressedKeys.contains(KeyEvent.VK_S)) character.moveBackward();
        repaint();
    }
}
