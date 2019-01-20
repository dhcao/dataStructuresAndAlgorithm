package chapterOne;

import java.util.ArrayList;
import java.util.List;

public class No1 {

	/**
	 * 对于n中不同的值的运行时间
	 * @param n
	 */
	public static void printCaculatorTime(int n){


		long start = System.nanoTime();

		double k = n/2;

		long end = System.nanoTime();

		long time = end-start;

		System.out.println(k+" : " + time);

		if(n>0){
			printCaculatorTime(n-1);
		}

		List<String> list = new ArrayList<>(3);

	}

	/**
	 * 输入abc，会打印所有的字符组合，输出就是：abc，acb，bac，bca，cab，cba
	 * @param str
	 */
	public static void permute(String str){
		char[] strCh = str.toCharArray();
		permute(strCh,0,str.length());

	}

	/**
	 * 递归：
	 * abc -- a,b,c
	 * abc -- b,a,c
	 * @param str
	 * @param low
	 * @param high
	 */
	private static void permute(char[] str,int low,int high){

		if(low == high){
			System.out.println(String.valueOf(str));
		}

		for (int i = low; i < str.length; i++) {
			swap(str,i,low);
			permute(str,low+1,high);
			swap(str,i,low);
		}

	}

	public static void swap(char[] str, int m, int n){
		char temp = str[m];
		str[m] = str[n];
		str[n] = temp;
	}

	public static void main(String[] args) {

		permute("abc");
	}
}
