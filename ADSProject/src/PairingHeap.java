
import java.util.LinkedHashMap;
import java.util.Vector;

public class PairingHeap {
	PairingNode root;
	int heapSize;
	LinkedHashMap<Integer, String> map;

	public PairingHeap() {
		root = null;
		heapSize = 0;
		map = new LinkedHashMap<Integer, String>();

	}

	public int size() {
		return heapSize;
	}

	public void insert(PairingNode node) {

		heapSize++;

		if (root == null) {
			// root = new PairingNode(node);
			root = node;
			return;
		}

		// if nodes priority is less it will become the parent/root
		// else it will become leftmost child
		if (node.priority < root.priority) {
			node.child = root;
			root.leftSibling = node;
			root = node;
		} else {
			node.leftSibling = root;
			node.rightSibling = root.child;

			if (root.child != null) {
				root.child.leftSibling = node;
			}
			root.child = node;
		}

	}

	public PairingNode extractMin() {

		PairingNode returnNode = null;
		if (heapSize == 0) {
			return returnNode;
		}

		heapSize--;
		// returnNode = new PairingNode(root.val, root.priority);
		returnNode = new PairingNode(root);

		if (heapSize == 0) {
			root = null;
			return returnNode;
		}

		// pass1
		Vector<PairingNode> list = new Vector<PairingNode>();
		PairingNode pointer = root.child;
		root.child.leftSibling = null;
		root.child = null;
		root = null;

		while (pointer != null) {
			PairingNode temp = pointer.rightSibling;
			pointer.leftSibling = pointer.rightSibling = null;
			list.add(pointer);
			pointer = temp;
		}

		if (list.size() == 1) {
			root = list.remove(0);
			return returnNode;
		}

		int i = 0;
		Vector<PairingNode> passOneList = new Vector<PairingNode>();

		if (list.size() % 2 == 0) {
			// for even no of nodes
			while (i < list.size()) {
				PairingNode node1 = list.get(i++);
				PairingNode node2 = list.get(i++);
				passOneList.add(Meld(node1, node2));
			}
		} else {
			// for odd no of nodes
			while (i + 1 < list.size()) {
				PairingNode node1 = list.get(i++);
				PairingNode node2 = list.get(i++);
				passOneList.add(Meld(node1, node2));
			}
			PairingNode node1 = list.get(i++);
			// PairingNode node2 = passOneList.get(passOneList.size() - 1);
			PairingNode node2 = passOneList.get(passOneList.size() - 1);
			passOneList.remove(passOneList.size() - 1);
			passOneList.add(Meld(node1, node2));
		}

		// pass 2
		int index = passOneList.size() - 1;
		PairingNode node = passOneList.get(index);
		index--;
		while (index >= 0) {
			node = Meld(node, passOneList.get(index));
			index--;
		}

		root = node;
		return returnNode;
	}

	public PairingNode Meld(PairingNode node1, PairingNode node2) {
		PairingNode node = null;

		if (node1.priority < node2.priority) {
			node2.leftSibling = node1;
			node2.rightSibling = node1.child;

			if (node1.child != null) {
				node1.child.leftSibling = node2;
			}

			node1.child = node2;
			node = node1;
		} else {
			node1.leftSibling = node2;
			node1.rightSibling = node2.child;

			if (node2.child != null) {
				node2.child.leftSibling = node1;
			}

			node2.child = node1;
			node = node2;
		}

		return node;
	}

	public void printPairingHeap() {
		PairingNode temp = root;

	}

	public void populateHeap(PairingHeap queue, int freqArr[]) {
		for (int i = 0; i < freqArr.length; i++) {
			if (freqArr[i] > 0)
				queue.insert(new PairingNode(i, freqArr[i]));
		}
	}

	public void buildHuffmanCode(PairingHeap queue) {

		while (queue.size() > 1) {
			PairingNode left = queue.extractMin();
			PairingNode right = queue.extractMin();
			PairingNode parent = new PairingNode(-1, left.priority + right.priority);
			parent.left = left;
			parent.right = right;
			queue.insert(parent);
		}
	}

	public void treeTraverse(PairingNode root, String code) {
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

	public LinkedHashMap<Integer, String> buildTreeUsingPairingHeap(int freqArr[]) {
		PairingHeap queue = new PairingHeap();
		populateHeap(queue, freqArr);
		// queue.print();
		// System.out.println("");
		buildHuffmanCode(queue);
		PairingNode root = queue.extractMin();
		queue.treeTraverse(root, "");
		return queue.map;
	}

	public static void main(String[] args) {

		PairingHeap heap = new PairingHeap();

		heap.insert(new PairingNode(4, 4));
		heap.insert(new PairingNode(1, 1));
		heap.insert(new PairingNode(3, 3));
		heap.insert(new PairingNode(2, 2));
		heap.insert(new PairingNode(4, 4));
		heap.insert(new PairingNode(2, 2));

		PairingNode node = heap.extractMin();
		System.out.println(node.val);

		PairingNode node2 = heap.extractMin();
		System.out.println(node2.val);

		PairingNode node3 = heap.extractMin();
		System.out.println(node3.val);

		node3 = heap.extractMin();
		System.out.println(node3.val);

		node3 = heap.extractMin();
		System.out.println(node3.val);

	}

}
