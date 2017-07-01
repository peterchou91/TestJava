package testReflect;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhouqing on 17/3/4.
 */
public class A {
    private int b = 1;
    private  static volatile A a = new A();
    public A(){
        try {
            //System.out.println("main sleep");
            //Thread.sleep(1);
        }catch(Exception e){

        }
        //b = 9;

    }
    public static synchronized A getInstance(){
        if (null == a){
            a =  new A();
        }
        return a;
    }

    public static void f(){
        int i =0;
        while(a.b == 1){
            //new String();
        }
        System.out.println(a.b);
    }

    public static void main (String[] args) throws Exception{
        Thread t1 = new Thread(){
            @Override
            public void run() {
                A.f();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                while(true)
                    a.b = 9;

            }
        };
        t1.start();
        t2.start();
        //Thread.sleep(1);
        //a.b = 9;
        //while(true);
        //getInstance();
        //System.out.println("end");
        ThreadLocal<Integer> t;
        //ReentrantLock
    }

}
