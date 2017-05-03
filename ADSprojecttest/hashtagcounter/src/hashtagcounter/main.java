package hashtagcounter;

/**
 * 
 * @title main.java
 * @function the entry function of the project, select one sample data file as input source
 *           and one text file as output destination. The code can find out most popular N
 *           hashtags from input and then output the Top N hashtags in sequence separated by 
 *           commas.
 *           The usage:
 *           1. Use makefile: In console window, locate the directory of makefile. Type "make",
 *              then it shows out 6 options: make new; make build; make clean; make rebuild; 
 *              make run; make jar. The details are shown in REPORT and makefile comment.
 *              "make new: new project, create src, bin (or res) directories."
 *				"make build: build project."
 *				"make clean: clear classes generated."
 *				"make rebuild: rebuild project."
 *				"make run inputfile=A outputfile=B: run the project, replace A with loc of input file, 
 *				 replace B with loc of output file."
 *				"make jar: package the project into an executable jar."
 *			 2. Use console and jar: Since hashtagcounter.jar is the executable jar package for
 *				this project and it is put in the root directory. Then just locate the hashtagcounter.jar
 *				and type "java -jar hashtagcounter.jar args[0] args[1]".
 *				Mention: args[0] should be replaced with the path of input data file; Similarly,
 *           			 args[1] should be replaced with the path of output file
 *           3. Run in Eclipse or Java IDE: I also implemented the direct solution to the project. Instead of
 *           	using args[0] and args[1], you can set the inputfileName and outputfileName. Then run the project.
 * @author Yingfei Xiang
 * @date 11/17/2016
 * 
 */
import java.io.*;
import java.util.*;
	
public class  main {
	//There are 3 different ways to read from input data files as following:
	//Method 1: Type all the input data in input_data String
	
	//The sample input data listed in Project1.pdf
	/*private static String input_data = "#saturday 5 #sunday 3 #saturday 10 #monday 2 #reading 4 "
	   + "#playing_games 2 #saturday 6 #sunday 8 #friday 2 #tuesday 2 #saturday 12 #sunday 11 "
	   + "#monday 6 3 #saturday 12 #monday 2 "
	   + "#stop 3 #playing 4 #reading 15 #drawing 3 #friday 12 #school 6 #class 5 5 stop";
	*/
	private static String input_data;
	
	public static void main(String[]args) throws FileNotFoundException {
		//Method 2: Set the exact location(path) of input data file and output file
		
		//String inputfileName = "F://workplace/ADSprojecttest/hashtagcounter/src/hashtagcounter/sampleInput.txt";
		//String outputfileName = "F://workplace/ADSprojecttest/hashtagcounter/output_test2.txt";
		
		//Method 3: Set the the location(path) of input data file and output file as two parameters, simply type
		//the location you desire
	    String inputfileName = args[0];
	    String outputfileName = args[1];
	    
	    //Use BufferedReader to read lines from input file
	    /*try {
	     *    //StringBuffer sb= new StringBuffer("");
	     *    FileReader fr = new FileReader(fileName);
	     *    BufferedReader br = new BufferedReader(fr);
	     *    String line;
	     *    while((line = br.readLine()) != null){
	     *    //sb.append(line+"/n");
	     *    input_data += line + "\n";
	     *    System.out.println(input_data);
	     *    }
	     *    br.close();
	     *    fr.close();
	     *    }catch(Exception e){
	     *    e.printStackTrace();
	     *    }*/
	    
	    //Use Scanner to read data from input file
	    File file = new File(inputfileName);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    try {
	            while(scanner.hasNextLine()) {
	                fileContents.append(scanner.nextLine() + " ");
	            }
	            input_data = fileContents.toString();
	            System.out.println(input_data);
	    	}finally {
	    		//Close Scanner
	            scanner.close();
	        }
	    	
	    	//New maxFibHeap
	        maxFibHeap mfh=new maxFibHeap();
	        //Build hashtable
            Hashtable<String, maxFibNode> ht = new Hashtable<String, maxFibNode>();
            //Split input data by space
	        String[] split_data = input_data.split("\\s+");
	        for(int i=0; i<split_data.length;i++) {
	        	//Judge whether first char is #
	        	if(split_data[i].charAt(0)=='#')
	        	{
	        		//Get the frequency of the hashtag
	        		int value = Integer.parseInt(split_data[i+1]);
	        		//Judge whether the maxFibHeap is empty
	        		if(mfh.maxkey()==-1)
	        		{
	        			//Insert the string(hashtag) and combine hashtables with nodes	
	        			mfh.insert(value,ht,split_data[i]);
	        			//System.out.println( split_data[i]);
	        		}
	        		else
	        		{
	        			//If maxFibHeap is not empty
	        			if(ht.containsKey(split_data[i]))
	        			{
	        				//If the string(hashtag) exists in maxFibHeap
	        				mfh.increase(ht,split_data[i],value);
	        				//System.out.println( split_data[i]);
	        			}
	        			else
	        			{
	        				//If the string(hashtag) does not exist in maxFibHeap
	        				mfh.insert(value,ht,split_data[i]);
	        				//System.out.println( split_data[i]);
	        			}
	        		}
	        		i++;
	        	}
	        	//Output the result if the first char is digit
	        	else if(Character.isDigit(split_data[i].charAt(0)))
	        	{
	        		//Use PrintWriter to output result to destination text file
	        		PrintWriter out;
	        		try{
	        		out = new PrintWriter(new FileWriter(outputfileName,true));	     		
	        		int value = Integer.parseInt(split_data[i]);
	        		System.out.println(" ");
	        		out.println("");
	        		//Get the string(hashtag) and key value of the max node
	        		String []opt=new String [value];
	        		String []oopt=new String [value];
	        		int [] KeyValue=new int [value];
	        		
	        		if(value >= 1){
	        		//Output the string(hashtag) of the max node
	        	    KeyValue[0]=mfh.maxkey();
	        	    opt[0]=mfh.maxString();
	        	    oopt[0]=opt[0].replaceAll("#","");
        			mfh.removeMax();
        			System.out.print(oopt[0]);
        			out.print(oopt[0]);
	        		}
	        		for(int j=1;j<value;j++)
	        		{
	        			KeyValue[j]=mfh.maxkey();
	        			opt[j]=mfh.maxString();
	        			oopt[j]=opt[j].replaceAll("#","");
	        			mfh.removeMax();
	        			System.out.print(","+oopt[j]);
	        			out.print(","+oopt[j]);
	        		}
	        		for(int j=0;j<value;j++)
	        		{
	        			//Re-insert the string(hashtag) that has been removed from heap
	        			mfh.insert(KeyValue[j],ht,opt[j]);
	        		}
	        		//Close PrintWriter
	        		out.close();
	        	}catch (IOException e) {
	                e.printStackTrace();
	            } 
	        	}
	        	//If encounters STOP, exit
	        	else if(split_data[i].equals("stop"))
	        	{
	        		break;
	        	}
	        	
	        }
	    }

}


