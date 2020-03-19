import java.util.*;

class Solution4 {

	public static void main(String[] argh) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			while (sc.hasNext()) {
				String input = sc.next();
				System.out.println(checkBrackets(input));
			}
		} finally {
			sc.close();
		}
	}

	static boolean checkBrackets(String s) {
		Stack<Character> st = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(') {
				st.push(s.charAt(i));
			} else {
				if (!st.isEmpty()) {
					char ch = st.peek();
					if (s.charAt(i) == ']' && ch == '[')
						st.pop();
					else if (s.charAt(i) == ')' && ch == '(')
						st.pop();
					else if (s.charAt(i) == '}' && ch == '{')
						st.pop();
					else
						return false;
				} else {
					return false;
				}
			}
		}
		return st.isEmpty();
	}
}
