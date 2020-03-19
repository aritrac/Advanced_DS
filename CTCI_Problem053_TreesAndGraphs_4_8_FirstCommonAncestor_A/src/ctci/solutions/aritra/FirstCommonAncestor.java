package ctci.solutions.aritra;

/*
 * Question: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 * 
 * Solution: With Link To Parents
 * If each node has a link to its parent, we could trace p and q's paths up until they intersect. This is essentially the same problem as question 2.7
 * which finds the intersection of two linked lists. The linked list in this case is the path from each node upto the root
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
		
		System.out.println("Common Ancestor of d and j = " + fca.commonAncestor(d, j).data);
		System.out.println("Common Ancestor of d and g = " + fca.commonAncestor(d, g).data);
		System.out.println("Common Ancestor of h and f = " + fca.commonAncestor(h, f).data);
	}
	
	TreeNode commonAncestor(TreeNode p, TreeNode q){
		int delta = depth(p) - depth(q); //get difference in depths
		TreeNode first = delta > 0 ? q : p; //get shallower node
		TreeNode second = delta > 0 ? p : q; //get deeper node
		second = goUpBy(second, Math.abs(delta)); //move deeper node up
		
		//Find where paths intersect
		while(first != second && first != null && second != null){
			first = first.parent;
			second = second.parent;
		}
		
		return first == null || second == null ? null : first;
	}
	
	int depth(TreeNode node){
		int depth = 0;
		while(node != null){
			node = node.parent;
			depth++;
		}
		return depth;
	}
	
	TreeNode goUpBy(TreeNode node, int delta){
		while(delta > 0 && node != null){
			node = node.parent;
			delta--;
		}
		return node;
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