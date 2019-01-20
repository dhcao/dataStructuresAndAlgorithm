package chapterTwo;

/**
 * 最大子序列求解问题，
 * 提供4种解法，体验起不同的运行时间
 * 最大子序列和：
 * 给定整数（可能有负数）A1，A2，A3，···，An，求Ak的和。
 * 例如：
 * 给定：-2，11，-4，13，-5，-2，答案为20（A2 - A4）
 */
public class No2 {

	public static void main(String[] args) {
		int[] a = {-2, 11, -4, 13, -5, -2};
		System.out.println(method1(a));
		System.out.println(method2(a));
		System.out.println(method3(a, 0, a.length - 1));
		System.out.println(method4(a));
	}

	/**
	 * 方法1：直接计算，每次都计算子序列和，再跟最大值比较，取最大值。
	 * 算法分析：
	 * O(N^3) = N*(N-1)*((j-i+1)+1+1);
	 *
	 * @param a
	 * @return
	 */
	public static int method1(int[] a) {

		// 1
		int maxSun = 0;

		// N
		for (int i = 0; i < a.length; i++) {
			// N - i
			for (int j = i; j < a.length; j++) {

				// 1
				int thisSum = 0;

				// j-i+1
				for (int k = i; k <= j; k++) {
					thisSum += a[k];
				}

				// 1
				if (thisSum > maxSun) {
					maxSun = thisSum;
				}
			}
		}
		return maxSun;
	}

	/**
	 * 方法2：同时计算2层循环，最后一层循环不再重新计算而是采用累加最新值的方法。
	 * 算法分析：
	 * O(N^2) = N*(N-i)*(1+1)
	 *
	 * @param a
	 * @return
	 */
	public static int method2(int[] a) {

		// 1
		int maxSun = 0;

		// N
		for (int i = 0; i < a.length; i++) {

			// N - i
			int thisSum = 0;
			for (int j = i; j < a.length; j++) {

				// 1
				thisSum += a[j];

				// 1
				if (thisSum > maxSun) {
					maxSun = thisSum;
				}
			}
		}
		return maxSun;
	}

	/**
	 * 这个方法就厉害了，叫做："分治"策略
	 *
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	public static int method3(int[] a, int left, int right) {

		// 基准情况
		if (left == right) {
			if (a[left] > 0) {
				return a[left];
			} else {
				return 0;
			}
		}

		int center = (left + right) / 2;
		int maxLeftSum = method3(a, left, center);
		int maxRightSum = method3(a, center + 1, right);

		int maxLeftBorderSum = 0;
		int leftBorderSum = 0;

		for (int i = center; i >= left; i--) {
			leftBorderSum += a[i];
			if (leftBorderSum > maxLeftBorderSum) {
				maxLeftBorderSum = leftBorderSum;
			}
		}

		int maxRightBorderSum = 0;
		int rightBorderSum = 0;

		for (int i = center + 1; i <= right; i++) {
			rightBorderSum += a[i];
			if (rightBorderSum > maxRightBorderSum) {
				maxRightBorderSum = rightBorderSum;
			}
		}

		return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);

	}

	/**
	 * 返回最大值
	 *
	 * @param maxLeftSum
	 * @param maxRightSum
	 * @param i
	 * @return
	 */
	private static int max3(int maxLeftSum, int maxRightSum, int i) {

		int max = maxLeftSum;
		if (max < maxRightSum) {
			max = maxRightSum;
		}
		if (max < i) {
			max = i;
		}
		return max;
	}

	/**
	 * 这个就更厉害了，叫做：联机算法。
	 * 联机算法：
	 * 他只对数据进行一次扫描，一旦a[i]被读入并处理，他就不需要再被记忆。
	 * 因此，如果数组在磁盘上或通过网络传送，那么它就可以被按顺序读入，在主存中不必存储数组的任何部分。
	 * 不仅如此，在任意时刻，算法都能对它已经读入对数据给出子序列问题对正确答案(其他问题不具备这个特性)
	 *
	 * @param a
	 * @return
	 */
	public static int method4(int[] a) {
		int maxSum = 0;
		int thisSum = 0;
		for (int i = 0; i < a.length; i++) {

			thisSum += a[i];

			if (thisSum > maxSum) {
				maxSum = thisSum;
			} else if (thisSum < 0) {
				thisSum = 0;
			}
		}
		return maxSum;
	}
}
