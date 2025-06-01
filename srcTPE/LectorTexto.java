import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LectorTexto {
    public static void main(String [] args){       
        try {
            ArrayList<Maquina> maquinas=new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get("TPE-PROG3/srcTPE/text.txt"));
            int piezasAproducir=Integer.parseInt(lines.get(0));
            for(int i=1;i < lines.size();i++){
                String[] cadenas=lines.get(i).split(",");
                int numPiezas=Integer.parseInt(cadenas[1]);
                maquinas.add(new Maquina(cadenas[0],numPiezas));
                //System.out.println(lines.get(i));
            }
            System.out.println(piezasAproducir);
            Fabrica fabrica=new Fabrica(maquinas);
            //System.out.println(maquinas);
            //System.out.println(fabrica.asignacionBacktracking(piezasAproducir));
            //System.out.println(fabrica.getCostoSolucion());
            System.out.println(fabrica.greedy(piezasAproducir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(System.getProperty("user.dir")); //sirve para encontrar el directorio donde esta parado
    }
}
