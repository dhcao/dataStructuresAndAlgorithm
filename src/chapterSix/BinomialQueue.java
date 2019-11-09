package chapterSix;

/**
 * 二项队列的实现
 * 关键操作：deleteMin，merge。
 * deleteMin需要快速找到最小值。
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class BinomialQueue<T extends Comparable<? super T>> {

	private static final int DEFAULT_TREES = 1;
	private int currentSize;
	private Node<T>[] theTrees;

	private static class Node<T>{

		T element;
		Node<T> leftChild;
		Node<T> nextSibling;

		public Node(T element) {
			this(element,null,null);
		}

		public Node(T element, Node<T> leftChild, Node<T> nextSibling) {
			this.element = element;
			this.leftChild = leftChild;
			this.nextSibling = nextSibling;
		}
	}




}
