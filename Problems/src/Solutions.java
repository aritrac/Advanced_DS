import java.util.*;

class Solutions {
	public static void main(String args[]) throws Exception {
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			long N = in.nextLong();
			if (N < 3)
				return;
			List<Long> arr = new ArrayList<Long>();

			for (long i = 0; i < N; i++) {
				arr.add(in.nextLong());
			}

			double sum = 0.0d;
			int i = 0;
			int j = 1;
			int k = 2;
			while (i != N - 3) {
				sum += Math
						.floor(((arr.get(i) + arr.get(j) + arr.get(k)) / (arr
								.get(i) * arr.get(j) * arr.get(k))));
				if (k != N - 1) {
					k++;
				} else {
					if (j != N - 2) {
						j++;
						k = j + 1;
					} else {
						if (i != N - 3) {
							i++;
							j = i + 1;
							k = j + 1;
						}
					}
				}
			}
			sum += Math.floor(((arr.get(i) + arr.get(j) + arr.get(k)) / (arr
					.get(i) * arr.get(j) * arr.get(k))));
			System.out.println((long) sum);
		} finally {
			in.close();
		}
	}
}
