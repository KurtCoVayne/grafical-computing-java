// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EventoMouseTarea extends JPanel implements MouseListener {
   int x1;
   int y1;
   int x2;
   int y2;
   boolean isDrawing = false;

   public EventoMouseTarea() {
      this.addMouseListener(this);
   }

   public void paintComponent(Graphics var1) {
      super.paintComponent(var1);
      Graphics2D var2 = (Graphics2D)var1;
      var2.setColor(Color.BLUE);
      if (this.isDrawing) {
         this.bresenham(this.x1, this.y1, this.x2, this.y2, var2);
      }

   }

   public void bresenham(int var1, int var2, int var3, int var4, Graphics2D var5) {
      int var6 = Math.abs(var3 - var1);
      int var7 = Math.abs(var4 - var2);
      int var8 = var1 < var3 ? 1 : -1;
      int var9 = var2 < var4 ? 1 : -1;
      int var10 = var6 - var7;

      while(true) {
         var5.drawLine(var1, var2, var1, var2);
         if (var1 == var3 && var2 == var4) {
            return;
         }

         int var11 = 2 * var10;
         if (var11 > -var7) {
            var10 -= var7;
            var1 += var8;
         }

         if (var11 < var6) {
            var10 += var6;
            var2 += var9;
         }
      }
   }

   public void mousePressed(MouseEvent var1) {
      this.x1 = var1.getX();
      this.y1 = var1.getY();
      this.isDrawing = false;
   }

   public void mouseReleased(MouseEvent var1) {
      this.x2 = var1.getX();
      this.y2 = var1.getY();
      this.isDrawing = true;
      this.repaint();
   }

   public void mouseClicked(MouseEvent var1) {
   }

   public void mouseEntered(MouseEvent var1) {
   }

   public void mouseExited(MouseEvent var1) {
   }

   public static void main(String[] var0) {
      JFrame var1 = new JFrame("Eventos del Mouse - Bresenham");
      var1.setDefaultCloseOperation(3);
      EventoMouseTarea var2 = new EventoMouseTarea();
      var1.add(var2);
      var1.setSize(500, 500);
      var1.setLocationRelativeTo((Component)null);
      var1.setVisible(true);
   }
}
