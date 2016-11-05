
	
	/**
	 * Java ����: 쳲�������
	 *
	 * @author skywang
	 * @date 2014/04/07
	 */

	public class Main {

	    private static final boolean DEBUG = false;

	    // ��8��
	    private static int a[] = {12,  7, 25, 15, 28, 33, 41, 1};
	    // ��14��
	    private static int b[] = {18, 35, 20, 42,  9, 
	                                 31, 23,  6, 48, 11,
	                              24, 52, 13,  2 };

	    // ��֤"������Ϣ(쳲������ѵĽṹ)"
	    public static void testBasic() {
	        FibHeap hb=new FibHeap();

	        // 쳲�������hb
	        System.out.println("== 쳲�������(hb)���������: ");
	        for(int i=0; i<b.length; i++) {
	            System.out.print( b[i]);
	            System.out.print( " ");
	            hb.insert(b[i]);
	        }
	        System.out.println("\n");
	        System.out.println("== 쳲�������(hb)ɾ����С�ڵ�\n");
	        hb.removeMin();
	        hb.print(); // ��ӡ쳲�������hb
	    }

	    // ��֤"�������"
	    public static void testInsert() {
	        FibHeap ha=new FibHeap();

	        // 쳲�������ha
	        System.out.println("== 쳲�������(ha)���������: ");
	        for(int i=0; i<a.length; i++) {
	            System.out.print( a[i]);
	            ha.insert(a[i]);
	        }
	        System.out.println("\n");
	        System.out.println("== 쳲�������(ha)ɾ����С�ڵ�\n");
	        ha.removeMin();
	        ha.print(); // ��ӡ쳲�������ha

	        System.out.print("== ����50\n");
	        ha.insert(50);
	        ha.print();
	    }

	    // ��֤"�ϲ�����"
	    public static void testUnion() {
	        FibHeap ha=new FibHeap();
	        FibHeap hb=new FibHeap();

	        // 쳲�������ha
	        System.out.print("== 쳲�������(ha)���������: ");
	        for(int i=0; i<a.length; i++) {
	            System.out.print( a[i]);
	            ha.insert(a[i]);
	        }
	        System.out.println("\n");
	        System.out.println("== 쳲�������(ha)ɾ����С�ڵ�\n");
	        ha.removeMin();
	        ha.print(); // ��ӡ쳲�������ha

	        // 쳲�������hb
	        System.out.println("== 쳲�������(hb)���������: ");
	        for(int i=0; i<b.length; i++) {
	            System.out.print(b[i]);
	            hb.insert(b[i]);
	        }
	        System.out.print("\n");
	        System.out.print("== 쳲�������(hb)ɾ����С�ڵ�\n");
	        hb.removeMin();
	        hb.print(); // ��ӡ쳲�������hb

	        // ��"쳲�������hb"�ϲ���"쳲�������ha"�С�
	        System.out.print("== �ϲ�ha��hb\n");
	        ha.union(hb);
	        ha.print();
	    }

	    // ��֤"ɾ����С�ڵ�"
	    public static void testRemoveMin() {
	        FibHeap ha=new FibHeap();
	        FibHeap hb=new FibHeap();

	        // 쳲�������ha
	        System.out.print("== 쳲�������(ha)���������: ");
	        for(int i=0; i<a.length; i++) {
	            System.out.print(a[i]);
	            ha.insert(a[i]);
	        }
	        System.out.print("\n");
	        System.out.print("== 쳲�������(ha)ɾ����С�ڵ�\n");
	        ha.removeMin();
	        //ha.print(); // ��ӡ쳲�������ha

	        // 쳲�������hb
	        System.out.print("== 쳲�������(hb)���������: ");
	        for(int i=0; i<b.length; i++) {
	            System.out.print( b[i]);
	            hb.insert(b[i]);
	        }
	        System.out.print("\n");
	        System.out.print("== 쳲�������(hb)ɾ����С�ڵ�\n");
	        hb.removeMin();
	        //hb.print(); // ��ӡ쳲�������hb

	        // ��"쳲�������hb"�ϲ���"쳲�������ha"�С�
	        System.out.print("== �ϲ�ha��hb\n");
	        ha.union(hb);
	        ha.print();

	        System.out.print("== ɾ����С�ڵ�\n");
	        ha.removeMin();
	        ha.print();
	    }

	    // ��֤"��С�ڵ�"
	    public static void testDecrease() {
	        FibHeap hb=new FibHeap();

	        // 쳲�������hb
	        System.out.print("== 쳲�������(hb)���������: ");
	        for(int i=0; i<b.length; i++) {
	            System.out.print(b[i]);
	            hb.insert(b[i]);
	        }
	        System.out.print("\n");
	        System.out.print("== 쳲�������(hb)ɾ����С�ڵ�\n");
	        hb.removeMin();
	        hb.print(); // ��ӡ쳲�������hb

	        System.out.print("== ��20��СΪ2\n");
	        hb.update(20, 2);
	        hb.print();
	    }

	    // ��֤"����ڵ�"
	    public static void testIncrease() {
	        FibHeap hb=new FibHeap();

	        // 쳲�������hb
	        System.out.println("== 쳲�������(hb)���������: ");
	        for(int i=0; i<b.length; i++) {
	            System.out.println( b[i]);
	            hb.insert(b[i]);
	        }
	        System.out.println("\n");
	        System.out.println("== 쳲�������(hb)ɾ����С�ڵ�\n");
	        hb.removeMin();
	        hb.print(); // ��ӡ쳲�������hb

	        System.out.println("== ��20����Ϊ60\n");
	        hb.update(20, 60);
	        hb.print();
	    }

	    // ��֤"ɾ���ڵ�"
	    public static void testDelete() {
	        FibHeap hb=new FibHeap();

	        // 쳲�������hb
	        System.out.println("== 쳲�������(hb)���������: ");
	        for(int i=0; i<b.length; i++) {
	            System.out.println( b[i]);
	            hb.insert(b[i]);
	        }
	        System.out.println("\n");
	        System.out.println("== 쳲�������(hb)ɾ����С�ڵ�\n");
	        hb.removeMin();
	        hb.print(); // ��ӡ쳲�������hb

	        System.out.println("== ɾ���ڵ�20\n");
	        hb.remove(20);
	        hb.print();
	    }

	    public static void main(String[] args) {
	        // ��֤"������Ϣ(쳲������ѵĽṹ)"
	        testBasic();
	        // ��֤"�������"
	        //testInsert();
	        // ��֤"�ϲ�����"
	        //testUnion();
	        // ��֤"ɾ����С�ڵ�"
	        //testRemoveMin();
	        // ��֤"��С�ڵ�"
	        //testDecrease();
	        // ��֤"����ڵ�"
	        //testIncrease();
	        // ��֤"ɾ���ڵ�"
	        //testDelete();
	    }
	}


