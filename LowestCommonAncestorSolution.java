class Node {
  int data;
  Node left;
  Node right;

  public Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
  
  public void setLeft(Node left) {
	  this.left = left;
  }
  
  public void setRight(Node right) {
	  this.right = right;
  }
  
  public Node getLeft() {
	  return left;
  }
  
  public Node getRight() {
	  return right;
  }
  
  public int getData() {
	  return data;
  }
}

class BT {
	
	private Node root;
	
	public BT() {
		root=null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	
}
