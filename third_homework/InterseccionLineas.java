import java.util.*;

class Puntos {
    double x, y;

    public Puntos() {
        this.x = 0;
        this.y = 0;
    }

    public Puntos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double orient(Puntos a, Puntos b, Puntos p) {
        return (b.x - a.x) * (p.y - a.y) - (b.y - a.y) * (p.x - a.x);
    }

    public static boolean inDisk(Puntos a, Puntos b, Puntos p) {
        return (p.x - a.x) * (p.x - b.x) + (p.y - a.y) * (p.y - b.y) <= 0;
    }

    public static boolean onSegment(Puntos a, Puntos b, Puntos p) {
        return orient(a, b, p) == 0 && inDisk(a, b, p);
    }

    public static boolean properInter(Puntos a, Puntos b, Puntos c, Puntos d, Puntos out) {
        double oa = orient(c, d, a);
        double ob = orient(c, d, b);
        double oc = orient(a, b, c);
        double od = orient(a, b, d);

        if (oa * ob < 0 && oc * od < 0) {
            double t = ob / (ob - oa);
            out.x = a.x * (1 - t) + b.x * t;
            out.y = a.y * (1 - t) + b.y * t;
            return true;
        }
        return false;
    }

    public static List<Puntos> inters(Puntos a, Puntos b, Puntos c, Puntos d) {
        List<Puntos> intersecciones = new ArrayList<>();
        Puntos out = new Puntos();
        
        if (properInter(a, b, c, d, out)) {
            intersecciones.add(out);
            return intersecciones;
        }

        if (onSegment(c, d, a)) intersecciones.add(a);
        if (onSegment(c, d, b)) intersecciones.add(b);
        if (onSegment(a, b, c)) intersecciones.add(c);
        if (onSegment(a, b, d)) intersecciones.add(d);

        return intersecciones;
    }
}

public class InterseccionLineas {
    public static void main(String[] args) {
        Puntos a = new Puntos(1, 1);
        Puntos b = new Puntos(4, 4);
        Puntos c = new Puntos(1, 4);
        Puntos d = new Puntos(4, 1);

        List<Puntos> intersecciones = Puntos.inters(a, b, c, d);
        System.out.println("Interseccion:");
        for (Puntos p : intersecciones) {
            System.out.println(p.x + " " + p.y);
        }
    }
}
