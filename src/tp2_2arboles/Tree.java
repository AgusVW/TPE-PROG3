package tp2_2arboles;

import java.util.ArrayList;

public class Tree { //arbol

	private TreeNode root;//raiz
	
	public Tree() {
		this.root = null;
	}
	
	public void add(Integer value) {//este metodo verifica que la info no sea null y llama al metodo privado con root
		if (this.root == null)
			this.root = new TreeNode(value);
		else
			this.add(this.root,value);
	}
	
	private void add(TreeNode actual, Integer value) {//metodo privado que arranca con la raiz para evitar malas inserciones
		if (actual.getValue() > value) {
			if (actual.getLeft() == null) { 
				TreeNode temp = new TreeNode(value);
				actual.setLeft(temp);
			} else {
				add(actual.getLeft(),value);
			}
		} else if (actual.getValue() < value) {
			if (actual.getRight() == null) { 
				TreeNode temp = new TreeNode(value);
				actual.setRight(temp);
			} else {
				add(actual.getRight(),value);
			}
		}
	}

	//traigo la raiz si no es null
	public Integer getRoot(){
		if (this.root!=null)
			return this.root.getValue();
		return null;
	}

	//verifico si mi arbol esta vacio o no
	public boolean isEmpty(){
		return this.root==null;
	}

	//metodo de buscar elemento verificando si el arbol actual su raiz no es null
	public boolean hasElem(int value){
		if (this.root!=null)
			return this.hasElem(this.root,value);
		return false;
	}

	//buscar elemento privado con la raiz ya pasa del publico para cumplir encapsulamiento
	private boolean hasElem(TreeNode nodeSeguimiento, int num){
		if (nodeSeguimiento.getValue()>num) {//el num buscado es menor por lo tanto va a la IZQUIERDA
			if (nodeSeguimiento.getLeft()!=null) {
				return hasElem(nodeSeguimiento.getLeft(),num);
			}
			else{
				return false;
			}
		}

		else if(nodeSeguimiento.getValue()<num){//el num buscado es menor por lo tanto va a la DERECHA
			if (nodeSeguimiento.getRight()!=null) {
				return hasElem(nodeSeguimiento.getRight(),num);
			}
			else{
				return false;
			}
		}
		//si no es ni mayor ni menor quiere decir que el elemento esta
		else
			return true;//quiere decir que es verdadero
	}

	public boolean delete(int num){
		if (this.isEmpty() || !this.hasElem(num)) {
			return false;
		}

		TreeNode raiz=delete(this.root,num);
		return raiz!=null;
	}

	private TreeNode delete(TreeNode node,int num){
		if (node==null)// si nodo es null es porque pase de largo todo y empiezo a retornar
			return null;

		if (node.getValue()>num){//mi nodo que busco esta a la izquierda
			node.setLeft(delete(node.getLeft(),num));//voy seteando recursivamente si se llegar a encontrar el nodo buscado
		}
		else if(node.getValue()<num){//sucede lo mismo que arriba pero para la derecha
			node.setRight(delete(node.getRight(),num));
		}
		else{//aca ya si no es ni mayor ni menor o null, quiere decir que es ==
			if (node.getLeft()==null && node.getRight()==null){//nodo hoja retorno null
				return null;
			}
			else if(node.getLeft()==null){//mi izquierda es null entonces mi nodo padre se engancha con mi derecha
				return node.getRight();
			}
			else if(node.getRight()==null){//lo mismo que el if de arribar pero engancho mi padre con mi izquierda
				return node.getLeft();
			}
			else{//mi nodo a eliminar tiene 2 hijos
				TreeNode nodoMasDerechoSubArbolIzquierdo=NMDSI(node.getLeft());//busco mi mas derecho de mi izquierda
				node.setValue(nodoMasDerechoSubArbolIzquierdo.getValue());//seteo el valor de mi nodo parado para no perder mi derecha e izquierda
				node.setLeft(delete(node.getLeft(),nodoMasDerechoSubArbolIzquierdo.getValue()));//ahora elimino mi valor repetido partiendo de mi izquierda
			}
		}
		return node;//retorno nodo para verificar en el metodo publico que no es null
	}

