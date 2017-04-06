import java.util.Iterator;
import java.util.LinkedHashMap;

public class encoder {

	String path;
	LinkedHashMap<Integer, String> map;

	/*
	 * read the input file and generate code table
	 */

	public void buildCodeTable(String fileName) {
		// String fileName = "C:/Users/aksha/Desktop/temp3.txt";
		int arr[] = new int[1000000];

		FileReadWrite objectIO = new FileReadWrite();
		objectIO.readFilePopulateArray(fileName, arr);

		FourWayHeap fheap = new FourWayHeap();
		map = fheap.buildTreeUsingFourWayHeap(arr);

		Iterator<Integer> itr = map.keySet().iterator();
		StringBuffer sb = new StringBuffer();

		while (itr.hasNext()) {
			int val = itr.next();
			String code = map.get(val);
			sb.append(val + " " + code + "\n");
		}

		String output = sb.toString();
		String outputPath = "code_table.txt";
		objectIO.writeCodeTableFile(outputPath, output);

	}

	/*
	 * read the input file and generate encoded binary
	 */

	public void buildEncodedFile(String fileName) {

		// String fileName = "C:/Users/aksha/Desktop/temp3.txt";

		FileReadWrite objectIO = new FileReadWrite();
		String bin = objectIO.encodeFileUsingMap(fileName, map);

		String output = bin;
		String outputPath = "encoded.bin";
		objectIO.writeBinaryFile(outputPath, output);

	}

	public static void main(String[] args) {
		// long st = System.currentTimeMillis();
		encoder enc = new encoder();
		enc.buildCodeTable(args[0]);
		enc.buildEncodedFile(args[0]);
		// long end = System.currentTimeMillis();
		// System.out.println("encoding time fourWay = " + (end - st));
	}
}
