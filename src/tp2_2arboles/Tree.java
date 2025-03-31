package tp2_2arboles;

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
		return this.root!=null;
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

	public int getHeight(){
		if (this.root!=null)
			return getHeight(this.root);
		return -1;
	}

	//recorro mis ambos lados y veo cual es el maximo entre los dos siempre y lo voy sumando
	//por defecto si tengo un nodo solo mi altura es 1
	private int getHeight(TreeNode node){
		int right=1 ,left=1;
		if (node.getRight()!=null)
			right+=getHeight(node.getRight());

		if (node.getLeft()!=null)
			left+=getHeight(node.getLeft());

		return Math.max(right,left);
	}

	
}
