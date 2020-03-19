package ctci.solutions.aritra;

/*
 * Question: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 * 
 * Solution: Similar to the earlier approach, we could trace p's path upwards and check if any of the nodes cover q. The first node that covers q(we already know that every
 * node on this path will cover p) must be the first common ancestor.
 * 
 * Observe that we don't need to re-check the entire subtree. As we move from a node x to its parent y, all the nodes under x have already been checked for q. Therefore, 
 * we only need to check the new nodes uncovered which will be the nodes under x's sibling.
 * 
 * To implement this, we can just traverse upwards from p, storing the parent and the sibling node in a variable. (The sibling node is always a child of parent and refers to
 * newly uncovered subtree). At each iteration, sibling gets set to the old parent's sibling node and parent gets set to parent.parent
 * 
 * Sample Tree
 *                                                                                                       
                                                                                                                                     
                                                                                                                                     
                                                                                                                                     
                                                                                                                                     
                                                                  @@                                                                 
                                                                @@@@@@@                                                              
                                                               @@    @@@                                                             
                                                              @@      @@                                                             
                                                              @        @@                                                            
                                                             @@        @@                                                            
                                                             @@    A   @@                                                            
                                                              @        @@                                                            
                                                              @@      @@                                                             
                                                              @@@   @@@@                                                             
                                                            @@@ @@@@@@@                                                              
                                                           @@@    @@@@@                                                              
                                                          @@@        @@                                                              
                                                         @@           @@                                                             
                                                        @@             @@                                                            
                                                      @@@              @@@                                                           
                                                     @@@                @@                                                           
                                                    @@@                  @@                                                          
                                                   @@                     @@                                                         
                                                  @@                       @@                                                        
                                                @@@                        @@                                                        
                                               @@@                          @@                                                       
                                              @@@                            @@                                                      
                                             @@                               @@    @@@                                              
                                            @@                                @@ @@@@@@@@                                            
                                   @@@@@@  @@                                  @@@@    @@@                                           
                                 @@@@  @@@@@                                   @@@       @@                                          
                                @@@      @@@                                   @@         @                                          
                                @@        @@                                   @          @@                                         
                                @          @                                   @      C   @@                                         
                               @@      B   @@                                  @@         @                                          
                               @@          @@                                  @@        @@                                          
                                @          @                                    @@@    @@@                                           
                                @@        @@                                     @@@@@@@@@                                           
                                @@@      @@                                     @@  @@@  @@                                          
                               @@ @@@@@@@@                                      @@        @@                                         
                              @@   @@@@@@                                       @         @@                                         
                              @@       @@                                      @@          @@                                        
                             @@        @@                                      @@          @@                                        
                            @@          @@                                     @            @@                                       
                            @@          @@                                    @@             @@                                      
                           @@            @@                                   @@             @@                                      
                          @@              @                                   @               @@                                     
                          @@              @@                                 @@                @@                                    
                         @@                @                                 @@                @@                                    
                        @@                 @@                               @@                  @@                                   
                        @@                  @@                              @@                   @@                                  
                       @@                   @@                              @@                   @@                                  
                      @@                     @@                            @@                     @@                                 
                     @@                      @@                            @@                     @@                                 
                     @@                       @@@@                         @@                      @@                                
                    @@                      @@@@@@@@                      @@@                     @@@@@@@                            
                   @@                      @@@ @@  @@@                  @@@@;@@@                @@@@@@@@@@@                          
               @@@@@@                     @@        @@@                @@@    @@@              @@@       @@@                         
             @@@@@@@@                    @@          @@               @@        @@            @@           @@                        
            @@@    @@                    @@           @               @         @@            @             @                        
            @@      @@                   @            @@             @@          @            @       G     @                        
           @@       @@                   @            @@             @@     F    @            @@           @@                        
           @@        @                   @@     E     @               @@        @@             @@        @@@                        
           @@    D   @                   @@          @@               @@       @@@              @@@@   @@@@@                         
            @        @                    @@        @@@                @@@   @@@@                @@@@@@@@@                           
            @@      @@                     @@@     @@@                  @@@@@@@                                                      
             @@@  @@@                       @@@@@@@@                        @@                                                       
              @@@@@@                        @@ @@@                          @@                                                       
                                           @@                                @@                                                      
                                           @@                                 @                                                      
                                           @                                  @@                                                     
                                          @@                                   @@                                                    
                                          @@                                   @@                                                    
                                         @@                                     @@                                                   
                                         @@                                     @@                                                   
                                         @                                       @@                                                  
                                        @@                                       @@                                                  
                                        @@                                        @@                                                 
                                       @@                                         @@                                                 
                                       @@                                          @@@@                                              
                                       @                                         @@@@@@@@@                                           
                                      @@                                       @@@@     @@@@                                         
                                   @@@@                                        @@         @@                                         
                                 @@@@@@@@                                     @@           @@                                        
                                @@@    @@@                                    @@     H     @@                                        
                               @@        @@                                   @@           @@                                        
                               @          @@                                   @@         @@                                         
                              @@          @@                                    @@@@   @@@@                                          
                              @@     I    @@                                     @@@@@@@@@                                           
                               @@        @@                                                                                          
                               @@@      @@@                                                                                          
                                @@@@@@@@@@                                                                                           
                                  @@@@@@                                                                                             
                                      @@                                                                                             
                                      @@                                                                                             
                                       @@                                                                                            
                                       @@                                                                                            
                                        @@                                                                                           
                                         @                                                                                           
                                         @@                                                                                          
                                          @@                                                                                         
                                          @@                                                                                         
                                           @@                                                                                        
                                           @@                                                                                        
                                            @@                                                                                       
                                            @@                                                                                       
                                             @@                                                                                      
                                              @                                                                                      
                                              @@                                                                                     
                                               @@ @@@@@@@                                                                            
                                               @@@@@   @@@@                                                                          
                                               @@         @@                                                                         
                                               @           @                                                                         
                                              @@           @@                                                                        
                                              @@     J     @@                                                                        
                                               @@         @@                                                                         
                                               @@@      @@@                                                                         
                                                @@@@@@@@@@@                                                                          
                                                  @@@@@@@                                                                            
                                                                                                                                     
                                                                                                                                     
                                                                                                                                     
                                                                          
 * 											
 */

