
	/**
	 * Java ����: 쳲�������
	 *
	 */
import java.util.*;
	public class FibHeap {

	    private int keyNum;         // ���нڵ������
	    private FibNode max;        // ���ڵ�(ĳ�����ѵĸ��ڵ�)

	    public FibHeap() {
	        this.keyNum = 0;
	          this.max = null;
	    }

	    /* 
	     * ��node��˫�����Ƴ�
	     */
	    private void removeNode(FibNode node) {
	        node.left.right = node.right;
	        node.right.left = node.left;
	    }
	     
	    /*
	     * ��node�ѽ�����root���֮ǰ(ѭ��������)
	     *   a ���� root
	     *   a ���� node ���� root
	    */
	    private void addNode(FibNode node, FibNode root) {
	        node.left        = root.left;
	        root.left.right  = node;
	        node.right       = root;
	        root.left        = node;
	    }
	     
	    /*
	     * ���ڵ�node���뵽쳲���������
	     */ // update 2016-11-6 min heap ��Ϊ max heap
	    private void insert(FibNode node) {
	        if (keyNum == 0)
	            max = node;
	        else {
	            addNode(node, max);
	            if (node.key > max.key)
	                max = node;
	        }

	        keyNum++;
	    }
	     
	    /* 
	     * �½���ֵΪkey�Ľڵ㣬��������뵽쳲���������
	     *///update in 2016 11 6
	    public  void insert(int key, Hashtable map, String hashtag) {
	        FibNode node;
	        node = new FibNode(key);
	        insert(node);
	        // hasptalb string ӳ�䵽 node
	        map.put(hashtag, node);
	        //�� string ��ֵ�����硰saturday"���뵽node�ĳ�Ա������
	        node.hashtag=hashtag;
	    }
	     
	    /*
	     * ��˫������b���ӵ�˫������a�ĺ���
	     */
	    private void catList(FibNode a, FibNode b) {
	        FibNode tmp;

	        tmp           = a.right;
	        a.right       = b.right;
	        b.right.left  = a;
	        b.right       = tmp;
	        tmp.left      = b;
	    }

	    /*
	     * ��other�ϲ�����ǰ����
	     */
	    public void union(FibHeap other) {
	        if (other==null)
	            return ;

	        if((this.max) == null) {                // this��"���ڵ�"
	            this.max = other.max;
	            this.keyNum = other.keyNum;
	            other = null;
	        } else if((other.max) == null) {        // this��"���ڵ�" && other��"���ڵ�"
	            other = null;
	        } else {                                // this��"���ڵ�" && other��"���ڵ�"
	            // ��"other�и�����"��ӵ�"this"��
	            catList(this.max, other.max) ;

	            if (this.max.key < other.max.key)
	                this.max = other.max;
	            this.keyNum += other.keyNum;
	            other = null;;
	        }
	    }

	    /*
	     * ��"�ѵ������"�Ӹ��������Ƴ���
	     * ����ζ��"�����ڵ���������"�Ӷ����Ƴ�!
	     */
	    private FibNode extractMax() {
	        FibNode p = max;

	        if (p == p.right)
	            max = null;
	        else {
	            removeNode(p);
	            max = p.right;
	        }
	        p.left = p.right = p;

	        return p;
	    }
	     
	    /*
	     * ��node���ӵ�root�����
	     */
	    private void link(FibNode node, FibNode root) {
	        // ��node��˫�������Ƴ�
	        removeNode(node);
	        // ��node��Ϊroot�ĺ���
	        if (root.child == null)
	            root.child = node;
	        else
	            addNode(node, root.child);

	        node.parent = root;
	        root.degree++;
	        node.marked = false;
	    }
	     
	    /* 
	     * �ϲ�쳲������ѵĸ�������������ͬ��������
	     */
	    private void consolidate() {
	        // ����log2(keyNum)��floor��ζ������ȡ����
	        // ex. log2(13) = 3������ȡ��Ϊ4��
	        int maxDegree = (int) Math.floor(Math.log(keyNum) / Math.log(2.0));
	        int D = maxDegree + 1;
	        FibNode[] cons = new FibNode[D+1];

	        for (int i = 0; i < D; i++)
	            cons[i] = null;
	     
	        // �ϲ���ͬ�ȵĸ��ڵ㣬ʹÿ����������Ψһ
	        while (max != null) {
	            FibNode x = extractMax();            // ȡ�����е������(���ڵ����ڵ���)
	            int d = x.degree;                        // ��ȡ������Ķ���
	            // cons[d] != null����ζ����������(x��y)��"����"��ͬ��
	            while (cons[d] != null) {
	                FibNode y = cons[d];                // y��"��x�Ķ�����ͬ����" 
	                if (x.key < y.key) {    // ��֤x�ļ�ֵ��y��
	                    FibNode tmp = x;
	                    x = y;
	                    y = tmp;
	                }

	                link(y, x);    // ��y���ӵ�x��
	                cons[d] = null;
	                d++;
	            }
	            cons[d] = x;
	        }
	        max = null;
	     
	        // ��cons�еĽ�����¼ӵ�������
	        for (int i=0; i<D; i++) {

	            if (cons[i] != null) {
	                if (max == null)
	                    max = cons[i];
	                else {
	                    addNode(cons[i], max);
	                    if ((cons[i]).key > max.key)
	                        max = cons[i];
	                }
	            }
	        }
	    }
	     
	    /*
	     * �Ƴ����ڵ�
	     */
	    public void removeMax() {
	        if (max==null)
	            return ;

	        FibNode m = max;
	        // ��maxÿһ������(���ӺͶ��ӵ��ֵ�)����ӵ�"쳲������ѵĸ�����"��
	        while (m.child != null) {
	            FibNode child = m.child;

	            removeNode(child);
	            if (child.right == child)
	                m.child = null;
	            else
	                m.child = child.right;

	            addNode(child, max);
	            child.parent = null;
	        }

	        // ��m�Ӹ��������Ƴ�
	        removeNode(m);
	        // ��m�Ƕ���Ψһ�ڵ㣬�����öѵ���С�ڵ�Ϊnull��
	        // �������öѵ���С�ڵ�Ϊһ���ǿսڵ�(m.right)��Ȼ���ٽ��е��ڡ�
	        if (m.right == m)
	            max = null;
	        else {
	            max = m.right;
	            consolidate();
	        }
	        keyNum--;

	        m = null;
	    }

	    /*
	     * ��ȡ쳲�������������ֵ��ʧ�ܷ���-1
	     */
	    public int maximum() {
	        if (max==null)
	            return -1;

	        return max.key;
	    }
	    
	    public String maximum_string(Hashtable<String, FibNode> map)
	    {
	    	//String max_string=map.get(max);
	    	   //Map,HashMap��û��ʵ��Iteratable�ӿ�.����������ǿforѭ��. 
	    	String max_key=null;
//	        for(String getKey: map.keySet()){  
//	    	     if(map.get(getKey).equals(max)){  
//	    	     max_key = getKey;  
//	    	            }  
//	        }
	    	if (max==null)
	            return null;
	    	
	    	max_key=max.hashtag;
	    	return max_key;
	    	
	    }
	      
	    /* 
	     * �޸Ķ���
	     */
	    private void renewDegree(FibNode parent, int degree) {
	        parent.degree -= degree;
	        if (parent. parent != null)
	            renewDegree(parent.parent, degree);
	    }
	     
	    /* 
	     * ��node�Ӹ��ڵ�parent���������а��������
	     * ��ʹnode��Ϊ"�ѵĸ�����"�е�һԱ��
	     */
	    private void cut(FibNode node, FibNode parent) {
	        removeNode(node);
	        renewDegree(parent, node.degree);
	        // nodeû���ֵ�
	        if (node == node.right) 
	            parent.child = null;
	        else 
	            parent.child = node.right;

	        node.parent = null;
	        node.left = node.right = node;
	        node.marked = false;
	        // ��"node������"��ӵ�"������"��
	        addNode(node, max);
	    }

	    /* 
	     * �Խڵ�node����"��������"
	     *
	     * �������У���������Ľ���ƻ����������ʣ�
	     *     �����������(��������˫��������ɾ��������
	     *     ����뵽����������ڵ��γɵ�˫��������)��
	     *     Ȼ���ٴ�"���нڵ�ĸ��ڵ�"�����������ڵ�ݹ�ִ�м�����֦
	     */
	    private void cascadingCut(FibNode node) {
	        FibNode parent = node.parent;

	        if (parent != null) {
	            if (node.marked == false) 
	                node.marked = true;
	            else {
	                cut(node, parent);
	                cascadingCut(parent);
	            }
	        }
	    }

	    /* 
	     * ��쳲��������нڵ�node��ֵ����Ϊkey
	     */
	    private void increase(FibNode node, int key) {
	        if (max==null ||node==null) 
	            return ;

	        if (key < node.key) {
	            System.out.print("increase failed: the new key(%d) is no greater than current key(%d)\n"+key+" "+node.key);
	            return ;
	        }

	        FibNode parent = node.parent;
	        node.key = key;
	        if (parent!=null && (node.key > parent.key)) {
	            // ��node�Ӹ��ڵ�parent�а������������node��ӵ���������
	            cut(node, parent);
	            cascadingCut(parent);
	        }

	        // �������ڵ�
	        if (node.key > max.key)
	            max = node;
	    }

	    /* 
	     * ��쳲��������нڵ�node��ֵ��СΪkey
	     */
	    private void decrease(FibNode node, int key) {
	        if (max==null ||node==null) 
	            return ;

	        if ( key >= node.key) {
	            System.out.print("decrease failed: the new key(%d) is no smaller than current key(%d)\n "+key+" "+node.key);
	            return ;
	        }

	        // ��nodeÿһ������(����������,����,...)����ӵ�"쳲������ѵĸ�����"��
	        while (node.child != null) {
	            FibNode child = node.child;
	            removeNode(child);               // ��child��node����������ɾ��
	            if (child.right == child)
	                node.child = null;
	            else
	                node.child = child.right;

	            addNode(child, max);       // ��child��ӵ���������
	            child.parent = null;
	        }
	        node.degree = 0;
	        node.key = key;

	        // ���node���ڸ������У�
	        //     ��node�Ӹ��ڵ�parent���������а��������
	        //     ��ʹnode��Ϊ"�ѵĸ�����"�е�һԱ��
	        //     Ȼ�����"��������"
	        // �������ж��Ƿ���Ҫ���¶ѵ����ڵ�
	        FibNode parent = node.parent;
	        if(parent != null) {
	            cut(node, parent);
	            cascadingCut(parent);
	        } else if(max == node) {
	            FibNode right = node.right;
	            while(right != node) {
	                if(node.key < right.key)
	                    max = right;
	                right = right.right;
	            }
	        }
	    }

	    /* 
	     * ����쳲������ѵĽڵ�node�ļ�ֵΪkey
	     */
	    private void update(FibNode node, int key) {
	        if(key < node.key)
	            decrease(node, key);
	        else if(key > node.key)
	            increase(node, key);
	        else
	            System.out.println("No need to update!!!\n");
	    }
	      //update from 2016 11 7
	    public void update(Hashtable<String, FibNode> map, String hashtag, int newkey) {
	    	
	        FibNode node;
	        //��ȡ��Ҫ���µĽڵ�
	        node = map.get(hashtag);
	        
	       int oldkey=node.key;
	      //  node = search(oldkey);
	       if (node!=null)
	    	   //���� node �� frequency
	        update(node, newkey+oldkey);
	    }

	    /*
	     * ������root�в��Ҽ�ֵΪkey�Ľڵ�
	     */
	    private FibNode search(FibNode root, int key) {
	        FibNode t = root;    // ��ʱ�ڵ�
	        FibNode p = null;    // Ҫ���ҵĽڵ�

	        if (root==null)
	            return root;

	        do {
	            if (t.key == key) {
	                p = t;
	                break;
	            } else {
	                if ((p = search(t.child, key)) != null) 
	                    break;
	            }
	            t = t.right;
	        } while (t != root);

	        return p;
	    }
	     
	    /*
	     * ��쳲��������в��Ҽ�ֵΪkey�Ľڵ�
	     */
	    private FibNode search(int key) {
	        if (max==null)
	            return null;

	        return search(max, key);
	    }

	    /*
	     * ��쳲����������Ƿ���ڼ�ֵΪkey�Ľڵ㡣
	     * ���ڷ���true�����򷵻�false��
	     */
	    public boolean contains(int key) {
	        return search(key)!=null ? true: false;
	    }

	    /*
	     * ɾ�����node
	     */
	    private void remove(FibNode node) {
	        int m = max.key;
	        increase(node, m-1);
	        removeMax();
	    }

	    public void remove(int key) {
	        if (max==null)
	            return ;

	        FibNode node = search(key);
	        if (node==null)
	            return ;

	        remove(node);
	    }
	     
	    /* 
	     * ����쳲�������
	     */
	    private void destroyNode(FibNode node) {
	        if(node == null)
	            return;

	        FibNode start = node;
	        do {
	            destroyNode(node.child);
	            // ����node������nodeָ����һ��
	            node = node.right;
	            node.left = null;
	        } while(node != start);
	    }
	     
	    public void destroy() {
	        destroyNode(max);
	    }

	    /*
	     * ��ӡ"쳲�������"
	     *
	     * ����˵����
	     *     node       -- ��ǰ�ڵ�
	     *     prev       -- ��ǰ�ڵ��ǰһ���ڵ�(���ڵ�or�ֵܽڵ�)
	     *     direction  --  1����ʾ��ǰ�ڵ���һ������;
	     *                    2����ʾ��ǰ�ڵ���һ���ֵܽڵ㡣
	     */
	    private void print(FibNode node, FibNode prev, int direction) {
	        FibNode start=node;

	        if (node==null)
	            return ;
	        do {
	            if (direction == 1)
	                System.out.println(node.key+"("+node.degree +")"+" is " +prev.key+" child " );
	            else
	            	System.out.println(node.key+"("+node.degree +")"+" is " +prev.key+" next " );

	            if (node.child != null)
	                print(node.child, node, 1);

	            // �ֵܽڵ�
	            prev = node;
	            node = node.right;
	            direction = 2;
	        } while(node != start);
	    }

	    public void print() {
	        if (max==null)
	            return ;

	        int i=0;
	        FibNode p = max;
	        System.out.println("== 쳲������ѵ���ϸ��Ϣ: ==\n");
	        do {
	            i++;
	            //System.out.println("%2d. %4d(%d) is root\n "+i+" "+p.key +" "+p.degree);
	            System.out.println(i +". "+p.key+"("+p.degree +")"+"is root");

	            print(p.child, p, 1);
	            p = p.right;
	        } while (p != max);
	        System.out.println("\n");
	    }
	}


