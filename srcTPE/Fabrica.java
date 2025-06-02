package srcTPE;
import java.util.ArrayList;
import java.util.Collections;

public class Fabrica {
    private ArrayList<Maquina> maquinas;
    private ArrayList<Maquina> solucion;
    private int costoProcesoTotal;
    private int costoSolucion;

    public Fabrica(ArrayList<Maquina> maquinas){
        this.maquinas=new ArrayList<>(maquinas);
        this.solucion=new ArrayList<>();
        this.costoProcesoTotal=0;
        this.costoSolucion=0;
    }

    public ArrayList<Maquina> asignacionBacktracking(int piezas){
        if(!this.maquinas.isEmpty() && piezas>0){
            ArrayList<Maquina> solucionTemporal=new ArrayList<>();
            backtracking(piezas,solucionTemporal);
            return this.solucion;
        }
        return null;
    }

    //proceso completo con todo el arbol completo 489
    private void backtracking(int piezasRestante,ArrayList<Maquina> solucionTemporal){
        if(piezasRestante==0){
            if(solucionTemporal.size() < solucion.size() || solucion.isEmpty()){
                this.costoSolucion=costoProcesoTotal;
                solucion.clear();
                solucion.addAll(solucionTemporal);
            }
        }
        else{
            for(Maquina maquina:maquinas){
                if(!poda(maquina.getCantPiezas(),piezasRestante)){
                    costoProcesoTotal++;
                    int pTemp=maquina.getCantPiezas();
                    solucionTemporal.add(maquina);
                    backtracking(piezasRestante-pTemp,solucionTemporal);
                    solucionTemporal.removeLast();
                }
            }
        }
    }

    private boolean poda(int piezasCandidatas,int restantes){
        return piezasCandidatas>restantes;
    }

    public int getCostoSolucion() {
        return costoSolucion;
    }

    public int getCostoProcesoTotal() {
        return costoProcesoTotal;
    }
     //BACKTRACKING


    //GREEDY
    public ArrayList<Maquina> greedy(int objetivo){
        //declaro mi arreglo solucion
        ArrayList<Maquina> solucion=new ArrayList<>();

        //copiamos arreglo
        ArrayList<Maquina> maquinasOrd=new ArrayList<>(this.maquinas);
        maquinasOrd.sort(Collections.reverseOrder());//ordeno el arreglo de las copias de las maquinas descencdentemente

        while(!maquinasOrd.isEmpty() && !solucion(solucion,objetivo)){
            Maquina candidato=seleccionar(maquinasOrd, objetivo);
            maquinasOrd.remove(candidato);
            while(esFactible(solucion, candidato, objetivo)){
                solucion.add(candidato);
            }
        }
        if(solucion(solucion,objetivo))
            return solucion;
        else
            return null;
    }

    public boolean solucion(ArrayList<Maquina> solucion,int objetivo){
        int contador=0;
        for(Maquina maquina:solucion)
            contador+=maquina.getCantPiezas();
        
        return contador==objetivo;
    }

    public Maquina seleccionar(ArrayList<Maquina> maquinas,int objetivo){
        for(Maquina maquina:maquinas){
            if(maquina.getCantPiezas()<=objetivo)
                return maquina;
        }
        return null;
    }

    public boolean esFactible(ArrayList<Maquina> solucion,Maquina candidato,int objetivo){
        int suma=0;
        for(Maquina maquina:solucion){
            suma+=maquina.getCantPiezas();
        }
        return (suma + candidato.getCantPiezas()) <= objetivo;
    }

}
