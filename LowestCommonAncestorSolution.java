import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;


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



///////////////////////////////////////////////////////////////////
//// DirectedAcyclicGraph.java

/**
 A directed acyclic graph (DAG).


 @author Yuhong Xiong, Shuvra S. Bhattacharyya
 @version $Id: DirectedAcyclicGraph.java 57040 2010-01-27 20:52:32Z cxh $
 @since Ptolemy II 0.2
 @Pt.ProposedRating Green (yuhong)
 @Pt.AcceptedRating Green (kienhuis)
 */

class DirectedAcyclicGraph {
    /** Construct an empty DAG.
     */
	
	private static final class MultiMap {
		private final Map fMap= new LinkedHashMap();

		/**
		 * Adds <code>val to the values mapped to by key. If
		 * <code>val is null, key is added to the key set of
		 * the multimap.
		 * 
		 * @param key the key
		 * @param val the value
		 */
		public void put(Object key, Object val) {
			Set values= (Set) fMap.get(key);
			if (values == null) {
				values= new LinkedHashSet();
				fMap.put(key, values);
			}
			if (val != null)
				values.add(val);
		}

		/**
		 * Returns all mappings for the given key, an empty set if there are no mappings.
		 * 
		 * @param key the key
		 * @return the mappings for <code>key
		 */
		public Set get(Object key) {
			Set values= (Set) fMap.get(key);
			return values == null ? Collections.EMPTY_SET : values;
		}

		public Set keySet() {
			return fMap.keySet();
		}

		/**
		 * Removes all mappings for <code>key and removes key from the key
		 * set.
		 * 
		 * @param key the key to remove
		 * @return the removed mappings
		 */
		public Set removeAll(Object key) {
			Set values= (Set) fMap.remove(key);
			return values == null ? Collections.EMPTY_SET : values;
		}

		/**
		 * Removes a mapping from the multimap, but does not remove the <code>key from the
		 * key set.
		 * 
		 * @param key the key
		 * @param val the value
		 */
		public void remove(Object key, Object val) {
			Set values= (Set) fMap.get(key);
			if (values != null)
				values.remove(val);
		}
		
		/*
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return fMap.toString();
		}
	}
	
	private final MultiMap fOut= new MultiMap();
	private final MultiMap fIn= new MultiMap();
	
	int nodeCount = 0;
    public DirectedAcyclicGraph() {
        super();
    }

    /** Construct an empty DAG with enough storage allocated
     *  for the specified number of elements.  Memory management is more
     *  efficient with this constructor if the number of elements is
     *  known.
     *  @param nodeCount The number of elements.
     */
    public DirectedAcyclicGraph(int nodeCount) {
    	super(nodeCount);
    	this.nodeCount = nodeCount;  
    }
    
    /**
	 * Adds a vertex to the graph. If the vertex does not exist prior to this call, it is added with
	 * no incoming or outgoing edges. Nothing happens if the vertex already exists.
	 * 
	 * @param vertex the new vertex
	 */
	public void addVertex(Object vertex) {
		if (vertex != null) {
		fOut.put(vertex, null);
		fIn.put(vertex, null);
		}
	}

    ///////////////////////////////////////////////////////////////////
    ////                         public methods                    ////

    
    /** Compare two elements in this CPO.
     *  @param e1 An Object representing a CPO element.
     *  @param e2 An Object representing a CPO element.
     *  @return One of <code>CPO.LOWER, CPO.SAME,
     *   CPO.HIGHER, CPO.INCOMPARABLE</code>.
     *  @exception IllegalArgumentException If at least one of the
     *   specified Objects is not an element of this CPO.
     */
    public int compare(Node e1, Node e2) {
        _validate();

        int i1 = e1.getData();
        int i2 = e2.getData();

        return _compareNodeId(i1, i2);
    }

    
    /** Test if this CPO is a lattice.
     *  By a theorem in Davey and Priestley, only the LUB or the GLB
     *  need to be checked, but not both. The implementation tests the
     *  existence of the LUB of any pair of elements, as well as the
     *  existence of the bottom and top elements. The complexity is
     *  O(|N|*|N|) where N for elements, and an individual computation
     *  is the LUB of two elements.
     *  @return True if this CPO is a lattice;
     *   <code>false</code> otherwise.
     */
    public boolean isLattice() {
        _validate();

        if ((bottom() == null) || (top() == null)) {
            return false;
        }

        Object[] nodes = weightArray(nodes());

        for (int i = 0; i < (nodes.length - 1); i++) {
            for (int j = i + 1; j < nodes.length; j++) {
                if (leastUpperBound(nodes[i], nodes[j]) == null) {
                    // Uncomment this to find the offending nodes.
                    // System.out.println(">>>>> No LUB: " + nodes[i] + ", " + nodes[j]);
                    return false;
                }
            }
        }

        return true;
    }


