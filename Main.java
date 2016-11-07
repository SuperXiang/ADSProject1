


	

/**
	 * Java ����: 쳲�������
	 *
	 * @author skywang
	 * @date 2014/04/07
	 */

import java.util.*;
	
	public class  Main {

	    private static final boolean DEBUG = false;

	    // ��8��
	    private static int a[] = {12,  7, 25, 15, 28, 33, 41, 1};
	    // ��14��
	    private static int b[] = {18, 35, 20, 42,  9, 
	                                 31, 23, 6, 48, 11,
	                              24, 52, 13,  2 };
	   
	    
	    
	 //   private static String c[] = {"#saturday 5","#sunday 3","#saturday 10","#monday 2","#reading 4","#playing_games 2",
	  //  	"#saturday 6","#sunday 8","#friday 2","#tuesday 2","#saturday 12","#sunday 11","#monday 6"};
      //�����������
	    private static String input_data = "#saturday 5 #sunday 3 #saturday 10 #monday 2 #reading 4"
	    		+ " #playing_games 2 #saturday 6 #sunday 8 #friday 2 "
	    		+ "#tuesday 2 #saturday 12 #sunday 11 #monday 6 3 #saturday 12 #monday 2 "
	    		+ "#stop 3 #playing 4 #reading 15 #drawing 3 #friday 12 #school 6 #class 5 5 stop";
	    // ��֤"������Ϣ(쳲������ѵĽṹ)"
	    
	    public static void main(String[]args) {
	        FibHeap hb=new FibHeap();
	        
	        // create a haspmap 
            Hashtable<String, FibNode> ht = new Hashtable<String, FibNode>();
	        // 쳲�������hb
	        
	        System.out.println("== 쳲�������(hb)���������: ");
	        String[] split_data = input_data.split("\\s+");
	        for(int i=0; i<split_data.length;i++) {
	        	
	        	//�ж�string��һ���ַ��ǲ��ǣ�
	        	if(split_data[i].charAt(0)=='#')
	        	{
	        		//��ȡ��string �� frequency
	        		int value = Integer.parseInt(split_data[i+1]);
	        		
	        		//�ж�fibonacci heap�Ƿ�Ϊ��
	        		if(hb.maximum()==-1)
	        		{
	        			
	        			//���벢������ַ�������hashtable��node����	
	        			hb.insert(value,ht,split_data[i]);	
	        			System.out.print( split_data[i]);
	        		}
	        		else
	        		{
	        			//���fibonacci heap��Ϊ��
	        			if(ht.containsKey(split_data[i]))
	        			{
	        				//������ڸ�string,ֱ�Ӹ���frequency
	        				hb.update(ht,split_data[i],value);
	        				System.out.print( split_data[i]);
	        			}
	        			else
	        			{
	        				//��������ڸ� string, �����stringֵ
	        				hb.insert(value,ht,split_data[i]);
	        				System.out.print( split_data[i]);
	        			}
	        		}
	        		i++;
	        	}
	        	//�����������ֵ n��������
	        	else if(Character.isDigit(split_data[i].charAt(0)))
	        	{
	        		int value = Integer.parseInt(split_data[i]);
	        		System.out.println(" ");
	        		// ��֪���᲻���ڴ��ñ���
	        		String []output=new String [value];
	        		int [] KeyValue=new int [value];
	        		for(int j=0;j<value;j++)
	        		{
	        			//������ڵ��Ӧ��string �� value
	        			 KeyValue[j]=hb.maximum();
	        			output[j]=hb.maximum_string(ht);
	        			hb.removeMax();
	        			System.out.println(output[j]+","); 
	        		}
	        		
	        		for(int j=0;j<value;j++)
	        		{
	        			//���½�remove��ֵ����fibonacci heap
	        			hb.insert(KeyValue[j],ht,output[j]);
	        			
	        		}
	        		//System.out.println(hb.maximum()); 
	        	}
	        	//������� stop, �˳�ѭ��
	        	else if(split_data[i].equals("stop"))
	        	{
	        		break;
	        	}
	        	
	        }
	    }


	}


