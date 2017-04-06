import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CompareHeaps {
	public static void main(String[] args) {

		String fileName = "sample_input_large.txt";	
		String line = null;
		int arr[] = new int[1000000];
		int times = 10;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				arr[Integer.parseInt(line)]++;
			}

			bufferedReader.close();

			long st = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				BinaryHeap bheap = new BinaryHeap();
				bheap.buildTreeUsingBinaryHeap(arr);
			}
			long end = System.currentTimeMillis();
			System.out.println("time binary =  " + (end - st));
			System.out.println("-----------------------------------------------------------------------");

			st = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				FourWayHeap fheap = new FourWayHeap();
				fheap.buildTreeUsingFourWayHeap(arr);
			}
			end = System.currentTimeMillis();
			System.out.println("time fourWay =  " + (end - st));
			System.out.println("-----------------------------------------------------------------------");

			long st1 = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
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
		}

	}
}
