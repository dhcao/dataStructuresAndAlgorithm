package chapterThree;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

	private int theSize;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;


	@Override
	public Iterator<T> iterator() {
		return null;
	}

	/**
	 * 这个内部类就有意思了，它有三个数据，分别是数据和前节点，后节点。
	 * 前后节点都直接使用node类，这样的好处显而易见。
	 * 我们可以在new一个新节点（就是插入一个数据的时候），为它明确的指定前节点和后节点。
	 * 这样可以方便的将新节点记录，并完成它的数据结构（记录前后节点和数据）。
	 * @param <T>
	 */
	public static class Node<T> {

		public T data;
		public Node<T> prev;
		public Node<T> next;

		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}
	}

	public MyLinkedList(){
		doClear();
	}

	public void clear(){

	}
	private void doClear() {

	}

	public int size(){
		return theSize;
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public boolean add(T x){
		add(size(),x);
		return true;
	}

	public void add(int idx,T x){
		addBefore(getNode(idx,0,size()),x);
	}

	public T get(int idx){
		return getNode(idx).data;
	}

	public T set(int idx,T newVal){
		Node<T> p = getNode(idx);
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;

	}

	public void addBefore(Node<T> p,T x){

	}

	private Node<T> getNode(int idx) {
		return null;
	}

	private Node<T> getNode(int idx,int lower,int upper){
		return null;
	}
}
