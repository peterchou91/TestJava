import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhouqing on 16/11/30.
 * 允许5个线程同时读，1个线程写
 * 读写互斥，写写互斥
 */
public class Lock {
    private volatile AtomicInteger readCount = new AtomicInteger(0);
    private volatile AtomicInteger writeCount = new AtomicInteger(0);
    private volatile AtomicInteger writeWaiting = new AtomicInteger(0);

    static Object A = new Object();
    static Object B = new Object();

    public static  void f(){
        synchronized (A){
            try {
                Thread.sleep(10);
            }catch(Exception e){

            } finally {

            }

            synchronized (A){
                System.out.println("f()");
            }
        }
    }

    public synchronized void lockRead(){
        while(readCount.get() >= 5 || writeCount.get() > 0 || writeWaiting.get() > 0){
            try{
                long currentThreadId = Thread.currentThread().getId();
                System.out.printf("%d waiting to read ... (readCount:%d,writeCount:%d,writeWaiting:%d\n)",currentThreadId,readCount.get(),writeCount.get(),writeWaiting.get());
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        readCount.incrementAndGet();
        long currentThreadId = Thread.currentThread().getId();
        System.out.println(currentThreadId + " is reading , readCount:" + readCount );
    }

    public synchronized void unlockRead(){
        this.notifyAll();
        long currentThreadId = Thread.currentThread().getId();
        readCount.decrementAndGet();
        System.out.println(currentThreadId + " read finished");

    }

    public synchronized void lockWrite() {
        writeWaiting.incrementAndGet();
        while (writeCount.get() > 0 || readCount.get() > 0) {
            try {
                writeWaiting.get();
                long currentThreadId = Thread.currentThread().getId();
                System.out.printf("%d waiting to write ...(readCount:%d,writeCount:%d,writeWaiting:%d\n)",currentThreadId,readCount.get(),writeCount.get(),writeWaiting.get());
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writeWaiting.decrementAndGet();
        writeCount.incrementAndGet();
        long currentThreadId = Thread.currentThread().getId();
        System.out.println(currentThreadId + " is writing..., wirteCount:" + writeCount);
    }

    public synchronized void unlockWrite(){
       this.notifyAll();
       writeCount.decrementAndGet();
       long currentThreadId = Thread.currentThread().getId();
       System.out.println(currentThreadId + " write finished");
    }


    public static void main(String[] args){
       Lock lock = new Lock();
       for(int i = 0; i < 6; i++){
           Thread t = new Thread(new RunReadTask(lock));
           Thread t1 = new Thread(new RunWriteTask(lock));
           t.start();
           t1.start();
       }
        //f();
        //ReentrantLock
    }

    public static class RunWriteTask implements Runnable{

        Lock lock = null;

        public RunWriteTask(Lock lock){
            this.lock = lock;
        }

        @Override
        public void run() {
           // lock.lockRead();
            lock.lockWrite();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            //lock.unlockRead();
            lock.unlockWrite();
        }
    }

     public static class RunReadTask implements Runnable{
         Lock lock = null;
         public RunReadTask(Lock lock){
             this.lock = lock;
         }
         @Override
         public void run() {
             lock.lockRead();
             try{
                 Thread.sleep(1000);
             }catch(InterruptedException e){
                 e.printStackTrace();
             }
             lock.unlockRead();
         }
     }
}
