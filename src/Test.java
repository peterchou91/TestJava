import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhouqing on 15/9/7.
 */
public class Test {
    private static int a;
    public void f(Test t)
    {
        int b = t.a + 1;

    }
    public static void main(String[] args) throws Exception{
        Map<String,String> map = new TreeMap<String,String>();
        Set<Integer> set = new HashSet<Integer>();
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        for (int i : arr) {
            set.add(i);
        }
        set.add(null);
        List<Integer> list = new LinkedList<Integer>();
        list.add(null);
        /*
        Iterator<Integer> it1 = set.iterator();
        Iterator<Integer> it2 = set.iterator();
        while (it1.hasNext()) {
            int e = it1.next();
            if (e == 4) it1.remove();
        }
        System.out.println(set.size());
        it2.next();
        while (it2.hasNext()) {
            int e = it1.next();
            System.out.println(e);
        }
        */

        /*
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./object"));
        People p = (People)in.readObject();
        System.out.println(p.name);
        */

        Thread t = new Thread(){
            public void run(){
                try {
                    synchronized (this) {
                        this.wait();
                    }
                    System.out.println(a);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                try {
                    a++;
                    synchronized (this) {
                        this.notify();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        t2.start();
        Thread.sleep(10);
        t.start();
        t.join();
        t2.join();
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>();
    }

}

class Test2{
    private int b = 0;
}

