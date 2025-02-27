public class Puntos2 {
    double x, y;

    public Puntos2() {
        this.x = 0;
        this.y = 0;
    }

    public Puntos2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Puntos2 transform(Matrix3x3 m) {
        Puntos3 p = new Puntos3(this);
        p = Matrix3x3.times(m.array, p);
        System.out.println("p1: " + this.x + ", " + this.y);
        System.out.println("p2: " + p.x + ", " + p.y);
        return new Puntos2(p.x, p.y);
    }
}
