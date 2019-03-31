package chapterSix;

import java.util.Arrays;

/**
 * 二叉堆/堆
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class BinaryHeap<T extends Comparable<? super T>> {

	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;
	private T[] array;

	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	public BinaryHeap(int capacity) {
		array = (T[]) new Comparable[capacity];
	}

	/**
	 * 构造
	 *
	 * @param items
	 */
	public BinaryHeap(T[] items) {

		currentSize = items.length;

		array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];

		int i = 1;
		for (T item : items) {
			array[i++] = item;
		}
		buildHeap();

	}

	/**
	 * 插入：上浮
	 *
	 * @param t
	 */
	public void insert(T t) {
		if (currentSize == array.length - 1) {
			enlargeArray(array.length * 2 + 1);
		}

		int hole = ++currentSize;
		for (array[0] = t; t.compareTo(array[hole / 2]) < 0; hole /= 2) {
			array[hole] = array[hole / 2];
		}
		array[hole] = t;
	}

	/**
	 * 返回最小值
	 *
	 * @return
	 */
	public T findMix() {
		return array[1];
	}

	/**
	 * 删除最小值（根节点）
	 *
	 * @return
	 */
	public T deleteMin() {

		if (isEmpty()) {
			throw new RuntimeException();
		}

		T minItem = findMix();

		array[1] = array[currentSize--];
		percolateDown(1);
		return minItem;
	}

	/**
	 * 判断是否为空
	 *
	 * @return
	 */
	public boolean isEmpty() {
		if (array[1] == null) {
			return true;
		}
		return false;
	}

	public void makeEmpty() {
		array = (T[]) new Comparable[array.length];
	}

	/**
	 * 下滤
	 *
	 * @param hole
	 */
	private void percolateDown(int hole) {
		int child;
		T tmp = array[hole];

		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {
				child++;
			}
			if (array[child].compareTo(tmp) < 0) {
				array[hole] = array[child];
			} else {
				break;
			}
		}
		array[hole] = tmp;
	}

	/**
	 * 构建
	 */
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--) {
			percolateDown(i);
		}
	}

	/**
	 * 扩容
	 *
	 * @param newSize
	 */
	private void enlargeArray(int newSize) {

		T[] oldArray = array;
		array = Arrays.copyOf(oldArray, newSize);
	}

}
