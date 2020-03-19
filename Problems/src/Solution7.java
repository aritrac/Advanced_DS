import java.util.*;

class Student7 {
	private int id;
	private String fname;
	private double cgpa;

	public Student7(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public double getCgpa() {
		return cgpa;
	}
}

class Sorter implements Comparator<Student7> {

	@Override
	public int compare(Student7 n1, Student7 n2) {
		if (n1.getCgpa() == n2.getCgpa()) {
			if (n1.getFname().equals(n2.getFname()))
				return n1.getId() - n2.getId();
			else
				return n1.getFname().compareTo(n2.getFname());
		} else {
			double d = n2.getCgpa() - n1.getCgpa();
			if (d < 0) {
				return -1;
			} else
				return 1;
		}
	}

}

// Complete the code
public class Solution7 {
	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			int testCases = Integer.parseInt(in.nextLine());

			List<Student7> studentList = new ArrayList<Student7>();
			while (testCases > 0) {
				int id = in.nextInt();
				String fname = in.next();
				double cgpa = in.nextDouble();

				Student7 st = new Student7(id, fname, cgpa);
				studentList.add(st);

				testCases--;
			}
			Collections.sort(studentList, new Sorter());
			for (Student7 st : studentList) {
				System.out.println(st.getFname());
			}
		} finally {
			in.close();
		}
	}
}
