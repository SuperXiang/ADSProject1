


	

/**
	 * Java 语言: 斐波那契堆
	 *
	 * @author skywang
	 * @date 2014/04/07
	 */

import java.util.*;
	
	public class  Main {

	    private static final boolean DEBUG = false;

	    // 共8个
	    private static int a[] = {12,  7, 25, 15, 28, 33, 41, 1};
	    // 共14个
	    private static int b[] = {18, 35, 20, 42,  9, 
	                                 31, 23, 6, 48, 11,
	                              24, 52, 13,  2 };
	   
	    
	    
	 //   private static String c[] = {"#saturday 5","#sunday 3","#saturday 10","#monday 2","#reading 4","#playing_games 2",
	  //  	"#saturday 6","#sunday 8","#friday 2","#tuesday 2","#saturday 12","#sunday 11","#monday 6"};
      //输入测试数据
	    private static String input_data = "#saturday 5 #sunday 3 #saturday 10 #monday 2 #reading 4"
	    		+ " #playing_games 2 #saturday 6 #sunday 8 #friday 2 "
	    		+ "#tuesday 2 #saturday 12 #sunday 11 #monday 6 3 #saturday 12 #monday 2 "
	    		+ "#stop 3 #playing 4 #reading 15 #drawing 3 #friday 12 #school 6 #class 5 5 stop";
	    // 验证"基本信息(斐波那契堆的结构)"
	    
	    public static void main(String[]args) {
	        FibHeap hb=new FibHeap();
	        
	        // create a haspmap 
            Hashtable<String, FibNode> ht = new Hashtable<String, FibNode>();
	        // 斐波那契堆hb
	        
	        System.out.println("== 斐波那契堆(hb)中依次添加: ");
	        String[] split_data = input_data.split("\\s+");
	        for(int i=0; i<split_data.length;i++) {
	        	
	        	//判断string第一个字符是不是＃
	        	if(split_data[i].charAt(0)=='#')
	        	{
	        		//获取该string 的 frequency
	        		int value = Integer.parseInt(split_data[i+1]);
	        		
	        		//判断fibonacci heap是否为空
	        		if(hb.maximum()==-1)
	        		{
	        			
	        			//插入并输出该字符串，将hashtable和node链接	
	        			hb.insert(value,ht,split_data[i]);	
	        			System.out.print( split_data[i]);
	        		}
	        		else
	        		{
	        			//如果fibonacci heap不为空
	        			if(ht.containsKey(split_data[i]))
	        			{
	        				//如果存在该string,直接更新frequency
	        				hb.update(ht,split_data[i],value);
	        				System.out.print( split_data[i]);
	        			}
	        			else
	        			{
	        				//如果不存在该 string, 插入该string值
	        				hb.insert(value,ht,split_data[i]);
	        				System.out.print( split_data[i]);
	        			}
	        		}
	        		i++;
	        	}
	        	//如果输入是数值 n，输出结果
	        	else if(Character.isDigit(split_data[i].charAt(0)))
	        	{
	        		int value = Integer.parseInt(split_data[i]);
	        		System.out.println(" ");
	        		// 不知道会不会内存用崩溃
	        		String []output=new String [value];
	        		int [] KeyValue=new int [value];
	        		for(int j=0;j<value;j++)
	        		{
	        			//输出最大节点对应的string 和 value
	        			 KeyValue[j]=hb.maximum();
	        			output[j]=hb.maximum_string(ht);
	        			hb.removeMax();
	        			System.out.println(output[j]+","); 
	        		}
	        		
	        		for(int j=0;j<value;j++)
	        		{
	        			//重新将remove的值插入fibonacci heap
	        			hb.insert(KeyValue[j],ht,output[j]);
	        			
	        		}
	        		//System.out.println(hb.maximum()); 
	        	}
	        	//如果遇到 stop, 退出循环
	        	else if(split_data[i].equals("stop"))
	        	{
	        		break;
	        	}
	        	
	        }
	    }


	}


