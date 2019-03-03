package chapterFour;

/**
 * avl树
 */
public class AvlTree<T extends Comparable> {

	private AvlNode root;
	private static final int ALLOWED_IMBALANCE = 1;

	/**
	 * 树节点
	 *
	 * @param <T>
	 */
	private static class AvlNode<T> {

		T element;
		AvlNode<T> left;
		AvlNode<T> right;
		int height;

		AvlNode(T t) {
			this(t, null, null);
		}

		public AvlNode(T t, AvlNode lt, AvlNode rt) {
			this.element = t;
			this.left = lt;
			this.right = rt;
		}
	}

	/**
	 * 返回节点高度
	 *
	 * @param t
	 * @return
	 */
	private int height(AvlNode t) {
		return t == null ? -1 : t.height;
	}

	/**
	 * 在树t处插入一个节点x
	 *
	 * @param x
	 * @param t
	 * @return
	 */
	private AvlNode<T> insert(T x, AvlNode<T> t) {

		// 如果子树不存在，则创建一个节点，x为该节点的根节点
		if (t == null) {
			return new AvlNode<>(x, null, null);
		}

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {

		}


		return balance(t);
	}

	/**
	 * 平衡节点
	 *
	 * @param t
	 * @return
	 */
	private AvlNode<T> balance(AvlNode<T> t) {

		if (t == null) {
			return t;
		}

		// 左-左，但旋转即可。左-右，需要双旋转
		if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
			if (height(t.left.left) >= height(t.left.right)) {
				t = rotateWithLeftChild(t);
			} else {
				t = doubleWithLeftChild(t);
			}
		} else {
			if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
				if (height(t.right.right) > height(t.right.left)) {
					t = rotateWithRightChild(t);
				} else {
					t = doubleWithRightChild(t);
				}
			}

		}

		t.height = Math.max(height(t.left), height(t.right));

		return t;
	}

	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
		k3.left = rotateWithLeftChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	/**
	 * desc:看懂上面的图，再看代码：参看第一种。
	 * 假设：k2树不再平衡，在k2的左儿子k1的左子树进行插入，导致k2子树不再平衡。采用右旋转
	 * 右旋转：
	 * 1.用k2的左子树k1来代替k2的位置。（k2丢失左子树）
	 * 2.让k2成为k1的右子树。
	 * 3.k1的右子树，重新成为k2的左子树。
	 *
	 * @auther:  dhcao
	 */
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {

		AvlNode<T> k1 = k2.left;

		k2.left = k1.right;
		k1.right = k2;
		// height方法返回该子树的 高
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	private AvlNode<T> doubleWithRightChild(AvlNode<T> t) {
		return null;
	}

	private AvlNode<T> rotateWithRightChild(AvlNode<T> t) {
		return null;
	}

}
