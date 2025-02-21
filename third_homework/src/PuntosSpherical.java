public class PuntosSpherical {
    double r;
    //Theta y Betha están en radianes
    double theta;
    double betha;
    public PuntosSpherical(Puntos3 P){
        this.r=Math.sqrt(P.x*P.x+P.y*P.y+P.w*P.w);
        this.theta=Math.atan(P.y/P.x);
        this.betha=Math.atan(Math.sqrt(P.x*P.x+P.y*P.y)/P.w);
    }
    public static void main(String args[]){
        Puntos3 P = new Puntos3(1,2,2);
        PuntosSpherical Ps = new PuntosSpherical(P);
        System.out.println(Ps.r);
        System.out.println(Ps.theta);
        System.out.println(Ps.betha); 
    }
}
