package hashtagcounter;

/**
 * 
 * @title maxFibNode.java
 * @function build up the basic structure for maxFibHeap
 * @author Yingfei Xiang
 * @date 11/17/2016
 * 
 */
//Node structure for maxFibHeap
public class maxFibNode {        
	        maxFibNode leftsib;    // left sibling
	        maxFibNode rightsib;   // right sibling
	        maxFibNode child;      // child of the node
	        maxFibNode parent;     // parent of the node
	        int key;               // key of the node
	        int degree;            // degree of the node
	        boolean mark_val;      // mark the node whether its child is removed
	        String hashtags;       // store hashtags in string 

	        //Initialize the variables and parameters
	        public maxFibNode(int key) {
	            this.leftsib = this;
	            this.rightsib = this;
	            this.child = null;
	            this.parent = null;
	            this.key = key;
	            this.degree = 0;
	            this.mark_val = false;
	            this.hashtags = null;
	        }
}
