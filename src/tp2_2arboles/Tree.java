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
			return this.hasElemPunteroAsignado(this.root,value);
		return false;
	}

	//buscar elemento privado con la raiz ya pasa del publico para cumplir encapsulamiento
	private boolean hasElemPunteroAsignado(TreeNode nodeSeguimiento,int num){
		if (nodeSeguimiento.getValue()>num) {//el num buscado es menor por lo tanto va a la IZQUIERDA
			if (nodeSeguimiento.getLeft()!=null) {
				return hasElemPunteroAsignado(nodeSeguimiento.getLeft(),num);
			}
			else{
				return false;
			}
		}

		else if(nodeSeguimiento.getValue()<num){//el num buscado es menor por lo tanto va a la DERECHA
			if (nodeSeguimiento.getRight()!=null) {
				return hasElemPunteroAsignado(nodeSeguimiento.getRight(),num);
			}
			else{
				return false;
			}
		}
		//si no es ni mayor ni menor quiere decir que el elemento esta
		else
			return true;//quiere decir que es verdadero
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
			return getHeight(this.root);
		return -1;
	}

	//recorro mis ambos lados y veo cual es el maximo entre los dos siempre y lo voy sumando
	//por defecto si tengo un nodo solo mi altura es 1
	private int getHeight(TreeNode node){
		int right=1 ,left=1;//altura de ambos lados siempre es 1 si no es null
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

		if (node.getLeft()!=null) {
			retorno.addAll(getFrontera(node.getLeft()));
		}else if(node.getLeft()==null){//cuando llega a que su izquierda es null se agrega
			retorno.add(node.getValue());
		}

		if (node.getRight()!=null) {
			retorno.addAll(getFrontera(node.getRight()));
		}else if(node.getRight()==null && node.getLeft()!=null){//verifico que su derecha sea null pero que su izquierda no sino se me repiten valores
			retorno.add(node.getValue());
		}

		return retorno;
	}

	public boolean delete(int num){
		if (this.isEmpty())
			return false;

		return delete(this.root,num);
	}

	private boolean delete(TreeNode node,int num){
		if (node.getValue()>num){
			if (node.getLeft()!=null) {
				return delete(node.getLeft(),num);
			}
			else if (node.getLeft()==null){
				return false;
			}
		}
		else if (node.getValue()<num){
			if (node.getRight()!=null) {
				return delete(node.getRight(),num);
			}
			else if (node.getRight()==null){
				return false;
			}
		}
		else if(node.getValue()==num){
			if (node.getLeft()==null && node.getRight()==null) {
				node=null;
				return true;
			}
			else if(node.getLeft()!=null && node.getRight()!=null){
				TreeNode nodoMasDerSubarbIzq=NMDSI(node.getLeft());
				node.setValue(nodoMasDerSubarbIzq.getValue());
				return true;
			}
			else if(node.getLeft()!=null){
				node=node.getLeft();
				return true;
			}
			else{
				node=node.getRight();
				return true;
			}
		}
		return false;
	}

	private TreeNode NMDSI(TreeNode node) {//buscar nodo mas derecho de mi subarbol izquierdo
		TreeNode retorno=null;
		if (node.getRight()!=null){
			return NMDSI(node.getRight());
		}
		if (node.getRight()==null){
			retorno=node;
			if (node.getLeft()!=null){
				node=node.getLeft();
			}else{
				node=null;
			}
		}
		return retorno;
	}
	
}
