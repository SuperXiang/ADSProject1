package hashtagcounter;

/**
 * 
 * @title maxFibHeap.java
 * @function construct the main functions for maxFibHeap, embedded with hashtable
 * @author Yingfei Xiang
 * @date 11/17/2016
 * 
 */
import java.util.*;

//Main functions for maxFibHeap
public class maxFibHeap {
	int num_nodes;           // number of the nodes
    maxFibNode max_node;     // the node with max value(root)

    //Initialize the variables
    public maxFibHeap() {
        this.num_nodes = 0;
        this.max_node = null;
    }
    
    //Add nodes before root(in linked list)
    private void add(maxFibNode node, maxFibNode parent) {
        node.leftsib = parent.leftsib;
        parent.leftsib.rightsib = node;
        node.rightsib = parent;
        parent.leftsib = node;
    }
   
    //Insert nodes into maxFibHeap, combine the nodes with hashtags and the key(using hashtable)
    public void insert(int key, Hashtable table,String hashtags) {
        maxFibNode node = new maxFibNode(key);
    	if (num_nodes == 0)
            max_node = node;
        else {
            add(node, max_node);
            //Adjust if new node is greater than max_node
            if (node.key > max_node.key)
            	max_node = node;
        }
    	num_nodes++;
        table.put(hashtags, node);
        node.hashtags = hashtags;
    }
    
    //Return the max key in maxFibHeap, return -1 if failed
    public int maxkey() {
        if (max_node == null)
        	return -1;
        return max_node.key;
    }
    
    //Return the String value combined with the max node
    public String maxString()
    {
    	String max_str = null;
    	if (max_node == null)
            return null;
    	max_str = max_node.hashtags;
    	return max_str;
    }
    
    //Link the node to parent node
    private void link(maxFibNode nd, maxFibNode prt)
    {
    	nd.leftsib.rightsib = nd.rightsib;
    	nd.rightsib.leftsib = nd.leftsib;
        if (prt.child == null)
        	prt.child = nd;
        else
            add(nd, prt.child);
        nd.parent = prt;
        prt.degree++;
        nd.mark_val = false;
    }
    
    //Remove the node with max key in maxFibHeap
    public void removeMax() {
        if (max_node == null) 
        	return ;
        maxFibNode l = max_node;
        //Add the child and child's siblings to maxFibNode(the root of the linked list)
        while (l.child != null) {
            maxFibNode child = l.child;
            child.leftsib.rightsib = child.rightsib;
            child.rightsib.leftsib = child.leftsib;
            if (child.rightsib == child)
                l.child = null;
            else
                l.child = child.rightsib;
            child.parent = null;
            add(child, max_node); 
        }
        l.leftsib.rightsib = l.rightsib;
        l.rightsib.leftsib = l.leftsib;
        //If l is the only node in the heap, set the max node to NULL
        //Else set the max node as l.leftsib, then consolidate()
        if (l.leftsib == l)
        {
        	max_node = null; 
        	//System.out.println("only max node!");
        } else {
        	max_node = l.leftsib;
            consolidate();
        }
        l = null;
        num_nodes--;  
    } 
    
    //Consolidate the nodes in maxFibHeap after the max node is removed
    private void consolidate() {
    	int max_degree = num_nodes;
        maxFibNode[] cns = new maxFibNode[max_degree+2];
        for (int i = 0; i < max_degree+1; i++)
        	cns[i] = null;
        //Merge the root node with same degrees
        while (max_node != null) {
            maxFibNode x = max_node;
            maxFibNode p = max_node;
            if(p==p.leftsib) max_node=null;
            else {
            	p.leftsib.rightsib = p.rightsib;
                p.rightsib.leftsib = p.leftsib;
                max_node = p.leftsib;
            	}
            p.leftsib = p;
            p.rightsib = p;
            int d = x.degree;  
            while (cns[d] != null) 
            {
                if (x.key < cns[d].key)
                {    
                    maxFibNode temp = x;
                    x = cns[d];
                    cns[d] = temp;
                }
                link(cns[d], x); 
                
                cns[d] = null;
                d++;
            }
            cns[d] = x;
        }
        max_node = null;
        //Add the nodes in cns to the root list
        for (int i = 0; i < max_degree + 1; i++) {
            if(cns[i] != null) {
                if (max_node == null) 
                	max_node = cns[i];
                else {
                    add(cns[i], max_node);
                    if ((cns[i]).key > max_node.key)
                    	max_node = cns[i];
                }
            }
        }
        
    }
   
    //Cut the node from its parent and then add it to the root linked list of maxFibHeap
    protected void cut(maxFibNode node, maxFibNode parent) {
        parent.degree--;
    	node.rightsib.leftsib = node.leftsib;
        node.leftsib.rightsib = node.rightsib;
        //If node has no siblings
        if (parent.degree == 0) {
            parent.child = null;
        }
        if (parent.child == node) {
            parent.child = node.rightsib;
        }
        node.leftsib = max_node;
        node.rightsib = max_node.rightsib;
        max_node.rightsib = node;
        node.rightsib.leftsib = node;
        node.parent = null;
        node.mark_val = false;
    }

    //If it violates the rule of maxFibHeap after increasing the key,
    //then cascading cut the node d
    protected void cascadingCut(maxFibNode d) {
    	maxFibNode parentd = d.parent;
    	if (parentd != null) {
    		if (!d.mark_val) {
    			d.mark_val = true;  //Set the mark_val to true
    		} else {
    			cut(d,parentd);
    			cascadingCut(parentd);
    		}
    	}
    }
    
    //Increase the old key of the node to new key
    public void increase(Hashtable<String, maxFibNode> table, String hashtags, int new_key) {
    	maxFibNode nd;
        nd = table.get(hashtags);
        int old_val = nd.key;
        int new_val = 0;
        if (nd!=null)
    	//Update the key(frequency) to new_key
        if(new_key > 0)
        {
        	new_val = new_key + old_val;
        	if (max_node == null ||nd==null) 
        		return ;
        	if (new_val < nd.key) 
        		return ;
        	maxFibNode parent = nd.parent;
        	nd.key = new_val;
        	//Cut the node from its parent and then add it to the root linked list
        	if (parent != null && (nd.key > parent.key)) {
        		cut(nd,parent);
        		cascadingCut(parent);
        	}
       //Renew the max node
       if (nd.key > max_node.key)
    	   max_node = nd;
        }
    }

}

