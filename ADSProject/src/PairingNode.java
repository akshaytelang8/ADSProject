

public class PairingNode {

	PairingNode child;
	PairingNode leftSibling;
	PairingNode rightSibling;

	int val;
	int priority;
	PairingNode left;
	PairingNode right;
	String code;

	public PairingNode() {
		// TODO Auto-generated constructor stub
	}

	public PairingNode(PairingNode node) {
		// TODO Auto-generated constructor stub
		this.val = node.val;
		this.priority = node.priority;
		this.left = node.left;
		this.right = node.right;
	}

	public PairingNode(int val, int priority) {
		this.val = val;
		this.priority = priority;
	}
}
