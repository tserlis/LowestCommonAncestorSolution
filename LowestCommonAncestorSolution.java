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
	
	public void insert(int data) {
		root = insert(root, data);
	}
	
	private Node insert(Node node, int data) {
		if (node == null) {
			node = new Node(data);
		}
		else {
			if (data<=node.getData()) {
				node.left = insert(node.left, data);
			}
			else {
				node.right = insert(node.right, data);
			}
		}
		
		return node;
	}
	
	public boolean search(int data) {
		return search(root, data);
	}
	
	private boolean search(Node node, int data) {
		if (node.getData() == data) {
			return true;
		}
		if (node.getLeft() != null) {
			if (search(node.getLeft(), data)) {
				return true;
			}
		}
		if (node.getRight() != null) {
			if (search(node.getRight(), data)) {
				return true;
			}
		}
		return false;
	}

	public Node FindLCA(Node a, Node b) {
		return FindLCA(root, a, b);
	}
	
	/*
	 * FindLCA sourced from https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor.java
	 */
	private Node FindLCA(Node root, Node a, Node b) {
		if (root == null || root.getData() == a.getData() || root.getData() == b.getData()) {
			return root;
		}
		Node left = FindLCA(root.getLeft(), a, b);
		Node right = FindLCA(root.getRight(), a, b);
		
		if (left == null && right == null) {
			return null;
		}
		else if (left == null) {
			return right;
		}
		else {
			return root;
		}
	}
}
