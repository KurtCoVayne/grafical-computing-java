import java.awt.Graphics2D;

public class Segmento {
    Puntos2 p1;
    Puntos2 p2;

    public Segmento(Puntos2 p1, Puntos2 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
    }

    public void transform(Matrix3x3 m) {
        p1.transform(m);
        p2.transform(m);
    }
}
