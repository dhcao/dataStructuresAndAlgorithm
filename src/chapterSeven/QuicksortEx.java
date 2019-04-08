package chapterSeven;

/**
 * @Author: dhcao
 * @Version: 1.0
 */
public class QuicksortEx<T extends Comparable<? super T>> {

	/**
	 * 对于数组大小进行界定，小于10使用快速排序。大于10个大数组使用快速排序
	 * 小于这个值大数组（小数组）使用插入排序。
	 */
	private static final int CUTOFF = 3;

	/**
	 * 启动程序
	 *
	 * @param a
	 * @param <T>
	 */
	public static <T extends Comparable<? super T>> void quicksort(T[] a) {
//		quicksort(a, 0, a.length - 1);
	}

	/**
	 * 快速排序
	 *
	 * @param a
	 * @param left
	 * @param right
	 * @param <T>
	 */
	private static <T extends Comparable<? super T>> void quicksort(T[] a, int left, int right) {

		// 定义小数组，界定范围为CUTOFF=10。不是小数组采用快速排序
		if (left + CUTOFF <= right) {

			// 拿到枢纽元（参考值）
			T pivot = median3(a, left, right);

			// 开始排序
			int i = left, j = right - 1;
			for (; ; ) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j) {
					swapReferences(a, i, j);
				} else {
					break;
				}
			}

			swapReferences(a, i, right - 1);


			quicksort(a, left, i - 1);
			quicksort(a, i + 1, right);

		} else { // 小数组采用插入排序
			insertionSort(a, left, right);
		}


	}

	/**
	 * 返回中值
	 *
	 * @param a
	 * @param left
	 * @param right
	 * @param <T>
	 * @return
	 */
	private static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {

		int center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0) {
			swapReferences(a, left, center);
		}
		if (a[right].compareTo(a[left]) < 0) {
			swapReferences(a, left, right);
		}
		if (a[right].compareTo(a[center]) < 0) {
			swapReferences(a, center, right);
		}

		swapReferences(a, center, right - 1);
		return a[right - 1];
	}


	/**
	 * 交换位置
	 *
	 * @param a
	 * @param i
	 * @param n
	 * @param <T>
	 */
	public static <T extends Comparable<? super T>> void swapReferences(T[] a, int i, int n) {
		T tmp = a[i];
		a[i] = a[n];
		a[n] = tmp;
	}

	/**
	 * 插入排序
	 *
	 * @param a
	 * @param left
	 * @param right
	 * @param <T>
	 */
	private static <T extends Comparable<? super T>> void insertionSort(T[] a, int left, int right) {

		int j;
		for (int p = left + 1; p <= right; p++) {
			T tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
				a[j] = a[j - 1];
			}
			a[j] = tmp;
		}
	}

	public static <T extends Comparable<? super T>> void print(T[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		// 1.测试插入排序

		Integer[] aaa = new Integer[]{1, 9, 2, 6, 13, 14, 68, 32, 16, 22, 8, 15, 21, 34, 32, 12, 41, 7, 9, 10};

		print(aaa);

		insertionSort(aaa, 0, aaa.length - 1);
		print(aaa);

		System.out.println("------快乐的分割线------");


		// 2.测试快速排序
		Integer[] aa = new Integer[]{1, 9, 2, 6, 13, 14, 68, 32, 16, 22, 8, 15, 21, 34, 32, 12, 41, 7, 9, 10};
		print(aa);
		quicksort(aa, 0, aa.length - 1);
		print(aa);

	}

}
