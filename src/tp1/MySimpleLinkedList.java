package tp1;
import java.util.Iterator;

public class MySimpleLinkedList<T extends Comparable<T>>  implements Iterable<T> {
	
	private Node<T> first;
	private int size;
	
	public MySimpleLinkedList() {
		this.first = null;
		this.size=0;
	}
	
	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info,null);
		tmp.setNext(this.first);
		this.first = tmp;
		size++;
	}
	
	public T extractFront() {
		if(first!=null){
			T tmp = first.getInfo();
			first = first.getNext();
			size--;
			return tmp;
		}
		return null;
	}

	public boolean isEmpty() {
		return first==null;
	}
	
	public T get(int index) {
		if (index<=size && index>0){
			Node<T> tmp=first;
			int ini=1;
			while(ini<index){
				tmp=tmp.getNext();
				ini++;
			}
			return tmp.getInfo();
		}
		return null;
	}

	public int getPorInfo(Integer info){
		int retorno=-1;
		if (info!=null && size!=0){
			Node<T> tmp=first;
			int ini=1;
			while(tmp.getInfo()!=info && ini<size){
				tmp=tmp.getNext();
				ini++;
			}
			if (tmp.getInfo()==info)
				retorno = ini;
		}
		return retorno;
	}
	
	public int size() {
		return this.size;
	}
	
	@Override
	public String toString() {
		String retorno="[ ";
		if (!isEmpty()) {
			retorno += first.getInfo();
			Node<T> tmp = first;
			for (int i = 1; i < size; i++) {
				tmp = tmp.getNext();
				retorno += "," + tmp.getInfo();
			}
		}
		retorno+=" ]";
		return retorno;
	}

	public void insertFound(T info){
		Node<T> nuevo=new Node<>(info,null);
		if (size!=0){
			//Node<T> tmp=first;
			Node<T> anterior=first;
			while(anterior.getNext()!=null){
				anterior=anterior.getNext();
			}
			anterior.setNext(nuevo);
			size++;
		}else{
			this.insertFront(info);
		}
	}

	public T extractIndex(int index){
		if (index<=size && index>=0){
			if (size==1 && index==1){
				return extractFront();
			}
			Node<T> eliminar=first;
			Node<T> anterior=null;
			T info=null;
			for(int i=1;i<index;i++){
				if (i==index-1)
					anterior=eliminar;

				eliminar=eliminar.getNext();
				info=eliminar.getInfo();
			}
			anterior.setNext(eliminar.getNext());
			size--;
			return info;
		}
		return null;
	}

	public Iterator<T> iterator(){
		return new MyIterable<T>(first);
	}

	public void insertOrdened(T info){
		Node<T> insert=new Node<>(info,null);
		if (this.first==null)
			this.first=insert;
		else{
			if (this.first.getInfo().compareTo(info)>=0){
				insert.setNext(first);
				this.first=insert;
			}else{
				Node<T> tmp=this.first;
				Node<T> anterior=null;
				while (tmp != null && tmp.getInfo().compareTo(insert.getInfo()) < 0) {
					anterior=tmp;
					tmp=tmp.getNext();
				}
				if (tmp!=null)
					insert.setNext(tmp);
				anterior.setNext(insert);
			}
		}
		size++;
	}

}
