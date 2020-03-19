import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

class WordCount {

	protected Map<String, Integer> counts;
	static Scanner in = new Scanner(System.in);

	public WordCount() {

		counts = new HashMap<String, Integer>();

	}

	public Map getCounts() {

		return counts;
	}

	public int parse(Scanner in, Pattern pattern) {
		int counter = 0;
		while (in.hasNext()) {
			// get the next token
			String token = in.next();
			// match the pattern within the token
			Matcher matcher = pattern.matcher(token);
			// process each match found in token (could be more than one)
			while (matcher.find()) {
				// get the String that matched the pattern
				String s = matcher.group().trim();
				// now do something with s

				counter = counts.containsKey(s) ? counts.get(s) : 0;
				counts.put(s, counter + 1);
			}

		}

		return counter;
	}

	public void report(PrintStream printstream) {
		List<Map.Entry<String, Integer>> results = new ArrayList<Map.Entry<String, Integer>>();
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			results.add(entry);
			Collections.sort(results,
					Collections.reverseOrder(Map.Entry.comparingByValue()));
			results.toString();

		}

		System.out.println(results); // The main problem is this outputs [is=3,
										// imple=2, it=1] but the junit doesn't
										// pass.

	}

}

// Test Cases

public class TestWordCount extends TestCase{
	@Test
	public void test_WordCount_parse() {
		WordCount wc = new WordCount();
		Scanner in = new Scanner(
				"this is a simple test, but it is not simple to pass");
		Pattern pattern = Pattern.compile("[i][a-z]+");
		wc.parse(in, pattern);

		assertEquals((Integer) 3, wc.getCounts().get("is"));
		assertEquals((Integer) 2, wc.getCounts().get("imple"));
		assertEquals((Integer) 1, wc.getCounts().get("it"));

	}
	
	@Test
	public void test_WordCount_report() {
		WordCount wc = new WordCount();
		Scanner in = new Scanner(
				"this is a simple test, but it is not simple to pass");
		Pattern pattern = Pattern.compile("[i][a-z]+");
		wc.parse(in, pattern);

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		wc.report(new PrintStream(output));
		String out = output.toString();
		String ls = System.lineSeparator();

		assertEquals("is=3_imple=2_it=1_".replace("_", ls), out);
	}
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestWordCount.class);
		for(Failure failure : result.getFailures()){
			System.out.println(failure.toString());
		}
	}
}