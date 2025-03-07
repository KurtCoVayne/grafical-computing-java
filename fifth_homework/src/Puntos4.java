public class Puntos4 {
    double x;
    double y;
    double z;
    double w;

    public Puntos4(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public Puntos4(Puntos3 p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        this.w = 1;
    }

    public Puntos4() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 1;
    }
    
    public Puntos4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Puntos4(double P[][]){
        this.x=P[0][0];
        this.y=P[1][0];
        this.w=P[2][0];
    }

    public Puntos4 wNorm() {
        return new Puntos4(this.x / this.w, this.y / this.w, this.z / this.w, 1);
    }
}