    /** Compute the least upper bound (LUB) of two elements.
     *  @param e1 An Object representing an element in this CPO.
     *  @param e2 An Object representing element in this CPO.
     *  @return An Object representing the LUB of the two specified
     *   elements, or <code>null</code> if the LUB does not exist.
     *  @exception IllegalArgumentException If at least one of the
     *   specified Objects is not an element of this CPO.
     */
    public Node leastUpperBound(Node e1, Node e2) {
        _validate();
        return _lubShared(e1, e2);
    }

    /** Compute the up-set of an element in this CPO.
     *  @param e An Object representing an element in this CPO.
     *  @return An array of Objects representing the elements in the
     *   up-set of the specified element.
     *  @exception IllegalArgumentException If the specified Object is not
     *   an element of this CPO.
     */
    public Object[] upSet(Object e) {
        _validate();
        return _upSetShared(e);
    }
    
    public boolean addEdge(Object origin, Object target) {
		if (origin != null) {
			if (target != null){
				if (hasPath(target, origin)){
					return false;
				}
				fOut.put(origin, target);
				fOut.put(target, null);
				fIn.put(target, origin);
				fIn.put(origin, null);
				return true;
			}
		}
	}
    
    private boolean hasPath(Object start, Object end) {
		// break condition
		if (start == end)
			return true;

		Set children= fOut.get(start);
		for (Iterator it= children.iterator(); it.hasNext();)
			// recursion
			if (hasPath(it.next(), end))
				return true;
		return false;
	}

 
    ///////////////////////////////////////////////////////////////////
    ////                         private methods                   ////
    // compare two elements using their nodeIds using _closure.
    private int _compareNodeId(int i1, int i2) {
        if (i1 == i2) {
            return 0;
        }

        if (_closure[i1][i2]) {
            return -1;
        }

        if (_closure[i2][i1]) {
            return 1;
        }

        return 2;	//incomparable
    }

 
    // compute the lub using _closure.  This method is shared by
    // leastUpperBound() and greatestLowerBound()
    private Node _lubShared(Node e1, Node e2) {
        int i1 = e1.getData();
        int i2 = e2.getData();

        int result = _compareNodeId(i1, i2);

        if ((result == -1) || (result == 0)) {
            return e2;
        } else if (result == 1) {
            return e1;
        } else { // incomparable

            // an array of flags indicating if the ith element is an
            // upper bound.
            int size = nodeCount;
            boolean[] isUpperBound = new boolean[size];
            int numUpperBound = 0;

            for (int i = 0; i < size; i++) {
                isUpperBound[i] = false;

                if (_closure[i1][i] && _closure[i2][i]) {
                    isUpperBound[i] = true;
                    numUpperBound++;
                }
            }

            // if the number of upper bounds is 0, there is no upper bound.
            // else, put all upper bounds in an array.  if there is only
            // one element in array, that is the LUB; if there is more than
            // one element, find the least one, which may not exist.
            if (numUpperBound == 0) { // This CPO has no top.
                return null;
            } else {
                int[] upperBound = new int[numUpperBound];
                int count = 0;

                for (int i = 0; i < size; i++) {
                    if (isUpperBound[i]) {
                        upperBound[count++] = i;
                    }
                }

                if (numUpperBound == 1) {
                    return upperBound[0];
                } else {
                    return _leastElementNodeId(upperBound);
                }
            }
        }
    }
    
