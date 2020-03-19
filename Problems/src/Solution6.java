import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class Solution6 {

        public static void main(String[] args){
            Class<Student> student = Student.class;
            Method[] methods = student.getDeclaredMethods();
            ArrayList<String> methodList = new ArrayList<>();
            for(Method m: methods){
                methodList.add(m.getName());
            }
            Collections.sort(methodList);
            for(String name: methodList){
                System.out.println(name);
            }
        }

    }

class Student{
    private String name;

    public String getName() {
        return name;
    }
    public void setId(String id) {
        //TODO
    }
    public void setEmail(String email) {
        //TODO
    }
    public void anothermethod(){  }
}
