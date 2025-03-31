package tp1;

import java.util.Iterator;

public class mainn {
    public static void main(String[] args) {
        MySimpleLinkedList<Integer> lista1=new MySimpleLinkedList<>();
        MySimpleLinkedList<Integer> lista2=new MySimpleLinkedList<>();
        lista1.insertFront(70);
        lista1.insertFront(40);
        lista1.insertFront(10);
        lista1.insertFront(10);

        //System.out.println(lista.extractFront());
        lista2.insertFront(80);
        lista2.insertFront(40);
        lista2.insertFront(20);
        lista2.insertFront(8);
        //System.out.println(lista1.get(3));
        //System.out.println(lista.extractIndex(3));
        //System.out.println(lista.toString());
        //System.out.println(lista1.getPorInfo(450));
        System.out.println("\t");
        //lista1.imprimirLista(lista1);
        MySimpleLinkedList<Integer> listaOrdenada=crearListaConElementoDeLaPrimeraAusentesEnLaSegunda(lista1,lista2);
        imprimirLista(listaOrdenada);

    }

    public static MySimpleLinkedList<Integer> crearListaOrdenadaConAmbasDesordenadas(MySimpleLinkedList<Integer> lista1,MySimpleLinkedList<Integer> lista2){
        MySimpleLinkedList<Integer> retorno=new MySimpleLinkedList<>();
        Iterator<Integer> it1=lista1.iterator();
        while(it1.hasNext()){
            Integer info1=it1.next();
            Iterator<Integer> it2=lista2.iterator();
            while(it2.hasNext()){
                Integer info2=it2.next();
                if (info1.equals(info2)) {
                    retorno.insertFront(info1);
                    break;
                }
            }
        }
        return retorno;
    }

    public static MySimpleLinkedList<Integer> crearListaConElementoDeLaPrimeraAusentesEnLaSegunda(MySimpleLinkedList<Integer> lista1,MySimpleLinkedList<Integer> lista2){
        MySimpleLinkedList<Integer> retorno=new MySimpleLinkedList<>();
        Iterator<Integer> it1=lista1.iterator();
        while(it1.hasNext()){
            Integer info1=it1.next();//50 30
            if(lista2.getPorInfo(info1)==-1)
                retorno.insertOrdened(info1);
        }
        return retorno;
    }

    public static void imprimirLista(MySimpleLinkedList<Integer> lista){
        Iterator<Integer> it=lista.iterator();
        while(it.hasNext()){
            int info=it.next();
            System.out.println(info);
        }
    }
}
