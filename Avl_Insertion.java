//Program to perform insertion in AVL Tree

//Avl tree is a binary search tree but the  diff between left and right sub-tree height is not greter than 1

///////////////////////////////////  AVL Tree insertion - O(log(n))  //////////////////////////////

class Node {
		int val, height; // each node has value and height
		Node left, right; //for child/sub trees

		Node( int myval){
				val =myval;
				height=1;
			}
}

class AVL {

	Node root;

	int getHeight (Node N){
		if (N ==null)
			return 0;
		else 
			return N.height;

	}

	int max(int a, int b) {
        return (a > b) ? a : b;
    }

    //right rotation function
    Node rotateRight(Node current){
    	Node new_current= current.left;            //change current root
    	Node under_newcurrent = new_current.right;

    	new_current.right= current;                //after rotation
    	current.left= under_newcurrent;

    	//update heights of nodes after rotation
    	current.height = max(getHeight(current.left), getHeight(current.right)) + 1;
        new_current.height = max(getHeight(new_current.left), getHeight(new_current.right)) + 1;
 		
 		return new_current;   //return root node

    }

    Node rotateLeft (Node new_current) {   // left rotate is exact opposite of rotateRight function
    	Node current= new_current.right;
    	Node under_newcurrent= current.left;

    	current.left = new_current;		// after rotation
    	new_current.right = under_newcurrent;

    	//update heights of nodes after rotation
    	current.height = max(getHeight(current.left), getHeight(current.right)) + 1;
    	new_current.height = max(getHeight(new_current.left), getHeight(new_current.right)) + 1;
        
    	return current; // return root node

    }
    
    // function to return difference between left and right sub tree
   int getLeftRightDiff(Node N) {
        if (N == null)
            return 0;
 		else 
 			return getHeight(N.left) - getHeight(N.right);
    }
  
  // funtion to perform insertion into AVL tree
  // first perform recursive BST insertion, if the Left & Right height difference is greater than 1, perform apt rotation
  Node insert(Node node, int val){

        if (node == null)
  	    return (new Node(val));

  		if (val<node.val)
  			node.left= insert (node.left,val); // insert as left child is lower than root value
  		else if (val > node.val)
            node.right = insert(node.right, val); // insert as right child if higher than root value
        else 
            return node;    // this is case when the value is same as the root - do no operation

        
        node.height= max(getHeight(node.left),getHeight(node.right)) +1; //update hieght after insert

       int leftRightDiff =  getLeftRightDiff(node); //check difference of left and right sub tree
       
       //Left-Left - Do right rotation
       if (leftRightDiff >1 && val <node.left.val)
       	return rotateRight (node);

       //Left-Right - first make it Left-Left then do right rotation
       if (leftRightDiff > 1 && val > node.left.val) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

       //Right-Right - Do left rotation
       if (leftRightDiff <-1 && val> node.right.val) 
       	return rotateLeft(node);

       //Right - Left - first make it Right-Right then do left rotation
       if (leftRightDiff < -1 && val < node.right.val) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
}// end of insert function

//function to print tree

void printTree (Node node){
      if (node != null) {
		System.out.print(node.val+",");
			printTree(node.left);
			printTree(node.right);
		}

}

public static void main(String[] args){
 AVL myAVL = new AVL();
     myAVL.root = myAVL.insert(myAVL.root,5);  //inserting values
     myAVL.root = myAVL.insert(myAVL.root,15);
     myAVL.root = myAVL.insert(myAVL.root,30);
     myAVL.root = myAVL.insert(myAVL.root,38);
     myAVL.root = myAVL.insert(myAVL.root,45);
     myAVL.root = myAVL.insert(myAVL.root,22);

     System.out.println("Tree traversed in pre order : ");
        myAVL.printTree(myAVL.root);

}

}// end of AVL class

