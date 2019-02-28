package chapterFive;

import java.util.LinkedList;
import java.util.List;

/**
 * 散列表实现方法之一：分离链接法
 * 分离链接法：在遇到冲突的时候，构建一个链表（linkedList）来存储，原位置存储链表位置。
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class SeparateChainingHashTable<T> {

	/**
	 * desc: 构造函数
	 *
	 * @return:
	 * @auther: dhcao
	 */
	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];

		// 为表中每一个位置都创建一个表，在冲突时放入数据
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<>();
		}
	}

	/**
	 * desc: 插入
	 *
	 * @auther: dhcao
	 */
	public void insert(T t) {
		List<T> whichList = theLists[myhash(t)];

		if (!whichList.contains(t)) {
			whichList.add(t);
		}

		if (++currentSize > theLists.length) {
			rehash();
		}
	}

	/**
	 * desc: 删除
	 *
	 * @auther: dhcao
	 */
	public void remove(T t) {
		List<T> whichList = theLists[myhash(t)];
		if (whichList.contains(t)) {
			whichList.remove(t);
			currentSize--;
		}
	}

	/**
	 * desc: 是否含有
	 *
	 * @auther: dhcao
	 */
	public boolean contains(T t) {

		// 在散列表中找到散列位置都链表
		List<T> whichList = theLists[myhash(t)];
		return whichList.contains(t);
	}

	/**
	 * desc: 清空散列表
	 *
	 * @auther: dhcao
	 */
	public void makeEmpty() {

		// 清空表都每个位置
		for (int i = 0; i < theLists.length; i++) {
			theLists[i].clear();
		}

		// 将当前表都大小置为0
		currentSize = 0;
	}

	private static final int DEFAULT_TABLE_SIZE = 101;

	private List<T>[] theLists;

	// 当前大小
	private int currentSize;

	/**
	 * desc: 重新构造散列表（在散列表达到最大大小之后）
	 *
	 * @auther: dhcao
	 */
	private void rehash() {
		List<T>[] oldLists = theLists;

		theLists = new List[nextPrime(2 * theLists.length)];

		for (int j = 0; j < theLists.length; j++) {
			theLists[j] = new LinkedList<>();
		}

		currentSize = 0;

		for (int i = 0; i < oldLists.length; i++) {
			for (T item : oldLists[i]) {
				insert(item);
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

		hashVal %= theLists.length;

		if (hashVal < 0) {
			hashVal += theLists.length;
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

