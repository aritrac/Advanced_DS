package ctci.solutions.aritra;

/*
 * Question: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 * 
 * Solution: Without Links to Parents
 * Alternatively, you could follow a chain in which p and q are on the same side. That is, if p and q are both on the left of the node,
 * branch left to look for the common ancestor. If they are both on the right, branch right to look for the common ancestor. When p and q are no longer on the same side
 * you must have found the first common ancestor
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
		//Error check - one node is not in the tree.
		if(!covers(root,p) || !covers(root,q)){
			return null;
		}
		return ancestorHelper(root,p,q);
	}
	
	TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q){
		if(root == null || root == p || root == q){
			return root;
		}
		
		boolean pIsOnLeft = covers(root.left,p);
		boolean qIsOnLeft = covers(root.left,q);
		
		if(pIsOnLeft != qIsOnLeft){ //Nodes are on different side
			return root;
		}
		TreeNode childSide = pIsOnLeft? root.left : root.right;
		return ancestorHelper(childSide,p,q);
	}
	
	boolean covers(TreeNode root, TreeNode p){
		if(root == null)
			return false;
		if(root == p)
			return true;
		return covers(root.left, p) || covers(root.right, p);
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
