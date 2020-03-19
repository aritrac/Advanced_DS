package ctci.solutions.aritra;

/*
 * Question: You are given an array with all the numbers from 1 to N appearing exactly once, except for one number that is missing. How can you find the missing number in O(N) time
 * and O(1) space? What if there were two numbers missing?
 * 
 * Solution: We need to use a bit of math for this. Since there are two missing numbers this time, we will find the sum of the missing numbers. Let the missing numbers be x and y
 * ,so we will find out x + y. And we will also find out the sum of the squares of the missing number,i.e. x^2 + y^2. Now we will proceed as follows
 * 
 * x + y = s      -> y = s - x;
 * x^2 + y^2 = t  -> x^2 + (s-x)^2 = t
 * 				  2x^2 - 2sx + s^2 - t = 0 which is of the form ax^2 + bx + c = 0
 * 
 * Now recall the quadratic formula
 * x = [-b (+/-) sqrt(b^2 - 4ac)]/2a
 * 
 * and in our case
 * a = 2
 * b = -2s
 * c = s^2-t
 * 
 * Using this we can determine the value of x and hence y, which is shown below
 * 
 * We could also have used product of x and y and sum of x and y instead of sum of squares of x and y and sum of x and y. But involving the product approach will lead to overflow issues
 * hence sticking with the simpler approach
 */

public class MissingTwoNumbers {
	int[] missingTwo(int[] array){
		int max_value = array.length + 2; //Since 2 numbers are missing, adding it to calculate the proper total sum
		int rem_square = squareSumToN(max_value,2);
		int rem_one = max_value * (max_value + 1)/2; //Using summation of series formula for summing 1 to N with n(n+1)/2
		
		for(int i = 0; i < array.length; i++){
			rem_square -= array[i] * array[i]; //calculating x^2 + y^2
			rem_one -= array[i]; //calculating x + y
		}
		
		return solveEquation(rem_one, rem_square);
	}
	
	int squareSumToN(int n, int power){
		int sum = 0;
		for(int i = 1; i <= n; i++){
			sum += (int)Math.pow(i, power);
		}
		return sum;
	}
	
	int[] solveEquation(int r1, int r2){
		/*
		 * ax^2 + bx + c
		 * -->
		 * x = [-b (+/-) sqrt(b^2 - 4ac)] / 2a
		 * In this case, it has to be a + not a -
		 */
		int a = 2;
		int b = -2 * r1; //-2 X s as s = x + y as s is r1
		int c = r1 * r1 - r2; //r2 is t and t = x^2 + y^2
		
		double part1 = -1 * b;
		double part2 = Math.sqrt(b*b - 4 * a * c);
		double part3 = 2 * a;
		
		int solutionX = (int)((part1 + part2) / part3);
		int solutionY = r1 - solutionX;
		
		int[] solution = {solutionX, solutionY};
		return solution;
	}
}
