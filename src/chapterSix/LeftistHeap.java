package chapterSix;

/**
 * 左式堆：
 * 左式堆用于2个堆的合并，保证时间界：O(logN)
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class LeftistHeap<T extends Comparable<? super T>> {

	// 根节点
	private Node<T> root;

	// 节点类
	private static class Node<T> {

		T element;
		Node<T> left;
		Node<T> right;
		int npl;

		public Node(T theElement) {
			this(theElement, null, null);
		}

		public Node(T theElement, Node<T> lt, Node<T> rt) {
			element = theElement;
			left = lt;
			right = rt;
			npl = 0;
		}
	}

	// 构造函数
	public LeftistHeap() {
		root = null;
	}

	/**
	 * 将rhs合并到此
	 *
	 * @param rhs
	 */
	public void merge(LeftistHeap<T> rhs) {
		if (this == rhs) {
			return;
		}
		root = merge(root, rhs.root);
		rhs.root = null;
	}

	/**
	 * 插入
	 *
	 * @param t
	 */
	public void insert(T t) {
		root = merge(new Node<>(t), root);
	}

	/**
	 * 最小值
	 *
	 * @return
	 */
	public T findMin() {
		return root.element;
	}

	/**
	 * 删除最小值
	 *
	 * @return
	 */
	public T deleteMin() {
		if (isEmpty()) {
			throw new RuntimeException();
		}
		T minItem = root.element;
		root = merge(root.left, root.right);
		return minItem;
	}

	/**
	 * 空堆判断
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * 清空堆
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * 合并h1和h2
	 *
	 * @param h1
	 * @param h2
	 * @return
	 */
	private Node<T> merge(Node<T> h1, Node<T> h2) {
		if (h1 == h2) {
			return h2;
		}
		if (h2 == null) {
			return h1;
		}

		// 以根的值小的树为主体
		if (h1.element.compareTo(h2.element) < 0) {
			return merge1(h1, h2);
		} else {
			return merge1(h2, h1);
		}
	}

	/**
	 * 合并，以h1根为根节点
	 *
	 * @param h1
	 * @param h2
	 * @return
	 */
	private Node<T> merge1(Node<T> h1, Node<T> h2) {

		if (h1.left == null) {
			h1.left = h2;
		} else {
			h1.right = merge(h1.right, h2);
			if (h1.left.npl < h1.right.npl) {
				swapChildren(h1);
			}
			h1.npl = h1.right.npl + 1;
		}
		return h1;
	}

	/**
	 * 交换左右子树
	 *
	 * @param t
	 */
	private void swapChildren(Node<T> t) {

		Node<T> tmp = t.left;
		t.left = t.right;
		t.right = tmp;
	}

}
