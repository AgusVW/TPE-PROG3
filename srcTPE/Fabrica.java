package srcTPE;

import java.util.ArrayList;

public class Fabrica {
    private ArrayList<Maquina> maquinas;
    private ArrayList<Maquina> solucion;
    private int costoProcesoTotal;
    private int costoBacktracking;

    public Fabrica(ArrayList<Maquina> maquinas){
        this.maquinas=new ArrayList<>(maquinas);
        this.solucion=new ArrayList<>();
        this.costoProcesoTotal=0;
        this.costoBacktracking=0;
    }


    public ArrayList<Maquina> asignacionGreedy(int piezas){
        ArrayList<Maquina> solucionGreedy = new ArrayList<>();
        int piezasDisponibles = piezas;
        int costo=0;
        int candidatos=0;
        int piezasProducidas=0;

        //ordenamos las maquinas de mayor a menor 
        ArrayList<Maquina> maquinasOrdenadas = new ArrayList<>(maquinas);
        maquinasOrdenadas.sort((m1,m2) -> Integer.compare(m2.getCantPiezas(), m1.getCantPiezas()));
        
        while (piezasDisponibles > 0){
            boolean maquinaAgregada = false; 
            //recorro todas las maquinas
            for (Maquina m : maquinasOrdenadas){
                candidatos++; 
                if (m.getCantPiezas() <= piezasDisponibles){
                    solucionGreedy.add(m);
                    piezasDisponibles -= m.getCantPiezas();
                    piezasProducidas+=m.getCantPiezas();
                    costo++;
                    maquinaAgregada=true;
                    break;
                }
            }
            if (!maquinaAgregada){
                return null;
            }
        }
        System.out.println("Solución obtenida: " + solucionGreedy);
        System.out.println("Cantidad de piezas producidas: " + piezasProducidas);
        System.out.println("Cantidad de maquinas en funcionamiento: " + costo);
        System.err.println("Cantidad de candidatos considerados: " + candidatos);
        return solucionGreedy;
    
    }

    public ArrayList<Maquina> asignacionBacktracking(int piezas){
        if(!this.maquinas.isEmpty() && piezas>0){
            ArrayList<Maquina> solucionTemporal=new ArrayList<>();
            backtracking(piezas,solucionTemporal);
            System.out.println("Solución obtenida: " + solucion);
            System.out.println("Cantidad de piezas producidas: " + piezas);
            System.out.println("Cantidad de maquinas en funcionamiento: " + getCostoBack());
            System.err.println("Cantidad de candidatos considerados: " + getCostoProcesoTotal());
                return this.solucion;
            }
        
        return null;
    }

    //proceso completo con todo el arbol completo 489
    private void backtracking(int piezasRestante,ArrayList<Maquina> solucionTemporal){
        if(piezasRestante==0){
            if(solucionTemporal.size() < solucion.size() || solucion.isEmpty()){
                this.costoBacktracking=solucionTemporal.size();
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
                    solucionTemporal.remove(solucionTemporal.size()-1);
                }
            }
        }
    }

    private boolean poda(int piezasCandidatas,int restantes){
        return piezasCandidatas>restantes;
    }

    public int getCostoBack() {
        return costoBacktracking;
    }

    public int getCostoProcesoTotal() {
        return costoProcesoTotal;
    }

}
