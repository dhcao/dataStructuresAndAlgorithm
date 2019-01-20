package chapterFour;

import java.nio.BufferUnderflowException;

/**
 * 二叉查找树：
 * 左子树的所有项的值均小于根节点，右子树的所有项的值均大于根节点。
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	/**
	 * 节点类
	 *
	 * @param <T>
	 */
	private static class BinaryNode<T> {

		private T element;
		private BinaryNode<T> left;
		private BinaryNode<T> right;

		BinaryNode(T t) {
			this(t, null, null);
		}

		public BinaryNode(T t, BinaryNode<T> lt, BinaryNode<T> rt) {
			element = t;
			left = lt;
			right = rt;
		}
	}

	// 根节点
	private BinaryNode<T> root;

	/**
	 * 构造函数
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * 清空
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * 判断树是否为空：只需要判断根节点是否为空即可。
	 *
	 * @return
	 */
	public Boolean isEmpty() {
		return root == null;
	}

	/**
	 * 是否含有节点x,含有则返回true，没有则返回fales
	 *
	 * @param x
	 * @return
	 */
	public boolean contains(T x) {
		return contains(x, root);
	}

	/**
	 * 寻找最小值
	 *
	 * @return
	 */
	public T findMin() {
		if (isEmpty()) {
			throw new BufferUnderflowException();
		}
		return findMin(root).element;
	}

	/**
	 * 寻找最小值
	 *
	 * @return
	 */
	public T findMax() {
		if (isEmpty()) {
			throw new BufferUnderflowException();
		}
		return findMax(root).element;
	}

	/**
	 * 插入
	 *
	 * @param t
	 */
	public void insert(T t) {
		root = insert(t, root);
	}

	/**
	 * 删除
	 *
	 * @param t
	 */
	public void remove(T t) {
		root = remove(t, root);
	}

	/**
	 * 打印全部
	 */
	public void printTree() {
		// todo
	}


	private BinaryNode<T> remove(T t, BinaryNode<T> root) {
		// todo
		return null;
	}

	private BinaryNode<T> insert(T t, BinaryNode<T> root) {
		// todo
		return null;
	}

	/**
	 * 我们不使用递归，加判断的递归，可以用while循环
	 * @param root
	 * @return
	 */
	private BinaryNode<T> findMax(BinaryNode<T> root) {

		if (root == null) {
			return null;
		}

		while (root.right != null){
			root = root.right;
		}
		return root;
	}

	/**
	 * 我们用递归的方法，遍历所有的左子树，直到最后。
	 * @param root
	 * @return
	 */
	private BinaryNode<T> findMin(BinaryNode<T> root) {
		if(root == null){
			return null;
		}
		if(root.left == null){
			return root;
		}else{
			return findMin(root.left);
		}
	}

	/**
	 * 如果T是空集，那么可以就返回false。否则，存在T处的项是X，那么就可以返回ture，否则对树对左子树或右子树进行一次递归。
	 *
	 * @param x
	 * @param root
	 * @return
	 */
	private boolean contains(T x, BinaryNode<T> root) {

		if (root == null) {
			return false;
		}

		// 判断x是在左子树还是右子树
		int compareResult = x.compareTo(root.element);

		if (compareResult < 0) {
			return contains(x, root.left);
		} else {
			return contains(x, root.right);
		}
	}

	public void printTrees(BinaryNode<T> tb) {
		// todo
	}

}
