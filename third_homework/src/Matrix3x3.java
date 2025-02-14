public class Matrix3x3{
    double array[][] = new double[3][3];
    public Matrix3x3(double array[][]){
        this.array=array;
    }
    public static Matrix3x3 times(double m1[][], double m2[][]){
        int r = m1.length;
        int c = m2[0].length;
        int l = m2.length;
        double matriz_mult[][] = new double[r][c];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                double cont=0;
                for(int k=0; k<l; k++){
                    cont+=m1[i][k]*m2[k][j];
                }
                matriz_mult[i][j]=cont;
            }
        }
        return new Matrix3x3(matriz_mult);
    }

    public static Puntos3 times(double m1[][], Puntos3 P1){
        double P[][] = {{P1.x},{P1.y},{P1.w}};
        return new Puntos3(times(m1,P).array);       
    }

    public static void main(String args[]){
        double m1[][] = {{1,0,5},{0,1,2},{0,0,1}};
        double m2[][] = {{1,0,0},{0,1,2},{0,0,1}};
        double m3[][] = {{1,0,0},{0,1,3},{0,0,1}};
        Puntos3 P1 =new Puntos3(3,4,1);
        double ans1[][]=times(m2,m3).array;
        Puntos3 P2 = times(m1,P1);
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print((int)ans1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println((int)P2.x);
        System.out.println((int)P2.y);
        System.out.println((int)P2.w);
    }
}


