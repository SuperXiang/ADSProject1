
//Fibonacci node
public class FibNode {
	        int key;            // 关键字(键值)
	        int degree;            // 度数
	        FibNode left;        // 左兄弟
	        FibNode right;        // 右兄弟
	        FibNode child;        // 第一个孩子节点
	        FibNode parent;        // 父节点
	        boolean marked;     // 是否被删除第一个孩子
	        String  hashtag;      // string value of hashtag 

	        public FibNode(int key) {
	            this.key    = key;
	            this.degree = 0;
	            this.marked = false;
	            this.left   = this;
	            this.right  = this;
	            this.parent = null;
	            this.child  = null;
	            this.hashtag= null;
	        }
}
