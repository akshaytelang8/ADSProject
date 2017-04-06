import java.util.HashMap;
import java.util.Iterator;

public class decoder {
	HashMap<String, String> map;
	String message;
	HuffmanNode root;

	public decoder() {
		map = new HashMap<String, String>();
		message = "";
		root = new HuffmanNode();
	}

	public void readCodeTablePopulateMap(String codeTablePath) {
		FileReadWrite obj = new FileReadWrite();
		obj.readCodeTablePopulateMap(codeTablePath, map);
	}

	public void readCodeTableBuildTree() {
		Iterator<String> itr = map.keySet().iterator();

		while (itr.hasNext()) {
			String key = itr.next();
			String value = map.get(key);
			buildTree(key, value);
		}
	}

	public void buildTree(String code, String value) {
		HuffmanNode temp = root;

		for (char ch : code.toCharArray()) {
			if (ch == '0') {
				if (temp.left == null) {
					temp.left = new HuffmanNode("-1", "-1");
					temp = temp.left;
				} else {
					temp = temp.left;
				}
			} else if (ch == '1') {
				if (temp.right == null) {
					temp.right = new HuffmanNode("-1", "-1");
					temp = temp.right;
				} else {
					temp = temp.right;
				}
			}
		}

		temp.code = code;
		temp.value = value;
	}

	public void printTree(HuffmanNode temp) {
		if (temp != null) {
			printTree(temp.left);
			System.out.println(temp.value);
			printTree(temp.right);
		}
	}

	/** 
	 * 
	 * */
	public void readEncodedMessage(String encodedMsgPath) {
		FileReadWrite obj = new FileReadWrite();
		message = obj.readBinaryFile(encodedMsgPath);

	}

	public void writeDecodedMessageUsingTree(String decodedMsgPath) {

		FileReadWrite obj = new FileReadWrite();
		StringBuffer sbuff = new StringBuffer();

		int index = 0;
		HuffmanNode temp = root;

		while (index < message.length()) {
			char ch = message.charAt(index);
			if (ch == '0') {
				temp = temp.left;
				if (temp.left == null && temp.right == null) {
					sbuff.append(temp.value + "\n");
					temp = root;
				}
			} else if (ch == '1') {
				temp = temp.right;
				if (temp.left == null && temp.right == null) {
					sbuff.append(temp.value + "\n");
					temp = root;
				}
			}

			index++;
		}

		String decodedMsg = sbuff.toString();
		//decodedMsg = decodedMsg.substring(0, decodedMsg.length()-1);
		obj.writeDecodedMessage(decodedMsgPath, decodedMsg);

	}

	public void writeDecodedMessage(String decodedMsgPath) {
		FileReadWrite obj = new FileReadWrite();
		StringBuffer sbuff = new StringBuffer();

		int beginIndex = 0;
		int endIndex = 1;

		while (endIndex <= message.length()) {
			String str = message.substring(beginIndex, endIndex);
			if (map.containsKey(str)) {
				String val = map.get(str);
				sbuff.append(val + "\n");
				beginIndex = endIndex;
				endIndex++;
			} else {
				endIndex++;
			}
		}
		String decodedMsg = sbuff.toString();
		obj.writeDecodedMessage(decodedMsgPath, decodedMsg);
	}

	public static void main(String[] args) {

		decoder decoder = new decoder();
		// decoder.readCodeTablePopulateMap("C:/Users/aksha/Desktop/code_table.txt");
		decoder.readCodeTablePopulateMap(args[1]);
		decoder.readCodeTableBuildTree();
		// decoder.readEncodedMessage("C:/Users/aksha/Desktop/encode.bin");
		decoder.readEncodedMessage(args[0]);
		decoder.writeDecodedMessageUsingTree("decoded.txt");
		// decoder.printTree(decoder.root);
		// decoder.writeDecodedMessage("C:/Users/aksha/Desktop/decoded.txt");

	}
}
