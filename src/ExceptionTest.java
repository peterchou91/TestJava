/**
 * Created by zhouqing on 16/12/7.
 */
public class ExceptionTest {
    public int f(){
        try{
            return 1;
        }finally {
            return 2;
        }
    }
    public static void main(String[] args){
        ExceptionTest et = new ExceptionTest();
        System.out.println(et.f());
    }
}
