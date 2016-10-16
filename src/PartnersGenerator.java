import java.util.ArrayList;
import java.util.Arrays;

public class PartnersGenerator {

	public static String[] STUDENTS = new String[5];
	public static String[] PAIRS = new String[STUDENTS.length];

	public static void main(String[] args) {

		String Student1 = "Earl";
		String Student2 = "Frank";
		String Student3 = "Justin";
		String Student4 = "Nick";
		String Student5 = "Steve";
		addName(Student1, 0);
		addName(Student2, 1);
		addName(Student3, 2);
		addName(Student4, 3);
		addName(Student5, 4);
		System.out.println(Arrays.toString(STUDENTS));
		randomizeStudents(STUDENTS);
		System.out.println(Arrays.toString(STUDENTS));
		pairStudents(STUDENTS);
		displayPartners(PAIRS);

	}

	public static void randomizeStudents(String[] Students) {
		for (int i = 0; i < STUDENTS.length; i++) {
			int randomIndex = (int) Math.random() * Students.length;
			String name = Students[i];
			Students[i] = Students[randomIndex];
			Students[randomIndex] = name;
		}
	}

	public static void pairStudents(String[] Students) {

		for (int i = 0; i < Students.length-2; i+=2) {
			String partner = Students[i] + " " + Students[i + 1];
			PAIRS[i] = partner;
		}
		
		if (Students.length%2 != 0){
			PAIRS[PAIRS.length-1] = Students[Students.length - 1];
		}
	}

	public void resetPairs(String[] Partners) {
		for (int i = 0; i < Partners.length; i++) {
			Partners[i] = "";
		}
	}

	public static void displayPartners(String[] pairs) {
		for (int i = 0; i < pairs.length - 2; i+=2) {
			String pair = pairs[i];
			System.out.println(pair);
		}
	}

	public static void addName(String name, int index) {
		STUDENTS[index] = name;
	}

	public void removeName(String name, String[] students) {
		for (int i = 0; i < students.length; i++) {
			String s = students[i];
			if (s.equals(name)) {
				students[i] = "";
			}
		}
	}
}
