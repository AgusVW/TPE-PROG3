package tp2_2arboles;
//ej4
/*
Se posee un árbol binario (no de búsqueda), donde los nodos internos están vacíos, mientras
que las hojas tienen valores enteros. Se debe implementar un método que recorra el árbol y
coloque valores en los nodos vacíos (los nodos internos). El valor de cada nodo interno debe ser
igual al valor de su hijo derecho, menos el valor de su hijo izquierdo. En caso de que el nodo
tenga un solo hijo, el valor del hijo faltante se reemplaza por un 0. Por ejemplo, tomando como
entrada el árbol de la izquierda, el árbol resultante debería quedar con los mismos valores que el
de la derecha
*/

public class TreeReplaceValuesNull {
    private TreeNode root;

    public TreeReplaceValuesNull() {
        this.root = null;
    }


    public void replaceInternalChildren(TreeNode root){
        if (root==null)//retornaria si llega al final
            return;

        if (root.getLeft()==null && root.getRight()==null)//si no tiene ni siquiera un hijo retorna
            return;

        replaceInternalChildren(root.getLeft());//recorro toda mi izquierda
        replaceInternalChildren(root.getRight());//recorro toda mi derecha

        int valueRight=0,valueLeft=0;//inicio mis dos valores siempre en 0

        if (root.getRight()!=null)//verifico en ambos if si tengo hijo derecho e izquierdo
            valueRight=root.getRight().getValue();

        if (root.getLeft()!=null)
            valueLeft=root.getLeft().getValue();

        root.setValue(valueRight-valueLeft);//edito mi valor y empezaria a retornar todo
    }
}
