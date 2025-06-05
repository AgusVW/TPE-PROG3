package srcTPE;

import java.util.ArrayList;

public class Solucion {
    private ArrayList<Maquina> maquinas;
    private int costoProcesoTotal;
    private int costoSolucion;
    private int piezasProducidas;
    private int piezasRestantes;

    public Solucion(int piezasAproducir) {
        this.maquinas = new ArrayList<>();
        this.costoProcesoTotal = 0;
        this.costoSolucion = 0;
        this.piezasProducidas = piezasAproducir;
        this.piezasRestantes = piezasAproducir;
    }

    public ArrayList<Maquina> getMaquinas(){
        return new ArrayList<>(this.maquinas);
    }

    public int size(){
        return this.maquinas.size();
    }

    public boolean isEmpty(){
        return this.maquinas.isEmpty();
    }

    public void addAll(ArrayList<Maquina> maquinas){
        this.maquinas.addAll(maquinas);
    }

    public void limpiarSolucion(){
        this.maquinas.clear();
    }

    public void addMaquina(Maquina maquina){
        this.maquinas.add(maquina);
    }

    public int getCostoProcesoTotal() {
        return costoProcesoTotal;
    }

    public void setCostoProcesoTotal(int costoProcesoTotal) {
        this.costoProcesoTotal = costoProcesoTotal;
    }

    public int getCostoSolucion() {
        return costoSolucion;
    }

    public void setCostoSolucion(int costoSolucion) {
        this.costoSolucion = costoSolucion;
    }

    public int getPiezasProducidas() {
        return piezasProducidas;
    }

    public void setPiezasProducidas(int piezasProducidas) {
        this.piezasProducidas = piezasProducidas;
    }

    public int getPiezasRestantes() {
        return piezasRestantes;
    }

    public void restarPiezasRestantes(int piezasRestantes) {
        this.piezasRestantes -= piezasRestantes;
    }

    public String solucionBacktracking(){
        return "Backtracking" +
                "\nSolucion Obtenida: " + this.maquinas +
                "\nCantidad de piezas: " + this.piezasProducidas +
                "\nCosto ProcesoTotal(estado): " + this.costoProcesoTotal +
                "\nCosto Solucion: " + this.costoSolucion +
                "\nCantidad de maquinas en Marcha: " + this.size();
    }

    public String solucionGreedy(){
        return "Greedy" +
                "\nSolucion Obtenida: " + this.maquinas +
                "\nCantidad de piezas: " + this.piezasProducidas +
                "\nCosto ProcesoTotal(candidatos): " + this.costoProcesoTotal +
                //"\nCosto Solucion: " + this.costoSolucion +
                "\nCantidad de maquinas en Marcha: " + this.size();
    }
}
