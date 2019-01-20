package chapterThree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 自己实现一个list，它拥有以下功能
 * 1.保持基础数组，数组的容量，已经储存当前项。
 * 2.提供机制以改变数组的容量，通过获得一个新数组，将老数组拷贝到新数组，允许虚拟机回收老数组。
 * 3.get和set实现
 * 4.基本实现：remove，size，isEmpty，clear等
 * 5.提供一个实现iterator等类。用来遍历！
 *
 * @param <T>
 */
public class MyArrayList<T> implements Iterable<T> {
	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>) new ArrayListIterator();
	}

	@Override
	public void forEach(Consumer<? super T> action) {

	}

	@Override
	public Spliterator<T> spliterator() {
		return null;
	}

	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * list大小
	 */
	private int theSize;
	/**
	 * 存放数据的数组
	 */
	private T[] theItems;

	public MyArrayList() {
		doClear();
	}

	/**
	 * 提供清空方法
	 */
	public void clear() {
		doClear();
	}

	/**
	 * 1.将list大小标记为0
	 * 2.将数组大小修改为默认
	 */
	private void doClear() {
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	public int size() {
		return theSize;
	}

	public T get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return theItems[idx];
	}

	/**
	 * 替换指定位置的数据
	 *
	 * @param idx
	 * @param newVal
	 * @return
	 */
	public T set(int idx, T newVal) {
		if (idx < 0 || idx >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		T old = theItems[idx];
		theItems[idx] = newVal;
		return old;
	}

	/**
	 * 1. 如果指定大小比实际大小要小，则不更改。
	 * 2. 否则，定义一个指定大小的数组，然后将内容转过来。
	 *
	 * @param newCapacity 数组大小
	 */
	public void ensureCapacity(int newCapacity) {
		if (newCapacity < theSize) {
			return;
		}
		T[] old = theItems;
		theItems = (T[]) new Object[newCapacity];
		for (int i = 0; i < size(); i++) {
			theItems[i] = old[i];
		}

	}

	/**
	 * 增加数据
	 *
	 * @param t
	 * @return
	 */
	public boolean add(T t) {
		add(size(), t);
		return true;
	}

	/**
	 * 添加参数
	 * 1.判断数组长度，如果list大小已经超过指定大小，则将打下扩容为原来的2倍。
	 * 2.如果中间插入，则整体右移
	 *
	 * @param idx
	 * @param t
	 */
	public void add(int idx, T t) {
		if (theItems.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}
		for (int i = theSize; i > idx; i--) {
			theItems[i] = theItems[i - 1];
		}
		theItems[idx] = t;
		theSize++;
	}

	/**
	 * 整体左移
	 *
	 * @param idx
	 * @return
	 */
	public T remove(int idx) {
		T removedItem = theItems[idx];
		for (int i = idx; i < size(); i++) {
			theItems[i] = theItems[i + 1];
		}
		theSize--;
		return removedItem;
	}

	private class ArrayListIterator implements Iterable<T> {

		private int current = 0;

		public boolean hanNext() {
			return current < size();
		}

		public T next() {
			if (!hanNext()) {
				throw new NoSuchElementException();
			}
			return theItems[current++];
		}

		public void remove() {
			MyArrayList.this.remove(--current);
		}

		public ArrayListIterator() {
			super();
		}

		@Override
		public Iterator<T> iterator() {
			return null;
		}
	}

}
