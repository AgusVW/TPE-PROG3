package tp2_2arboles;

public class testing {
    public static void main(String[] args){
        Tree arbol=new Tree();
        arbol.add(10);
        arbol.add(8);
        arbol.add(19);
        arbol.add(3);
        arbol.add(9);
        arbol.add(2);
        arbol.add(4);
        arbol.add(17);
        arbol.add(20);
        arbol.add(18);
        //arbol.add(21);
        //arbol.add(13);
        arbol.methodPrin(1);
        System.out.println("\n");
        //System.out.println(arbol.delete(10));

        //arbol.methodPrin(1);
        //System.out.println(arbol.getHeight());
        //arbol.printFrontera();
        //System.out.println(arbol.getMinElem());
        //System.out.println(arbol.isEmpty());
        System.out.println(arbol.getSumatoria());
    }
}
