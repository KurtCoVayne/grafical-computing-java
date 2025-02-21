public class vector3 {
    double x;
    double y;
    double w;
    public vector3(){
        this.x = 0;
        this.y = 0;
        this.w = 0;
    }
    public vector3(int x, int y, int w){
        this.x = x;
        this.y = y;
        this.w = w;
    }
    public static vector3 crossProduct(vector3 v1, vector3 v2){
        vector3 v3 = new vector3();
        v3.x = v1.y*v2.w - v1.w*v2.y;
        v3.y = -v1.x*v2.w + v1.w*v2.x;
        v3.w = v1.x*v2.y - v1.y*v2.x;
        return v3;
    }

    public static vector3 dotProduct(vector3 v1, vector3 v2){
        vector3 v3 = new vector3();
        v3.x = v1.x*v2.x;
        v3.y = v1.y*v2.y;
        v3.w = v1.w*v2.w;
        return v3;
    }

    public double Magnitude(){
        return Math.sqrt(this.x*this.x+this.y*this.y+this.w*this.w);
    }

    public void Normalize(){
        double mac=Magnitude();
        this.x/=mac;
        this.y/=mac;
        this.w/=mac;
    }
    public static void main(String args[]){
        vector3 v1 = new vector3(5,2,-1);
        vector3 v2 = new vector3(0,-1,4);
        vector3 v3 = vector3.crossProduct(v1, v2);
        System.out.println(v3.x);
        System.out.println(v3.y);
        System.out.println(v3.w);
    }
}
