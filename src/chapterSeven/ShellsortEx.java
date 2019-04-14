package chapterSeven;

/**
 * 希尔排序
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class ShellsortEx<T extends Comparable<? super T>> {

	/**
	 * 希尔排序。
	 * 增量序列：N/2，N/4 ....
	 *
	 * @param a
	 * @param <T>
	 */
	public static <T extends Comparable<? super T>> void shellsort(T[] a) {

		int j;

		for (int gap = a.length / 2; gap > 0; gap /= 2) { // 增量序列

			// 每个序列排序过程
			for (int i = gap; i < a.length; i++) {
				T tmp = a[i];
				for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
					a[j] = a[j - gap];
				}

				a[j] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[]{81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

		System.out.println("");

		shellsort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
