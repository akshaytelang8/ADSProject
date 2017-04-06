
import java.util.LinkedHashMap;
import java.util.Vector;

public class FourWayHeap {

	// int heapSize;
	Vector<Node> heap;
	LinkedHashMap<Integer, String> map;

	public FourWayHeap() {
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
		// 4-way
		Node parent = heap.get((index - 1) / 4);

		// min heapify bottom up
		while (node.priority < parent.priority) {
			// swap
			// Node temp = node;
			// node = parent;
			// parent = temp;
			Node.nodeSwap(node, parent);

			// 4-way
			index = (index - 1) / 4;
			if (index > 0) {
				node = parent;
				parent = heap.get((index - 1) / 4);
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
		// Node left = null;
		// Node right = null;

		Node first = null;
		Node second = null;
		Node third = null;
		Node fourth = null;

		// min heapify top-down
		Node root = heap.get(index);
		if ((4 * index + 1) <= (heap.size() - 1))
			first = heap.get(4 * index + 1);
		if ((4 * index + 2) <= (heap.size() - 1))
			second = heap.get(4 * index + 2);
		if ((4 * index + 3) <= (heap.size() - 1))
			third = heap.get(4 * index + 3);
		if ((4 * index + 4) <= (heap.size() - 1))
			fourth = heap.get(4 * index + 4);

		while ((first != null && first.priority < root.priority) || (second != null && second.priority < root.priority)
				|| (third != null && third.priority < root.priority)
				|| (fourth != null && fourth.priority < root.priority)) {

			int firstPriority = first == null ? Integer.MAX_VALUE : first.priority;
			int secondPriority = second == null ? Integer.MAX_VALUE : second.priority;
			int thirdPriority = third == null ? Integer.MAX_VALUE : third.priority;
			int fourthPriority = fourth == null ? Integer.MAX_VALUE : fourth.priority;

			// find smallest among four
			if (firstPriority <= secondPriority && firstPriority <= thirdPriority && firstPriority <= fourthPriority) {
				index = 4 * index + 1;
				Node.nodeSwap(root, first);
				root = first;
			} else if (secondPriority <= firstPriority && secondPriority <= thirdPriority
					&& secondPriority <= fourthPriority) {
				index = 4 * index + 2;
				Node.nodeSwap(root, second);
				root = second;
			} else if (thirdPriority <= firstPriority && thirdPriority <= secondPriority
					&& thirdPriority <= fourthPriority) {
				index = 4 * index + 3;
				Node.nodeSwap(root, third);
				root = third;
			} else if (fourthPriority <= firstPriority && fourthPriority <= secondPriority
					&& fourthPriority <= thirdPriority) {
				index = 4 * index + 4;
				Node.nodeSwap(root, fourth);
				root = fourth;
			}

			first = (4 * index + 1) <= (heap.size() - 1) ? heap.get(4 * index + 1) : null;
			second = (4 * index + 2) <= (heap.size() - 1) ? heap.get(4 * index + 2) : null;
			third = (4 * index + 3) <= (heap.size() - 1) ? heap.get(4 * index + 3) : null;
			fourth = (4 * index + 4) <= (heap.size() - 1) ? heap.get(4 * index + 4) : null;

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
			map.put(root.val, root.code);
			// System.out.println(root.val + " | " + root.code);
		}

		treeTraverse(root.left, code + "0");
		treeTraverse(root.right, code + "1");

	}

	public void buildHuffmanCode(FourWayHeap queue) {

		while (queue.size() > 1) {
			Node left = queue.extractMin();
			Node right = queue.extractMin();
			Node parent = new Node(-1, left.priority + right.priority);
			parent.left = left;
			parent.right = right;
			queue.insert(parent);
		}
	}

	public void populateHeap(FourWayHeap queue, int freqArr[]) {

		for (int i = 0; i < freqArr.length; i++) {
			if (freqArr[i] > 0)
				queue.insert(new Node(i, freqArr[i]));
		}

	}

	public LinkedHashMap<Integer, String> buildTreeUsingFourWayHeap(int freqArr[]) {
		FourWayHeap queue = new FourWayHeap();
		populateHeap(queue, freqArr);
		// queue.print();
		// System.out.println("");
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
