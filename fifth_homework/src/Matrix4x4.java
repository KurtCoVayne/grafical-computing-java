public class Matrix4x4 {
    double array[][] = new double[4][4];

    public Matrix4x4(double array[][]) {
        this.array = array;
    }

    public static Matrix4x4 eye() {
        double m[][] = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
        return new Matrix4x4(m);
    }

    public static Matrix4x4 translation(double dx, double dy, double dz) {
        double m[][] = { { 1, 0, 0, dx }, { 0, 1, 0, dy }, { 0, 0, 1, dz }, { 0, 0, 0, 1 } };
        return new Matrix4x4(m);
    }

    public static Matrix4x4 scaling(double sx, double sy, double sz) {
        double m[][] = { { sx, 0, 0, 0 }, { 0, sy, 0, 0 }, { 0, 0, sz, 0 }, { 0, 0, 0, 1 } };
        return new Matrix4x4(m);
    }

    public static Matrix4x4 zRotation(double theta) {
        double m[][] = { { Math.cos(theta), -Math.sin(theta), 0, 0 }, { Math.sin(theta), Math.cos(theta), 0, 0 },
                { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
        return new Matrix4x4(m);
    }

    public static Matrix4x4 xRotation(double theta) {
        double m[][] = { { 1, 0, 0, 0 }, { 0, Math.cos(theta), -Math.sin(theta), 0 },
                { 0, Math.sin(theta), Math.cos(theta), 0 }, { 0, 0, 0, 1 } };
        return new Matrix4x4(m);
    }

    public static Matrix4x4 yRotation(double theta) {
        double m[][] = { { Math.cos(theta), 0, Math.sin(theta), 0 }, { 0, 1, 0, 0 }, { -Math.sin(theta), 0, Math.cos(theta), 0 },
                { 0, 0, 0, 1 } };
        return new Matrix4x4(m);
    }

    // https://www.scratchapixel.com/lessons/mathematics-physics-for-computer-graphics/lookat-function/framing-lookat-function.html
    public static Matrix4x4 lookAt(Puntos3 eye, Puntos3 target, Puntos3 up) {
        Puntos3 zAxis = Puntos3.normalize(Puntos3.subtract(eye, target));
        Puntos3 xAxis = Puntos3.normalize(Puntos3.cross(up, zAxis));
        Puntos3 yAxis = Puntos3.cross(zAxis, xAxis);

        double[][] m = {
            { xAxis.x, xAxis.y, xAxis.z, -Puntos3.dot(xAxis, eye) },
            { yAxis.x, yAxis.y, yAxis.z, -Puntos3.dot(yAxis, eye) },
            { zAxis.x, zAxis.y, zAxis.z, -Puntos3.dot(zAxis, eye) },
            { 0, 0, 0, 1 }
        };

        return new Matrix4x4(m);
    }

    public static Matrix4x4 perspective(double d) {
        double m[][] = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1 / d, 0 } };
        return new Matrix4x4(m);
    }

    public static Matrix4x4 times(double m1[][], double m2[][]) {
        int r = m1.length;
        int c = m2[0].length;
        int l = m2.length;
        double matriz_mult[][] = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                double cont = 0;
                for (int k = 0; k < l; k++) {
                    cont += m1[i][k] * m2[k][j];
                }
                matriz_mult[i][j] = cont;
            }
        }
        return new Matrix4x4(matriz_mult);
    }

    public static Puntos4 times(double m1[][], Puntos4 P1) {
        double P[][] = { { P1.x }, { P1.y }, { P1.z }, { P1.w } };
        return new Puntos4(times(m1, P).array);
    }

}
