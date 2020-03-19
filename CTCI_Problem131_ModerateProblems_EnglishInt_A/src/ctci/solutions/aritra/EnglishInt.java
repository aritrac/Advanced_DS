package ctci.solutions.aritra;

import java.util.LinkedList;

/*
 * Question: Given any integer, print an English phrase that describes the integer (e.g. "One Thousand, Two Hundred Thirty Four")
 * 
 * Solution: This is not an especially challenging problem, but it is somewhat a tedious one. The key is to be organized in how you approach the problem-
 * and to make sure you have good test cases
 * We can think about converting a number like 19,323,984 as converting each of the three 3-digit segments of the number, and inserting "thousands" and "millions"
 * in between as appropriate. That is,
 * convert(19,323,984) = convert(19) + " million " + convert(323) + " thousand " + convert(984)
 * The code below implements this algorithm
 */

public class EnglishInt {

	String[] smalls = {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten",
			"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
	String[] tens = {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
	String[] bigs = {"","Thousand","Million","Billion"};
	String hundred = "Hundred";
	String negative = "Negative";
	
	String convert(int num){
		if(num == 0){
			return smalls[0];
		}else if(num < 0){
			return negative + " " + convert(-1 * num);
		}
		
		LinkedList<String> parts = new LinkedList<String>();
		int chunkCount = 0;
		
		while(num > 0){
			if(num % 1000 != 0){
				String chunk = convertChunk(num % 1000) + " " + bigs[chunkCount];
				parts.addFirst(chunk);
			}
			num /= 1000; //shift chunk
			chunkCount++;
		}
		return listToString(parts);
	}
	
	String convertChunk(int number){
		LinkedList<String> parts = new LinkedList<String>();
		
		//Convert hundreds place
		if(number >= 100){
			parts.addLast(smalls[number / 100]);
			parts.addLast(hundred);
			number %= 100;
		}
		
		//Convert tens place
		if(number >= 10 && number <= 19){
			parts.addLast(smalls[number]);
		}else if(number >= 20){
			parts.addLast(tens[number / 10]);
			number %= 10;
		}
		
		//Convert ones place
		if(number >= 1 && number <= 9){
			parts.addLast(smalls[number]);
		}
		
		return listToString(parts);
	}
	
	//Convert a linked list of strings to a string, dividing it up with spaces and reading from last to first
	String listToString(LinkedList<String> parts){
		StringBuilder sb = new StringBuilder();
		while(parts.size() > 1){
			sb.append(parts.pop());
			sb.append(" ");
		}
		sb.append(parts.pop());
		return sb.toString();
	}
}