public class FirstCommonAncestor {
	public static void main(String[] args) {
		TreeNode a = new TreeNode("a");
		TreeNode b = new TreeNode("b");
		TreeNode c = new TreeNode("c");
		TreeNode d = new TreeNode("d");
		TreeNode e = new TreeNode("e");
		TreeNode f = new TreeNode("f");
		TreeNode g = new TreeNode("g");
		TreeNode h = new TreeNode("h");
		TreeNode i = new TreeNode("i");
		TreeNode j = new TreeNode("j");
		
		FirstCommonAncestor fca = new FirstCommonAncestor();
		a.left = b;
		a.right = c;
		
		b.left = d;
		b.right = e;
		b.parent = a;
		
		d.parent = b;
		
		e.left = i;
		e.parent = b;
		
		i.right = j;
		i.parent = e;
		
		j.parent = i;
		
		c.left = f;
		c.right = g;
		c.parent = a;
		
		f.right = h;
		f.parent = c;
		
		h.parent = f;
		
		g.parent = c;
		
		System.out.println("Common Ancestor of d and j = " + fca.commonAncestor(a, d, j).data);
		System.out.println("Common Ancestor of d and g = " + fca.commonAncestor(a, d, g).data);
		System.out.println("Common Ancestor of h and f = " + fca.commonAncestor(a, h, f).data);
	}
	
	TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q){
		//Check if either node is not in the tree, or if one covers the other
		if(!covers(root,p) || !covers(root,q)){
			return null;
		}else if(covers(p,q)){
			return p;
		}else if(covers(q,p)){
			return q;
		}
		
		//Traverse upwards until you find a node that covers q.
		TreeNode sibling = getSibling(p);
		TreeNode parent = p.parent;
		while(!covers(sibling,q)){
			sibling = getSibling(parent);
			parent = parent.parent;
		}
		return parent;
	}
	
	boolean covers(TreeNode root, TreeNode p){
		if(root == null)
			return false;
		if(root == p)
			return true;
		return covers(root.left, p) || covers(root.right, p);
	}
	
	TreeNode getSibling(TreeNode node){
		if(node == null || node.parent == null){
			return null;
		}
		
		TreeNode parent = node.parent;
		return parent.left == node? parent.right : parent.left;
	}
}

class TreeNode{
	String data;
	TreeNode left;
	TreeNode right;
	TreeNode parent;
	
	public TreeNode(String data){
		this.data = data;
	}
}
