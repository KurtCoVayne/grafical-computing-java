
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class BresenhamOctante1 extends JPanel {
    Puntos p1,p2;

    public BresenhamOctante1(Puntos p1, Puntos p2) {
        this.p1=p1;
        this.p2=p2;
    }

    @Override
    public void paintComponent(Graphics g) {
        // super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        bresenham(p1,p2,g2d);
    }

    public void bresenham(Puntos p1,Puntos p2, Graphics2D g2d) {
        int x1=p1.x;
        int y1=p1.y;
        int x2=p2.x;
        int y2=p2.y;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int incE = 2 * dy;
        int incNE = 2 * dy - 2 * dx;
        int d = 2 * dy - dx;
        int y = y1;
        for (int x = x1; x <= x2; x++) {
            g2d.drawLine(x, y, x, y);
            if (d <= 0) {
                d += incE;
            } else {
                d += incNE;
                y += 1;
            }
        }
    }

    public static void main(String[] args) {
        // Entrada puntos
        Scanner scn = new Scanner(System.in);
        System.out.println("Ingresa coordenadas punto 1: ");
        int x1 = scn.nextInt();
        int y1 = scn.nextInt();
        Puntos p1 = new Puntos(x1,y1); 
        System.out.println("Ingrese coordenadas punto 2");
        int x2 = scn.nextInt();
        int y2 = scn.nextInt();
        Puntos p2 = new Puntos(x2,y2);

        // Crear un nuevo Frame
        JFrame frame = new JFrame("Puntos2");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new BresenhamOctante1(p1,p2));
        // Asignarle tamaño
        frame.setSize(50, 20);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
        scn.close();
    }

}