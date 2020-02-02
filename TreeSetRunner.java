public class TreeSetRunner{

//Nabhanya Neb	

	TreeSet set=new TreeSet();

	public TreeSetRunner(){
		while (set.size()<30){
			int rand=(int)(Math.random()*100)+1;
			set.add(rand);
		}
		System.out.println("Size: "+set.size());
		System.out.println("Pre:");
		set.traversePreOrder(set.getRoot());
		System.out.println();
		System.out.println("In: ");
		set.traverseInOrder(set.getRoot());
		System.out.println();
		System.out.println("Post: ");
		set.traversePostOrder(set.getRoot());
		System.out.println();
		System.out.println();


		TreeSet set2=set.copyPreOrder(set.getRoot());
		System.out.print("Pre Order of Set 2: ");
		set2.traversePreOrder(set2.getRoot());
		System.out.println();
		System.out.print("In Order of Set 2: ");
		set2.traverseInOrder(set2.getRoot());
		System.out.println();
		System.out.print("Post Order of Set 2: ");
		set2.traversePostOrder(set2.getRoot());	
		System.out.println();
		System.out.println();

		TreeSet set3=set.copyInOrder(set.getRoot());
		System.out.print("Pre Order of Set 3: ");
		set3.traversePreOrder(set3.getRoot());
		System.out.println();
		System.out.print("In Order of Set 3: ");
		set3.traverseInOrder(set3.getRoot());
		System.out.println();
		System.out.print("Post Order of Set 3: ");
		set3.traversePostOrder(set3.getRoot());	
		System.out.println();
		System.out.println("The Pre Order and the In Order transversals are the same. They Post Order is backwards of the Pre and In order. ");	
		System.out.println();
		System.out.println();	


		TreeSet set4=set.copyPostOrder(set.getRoot());
		System.out.print("Pre Order of Set 4: ");
		set4.traversePreOrder(set4.getRoot());
		System.out.println();
		System.out.print("In Order of Set 4: ");
		set4.traverseInOrder(set4.getRoot());
		System.out.println();
		System.out.print("Post Order of Set 4: ");
		set4.traversePostOrder(set4.getRoot());	
		System.out.println();
		System.out.println("None of the transversals are the same. They are no distinct patterns that can be identified.");
		System.out.println();
		System.out.println();

	}



	public static void main (String[] args){
		TreeSetRunner app=new TreeSetRunner();
	}


}