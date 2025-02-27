public class Puntos3 {
    double x;
    double y;
    double w;

    public Puntos3(double x, double y) {
        this.x = x;
        this.y = y;
        this.w = 1;
    }

    public Puntos3(Puntos2 p) {
        this.x = p.x;
        this.y = p.y;
        this.w = 1;
    }

    public Puntos3() {
        this.x = 0;
        this.y = 0;
        this.w = 0;
    }
    
    public Puntos3(double x, double y, double w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public Puntos3(double P[][]){
        this.x=P[0][0];
        this.y=P[1][0];
        this.w=P[2][0];
    }
}
