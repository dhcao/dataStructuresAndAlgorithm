package chapterFive;

/**
 * 散列表实现方法之二：探测散列表法
 * 线性探测法：在遇到冲突的时候，探测h+i位置，直到找到不冲突的位置进行插入。h为散列的位置，i为探测次数，1，2，3，4。
 * 平方探测法：在遇到冲突的时候，探测h+i^2位置，直到找到不冲突的位置进行插入。
 *
 * @auther: dhcao
 */
public class QuadraticProbingHashTable<T> {

	private static class HashEntry<T> {
		public T element;
		public boolean isActive;

		public HashEntry(T e) {
			this(e, true);
		}

		public HashEntry(T e, boolean i) {
			element = e;
			isActive = i;
		}
	}

	private static final int DEFAULT_TABLE_SIZE = 11;
	private HashEntry<T>[] array;
	private int currentSize;

	/**
	 * desc: 构造函数
	 *
	 * @auther: dhcao
	 */
	public QuadraticProbingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public QuadraticProbingHashTable(int size) {
		allocateArray(size);
		makeEmpty();
	}

	/**
	 * desc: 清空表
	 *
	 * @auther: dhcao
	 */
	public void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	/**
	 * desc: 是否包含x
	 *
	 * @auther: dhcao
	 */
	public boolean contains(T x) {
		int currentPos = findPos(x);
		return isActive(currentPos);
	}

	/**
	 * desc: 插入
	 *
	 * @auther: dhcao
	 */
	public void insert(T x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			return;
		}
		array[currentPos] = new HashEntry<>(x, true);

		if (currentSize > array.length / 2) {
			rehash();
		}
	}

	/**
	 * desc: 删除
	 *
	 * @auther: dhcao
	 */
	public void remove(T x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
		}
	}

	/**
	 * desc: 扩容
	 *
	 * @auther: dhcao
	 */
	private void allocateArray(int arraySize) {
		array = new HashEntry[nextPrime(arraySize)];
	}

	/**
	 * desc: 是否未被删除
	 *
	 * @auther: dhcao
	 */
	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}

	/**
	 * desc: 平方探测法
	 *
	 * @auther: dhcao
	 */
	private int findPos(T x) {
		int offset = 1;
		int currentPos = myhash(x);

		while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
			currentPos += offset;
			offset = ++offset * offset; // 平方探测
			if (currentPos >= array.length) {
				currentPos -= array.length;
			}
		}

		return currentPos;
	}

	private void rehash() {
		HashEntry<T>[] oldArray = array;

		allocateArray(nextPrime(2 * oldArray.length));
		currentSize = 0;

		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != null && oldArray[i].isActive) {
				insert(oldArray[i].element);
			}
		}
	}


	/**
	 * desc:获取hash值
	 *
	 * @auther: dhcao
	 */
	private int myhash(T x) {

		int hashVal = x.hashCode();

		hashVal %= array.length;

		if (hashVal < 0) {
			hashVal += array.length;
		}

		return hashVal;
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
