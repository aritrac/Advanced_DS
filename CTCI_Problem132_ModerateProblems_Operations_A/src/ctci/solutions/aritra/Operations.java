package ctci.solutions.aritra;

/*
 * Question: Write methods to implement the multiply, subtract, and divide operations for integers. The result of all of these are integers. Use only the add operator.
 * 
 * Solution: The only solution that we have to work with is the add operator. In each of these problems, it's useful to think in depth about what these operations really do or how to phrase
 * them in terms of other operations (either add or operations we've already completed)
 * 
 * Subtraction: The operation a - b is the same thing as a + (-1) * b. However, because we are not allowed to use the * (multiply) operator, we must implement a negate function.
 * Negating the value k is implemented by adding -1 k times. Observe that this will take O(k) time. If we want to optimize the solution a bit further, we can try to get to a zero 
 * faster.To do this, we can first reduce a by 1, then 2, then 4, then 8, and so on. We'll call this value delta. We want a to reach exactly 0. When reducing a by the next delta
 * would change the sign of a, we reset delta back to 1 and repeat the process.
 * 
 * For example:
 * a:		29		28		26		22		14		13		11		7		6		4		0
 * delta:	 -1      -2      -4      -8      -1      -2      -4     -1      -2      -4
 * 
 * Multiplication: The connection between addition and multiplication is equally straightforward. To multiply a by b, we just add a to itself b times.
 * 
 * Division: Of the 3 operations, division is certainly the hardest. The good thing is that we can use the multiply, subtract, and negate methods now to implement divide.
 * We are trying to compute x where x = a / b. Or, to put this another way, find x where a = bx. We've now changed the problem into one that can be stated with something
 * we know how to do: multiplication
 * 
 * We could implement this by multiplying b by progressively higher values, until we reach a. That would be fairly inefficient, particularly given that our implementation
 * of multiply involves a lot of adding.
 * Alternatively, we can look at the equation a = bx to see that we can compute x by adding b to itself repeatedly until we reach a. The number of times we need to do that
 * will equal x. Of course, a might not be evenly divisible by b, and that's okay. Integer division, which is what we've been asked to implement is supposed to truncate
 * the result.
 * 
 */

public class Operations {
	//Flip a positive sign to negative or negative sign to pos
	int negate(int a){
		int neg = 0;
		int newSign = a < 0 ? 1 : -1;
		while(a != 0){
			neg += newSign;
			a += newSign;
		}
		return neg;
	}
	
	int optimizedNegate(int a){
		int neg = 0;
		int newSign = a < 0 ? 1 : -1;
		int delta = newSign;
		while(a != 0){
			boolean differentSigns = (a + delta > 0) != (a > 0); //different signs indicate whether adding delta negates a or not
			if(a + delta != 0 && differentSigns){ //If delta is too big, reset it
				delta = newSign;
			}
			neg += delta;
			a += delta; //increase or decrease by delta
			delta += delta; //Double the delta
		}
		return neg;
	}
	
	//Subtract two numbers by negating b and adding them
	int minus(int a, int b){
		return a + negate(b);
	}
	
	//Subtract two numbers by negating b using optimised negate function and adding them
	int optimizedMinus(int a, int b){
		return a + optimizedNegate(b);
	}
	
	//Multiply a by b by adding a to itself b times
	int multiply(int a, int b){
		if(a < b){
			return multiply(b,a); //algorithm is faster if b < a, loop is run b times and a is added to itself b times
		}
		int sum = 0;
		for(int i = abs(b); i > 0; i = minus(i,1)){
			sum += a;
		}
		if(b < 0){ //This part handles the multiplication of 2 negative numbers, in which case we need to flip the sum.
			sum = negate(sum); 
		}
		return sum;
	}
	
	//Return absolute value
	int abs(int a){
		if(a < 0){
			return negate(a);
		}else{
			return a;
		}
	}
	
	int divide(int a, int b) throws java.lang.ArithmeticException{
		if(b == 0){
			throw new java.lang.ArithmeticException("ERROR");
		}
		int absa = abs(a);
		int absb = abs(b);
		
		int product = 0;
		int x = 0; 
		while(product + absb <= absa){ //dont go past a
			product += absb;
			x++;
		}
		
		if((a < 0 && b < 0) || (a > 0 && b > 0)){
			return x;
		}else{
			return negate(x);
		}
	}
}
