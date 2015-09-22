import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 19, 2015
 * @java Version : Java 8
 */
public class InputFileCreator {
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public static void main(String[] args) {

		FileWriter resultFile = null;
		
		String[] keys ={"authors","release date","title","list price","publisher"};

		try {
			resultFile = new FileWriter("sample-1000000.csv");
			for(long i=0;i<1000000;i++){
			resultFile.write(randInt(10000,100000)+","+ keys[randInt(0,4)]+","+"value"+randInt(1,1000)+"\r\n");
			}
			resultFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
