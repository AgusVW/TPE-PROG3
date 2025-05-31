package srcTPE;

import java.util.ArrayList;

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

}
