package chapterFive;

/**
 * 这是一个简单接口，负责将散列函数族发送给布谷鸟散列。
 * @Author: dhcao
 * @Version: 1.0
 */
public interface HashFamily<T> {
	int hash(T t,int which);
	int getNumberOfFunctions();
	void generateNewFunctions();
}
