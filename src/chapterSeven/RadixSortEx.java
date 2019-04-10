package chapterSeven;

import java.util.ArrayList;

/**
 * 基数排序，属于"分配式排序"，亦是"桶排序"的一种实现。
 *
 * @Author: dhcao
 * @Version: 1.0
 */
public class RadixSortEx {

	/**
	 * 基数排序
	 * Radix sort an array of Strings
	 * Assume all words are ASCII
	 * All string have same length
	 *
	 * @param arr
	 * @param stringLen
	 */
	public static void radixSortA(String[] arr, int stringLen) {

		final int BUCKETS = 256;

		// list数组来表示存放所有的桶
		ArrayList<String>[] buckets = new ArrayList[BUCKETS];

		/*
		 * 用arraylist来构建桶，优点是桶里面可以存放多个数据，缺点很明显是消耗内存。
		 * 因为把每个字符规定在256范围内，所以可以创建256个桶。
		 */
		for (int i = 0; i < BUCKETS; i++) {
			buckets[i] = new ArrayList<>();
		}

		// pos 为2，1，0
		for (int pos = stringLen - 1; pos >= 0; pos--) {

			// 遍历所有的字符串，将所有的字符串放入桶中。
			for (String s : arr) {

				/*
				 * 当pos = 2，s = "abc"时。s.charAt(pos) = "c";
				 * buckets['c'] = buckets['99'];  99为字符'c'的ASCII码值
				 * 那么以下就会得到将：99号桶中存放的是字符串"abc"
				 */
				buckets[s.charAt(pos)].add(s);
			}

			int idx = 0;

			// 遍历所有的桶
			for (ArrayList<String> thisBucket : buckets) {

				/*
				 * 将每个桶中的字符串列表，按顺序的添加到数组中。
				 */
				for (String s : thisBucket) {
					arr[idx++] = s;
				}
				thisBucket.clear();

			}

		}
	}

	/**
	 * 计数基数排序
	 * 对定长字符串进行排序，不像基数排序那养用Arraylist来维护桶，转而使用count计数器
	 * 来计算桶中存放元素个数。
	 *
	 * @param arr
	 * @param stringLen
	 */
	public static void countingRadixSort(String[] arr, int stringLen) {

		// 桶依然取256，理由同上"基数排序"，因为最多不过256。
		final int BUCKETS = 256;

		int N = arr.length;
		String[] buffer = new String[N]; // 同大小的缓存数组

		String[] in = arr;
		String[] out = buffer;

		// pos表示位置，在长度为3的字符串中，pos：2，1，0；
		for (int pos = stringLen - 1; pos >= 0; pos--) {

			// 构建一个定长数组用来计数，也是桶
			int[] count = new int[BUCKETS + 1];

			/*
			 * 记录每个桶里会装多少个元素
			 * 遍历到in[i] = "abc"时，当pos = 2。in[i].charAt(pos) = 99。
			 * 此时在第100号桶中放入存放元素个数+1
			 * count[k+1]表示桶k中元素个数。
			 */
			for (int i = 0; i < N; i++) {
				count[in[i].charAt(pos) + 1]++;
			}

			for (int b = 1; b <= BUCKETS; b++) {
				count[b] += count[b - 1];
			}

			for (int i = 0; i < N; i++) {
				out[count[in[i].charAt(pos)]++] = in[i];
			}

			String[] tmp = in;
			in = out;
			out = tmp;
		}

		if (stringLen % 2 == 1) {
			for (int i = 0; i < arr.length; i++) {
				out[i] = in[i];
			}
		}

	}

	public static void radixSort(String[] arr,int maxLen){
		final int BUCKETS = 256;

		// 长度数组，里面放长度桶
		ArrayList<String>[] wordsByLength = new ArrayList[maxLen + 1];
		// 放ASCII码桶
		ArrayList<String>[] buckets = new ArrayList[BUCKETS];

		/*
		 * 下面2个for循环都是构建桶
		 */
		for (int i = 0; i < wordsByLength.length; i++) {
			wordsByLength[i] = new ArrayList<>();
		}

		for (int i = 0; i < BUCKETS; i++) {
			buckets[i] = new ArrayList<>();
		}

		// 对长度进行一次桶排序
		for (String s:arr) {
			wordsByLength[s.length()].add(s);
		}

		int idx = 0;
		for (ArrayList<String> wordList : wordsByLength) {
			for (String s:wordList) {
				arr[idx++] = s;
			}
		}

		int startingIndex = arr.length;
		for (int pos = maxLen - 1; pos >= 0; pos--) {
			startingIndex -=wordsByLength[pos +1].size();

			for (int i = startingIndex; i < arr.length; i++) {
				buckets[arr[i].charAt(pos)].add(arr[i]);
			}

			idx = startingIndex;
			for (ArrayList<String> thisBucket : buckets) {
				for (String s:thisBucket) {
					arr[idx++] = s;
				}
				thisBucket.clear();
			}
		}

	}


	public static void main(String[] args) {

		String[] arr = new String[]{"qwe", "tgf", "eqg", "iud"};
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}

		System.out.println("");

		countingRadixSort(arr, 3);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}

	}
}
