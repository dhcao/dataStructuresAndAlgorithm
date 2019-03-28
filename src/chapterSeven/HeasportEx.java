package chapterSeven;

/**
 * 堆排序
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class HeasportEx<T extends Comparable<? super T>> {

	/**
	 * 左儿子，
	 * 二叉堆的下标从1开始，这里明显从0开始，所以左儿子需要 +1 返回
	 *
	 * @param i
	 * @return
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * 下滤
	 *
	 * @param a   堆数组
	 * @param i   下滤节点位置
	 * @param n   二叉堆大小
	 * @param <T>
	 */
	private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {

		int child;
		T tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);

			// 如果左儿子比右儿子小，则选择右儿子。
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) {
				child++;
			}

			// 如果父节点小于儿子节点，则将父节点下滤
			if (tmp.compareTo(a[child]) < 0) {
				a[i] = a[child];
			} else {
				break;
			}
		}

		a[i] = tmp;
	}

	/**
	 * 堆排序
	 *
	 * @param a   排序数组
	 * @param <T>
	 */
	public static <T extends Comparable<? super T>> void heapsort(T[] a) {

		for (int i = a.length / 2 - 1; i >= 0; i--) {
			percDown(a, i, a.length);
		}
		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i);
			percDown(a, 0, i);
		}
	}

	/**
	 * 删除最大值
	 *
	 * @param a   二叉堆
	 * @param i   删除位置
	 * @param n   堆大小
	 * @param <T>
	 */
	public static <T extends Comparable<? super T>> void swapReferences(T[] a, int i, int n) {
		T tmp = a[i];
		a[i] = a[n];
		a[n] = tmp;
		percDown(a, i, n);
	}

	public static void main(String[] args) {
		Integer[] aaa = {1,5,2,5,8,4,6,3,0};
		for (int i = 0; i < aaa.length; i++) {
			System.out.print(aaa[i]+" ");
		}
		System.out.println("");
		heapsort(aaa);
		for (int i = 0; i < aaa.length; i++) {
			System.out.print(aaa[i]+" ");
		}
	}

}
