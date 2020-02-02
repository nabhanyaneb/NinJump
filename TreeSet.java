public class TreeSet{

//Nabhanya Neb
	TreeNode root, rootC;
	TreeSet copy;
	int size=0;

	public TreeSet(){
		root=null;
	}
	public TreeSet(int val){
		root=new TreeNode(val);
		size=1;
	}

		public void add(int val){

			if (root==null) {
				root=new TreeNode(val);
				size++;
			}
		
			boolean end=false;
			TreeNode temp=root;

			while(!end){
				if (val!=temp.getValue()){
					if (val<temp.getValue() && temp.hasLeft()==true){
						temp=temp.getLeft();
					}
					else if (val>temp.getValue() && temp.hasRight()==true){
						temp=temp.getRight();
					}
					else{
						if (val<temp.getValue()){
							temp.setLeft(new TreeNode(val));
							size++;
						}
						else if (val>temp.getValue()){
							temp.setRight(new TreeNode(val));
							size++;
						}
					}
				}
				else
					break;

			
			}
		}

		public void traverseInOrder(TreeNode root){
			if (root != null)
				{ 
					traverseInOrder(root.getLeft());
					System.out.print(root.getValue()+" ");
					traverseInOrder(root.getRight());
				}
		}

		public void traversePreOrder(TreeNode root){
				if (root !=null)
				{	System.out.print(root.getValue()+" ");
					traversePreOrder(root.getLeft());
					traversePreOrder(root.getRight());
				}
		}

		public void traversePostOrder(TreeNode root){
				if (root !=null)
				{	traversePostOrder(root.getLeft());
					traversePostOrder(root.getRight());
					System.out.print(root.getValue()+" ");
				}
		}

		
		public TreeSet copyInOrder(TreeNode root){
			if (root==getRoot())
					copy=new TreeSet();
			if (root != null)
				{ 
					copyInOrder(root.getLeft());
					//System.out.println(root.getValue());
					copy.add(root.getValue());
					copyInOrder(root.getRight());
				}

				return copy;
		}

		public TreeSet copyPreOrder(TreeNode root){
			if (root==getRoot())
					copy=new TreeSet();

				if (root !=null)
				{	//System.out.println(root.getValue());
					copy.add(root.getValue());
					copyPreOrder(root.getLeft());
					copyPreOrder(root.getRight());
				}

				return copy;
		}

		public TreeSet copyPostOrder(TreeNode root){
			if (root==getRoot())
					copy=new TreeSet();
				if (root !=null)
				{	copyPostOrder(root.getLeft());
					copyPostOrder(root.getRight());
					//System.out.println(root.getValue());
					 copy.add(root.getValue());
				}

				return copy;
		}
		public TreeNode getRoot(){
			return root;
		}

		public int size(){
			return size;
		}



	public class TreeNode{
		TreeNode r;
		TreeNode l;
		int s;

		public TreeNode(int val){
			s=val;
			r=null;
			l=null;
		}

		public TreeNode(int val, TreeNode right, TreeNode left){
			s=val;
			r=right;
			l=left;
		}

		public TreeNode getRight(){
			return r;
		}

		public TreeNode getLeft(){
			return l;
		}

		public void setRight(TreeNode right){
			r=right;
		}

		public void setLeft(TreeNode left){
			l=left;
		}
		public boolean hasLeft(){
			if (l!=null) return true;
				return false;
		}

		public boolean hasRight(){
			if (r!=null) return true;
				return false;
		}	

		public int getValue(){
			return s;
		}	

		public String toString(){
			return "Value: "+s;
		}
	}
}