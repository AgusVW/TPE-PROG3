package tp1;
import java.util.Iterator;

public class MyIterable<T> implements Iterator<T> {
    private Node<T> puntero;

    public MyIterable(Node<T> puntero){
        this.puntero=puntero;
    }

    @Override
    public boolean hasNext() {
        return this.puntero!=null;
    }

    @Override
    public T next() {
        T info=this.puntero.getInfo();
        this.puntero=this.puntero.getNext();
        return info;
    }
}
