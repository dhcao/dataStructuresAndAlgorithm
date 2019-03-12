package chapterFive;

import java.util.Random;

/**
 * 布谷鸟散列算法实现
 * 布谷鸟散列：假设有N个项，我们维护两个分别超过半空的表，且有两个独立的散列函数，可以把每个项分配到每个表中的一个位置。
 * 布谷鸟散列保持不变的是一个项总是会被储存在这两个位置之一。
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class CuckooHashTable<T> {

	// 负载因子
	private static final double MAX_LOAD = 0.4;
	// 重新哈希系数
	private static final int ALLOWED_REHASHES = 1;
	// 默认表大小
	private static final int DEFAULT_TABLES_SIZE = 101;

	private final HashFamily<? super T> hashFunctions;

	// hash算法数目
	private final int numHashFunctions;
	private T[] array;
	// 当前表大小
	private int currentSize;

	public CuckooHashTable(HashFamily<? super T> hf) {
		this(hf, DEFAULT_TABLES_SIZE);
	}

	public CuckooHashTable(HashFamily<? super T> hf, int size) {

		allocateArray(nextPrime(size));
		doClear();
		hashFunctions = hf;
		numHashFunctions = hf.getNumberOfFunctions();
	}

	/**
	 * 清空表
	 */
	public void makeEmpty() {
		doClear();
	}

	/**
	 * 是否包含t
	 *
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		return findPos(t) != -1;
	}

	/**
	 * 计算hash值，利用指定的hash函数
	 *
	 * @param t
	 * @param which 选择hash函数
	 * @return
	 */
	public int myhash(T t, int which) {
		int hashVal = hashFunctions.hash(t, which);
		hashVal %= array.length;
		if (hashVal < 0) {
			hashVal += array.length;
		}
		return hashVal;
	}

	/**
	 * 寻找t
	 *
	 * @param t
	 * @return
	 */
	public int findPos(T t) {
		// 遍历所有的哈希算法，如果该散列位置没有值，则返回-1，若散列位置有值而且等于t，则返回该位置。
		for (int i = 0; i < numHashFunctions; i++) {
			int pos = myhash(t, i);
			if (array[pos] != null && array[pos].equals(t)) {
				return pos;
			}
		}
		return -1;
	}

	/**
	 * 删除t
	 *
	 * @param t
	 * @return
	 */
	public boolean remove(T t) {

		int pos = findPos(t);

		if (pos != -1) {
			array[pos] = null;
			currentSize--;
		}

		return pos != -1;
	}

	/**
	 * 插入t
	 *
	 * @param t
	 * @return
	 */
	public boolean insert(T t) {
		if (contains(t)) {
			return false;
		}

		if (currentSize >= array.length * MAX_LOAD) {
			expand();
		}

		return insertHelper1(t);
	}

	private void expand() {
		rehash((int) (array.length / MAX_LOAD));
	}

	private void rehash() {
		hashFunctions.generateNewFunctions();
		rehash(array.length);
	}

	private void rehash(int newLength) {
		T[] oldArray = array;
		allocateArray(nextPrime(newLength));
		currentSize = 0;

		for (T str : oldArray) {
			if (str != null) {
				insert(str);
			}
		}
	}

	/**
	 * 删除
	 */
	private void doClear() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	/**
	 * 扩容表大小
	 *
	 * @param arraySize
	 */
	private void allocateArray(int arraySize) {
		array = (T[]) new Object[arraySize];
	}

	/**
	 * 插入辅助函数
	 *
	 * @return
	 */

	private int rehashes = 0;
	private Random r = new Random();

	private boolean insertHelper1(T t) {
		final int COUNT_LIMIT = 100;
		while (true) {
			int lastPos = -1;
			int pos;

			for (int count = 0; count < COUNT_LIMIT; count++) {

				for (int i = 0; i < numHashFunctions; i++) {
					pos = myhash(t, i);

					// 如果存在算法能让它插入，则直接插入表中
					if (array[pos] == null) {
						array[pos] = t;
						currentSize++;
						return true;
					}
				}
				// 如果所有的算法得到的hash值都没有空位置，则要挤走一个小朋友。
				int i = 0;
				do {
					pos = myhash(t, r.nextInt(numHashFunctions));
				} while (pos == lastPos && i++ < 5);

				T tmp = array[lastPos = pos];
				array[pos] = t;
				t = tmp;
			}

			if (++rehashes > ALLOWED_REHASHES) {
				expand();
				rehashes = 0;
			} else {
				rehash();
			}

		}
	}

	/**
	 * desc:下一个素数
	 *
	 * @auther: dhcao
	 */
	private static int nextPrime(int n) {

		boolean state = isPrime(n);
		while (!state) {
			state = isPrime(++n);
		}
		return n;
	}

	/**
	 * desc:是不是素数
	 *
	 * @auther: dhcao
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3) {
			return true;
		}
		if (n % 6 != 1 && n % 6 != 5) {
			return false;
		}
		for (int i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}
}
