package srcTPE;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class LectorTexto {
    public static void main(String [] args){       
        try {
            ArrayList<Maquina> maquinas=new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get("srcTPE/text.txt"));
            int piezasAproducir=Integer.parseInt(lines.get(0));
            for(int i=1;i < lines.size();i++){
                String[] cadenas=lines.get(i).split(",");
                int numPiezas=Integer.parseInt(cadenas[1]);
                maquinas.add(new Maquina(cadenas[0],numPiezas));
                //System.out.println(lines.get(i));
            }
            System.out.println("Cantidad de piezas que se deben producir: " + piezasAproducir);
            System.out.println("Maquinas disponibles:");
            for (Maquina m : maquinas){
                System.out.println(m.getNombre() + ": Cantidad de piezas que produce= " + m.getCantPiezas());
            }

            Fabrica fabrica=new Fabrica(maquinas);

            //Greedy
            /*  Para la estatregia greedy ordenamos las maquinas de mayor a menor en base a la cantidad de piezas que produce de cada una.
                El algoritmo esta adaptado para poder utilizar mas de una vez una maquina si es necesario, priorizando siempre seleccionar
                la maquina de mayor produccion de piezas que no supere la cantidad restante de piezas a producir.
                Ya que Greedy no evalua todas las combinaciones posibles no garantiza encontrar una solucion (si exite) o la mejor (si la hay) en todos los casos
                Por ejemplo para la configuracion:
                12
                M1,7
                M2,9
                M3,4
                M4,2
                Greedy no encuentra una solucion (mientras que Backtracking si) teniendo en cuenta la estrategia que sigue.
            */
            
            System.out.println("---------------------------------------------------");
            System.out.println("GREEDY:");
            ArrayList<Maquina> solucionGreedy = fabrica.asignacionGreedy(piezasAproducir);
            if (solucionGreedy == null){
                System.out.println("No se puedo encontrar una solucion");
            }
            
            //Backtracking 
            System.out.println("---------------------------------------------------");
            System.out.println("BACKTRACKING:");
            ArrayList<Maquina> solucionBack = fabrica.asignacionBacktracking(piezasAproducir);
            if (solucionBack == null){
                System.out.println("No se puedo encontrar una solucion");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(System.getProperty("user.dir")); //sirve para encontrar el directorio donde esta parado
    }
}
