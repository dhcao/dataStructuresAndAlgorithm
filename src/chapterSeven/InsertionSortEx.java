package chapterSeven;


/**
 * 插入排序
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class InsertionSortEx<T extends Comparable<? super T>> {

	/**
	 * 直接对数组a进行排序
	 *
	 * @param a   需要排序数组
	 * @param <T> 实现Comparable接口对类型
	 */
	public static <T extends Comparable<? super T>> void insertionSort1(T[] a) {

		// 技巧1：在循环中重复定义的函数，可以放到循环外定义。
		int j;

		/**
		 * 从位置p=1开始，对所有对数组元素进行排序
		 */
		for (int p = 1; p < a.length; p++) {

			// 提前将排序的元素a[p]准备好
			T tmp = a[p];

			// 将位置p上的元素向左边移动(即p左边的元素向右移动)（由于左边从0到p-1的元素已经在排序状态）
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {

				// 技巧2：将必须的操作留在循环中，不需要的操作可以移到循环外
				a[j] = a[j - 1];
			}
			a[j] = tmp;
		}

	}

	/**
	 * 将数组中的某一段进行排序。
	 * @param a 数组
	 * @param left 需要排序的左界
	 * @param right 需要排序的右界
	 * @param <T> 实现Comparable接口对类型
	 */
	public static <T extends Comparable<? super T>> void insertionSort2(T[] a,int left,int right){

		int j;
		for (int p = left + 1; p <= right; p++) {
			T tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
				a[j] = a[j - 1];
			}
			a[j] = tmp;
		}
	}

}
