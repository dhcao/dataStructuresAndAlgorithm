package trieAbout;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.util.TreeMap;

/**
 * 创建自己的trie
 * @Author: dhcao
 * @Version: 1.0
 */
public class Trie {

    /**
     * node, assume every node is a character (just a example);
     * 我们使用的时候，敏感词还是各种语言都有的；；
     */
    private class Node{

        public boolean isWord;

        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }


    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 添加一个新的单词
     * 不用递归
     * @param word
     */
    public void add(String word){

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }

            cur = cur.next.get(c);
        }

        // 单词是否已经存在
        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 查找trie中是否存在word
     * @param word
     * @return
     */
    public boolean contains(String word){

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    /**
     * 查询是否在Trie中有单词以prefix为前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){

        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}
