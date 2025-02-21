public class PuntosCylindrical {
    double r;
    //Theta está en radianes
    double theta;
    double w;
    
    public PuntosCylindrical(Puntos3 P){
        this.r=Math.sqrt(P.x*P.x+P.y*P.y);
        this.theta=Math.atan(P.y/P.x);
        this.w=P.w;
    }
}
