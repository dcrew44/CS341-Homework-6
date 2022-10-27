import java.io.File;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\hayde\\OneDrive\\CS-341 Eclipse\\HW2 Scrabble App\\src\\TileArray.java");
		JavaFile f = new JavaFile(file);
		System.out.println(f.countLines());
		String[] methods = f.getMethods();
		System.out.println(f.forCount());
		for(String m : methods) {
			System.out.println(m);
		}
		
		System.out.println(f.generateReport());
	}

}
