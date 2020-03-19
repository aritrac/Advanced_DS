import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution3 {
	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			int N = in.nextInt();
			if (N < 1 || N > 100000) {
				return;
			}
			char[] foodType = new char[N];
			char[] sleepType = new char[N];
			String S1 = in.next();
			if (S1.length() > N)
				return;
			foodType = S1.toCharArray();
			String S2 = in.next();
			if (S2.length() > N)
				return;
			sleepType = S2.toCharArray();
			int q = in.nextInt();
			if (q < 1 || q > 100000)
				return;
			while (q > 0) {
				int L = in.nextInt();
				if (L < 1 || L > N) {
					return;
				}
				int R = in.nextInt();
				if (R < 1 || R > N)
					return;
				System.out.println(getCount(foodType, sleepType, L - 1, R - 1));
				q--;
			}
		} finally {
			in.close();
		}
	}

	public static long getCount(char[] foodType, char[] sleepType, int L, int R) {
		Map<Character, Integer> foodCountSame = new HashMap<Character, Integer>();
		int zeroCount = -1;
		int oneCount = -1;
		long foodComb = 0;
		for (int i = L; i <= R; i++) {
			if (!foodCountSame.containsKey(sleepType[i])) {
				foodCountSame.put(sleepType[i], 0);
			} else {
				foodCountSame.put(sleepType[i],
						foodCountSame.get(sleepType[i]) + 1);
			}
			if (foodType[i] == '0')
				zeroCount++;
			else
				oneCount++;
		}

		Set<Character> keySet = foodCountSame.keySet();
		Iterator<Character> iter = keySet.iterator();
		while (iter.hasNext()) {
			char key = iter.next();
			foodComb += foodCountSame.get(key);
		}
		return (zeroCount + oneCount + foodComb) % ((long) Math.pow(10, 9) + 7);
	}
}
