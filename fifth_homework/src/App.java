import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

class Camera {
    double theta = Math.PI / 4; // Azimuth angle (left/right)
    double phi = Math.PI / 4;   // Polar angle (up/down)
    double radius = 500;        // Distance from the object
}

public class App extends JPanel implements KeyListener {
    private final Personaje character;
    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Camera camera = new Camera();

    public App() {
        ArrayList<Puntos3> puntos = Casita.casitaPuntos();
        Map<Integer, ArrayList<Integer>> segmentos = Casita.casitaEdges();

        character = new Personaje(0, 0, 0, puntos, segmentos);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double camX = character.cx + camera.radius * Math.sin(camera.phi) * Math.cos(camera.theta);
        double camY = character.cy + camera.radius * Math.cos(camera.phi);
        double camZ = character.cz + camera.radius * Math.sin(camera.phi) * Math.sin(camera.theta);

        Matrix4x4 viewMatrix = Matrix4x4.lookAt(
            new Puntos3(camX, camY, camZ),  // Posicion de la camara
            new Puntos3(character.cx, character.cy, character.cz),
            new Puntos3(0, 1, 0) // Vector normal
        );

        character.draw(g2d, viewMatrix);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Orbitando una casita");
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
        if (pressedKeys.contains(KeyEvent.VK_A))
            camera.theta -= Config.ANGLE_SPEED; // Izquierda
        if (pressedKeys.contains(KeyEvent.VK_D))
            camera.theta += Config.ANGLE_SPEED; // Derecha
        if (pressedKeys.contains(KeyEvent.VK_W))
            camera.phi -= Config.ANGLE_SPEED;   // Arriba
        if (pressedKeys.contains(KeyEvent.VK_S))
            camera.phi += Config.ANGLE_SPEED;   // Abajo
        repaint();
    }
}

class Personaje {
    public double cx, cy, cz;
    private final ArrayList<Puntos3> puntos;
    private final Map<Integer, ArrayList<Integer>> segmentos;

    public Personaje(double rotationX, double rotationY, double rotationZ,
                      ArrayList<Puntos3> puntos, Map<Integer, ArrayList<Integer>> segmentos) {
        this.puntos = puntos;
        this.segmentos = segmentos;
        this.cx = 0;
        this.cy = 0;
        this.cz = 0;
        for (Puntos3 p : puntos) {
            this.cx += p.x;
            this.cy += p.y;
            this.cz += p.z;
        }
        this.cx /= puntos.size();
        this.cy /= puntos.size();
        this.cz /= puntos.size();
    }

    public void draw(Graphics2D g2d, Matrix4x4 viewMatrix) {
        Matrix4x4 persp = Matrix4x4.perspective(-Config.FOCAL_DISTANCE);
        Matrix4x4 finalMatrix = Matrix4x4.times(persp.array, viewMatrix.array);

        g2d.setColor(Color.RED);
        for (Map.Entry<Integer, ArrayList<Integer>> entry : segmentos.entrySet()) {
            Puntos3 pu = puntos.get(entry.getKey());
            for (int v : entry.getValue()) {
                Puntos3 pv = puntos.get(v);
                Puntos4 pu4 = new Puntos4(pu);
                Puntos4 pv4 = new Puntos4(pv);
                Puntos4 puTransformed = Matrix4x4.times(finalMatrix.array, pu4);
                Puntos4 pvTransformed = Matrix4x4.times(finalMatrix.array, pv4);

                // Centrar a la pantalla
                g2d.drawLine(
                    (int) puTransformed.x + Config.SCREEN_WIDTH / 2,
                    (int) (Config.SCREEN_HEIGHT / 2 - puTransformed.y),
                    (int) pvTransformed.x + Config.SCREEN_WIDTH / 2,
                    (int) (Config.SCREEN_HEIGHT / 2 - pvTransformed.y)
                );
            }
        }
    }
}
