
public class Node {
	int val;
	int priority;
	Node left;
	Node right;
	String code;

	public Node() {

	}

	public Node(Node node) {
		this.val = node.val;
		this.priority = node.priority;
		this.left = node.left;
		this.right = node.right;
	}

	public Node(int val, int priority) {
		this.val = val;
		this.priority = priority;
	}

	public static void nodeSwap(Node node1, Node node2) {
		Node temp = new Node();

		temp.val = node1.val;
		temp.priority = node1.priority;
		temp.left = node1.left;
		temp.right = node1.right;

		node1.val = node2.val;
		node1.priority = node2.priority;
		node1.left = node2.left;
		node1.right = node2.right;

		node2.val = temp.val;
		node2.priority = temp.priority;
		node2.left = temp.left;
		node2.right = temp.right;

	}

}
