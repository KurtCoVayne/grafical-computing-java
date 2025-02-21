public class vector4 {
    double x;
    double y;
    double w;
    double z;
    public vector4(){
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.z = 0;
    }
    public vector4(double x, double y, double w, double z){
        this.x = x;
        this.y = y;
        this.w = w;
        this.z = z;
    }

    public static vector4 dotProduct(vector4 v1, vector4 v2){
        vector4 v4 = new vector4();
        v4.x = v1.x*v2.x;
        v4.y = v1.y*v2.y;
        v4.w = v1.w*v2.w;
        v4.z = v1.z*v2.z;
        return v4;
    }

    public double Magnitude(){
        return Math.sqrt(this.x*this.x+this.y*this.y+this.w*this.w+this.z*this.z);
    }

    public void Normalize(){
        double mac=Magnitude();
        this.x/=mac;
        this.y/=mac;
        this.w/=mac;
        this.z/=mac;
    }
    public static void main(String args[]){
        vector4 v1 = new vector4(5,2,-1,2);
        vector4 v2 = new vector4(0,-1,4,15);
        vector4 v3 = vector4.dotProduct(v1, v2);
        System.out.println(v3.x);
        System.out.println(v3.y);
        System.out.println(v3.w);
        System.out.println(v3.z);
    }
}
