package chapterSeven;

/**
 * 归并排序
 * 将两个已排序的数组进行合并排序。
 * 分治策略：如果处理一个长度为8的数组，可以分别将前4个和后4个排序，然后再使用归并排序合并他们。
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class MergeSortEx<T extends Comparable<? super T>> {


	/**
	 * 排序方法
	 * 归并策略
	 *
	 * @param a
	 * @param tmpArray
	 * @param left
	 * @param right
	 * @param <T>
	 */
	private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	/**
	 * 递归归一！
	 *
	 * @param a
	 * @param tmpArray
	 * @param leftPos
	 * @param rightPos
	 * @param rightEnd
	 */
	private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {

		// 左边数组最大界
		int leftEnd = rightPos - 1;
		// 左边数组界 就是新数组的界。一般从0开始。
		int tmpPos = leftPos;
		// 数组长度（元素个数）
		int numElements = rightEnd - leftPos + 1;

		while (leftPos <= leftEnd && rightPos <= rightEnd) {

			// 比较数组的第一个值，将比较小的放入第三个数组。
			if (a[leftPos].compareTo(a[rightPos]) <= 0) {
				tmpArray[tmpPos++] = a[leftPos++];
			} else {
				tmpArray[tmpPos++] = a[rightPos++];
			}

		}

		// 左数组的指针没到底，说明右边数组到已经比较结束。
		while (leftPos <= leftEnd) {
			tmpArray[tmpPos++] = a[leftPos++];
		}

		// 右数组的指针没到底，说明左边数组到已经比较结束。
		while (rightPos <= rightEnd) {
			tmpArray[tmpPos++] = a[rightPos++];
		}

		for (int i = 0; i < numElements; i++, rightEnd--) {
			a[rightEnd] = tmpArray[rightEnd];
		}
	}

	/**
	 * 排序入口
	 *
	 * @param a
	 * @param <T>
	 */
	private static <T extends Comparable<? super T>> void mergeSort(T[] a) {
		T[] tmpArray = (T[]) new Comparable[a.length];
		mergeSort(a, tmpArray, 0, a.length - 1);
	}


	/**
	 * 测试方法
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		Integer[] aaa = new Integer[]{1, 4, 2, 7, 21, 34, 32, 12, 41, 7, 9, 10};

		for (int i = 0; i < aaa.length; i++) {
			System.out.print(aaa[i] + " ");
		}

		System.out.println("");

		mergeSort(aaa);
		for (int i = 0; i < aaa.length; i++) {
			System.out.print(aaa[i] + " ");
		}
	}
}
