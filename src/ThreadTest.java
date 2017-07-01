import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by zhouqing on 16/12/2.
 */
public class ThreadTest {
    //public static int base = 0;
    public static void main(String[] args) throws Exception{
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = bean.dumpAllThreads(true,true);
        for(ThreadInfo info : threadInfos){
            System.out.println(info.getThreadId() + " " + info.getThreadName());
        }

        CountTask task = new CountTask();

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(task.base);

    }

   static class CountTask implements Runnable{
        public volatile int base = 0;
        public void run(){
            for(int i = 0; i < 100000; i++){
                synchronized (this) {
                    base++;
                }
            }
        }
       public int getBase(){
           return base;
       }
    }
}
