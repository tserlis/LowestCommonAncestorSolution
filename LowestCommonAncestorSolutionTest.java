package proj2;

import static org.junit.Assert.*;

import org.junit.Test;

public class LowestCommonAncestorSolutionTest {

	@Test
	public void BTreeIsEmptyTest() {
		BT bt = new BT();
		assertTrue(bt.isEmpty());
		bt.insert(0);
		assertFalse(bt.isEmpty());
	}
	
	@Test
	public void TreeSearchTrue() {
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

}
