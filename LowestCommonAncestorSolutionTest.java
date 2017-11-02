import static org.junit.Assert.*;

import org.junit.Test;

public class LowestCommonAncestorSolutionTest {
	
	@Test
	public void NodeGetDataTest() {
		Node node = new Node(0);
		assertEquals(0, node.getData());
	}
	
	@Test
	public void NodeSetLeftGetLeftTest() {
		Node node = new Node(0);
		Node left = new Node(1);
		node.setLeft(left);
		assertSame(left, node.getLeft());
	}
	
	@Test
	public void NodeSetRightGetRightTest() {
		Node node = new Node(0);
		Node right = new Node(1);
		node.setRight(right);
		assertSame(right, node.getRight());
	}

	@Test
	public void BTreeIsEmptyTestAndInsertTest() {
		BT bt = new BT();
		assertTrue(bt.isEmpty());
		bt.insert(0);
		assertFalse(bt.isEmpty());
	}
	
	@Test
	//test to find the LCA when it is the root node
	public void FindLCAWhenRootNode() {
		BT bt = new BT();
		bt.insert(1);
		bt.insert(0);
		bt.insert(2);
		Node a = new Node(1);
		Node b = new Node(2);
		assertEquals(1, bt.FindLCA(a, b).getData());
	}
	
	@Test
	public void LCAIn1NodeTreeErrorTest() {
		BT bt = new BT();
		bt.insert(0);
		Node a = new Node(1);
		Node b = new Node(2);
		assertEquals(null, bt.FindLCA(a, b));
	}
	
	@Test
	 //test for finding LCA in a tree with only one branch
	public void LCAin1LeftBranchTree() {
		BT bt = new BT();
		bt.insert(1);
		bt.insert(0);
		Node a = new Node(1);
		Node b = new Node(0);
		assertEquals(1, bt.FindLCA(a, b).getData());
	}
	
	@Test
	public void LCAin1RightBranchTree() {
		BT bt = new BT();
		bt.insert(0);
		bt.insert(1);
		Node a = new Node(0);
		Node b = new Node(1);
		assertEquals(0, bt.FindLCA(a, b).getData());
	}
	
	@Test
	public void LCAAtDifferentHeightsInTree() {
		BT bt = new BT();
		bt.insert(4);
		bt.insert(3);
		bt.insert(2);
		bt.insert(1);
		bt.insert(5);
		bt.insert(0);
		Node a = new Node(3);
		Node b = new Node(1);
		assertEquals(4, bt.FindLCA(a, b).getData());
	}
	
	@Test
	public void LCAAtSameHeightInTree() {
		BT bt = new BT();
		bt.insert(4);
		bt.insert(3);
		bt.insert(2);
		bt.insert(1);
		bt.insert(5);
		bt.insert(0);
		Node a = new Node(2);
		Node b = new Node(3);
		assertEquals(4, bt.FindLCA(a, b).getData());
	}
	
	@Test
	public void LCAAllRightBranches() {
		BT bt = new BT();
		bt.insert(0);
		bt.insert(1);
		bt.insert(2);
		Node a = new Node(1);
		Node b = new Node(2);
		assertEquals(1, bt.FindLCA(a, b).getData());
	}
  
  @Test
	public void LCAin2ElementDAG() {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph(2);
		Node a = new Node(0);
		Node b = new Node(1);
		dag.addVertex(a);
		dag.addVertex(b);
		dag.addEdge(a, b);
		assertEquals(0, dag.leastUpperBound(a, b).getData());
	}
	
	@Test
	public void LCAin3ElementGraph() {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph(2);
		Node a = new Node(0);
		Node b = new Node(1);
		Node c = new Node(2);
		dag.addVertex(a);
		dag.addVertex(b);
		dag.addVertex(c);
		dag.addEdge(a, b);
		dag.addEdge(a, c);
		assertEquals(0, dag.leastUpperBound(b, c).getData());
	}
	
	@Test
	public void LCAinAllOneRouteDAG() {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph(2);
		Node a = new Node(0);
		Node b = new Node(1);
		Node c = new Node(2);
		dag.addVertex(a);
		dag.addVertex(b);
		dag.addVertex(c);
		dag.addEdge(a, b);
		dag.addEdge(b, c);
		assertEquals(1, dag.leastUpperBound(b, c).getData());
	}
	
	@Test
	public void LCAatDifferentDepthsInGraph() {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph(2);
		Node a = new Node(0);
		Node b = new Node(1);
		Node c = new Node(2);
		Node d = new Node(3);
		dag.addVertex(a);
		dag.addVertex(b);
		dag.addVertex(c);
		dag.addVertex(d);
		dag.addEdge(a, b);
		dag.addEdge(b, c);
		dag.addEdge(a, d);
		assertEquals(0, dag.leastUpperBound(d, c).getData());
	}
}
