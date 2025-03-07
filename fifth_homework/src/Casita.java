import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Casita {

    public static ArrayList<Puntos3> casitaPuntos() {
        ArrayList<Puntos3> puntos = new ArrayList<>();
        puntos.add(new Puntos3(-100, -100, -1000));
        puntos.add(new Puntos3(100, -100, -1000));
        puntos.add(new Puntos3(100, 100, -1000));
        puntos.add(new Puntos3(-100, 100, -1000));
        puntos.add(new Puntos3(-100, -100, -1200));
        puntos.add(new Puntos3(100, -100, -1200));
        puntos.add(new Puntos3(100, 100, -1200));
        puntos.add(new Puntos3(-100, 100, -1200));
        puntos.add(new Puntos3(0, 200, -1100));
        return puntos;
    }
    
    public static Map<Integer, ArrayList<Integer>> casitaEdges() {
        Map<Integer, ArrayList<Integer>> segmentos = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            segmentos.put(i, new ArrayList<>());
        }
        segmentos.get(0).add(1);
        segmentos.get(1).add(2);
        segmentos.get(2).add(3);
        segmentos.get(3).add(0);
        segmentos.get(4).add(5);
        segmentos.get(5).add(6);
        segmentos.get(6).add(7);
        segmentos.get(7).add(4);
        segmentos.get(0).add(4);
        segmentos.get(1).add(5);
        segmentos.get(2).add(6);
        segmentos.get(3).add(7);
        segmentos.get(3).add(8);
        segmentos.get(2).add(8);
        segmentos.get(6).add(8);
        segmentos.get(7).add(8);
        return segmentos;
    }
}