 // compute the least element of a subset nodeIds using _closure.
    // if ids.length = 0, return null.
    private Object _leastElementNodeId(int[] ids) {
        // Algorithm: Use a linked list storing all the elements incomparable
        // with at least one other. The least element, if it exists, must be
        // less than all the elements in this list. Compare the elements in
        // the ids array in consecutive pairs. Elements found  higher in a
        // pair-comparison are removed from the ids array. Elements found
        // incomparable are removed from the ids array and put into the list.
        // If two elements are found equal, one of them is arbitrarily removed
        // from the ids array. Repeat the above process until the ids array
        // contains no more than one element. In the end, if the ids array
        // contains no elements, return null. If it contains an element,
        // compare it with all the elements in the list. If it is found lower
        // than all of them, then this is the least element, otherwise there
        // exists no least element.
        // This algorithm computes the least element of a poset in O(n) time.
        // (ematsi 09/2003)
        // list of incomparable elements.
        LinkedList incomparables = new LinkedList();
        int virtualLength = ids.length;

        while (virtualLength > 1) {
            int i;
            int virtualIndex = 0;
            int numberOfRemovedElements = 0;

            for (i = 0; i < (virtualLength - 1);) {
                switch (_compareNodeId(ids[i++], ids[i++])) {
                case -1:
                case 0:
                    ids[virtualIndex++] = ids[i - 2];
                    numberOfRemovedElements++;
                    break;

                case 1:
                    ids[virtualIndex++] = ids[i - 1];
                    numberOfRemovedElements++;
                    break;

                case 2:
                    incomparables.addLast(Integer.valueOf(ids[i - 2]));
                    incomparables.addLast(Integer.valueOf(ids[i - 1]));
                    numberOfRemovedElements += 2;
                    break;

                default:
                }
            }

            if (i == (virtualLength - 1)) {
                ids[virtualIndex] = ids[i];
            }

            virtualLength -= numberOfRemovedElements;
        }

        if (virtualLength == 0) {
            return null;
        } else if (incomparables.size() != 0) {
            for (ListIterator iterator = incomparables.listIterator(0); iterator
                    .hasNext();) {
                int result = _compareNodeId(ids[0], ((Integer) iterator.next())
                        .intValue());

                if ((result == 1) || (result == 2)) {
                    return null;
                }
            }
        }

        return ids[0];
    }

    

   

    // call sequence (the lower methods are called by the higher ones):
    //
    // leastUpperBound     leastUpperBound([])     leastElement
    // greatestLowerBound  greatestLowerBound([])  greatestElement
    //         |                    |                    |
    //         |                    |                    |
    // _lubShared(Object) _lubShared(Object[])   _leastElementShared
    //         |                    |                    |
    //         -------------------------------------------
    //                              |
    //                  _leastElementNodeId(int[])
    //
    // downSet
    // upSet
    //   |
    // _upSetShared
    // compute transitive closure.  Throws GraphStateException if detects
    // cycles.  Find bottom and top elements.
    private void _validate() {
        boolean[][] transitiveClosure = transitiveClosure();

        if (!((CachedStrategy) _transitiveClosureAnalysis.analyzer())
                .obsolete()
                && isAcyclic()) {
            _closure = transitiveClosure;
            return;
        }

        if (!isAcyclic()) {
            throw new GraphStateException(
                    "DirectedAcyclicGraph._validate: Graph is cyclic.");
        }

        // find bottom
        _bottom = null;

        for (int i = 0; i < nodeCount(); i++) {
            if (inputEdgeCount(node(i)) == 0) {
                if (_bottom == null) {
                    _bottom = nodeWeight(i);
                } else {
                    _bottom = null;
                    break;
                }
            }
        }

        // find top
        _top = null;

        for (int i = 0; i < nodeCount(); i++) {
            if (outputEdgeCount(node(i)) == 0) {
                if (_top == null) {
                    _top = nodeWeight(i);
                } else {
                    _top = null;
                    break;
                }
            }
        }

        _closure = transitiveClosure;
        _tranClosureTranspose = null;
    }

    // compute the transposition of transitive closure and point _closure
    // to the transposition
    private void _validateDual() {
        _validate();

        boolean[][] transitiveClosure = transitiveClosure();

        if (_tranClosureTranspose == null) {
            int size = transitiveClosure.length;
            _tranClosureTranspose = new boolean[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    _tranClosureTranspose[i][j] = transitiveClosure[j][i];
                }
            }
        }

        _closure = _tranClosureTranspose;
    }

    ///////////////////////////////////////////////////////////////////
    ////                         private variables                 ////
    // _closure = _transitiveClosure for lub, upSet, leastElement;
    // _closure = _tranClosureTranspose for the dual operations: glb,
    //   downSet, greatestElement.
    // all the private methods, exception _validate() and _validateDual(),
    // use _closure instead of _transitiveClosure or _tranClosureTranspose.
    private boolean[][] _closure = null;

    private boolean[][] _tranClosureTranspose = null;

    // The graph analysis for computation of the transitive closure.
    private TransitiveClosureAnalysis _transitiveClosureAnalysis;

    private Object _bottom = null;

    private Object _top = null;
}
