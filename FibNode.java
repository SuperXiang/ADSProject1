
//Fibonacci node
public class FibNode {
	        int key;            // �ؼ���(��ֵ)
	        int degree;            // ����
	        FibNode left;        // ���ֵ�
	        FibNode right;        // ���ֵ�
	        FibNode child;        // ��һ�����ӽڵ�
	        FibNode parent;        // ���ڵ�
	        boolean marked;     // �Ƿ�ɾ����һ������
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
