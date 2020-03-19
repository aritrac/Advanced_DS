import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution2 {
	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			int T = in.nextInt();
			if (T < 1 || T > 10)
				return;

			while (T > 0) {
				String s = in.next();
				if (s.length() < 1 || s.length() > 50000)
					return;
				int count = 1;
				List<String> rotations = new ArrayList<String>();
				rotations.add(s);
				for (int i = 1; i < s.length(); i++) {
					String rotatedString = rotateOnce(s);
					if (!rotations.contains(rotatedString)) {
						count++;
						rotations.add(rotatedString);
					}
					s = rotatedString;
				}
				System.out.println(count);
				T--;
			}
		} finally {
			in.close();
		}
	}

	public static String rotateOnce(String str) {
		return "" + str.charAt(str.length() - 1)
				+ str.substring(0, str.length() - 1);
	}
}
