//package srcTPE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LectorTexto {
    public static void main(String [] args){       
        try {
            ArrayList<Maquina> maquinas=new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get("TPE-PROG3/src/text.txt"));
            int piezasAproducir=Integer.parseInt(lines.get(0));

            for(int i=1;i < lines.size();i++){
                String[] cadenas=lines.get(i).split(",");
                int numPiezas=Integer.parseInt(cadenas[1]);//piezas que imprime maquina
                String nombre=cadenas[1];//nombre maquina
                maquinas.add(new Maquina(nombre,numPiezas));
            }

            Fabrica fabrica=new Fabrica(maquinas);

            System.out.println(fabrica.asignacionBacktracking(piezasAproducir));
            System.out.println();
            System.out.println(fabrica.greedy(piezasAproducir));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