	private TreeNode NMDSI(TreeNode node) {//buscar nodo mas derecho de mi subarbol izquierdo
		if (node.getRight()!=null){
			return NMDSI(node.getRight());
		}

		return node;
	}

	//metodo general para poder imprimir de 3 maneras
	public void methodPrin(int select){
		if (select==1)
			printPreOrder(this.root);

		else if (select==2)
			printPosOrder(this.root);

		else if (select==3)
			printEnOrder(this.root);

		else
			System.out.println("Seleccione 1.preOrder, 2.posOrder, 3.enOrder");
	}

	//imprimo tocando los nodos de izquierda
	private void printPreOrder(TreeNode node){
		if (node==null) {
			System.out.print("-, ");
			return;
		}

		System.out.print(node.getValue()+", ");
		printPreOrder(node.getLeft());
		printPreOrder(node.getRight());
	}

	//imprimo tocando los nodos de derecha
	private void printPosOrder(TreeNode node){
		if (node==null) {
			System.out.print("-, ");
			return;
		}

		printPosOrder(node.getLeft());
		printPosOrder(node.getRight());
		System.out.print(node.getValue()+", ");
	}

	//imprimo tocando los nodos de abajo
	private void printEnOrder(TreeNode node){
		if (node==null) {
			System.out.print("-, ");
			return;
		}

		printEnOrder(node.getLeft());
		System.out.print(node.getValue()+", ");
		printEnOrder(node.getRight());
	}

	//metodo de obtener el maximo verificando si mi raiz es null
	public int getMaxElem(){
		if (this.root!=null)
			return getMaxElem(this.root);

		return 0;
	}

	//metodo privado para buscar mi max mientras mi derecha no sea null
	private int getMaxElem(TreeNode node){
		if (node.getRight()!=null)
			return getMaxElem(node.getRight());

		return node.getValue();
	}

	//metodo de obtener el minimo verificando si mi raiz es null
	public int getMinElem(){
		if (this.root!=null)
			return getMinElem(this.root);

		return 0;
	}

	//metodo privado para buscar mi min mientras mi izquierda no sea null
	private int getMinElem(TreeNode node){
		if (node.getLeft()!=null)
			return getMinElem(node.getLeft());

		return node.getValue();
	}

	public int getHeight(){
		if (this.root!=null)
			return getHeight(this.root)-1;
		return -1;
	}

	//recorro mis ambos lados y veo cual es el maximo entre los dos siempre y lo voy sumando
	//por defecto si tengo un nodo solo mi altura es 1
	private int getHeight(TreeNode node){
		int right=1 ,left=1;//altura de ambos lados siempre es 0 si no es null
		if (node.getRight()!=null)
			right+=getHeight(node.getRight());

		if (node.getLeft()!=null)
			left+=getHeight(node.getLeft());

		return Math.max(right,left);//retorna el maximo de ambos valores
	}

	//imprimo la rama mas larga viendo si no es null mi raiz
	public void printBranchLongest(){
		if (this.root!=null)
			System.out.println(getLongestBranch(this.root).toString());
		else
			System.out.println("null");
	}

	//metodo recursivo para buscar la rama mas larga recorro de arriba hacia abajo
	private ArrayList<Integer> getLongestBranch(TreeNode node){
		ArrayList<Integer> left=new ArrayList<>();//creo ambos lados en listas
		ArrayList<Integer> right=new ArrayList<>();

		left.add(node.getValue());//siempre me añado cuando estoy en mi propia altura
		if (node.getLeft()!=null) {
			left.addAll(getLongestBranch(node.getLeft()));
		}

		right.add(node.getValue());//luego de todo el lado izquierdo paso al derecho
		if (node.getRight()!=null) {
			right.addAll(getLongestBranch(node.getRight()));
		}

		if (left.size()>right.size())//la lista de mayor tamaño va a ser la que se va agregando recursivamente
			return left;
		else
			return right;
	}

