package chapterTwo;


public class No3 {
	public static void main(String[] args) {
		System.out.println(pow(2,62));
		System.out.println(mi(2,62));
	}

	private static long mi(long i, long j) {
		long start = System.nanoTime();
		long result = 1;
		for (long k = 0; k < j; k++) {
			result *= i;
		}
		System.out.println(System.nanoTime()-start);
		return result;
	}

	/**
	 * 求x的n次方的值。
	 * 幂函数：
	 * x的n次幂
	 * @param x
	 * @param n
	 * @return
	 */
	public static long pow(long x, long n) {

		long start = System.nanoTime();
		if (n == 0) {
			System.out.println(System.nanoTime()-start);
			return 1;
		}
		// 如果是偶数
		if (isEven(n)) {
			return pow(x * x, n / 2);
		} else {
			return pow(x * x, n / 2) * x;
		}
	}

	/**
	 * 判断x是否是偶数
	 *
	 * @param x
	 * @return
	 */
	private static Boolean isEven(long x) {
		if (x % 2 == 0) {
			return true;
		}
		if (x % 2 == 1) {
			return false;
		}
		return false;
	}
}
