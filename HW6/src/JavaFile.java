import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFile {
	private String fileString;
	
	public final Pattern pattern = Pattern.compile(".*([a-zA-Z_$][a-zA-Z0-9_$]*" + 
			"(<[a-zA-Z_$][a-zA-Z0-9_$]*>)?(\\[])?\s+[a-zA-Z_$][a-zA-Z0-9_$]*\s*\\(.*\\)).*");
	
	public JavaFile(File f) {
		fileString = "";
		try {
			Scanner in = new Scanner(f);
			while(in.hasNext()) {
				fileString += in.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFileString() {
		return fileString;
	}
	
	public int countLines() {
		int lines = 0;
		Scanner in = new Scanner(fileString);
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.contains("/*")) {
				while(!line.contains("*/")) {
					line = in.nextLine();
				}
			} else if (line.contains("//")||line.isBlank()) {
				
			} else {
				lines++;
			}
		}
		return lines;
	}
	
	public String[] getMethods() {
		ArrayList<String> methodList = new ArrayList();
		Scanner in = new Scanner(fileString);

		while(in.hasNext()) {
			
			String line = in.nextLine();
			Matcher matcher = pattern.matcher(line);
			if(matcher.find()) {
				int lineCount = 1;
				Stack<String> stack = new Stack();
				stack.push("{");
				
				while (!stack.isEmpty()) {
					String iline=in.nextLine();
					lineCount++;
					if (iline.contains("{")) {
						stack.push("{");
					} 
					if (iline.contains("}")) {
						stack.pop();
					}
				}
				methodList.add(line.substring(0,line.length()-1) + "\tLine Count: "+lineCount);
			} 
		}
		
		String[] methods = new String[methodList.size()];
		for(int i = 0; i< methods.length; i++) {
			methods[i] = methodList.get(i);
		}
		return methods;
	}
	
	public int forCount() {
		return elementCount("for");
	}
	
	public int whileCount() {
		return elementCount("while");
	}
	
	public int ifCount() {
		return elementCount("if");
	}
	
	public int elseifCount() {
		return elementCount("else if");
	}
	
	public int elseCount() {
		return elementCount("else");
	}
	
	public int elementCount(String s) {
		Pattern regex = Pattern.compile(s+"+ ([a-zA-Z0-9]*)+");
		if (s == "else") {
		Pattern regex2 = Pattern.compile(s+"^ if");
		regex = regex2;
		}
		
		Scanner in = new Scanner(fileString);
		
		int eCount = 0;
		while(in.hasNext()) {
			String line = in.nextLine();
			Matcher matcher = regex.matcher(line);
			if(matcher.find()) {
				eCount++;
			}
		}
		
		return eCount;
	}
	public String generateReport() {
		StringBuilder report = new StringBuilder();
		report.append("\tJavaFile Report\n");
		report.append("\tLines of Code: \t"+countLines()+"\n");
		report.append("\tMethods: \n");
		String[] methods = getMethods();
		for(String m: methods) {
			report.append("\t"+m+"\n");
		}
		
		report.append("\t\t\t\t\tControl Structures Table: \n");
		report.append("\t________________________________________________________________________________________________________________________________\n");
		report.append("\t|\tfor loops\t|\twhile loops\t|\tif statements\t|\telse if statements\t|\telse statements\t|\t\n");
		report.append("\t________________________________________________________________________________________________________________________________\n");
		report.append("\t|\t\t"+forCount()+"\t|\t\t"+whileCount()+"\t|\t\t"+ifCount()+"\t|\t\t"+elseifCount()+"\t\t  |\t\t"+elseCount()+"\t|\t\n");
		
		return report.toString();
		
	}
}