	//imprimo hojas
	public void printFrontera(){
		if (this.root!=null)
			System.out.println(getFrontera(this.root).toString());
		else{
			System.out.println("null");
		}
	}

	//metodo para obtener todo los hijos externos(hojas)
	private ArrayList<Integer> getFrontera(TreeNode node){
		ArrayList<Integer> retorno=new ArrayList<>();

		if (node.getLeft()!=null) {//si mi izquierda es desigual a null sigo
			retorno.addAll(getFrontera(node.getLeft()));
		}

		if(node.getLeft()==null && node.getRight()==null){//cuando su izquierda y derecha es null se agrega
			retorno.add(node.getValue());
			return retorno;//si veo que ambos son null ya no tiene sentido verificar mi derecha en el if de abajo
		}

		if (node.getRight()!=null) {//si mi derecha es desigual a null sigo
			retorno.addAll(getFrontera(node.getRight()));
		}

		return retorno;
	}

	//imprimo los numeros de cierto nivel de profundidad
	//si el nivel es menor o mayor a la altura retorna o si el arbol esta vacio
	public void printElemAtLevel(int nivel){
		if (this.isEmpty() || nivel>this.getHeight() || nivel<0) {
			System.out.println("El arbol esta vacio o el nivel solicitado es menor o pasa la altura del arbol");
			return;
		}

		System.out.println(getElemAtLevel(this.root,nivel,0).toString());
	}

	private ArrayList<Integer> getElemAtLevel(TreeNode node,int nivel,int nivelActual){
		ArrayList<Integer> retorno=new ArrayList<>();
		if (nivel==0){//si el nivel es 0 significa que solamente se va a imprimir la raiz y retorna
			retorno.add(node.getValue());
			return retorno;
		}

		if (nivel!=nivelActual){//si el nivel actual es != a nivel buscado quiere decir que tengo que seguir bajando
			if (node.getLeft()!=null)
				retorno.addAll(getElemAtLevel(node.getLeft(),nivel,nivelActual+1));

			if (node.getRight()!=null)
				retorno.addAll(getElemAtLevel(node.getRight(),nivel,nivelActual+1));
		}
		else{//en este else se va a significar que el nivel ya no es != entonces es el nivel buscado
			retorno.add(node.getValue());
		}

		return retorno;
	}//TERMINA EJERCICIO 1



	//EJERCICIO 2
	public int getSumatoria(){
		if (this.isEmpty())
			return -1;

		return getSumatoria(this.root);
	}

	private int getSumatoria(TreeNode node){//sumatoria de hijos internos
		int contador=0;//arranco contador 0
		if (node.getLeft()!=null || node.getRight()!=null){//si veo que tengo un hijo almenos me sumo al contador
			contador+=node.getValue();
		}
		if (node.getLeft()!=null){//recorro primero todo mi izquierda y me lo sumo a mi
			contador+=getSumatoria(node.getLeft());
		}
		if (node.getRight()!=null){//luego recorro toda mi derecha y me sumo nuevamente a mi
			contador+=getSumatoria(node.getRight());
		}

		return contador;//retorno el contador total de hijos interno(sumatoria)
	}



	//EJERCICIO 3 imprimir hojas mayor a un valor
	public void printFronteraMayorA(int num){
		if (this.isEmpty())
			System.out.println("el arbol esta vacio");
		else
			System.out.println(getFronteraMayorA(this.root,num).toString());
	}

	private ArrayList<Integer> getFronteraMayorA(TreeNode node,int num){
		ArrayList<Integer> retorno=new ArrayList<>();

		if(node.getLeft()!=null){
			retorno.addAll(getFronteraMayorA(node.getLeft(),num));
		}
		if(node.getRight()==null && node.getLeft()==null && node.getValue()>num){//mismo que el print hojas pero
			retorno.add(node.getValue());										// agrego la otra condicion si hoja es > a numero
			return retorno;
		}
		if(node.getRight()!=null){
			retorno.addAll(getFronteraMayorA(node.getRight(),num));
		}

		return retorno;
	}



	
}
