package chapterFour;

import java.nio.BufferUnderflowException;

/**
 * 二叉查找树：
 * 左子树的所有项的值均小于根节点，右子树的所有项的值均大于根节点。
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	/**
	 * 节点类
	 * @param <T>
	 */
	private static class BinaryNode<T>{

		private T element;
		private BinaryNode<T> left;
		private BinaryNode<T> right;

		BinaryNode(T t){
			this(t,null,null);
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
	public BinarySearchTree (){
		root = null;
	}

	/**
	 * 清空
	 */
	public void makeEmpty(){
		root = null;
	}

	/**
	 * 判断树是否为空：只需要判断根节点是否为空即可。
	 * @return
	 */
	public Boolean isEmpty(){
		return root == null;
	}

	/**
	 * 是否含有节点x
	 * @param x
	 * @return
	 */
	public boolean contains(T x){
		return contains(x,root);
	}

	/**
	 * 寻找最小值
	 * @return
	 */
	public T findMin(){
		if(isEmpty()){
			throw new BufferUnderflowException();
		}
		return findMin(root).element;
	}

	/**
	 * 寻找最小值
	 * @return
	 */
	public T findMax(){
		if(isEmpty()){
			throw new BufferUnderflowException();
		}
		return findMax(root).element;
	}

	/**
	 * 插入
	 * @param t
	 */
	public void insert(T t){
		root = insert(t,root);
	}

	/**
	 * 删除
	 * @param t
	 */
	public void remove(T t){
		root = remove(t,root);
	}

	/**
	 * 打印全部
	 */
	public void printTree(){
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

	private T findMax(BinaryNode<T> root) {
		// todo
		return null;
	}


	private T findMin(BinaryNode<T> root) {
		//todo
		return null;
	}

	// todo
	private boolean contains(T x, BinaryNode<T> root) {
		// todo
		return Boolean.parseBoolean(null);
	}

	public void printTrees(BinaryNode<T> tb){
		// todo
	}

}
