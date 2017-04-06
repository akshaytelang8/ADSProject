
import java.util.LinkedHashMap;
import java.util.Vector;

public class BinaryHeap {

	// int heapSize;
	Vector<Node> heap;
	LinkedHashMap<Integer, String> map;

	public BinaryHeap() {
		// heapSize = 0;
		heap = new Vector<Node>();
		map = new LinkedHashMap<Integer, String>();
	}

	public int size() {
		return heap.size();
	}

	public void insert(Node node) {
		// add new node
		heap.addElement(node);
		// heapSize++;

		if (heap.size() == 1) {
			return;
		}

		int index = heap.size() - 1;
		Node parent = heap.get((index - 1) / 2);

		// min heapify bottom up
		while (node.priority < parent.priority) {
			// swap
			// Node temp = node;
			// node = parent;
			// parent = temp;
			Node.nodeSwap(node, parent);

			index = (index - 1) / 2;
			if (index > 0) {
				node = parent;
				parent = heap.get((index - 1) / 2);
			} else {
				break;
			}

		}

	}

	public Node extractMin() {
		if (heap.size() == 0) {
			return null;
		}
		// get first (min) and last element
		Node min = heap.get(0);
		Node last = heap.get(heap.size() - 1);
		// swap
		// Node temp = min;
		// min = last;
		// last = temp;

		Node.nodeSwap(min, last);

		// store last (min) node to return
		Node returnNode = new Node(last);
		// remove last
		int lastIndex = heap.size() - 1;
		heap.remove(lastIndex);
		// heapSize--;

		if (heap.size() == 0) {
			return returnNode;
		}

		int index = 0;
		Node left = null;
		Node right = null;

		// min heapify top-down
		Node root = heap.get(index);
		if ((2 * index + 1) <= (heap.size() - 1))
			left = heap.get(2 * index + 1);
		if ((2 * index + 2) <= (heap.size() - 1))
			right = heap.get(2 * index + 2);

		while ((left != null && left.priority < root.priority) || (right != null && right.priority < root.priority)) {

			int leftPriority = left == null ? Integer.MAX_VALUE : left.priority;
			int rightPriority = right == null ? Integer.MAX_VALUE : right.priority;

			if (leftPriority < rightPriority) {
				Node.nodeSwap(root, left);
				root = left;
				index = 2 * index + 1;

				if ((2 * index + 1) <= (heap.size() - 1))
					left = heap.get(2 * index + 1);
				else
					left = null;
				if ((2 * index + 2) <= (heap.size() - 1))
					right = heap.get(2 * index + 2);
				else
					right = null;
				/*
				 * temp = root; root = left; left = temp;
				 */
			} else {
				Node.nodeSwap(root, right);
				root = right;
				index = 2 * index + 2;

				if ((2 * index + 1) <= (heap.size() - 1))
					left = heap.get(2 * index + 1);
				else
					left = null;
				if ((2 * index + 2) <= (heap.size() - 1))
					right = heap.get(2 * index + 2);
				else
					right = null;
				/*
				 * temp = root; root = right; right = temp;
				 */
			}
		}

		return returnNode;
	}

	public void print() {
		for (int i = 0; i < heap.size(); i++) {
			// System.out.print(heap.get(i).priority + " ");
		}
	}

	public void treeTraverse(Node root, String code) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			root.code = code;
			// System.out.println(root.val + " | " + root.code);
			map.put(root.val, root.code);
		}

		treeTraverse(root.left, code + "0");
		treeTraverse(root.right, code + "1");

	}

	public void buildHuffmanCode(BinaryHeap queue) {

		while (queue.size() > 1) {
			Node left = queue.extractMin();
			Node right = queue.extractMin();
			Node parent = new Node(-1, left.priority + right.priority);
			parent.left = left;
			parent.right = right;
			queue.insert(parent);
		}
	}

	public void populateHeap(BinaryHeap queue, int freqArr[]) {

		for (int i = 0; i < freqArr.length; i++) {
			if (freqArr[i] > 0)
				queue.insert(new Node(i, freqArr[i]));
		}

	}

	public LinkedHashMap<Integer, String> buildTreeUsingBinaryHeap(int freqArr[]) {
		BinaryHeap queue = new BinaryHeap();
		populateHeap(queue, freqArr);
		buildHuffmanCode(queue);
		Node root = queue.extractMin();
		queue.treeTraverse(root, "");
		return queue.map;
	}

	/*
	 * public static void main(String[] args) { BinaryHeap queue = new
	 * BinaryHeap();
	 * 
	 * queue.insert(new Node(30, 30)); queue.insert(new Node(4, 4));
	 * queue.insert(new Node(21, 21)); queue.insert(new Node(2, 2));
	 * queue.insert(new Node(25, 25)); queue.insert(new Node(12, 12));
	 * queue.insert(new Node(72, 72));
	 * 
	 * queue.print(); System.out.println("");
	 * 
	 * System.out.println("");
	 * 
	 * Node node = queue.extractMin(); System.out.println("" + node.priority);
	 * queue.print(); System.out.println("");
	 * 
	 * node = queue.extractMin(); System.out.println("" + node.priority);
	 * queue.print();
	 * 
	 * 
	 * queue.print(); System.out.println(""); Node root = queue.extractMin();
	 * queue.treeTraverse(root, ""); }
	 */

}
