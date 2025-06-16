//package srcTPE;
import java.util.ArrayList;
import java.util.Collections;

public class Fabrica {
    private ArrayList<Maquina> maquinas;
    private Solucion solucion;

    public Fabrica(ArrayList<Maquina> maquinas){
        this.maquinas=new ArrayList<>(maquinas);
        this.solucion=new Solucion(0);
    }

    /*
     * Estrategia Backtracking:
     * - Se genera un arbol de exploracion donde en cada nivel se decide si incluir o no
     *   una maquina para cubrir el total de las piezas restantes.
     * - Estado inicial: piezas a producir completas sin maquinas seleccionadas.
     * - Estafo final/corte: cuando piezasRestante = 0. Se considera solucion si cumple y usa
     *   menos maquinas que la mejor solucion encontrada.
     * - Podas aplicadas:
     *   . Si la solucion temp tiene igual o mas maquinas que la mejor solucion actual, se
     *     descarta esa rama.
     *     . Si ya no hya suficientes piezas para seguir, se detiene la rama.
     * - Se busca minimizar la cantidad de maquinas usadas.
     * - Aplica poda para descartar ramas donde la solucion en la que estoy (actual) es peor 
     * o igual a la mejor encontrada.
     * - Para evitar soluciones/combinciones repetidas y mantener un orden en la 
     * busqueda, usamos un indice. Por ejemplo i=0 -> M1, i=1 -> M2 y asi sucesivamente hasta
     * maquinas.size() -> cantidad de maquinas.
     * - Se actuaiza un contador de nodos procesador para comparar a eficiencia entre versiones 
     *   resultas con o sin poda.
     * - Eñ metodo devuelve un String que reperesenta la mejor combinacion encontrada.
     */
    public String asignacionBacktracking(int piezas){
        if(!this.maquinas.isEmpty() && piezas>0){
            solucion=new Solucion(piezas);
            ArrayList<Maquina> solucionTemporal=new ArrayList<>();
            backtracking(piezas,solucionTemporal,0); //inicia el arbol de exploracion
            return this.solucion.solucionBacktracking();
        }
        return null;
    }

    /*
     * Metodo recursivo que explora todas las combinacione sposibles usando backtracking y poda.
     */
    //proceso completo con todo el arbol completo 490 sin poda
    //proceso completo con todo el arbol completo 140 con poda con cantMaquinasActuales>cantMaquinasSolucion
    //proceso completo con todo el arbol completo 63 con poda con cantMaquinasActuales>=cantMaquinasSolucion
    //proceso completo con todo el arbol completo 28 con poda y verificacion,implementando un indice
    private void backtracking(int piezasRestante,ArrayList<Maquina> solucionTemporal,int indice){
        solucion.setCostoProcesoTotal(solucion.getCostoProcesoTotal()+1);
        if(piezasRestante==0){
            if(solucionTemporal.size() < solucion.size() || solucion.isEmpty()){
                solucion.setCostoSolucion(solucion.getCostoProcesoTotal());
                solucion.limpiarSolucion();
                solucion.addAll(solucionTemporal);
            }
        }
        else{
            for(int i=indice;i < maquinas.size();i++){
                Maquina maquinaTmp=maquinas.get(i);
                if(maquinaTmp.getCantPiezas()<=piezasRestante && !poda(solucion, solucionTemporal)){
                    int piezaTmp=maquinaTmp.getCantPiezas();
                    solucionTemporal.add(maquinaTmp);
                    backtracking(piezasRestante-piezaTmp, solucionTemporal, i);
                    solucionTemporal.remove(solucionTemporal.size()-1);
                }
            }
        }
    }

    private boolean poda(Solucion solucion,ArrayList<Maquina> maquinasActuales){
        if(solucion.isEmpty())//si esta vacio mi arreglo solucion retorno false directo,sino nunca arranca
            return false;

        return maquinasActuales.size()>=solucion.size();
    }


    /*
     * Estrategia Greedy:
     * - Los candidatos son las maquinas disponibles, ordenadas de MAYOR a MENOR en base a la cant de piezas que producen.
     * - Estrategia de seleccion: Selecciona de manera iterativa la maquina que produce mas cantidad de piezas para
     *   crubir la cantidad de piezas restantes, añadiendo la mayor cantidad de cada maquina sin superar el objetivo.(12 en este caso)
     * - Se considera solucion valida cuando la suma de las piezas es exactamente el objetivo. (12 en este caso)
     * - Esta estrategia no garantiza una solucion optima, pero permite encontarr una solucion de manera
     *   rapida y eficiente si existe.
     * - El metodo devuelve un String con la combinacion encontrada o "null" si no se alcanzo el objetivo exacto.
     */
    //GREEDY
    //costo solucion 3,ya que considero valido solo 3 candidatos
    public String greedy(int objetivo){
        //declaro mi arreglo solucion
        solucion=new Solucion(objetivo);

        //copiamos arreglo
        ArrayList<Maquina> maquinasOrd=new ArrayList<>(this.maquinas);
        maquinasOrd.sort(Collections.reverseOrder());//ordeno el arreglo de las copias de las maquinas descencdentemente

        while(!maquinasOrd.isEmpty() && !solucion(solucion,objetivo)){
            Maquina candidato=seleccionar(maquinasOrd);
            maquinasOrd.remove(candidato);
            int factible=esFactible(solucion, candidato, objetivo);
            if(factible>0){//factible me da mas de 0 quiere decir que el candidato lo puedo meter alemnos una vez a mi solucion
                solucion.setCostoProcesoTotal(solucion.getCostoProcesoTotal()+1);
                for(int i=0;i<factible;i++){
                    solucion.addMaquina(candidato);
                }
                solucion.restarPiezasRestantes(candidato.getCantPiezas()*factible);
            }
        }
        if(solucion(solucion,objetivo))
            return solucion.solucionGreedy();
        else
            return null;
    }

    private boolean solucion(Solucion solucion,int objetivo){
        return solucion.getPiezasRestantes()==0;//si mis piezas restantes son 0,llegue a mi solucion
    }

    private Maquina seleccionar(ArrayList<Maquina> maquinas){
        return maquinas.get(0);
    }

    //es factible para ahorrarme de hacer el while me fijo cuantas veces entra la maquina dependiendo de las piezas restantes que tenga
    private int esFactible(Solucion solucion,Maquina candidato,int objetivo){
        if(candidato.getCantPiezas()<=solucion.getPiezasRestantes()){//si mi candidato no se pasa de mi piezas restantes a producir
            int vecesArepetir=solucion.getPiezasRestantes()/candidato.getCantPiezas();//calculo cuantas veces entrar con mis piezas restantes
            return vecesArepetir;
        }
        return 0;
    }

    //este es factible lo usaba con el for por cada candidato iterando cuantas veces entre el candidato,mayor complejidad
    /*public boolean esFactible(ArrayList<Maquina> solucion,Maquina candidato,int objetivo){
        int suma=0;
        for(Maquina maquina:solucion){
            suma+=maquina.getCantPiezas();
        }
        return (suma + candidato.getCantPiezas()) <= objetivo;
    }*/

}
