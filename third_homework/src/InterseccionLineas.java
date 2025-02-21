import java.util.*;

public class InterseccionLineas {
    public static double orient(Puntos2 a, Puntos2 b, Puntos2 p) {
        return (b.x - a.x) * (p.y - a.y) - (b.y - a.y) * (p.x - a.x);
    }

    public static boolean inDisk(Puntos2 a, Puntos2 b, Puntos2 p) {
        return (p.x - a.x) * (p.x - b.x) + (p.y - a.y) * (p.y - b.y) <= 0;
    }

    public static boolean onSegment(Puntos2 a, Puntos2 b, Puntos2 p) {
        return orient(a, b, p) == 0 && inDisk(a, b, p);
    }

    public static boolean properInter(Puntos2 a, Puntos2 b, Puntos2 c, Puntos2 d, Puntos2 out) {
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

    public static List<Puntos2> inters(Puntos2 a, Puntos2 b, Puntos2 c, Puntos2 d) {
        List<Puntos2> intersecciones = new ArrayList<>();
        Puntos2 out = new Puntos2();
        
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
    public static void main(String[] args) {
        Puntos2 a = new Puntos2(1, 1);
        Puntos2 b = new Puntos2(4, 4);
        Puntos2 c = new Puntos2(1, 4);
        Puntos2 d = new Puntos2(4, 1);

        List<Puntos2> intersecciones = inters(a, b, c, d);
        System.out.println("Interseccion:");
        for (Puntos2 p : intersecciones) {
            System.out.println(p.x + " " + p.y);
        }
    }
}
