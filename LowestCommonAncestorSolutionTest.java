

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
	public void BTreeIsEmptyTest() {
		BT bt = new BT();
		assertTrue(bt.isEmpty());
		bt.insert(0);
		assertFalse(bt.isEmpty());
	}
	
	@Test
	public void TreeSearchTrueAndInsertTest() {
		BT bt = new BT();
		bt.insert(0);
		assertTrue(bt.search(0));
	}
	
	@Test
	public void TreeSearchFalse() {
		BT bt = new BT();
		bt.insert(0);
		assertFalse(bt.search(2));
	}
	
	@Test
	//test to find the LCA when it is the root node
	public void FindLCAWhenRootNode() {
		BT bt = new BT();
		bt.insert(0);
		bt.insert(1);
		bt.insert(2);
		
		assertEquals(0, bt.FindLCA(1,2));
	}
	
	@Test
	public void LCAIn1NodeTreeTest() {
		BT bt = new BT();
		bt.insert(0);
		assertEquals(0, bt.FindLCA(0, 0));
	}
	
	@Test
	public void LCAAtDifferentHeightsInTree() {
		BT bt = new BT();
		bt.insert(0);
		bt.insert(1);
		bt.insert(2);
		bt.insert(3);
		bt.insert(4);
		bt.insert(5);
		bt.insert(6);
		assertEquals(2, bt.FindLCA(6, 4));
	}
	
	@Test
	public void LCAAtSameHeightInTree() {
		BT bt = new BT();
		bt.insert(0);
		bt.insert(1);
		bt.insert(2);
		bt.insert(3);
		bt.insert(4);
		bt.insert(5);
		assertEquals(2, bt.FindLCA(5, 4));
		
	}

}
