import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    
    public static void oneMethod() {
        throw new NullPointerException("错误");
    }

    public void test () {

        try
    
        {  
    
                oneMethod ();
    
                System.out.print ( "condition 1");
    
         } 
    
        catch ( Exception e ) {
    
             System.out.print ( "condition 3");
    
        }
         finally
    
         {
    
                System.out.println ("condition 4" );
    
          }
    
     }
     public static void main(String[] args) {
         Test test = new Test();
         test.test();
         System.out.println(null + " ");
         ArrayList<Integer> as = new ArrayList<>();
         Iterator<Integer> iterator = as.iterator();
         while (iterator.hasNext()) {
             iterator.next();
         }
     }
}
