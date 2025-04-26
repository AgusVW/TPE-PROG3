package tp4_grafos;

import java.util.Iterator;
import java.util.HashMap;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap<Integer,LinkedList<Arco<T>>> vertices;

	public GrafoDirigido(){
		this.vertices=new HashMap<>();
	}

	@Override
	public void agregarVertice(int verticeId) {//si el vertice no esta en hash lo agrego
		if(!contieneVertice(verticeId)) {
			LinkedList<Arco<T>> lista = new LinkedList<>();
			this.vertices.put(verticeId,lista);
		}
	}

	@Override
	public void borrarVertice(int verticeId) {//si el vertice esta en la tabla de hash lo borro
		if(contieneVertice(verticeId))
			this.vertices.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(contieneVertice(verticeId1) && contieneVertice(verticeId2)){//verifico que los vertices exista
			Arco<T> arco=new Arco<>(verticeId1,verticeId2,etiqueta);//creo el arco a ingresar
			if (!this.vertices.get(verticeId1).contains(arco)){//verifico que el arco que quiero ingresar no este presente en el vertice
				this.vertices.get(verticeId1).add(arco);
			}
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)){
			Arco<T> arco=new Arco<>(verticeId1,verticeId2,null);
			this.vertices.get(verticeId1).remove(arco);
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)){
			Arco<T> arco=new Arco<>(verticeId1,verticeId2,null);
			return this.vertices.get(verticeId1).contains(arco);
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
			Arco<T> arco=new Arco<>(verticeId1,verticeId2,null);
			int estaArco=this.vertices.get(verticeId1).indexOf(arco);//verifico si esta en alguna posicio el arco y cual
			if(estaArco!=-1)
				return this.vertices.get(verticeId1).get(estaArco);
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		int cantidad=0;
		if(this.vertices.size()==0)
			return cantidad;

		for(LinkedList<Arco<T>> lista:this.vertices.values()){
			cantidad+=lista.size();
		}
		return cantidad;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();//key set me devuelve el conjunto de claves
												//y ese keySet tiene un iterator para devolver
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(contieneVertice(verticeId)){
			LinkedList<Arco<T>> listaArcos=this.vertices.get(verticeId);//traigo la lista de arco que tiene Iterator de tipo Arco
			LinkedList<Integer> listaAdyacentes=new LinkedList<>();//creo mi lista vacia para meter todos los destino de listaArcos
			for(Arco<T> arco:listaArcos){
				listaAdyacentes.add(arco.getVerticeDestino());
			}
			return listaAdyacentes.iterator();//retorno el Iterator de tipo Integer de mi lista de destinos(Adyacentes)
		}
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		if (this.vertices.size()==0)//si no tengo arcos retorno null
			return null;

		LinkedList<Arco<T>> arcosTotales=new LinkedList<>();//creo lista de arcos totales para buscar iterator de todos
		for(LinkedList<Arco<T>> listaArcos:this.vertices.values()){//recorro todas las listas de mis vertices
			arcosTotales.addAll(listaArcos);//a mi lista de retorno le agrego todos los arcos de todos mis vertices
		}
		return arcosTotales.iterator();//retorno el iterator de mi lista de arcos totales de todos los vertices
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(contieneVertice(verticeId)){
			return this.vertices.get(verticeId).iterator();
		}
		return null;
	}

}
