public class Puntos3 {
    double x, y, z;

    public Puntos3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Puntos3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Puntos3 transform(Matrix4x4 m) {
        Puntos4 p = new Puntos4(this);
        p = Matrix4x4.times(m.array, p);
        return new Puntos3(p.x, p.y, p.z);
    }

    public static Puntos3 subtract(Puntos3 a, Puntos3 b) {
        return new Puntos3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Puntos3 normalize(Puntos3 p) {
        double length = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
        if (length == 0) return new Puntos3(0, 0, 0);
        return new Puntos3(p.x / length, p.y / length, p.z / length);
    }

    public static Puntos3 cross(Puntos3 a, Puntos3 b) {
        return new Puntos3(
            a.y * b.z - a.z * b.y,
            a.z * b.x - a.x * b.z,
            a.x * b.y - a.y * b.x
        );
    }

    public static double dot(Puntos3 a, Puntos3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }
}
