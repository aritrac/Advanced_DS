import java.io.IOException;
import java.security.Permission;
import java.util.Scanner;

public class Solution5 {

 public static void main(String[] args) {
   DoNotTerminate.forbidExit();
   try {
    Calculate cal = new Calculate();
    int T = cal.get_int_val();
    while (T--> 0) {
     double volume = 0.0;
     int ch = cal.get_int_val();
     if (ch == 1) {
      int a = cal.get_int_val();
      volume = Calculate.do_calc().get_volume(a);
     } else if (ch == 2) {
      int l = cal.get_int_val();
      int b = cal.get_int_val();
      int h = cal.get_int_val();
      volume = Calculate.do_calc().get_volume(l, b, h);

     } else if (ch == 3) {
      double r = cal.get_double_val();
      volume = Calculate.do_calc().get_volume(r);

     } else if (ch == 4) {
      double r = cal.get_double_val();
      double h = cal.get_double_val();
      volume = Calculate.do_calc().get_volume(r, h);

     }
     cal.output.display(volume);
    }

   } catch (NumberFormatException e) {
    System.out.print(e);
   } catch (IOException e) {
    e.printStackTrace();
   } catch (DoNotTerminate.ExitTrappedException e) {
    System.out.println("Unsuccessful Termination!!");
   }


  } //end of main
} //end of Solution

class Calculate{
    
    Scanner in = new Scanner(System.in);
    public Output output = new Output();
    
    public static Calculate do_calc(){
        return new Calculate();
    }
    
    public double get_volume(int a){
        return a * a * a;
    }
    
    public double get_volume(int l, int b, int h){
        return l * b * h;
    }
    
    public double get_volume(double r){
        return (2/3.0)*Math.PI*r;
    }
    
    public double get_volume(double r, double h){
        return Math.PI*r*r*h;
    }
    
    public int get_int_val() throws NumberFormatException, IOException{
        int a = in.nextInt();
        if(a < 0)
            throw new NumberFormatException("All the values must be positive");
        return a;
    }
    
    public double get_double_val() throws NumberFormatException, IOException{
        double d = in.nextDouble();
        if(d < 0)
            throw new NumberFormatException("All the values must be positive");
        return d;
    }
    
    public void display(double volume){
        System.out.println(volume);
    }
}

class Output{
    public void display(double volume){
        System.out.println(volume);
    }
}

class DoNotTerminate {

	 public static class ExitTrappedException extends SecurityException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;}

	 public static void forbidExit() {
	  final SecurityManager securityManager = new SecurityManager() {
	   @Override
	   public void checkPermission(Permission permission) {
	    if (permission.getName().contains("exitVM")) {
	     throw new ExitTrappedException();
	    }
	   }
	  };
	  System.setSecurityManager(securityManager);
	 }
	} //end of Do_Not_Terminate