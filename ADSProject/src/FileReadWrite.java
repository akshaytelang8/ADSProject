
import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FileReadWrite {

	public void readFilePopulateArray(String inputPath, int arr[]) {

		String line = null;

		try {
			FileReader fileReader = new FileReader(inputPath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				arr[Integer.parseInt(line)]++;
			}

			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + inputPath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + inputPath + "'");
		}
	}

	public String encodeFileUsingMap(String inputPath, LinkedHashMap<Integer, String> map) {

		String line = null;
		String str = "";
		try {
			FileReader fileReader = new FileReader(inputPath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer sb = new StringBuffer();

			while ((line = bufferedReader.readLine()) != null) {
				int val = Integer.parseInt(line);
				str = map.get(val);
				sb.append(str);
			}
			str = sb.toString();
			bufferedReader.close();
			return str;

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + inputPath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + inputPath + "'");
		} finally {
			return str;
		}
	}

	public void writeCodeTableFile(String outputPath, String output) {

		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(outputPath);
			bw = new BufferedWriter(fw);
			bw.write(output);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeDecodedMessage(String outputPath, String output) {

		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(outputPath);
			bw = new BufferedWriter(fw);
			bw.write(output);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readCodeTablePopulateMap(String inputPath, HashMap<String, String> map) {

		String line = null;
		String arr[];
		String key, value;
		try {
			FileReader fileReader = new FileReader(inputPath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				arr = line.split(" ");
				key = arr[1];
				value = arr[0];
				map.put(key, value);
			}

			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + inputPath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + inputPath + "'");
		}
	}

	public void writeBinaryFile(String outputPath, String output) {

		try {

			BitSet bitSet = new BitSet(output.length());

			for (int i = 0; i < output.length(); i++) {
				if (output.charAt(i) == '1') {
					bitSet.set(i);
				}
			}

			OutputStream outputStream = new FileOutputStream(outputPath);
			outputStream.write(bitSet.toByteArray());
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readBinaryFile(String inputPath) {

		String str = "";

		try {
			int length = (int) (new File(inputPath).length());
			byte buffer[] = new byte[length];
			InputStream inputstream = new FileInputStream(inputPath);

			inputstream.read(buffer);
			BitSet set = BitSet.valueOf(buffer);

			StringBuffer sbuff = new StringBuffer();

			for (int i = 0; i <= set.length(); i++) {
				if (set.get(i)) {
					sbuff.append("1");
				} else {
					sbuff.append("0");
				}
			}

			str = sbuff.toString();
			inputstream.close();
			return str;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return str;
		}
	}

	public static void main(String[] args) {

		String fileName = "C:/Users/aksha/Desktop/temp2.txt";
		String line = null;
		int arr[] = new int[1000000];
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				arr[Integer.parseInt(line)]++;
			}

			// Always close files.
			bufferedReader.close();

			long st = System.currentTimeMillis();
			for (int i = 0; i < 1; i++) {
				BinaryHeap bheap = new BinaryHeap();
				bheap.buildTreeUsingBinaryHeap(arr);
			}
			long end = System.currentTimeMillis();
			System.out.println("time binary =  " + (end - st));
			System.out.println("-----------------------------------------------------------------------");

			st = System.currentTimeMillis();
			for (int i = 0; i < 1; i++) {
				FourWayHeap fheap = new FourWayHeap();
				fheap.buildTreeUsingFourWayHeap(arr);
			}
			end = System.currentTimeMillis();
			System.out.println("time fourWay =  " + (end - st));
			System.out.println("-----------------------------------------------------------------------");

			long st1 = System.currentTimeMillis();
			for (int i = 0; i < 1; i++) {
				PairingHeap pHeap = new PairingHeap();
				pHeap.buildTreeUsingPairingHeap(arr);
			}
			long end1 = System.currentTimeMillis();
			System.out.println("time pairing =  " + (end1 - st1));
			System.out.println("-----------------------------------------------------------------------");

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
}
