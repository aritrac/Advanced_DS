package ctci.solutions.aritra;

/*
 * Question: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 * 
 * Solution: 
 * We may recognize in the previous solution that we should only need to search the entire tree once to find p and q. We should then be able to bubble up the findings
 * to earlier nodes in the stack. The basic logic is the same as the earlier solution.
 * We recurse through the entire tree with a function called commonAncestor(TreeNode root, TreeNode p, TreeNode q). This function returns the following values
 * 1)Returns p, if root's subtree includes p and not q
 * 2)Returns q, if root's subtree includes q and not p
 * 3)Returns null, if neither p nor q are in root's subtree
 * 4)Else, returns the common ancestor of p and q
 * 
 * Finding the common ancestor of p and q in the final case is easy. When commonAncestor(n.left, p, q) and commonAncestor(n.right, p, q) both return non-null values
 * (indicating that p and q were found in different subtrees), then n will be the common ancestor
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
		Result r = commonAncestorHelper(root, p, q);
		if(r.isAncestor){
			return r.node;
		}
		return null;
	}
	
	Result commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q){
		if(root == null)
			return new Result(null,false);
		
		if(root == p && root == q){
			return new Result(root, true);
		}
		
		Result rx = commonAncestorHelper(root.left, p, q);
		if(rx.isAncestor){ //Found common ancestor
			return rx;
		}
		
		Result ry = commonAncestorHelper(root.right, p, q);
		if(ry.isAncestor){//Found common ancestor
			return ry;
		}
		
		if(rx.node != null && ry.node != null){
			return new Result(root, true); //This is the common ancestor
		}else if(root == p || root == q){
			//If we are currently at p or q, and we also found one of those nodes in a subtree, then this
			//truly is an ancestor and the flag should be true
			boolean isAncestor = rx.node != null || ry.node != null;
			return new Result(root, isAncestor);
		}else{
			return new Result(rx.node != null ? rx.node : ry.node, false);
		}
	}
	
	class Result{
		public TreeNode node;
		public boolean isAncestor;
		public Result(TreeNode n, boolean isAnc){
			node = n;
			isAncestor = isAnc;
		}
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
