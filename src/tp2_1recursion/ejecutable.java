package tp2_1recursion;

public class ejecutable {
    public static void main(String[] args) {
        int[] arrOrd={15,23,22,3};
        int[] arrDesord={40,50,20,10};
        //System.out.println(buscarDato(arrOrd,0,2));
        //System.out.println(valorIgualApos(arrOrd,4));
        ordenarPorBurbujeoComun(arrDesord);
        imprimirArreglo(arrDesord);
    }

    // 1. ¿Qué complejidad O tiene? (La complejidad en el peor caso)->O(n)
    // 2. ¿Trae algún problema hacerlo recursivo? ¿Cuál?->que el arreglo tiene que venir ya ordenado
    // 3. ¿Qué cambiaría si la estructura fuera una lista en lugar de un arreglo?->que en vez seguir por index va por nodo
    public static boolean estaOrdenado(int[] arr,int indice){
        if (arr[indice]>arr[indice+1])
            return false;
        else if (indice < arr.length-2)
            return estaOrdenado(arr,indice+1);
        return true;
    }

    //Implemente un algoritmo recursivo para buscar un elemento en un arreglo ordenado
    //ascendentemente.
    public static int buscarDato(int[] arr,int indice,int info){
        if (info<arr[indice])
            return -1;

        if (arr[indice]==info)
            return indice;

        if (indice<arr.length-1)
            return buscarDato(arr,indice+1,info);
        return -1;
    }

    //Implemente un algoritmo recursivo que convierta un número en notación decimal a su
    //equivalente en notación binaria.
    public static String decimalAbinario(int decimal){
        String res="";
        if (decimal>1) {
            int bin=decimal%2;
            decimal = decimal / 2;
            res+=bin;
            res+=decimalAbinario(decimal);
        }
        else if (decimal==1)
            res+="1";

        return res;
    }

    //Dado un arreglo ordenado de números distintos A se desea construir un algoritmo que
    //determine si alguno de los elementos de dicho arreglo contiene un valor igual a la posición en la
    //cuál se encuentra, es decir, A[i] = i.
    public static int valorIgualApos(int[] arr,int indice){
        if(indice>arr.length-1)
            return -1;
        else if(arr[indice]==indice)
            return indice;
        else if(indice<arr.length)
            return valorIgualApos(arr,indice+1);
        return -1;
    }

    //complejida o(n^2)pq recorro sin importa que los doble for
    public static void ordenarPorBurbujeoComun(int[] arr) {
        int i,j,aux=0;
        for ( i=0; i < arr.length - 1; i++) {
            for (j = 0; j < arr.length-i-1;j++){
                if (arr[j] > arr[j + 1]) {
                    aux = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = aux;
                }
            }
        }
    }

    //en el peor caso seria o(n^2) tambien igual que el comun
    //pero en el caso de que este ordenado a la primera seria o(n) pq recorre una sola vez el arreglo
    public static void ordenarBurbujeoCondicional(int[] arr){
        boolean swapped=true;//significa intercambiado
        int j=0;
        int aux;
        while(swapped){
            swapped=false;
            j++;
            for(int i=0;i<arr.length-j;i++){
                if (arr[i]>arr[i+1]){
                    aux=arr[i+1];
                    arr[i+1]=arr[i];
                    arr[i]=aux;
                    swapped=true;
                }
            }
        }
    }

    public static void imprimirArreglo(int [] arr){
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }


}
