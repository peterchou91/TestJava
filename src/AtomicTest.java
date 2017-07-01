import java.util.*;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zhouqing on 16/12/3.
 */
public class AtomicTest {
    public static void main(String[] args){
        AtomicInteger ai = new AtomicInteger(0);
        ai.addAndGet(3);
        System.out.println(ai.get());
        AtomicReference<User> ar = new AtomicReference<User>();
        User u1 = new User("zhou",21);
        ar.set(u1);
        System.out.println("u1 " + u1.toString());
        System.out.println("u1 in ar " + ar.get().toString());
        User u2 = new User("qing",23);
        System.out.println("u2 " + u2.toString());
        User u3 = u1;
        System.out.println(u3 == u1);
        ar.compareAndSet(u3, u2);
        System.out.println("u1 in ar after update " + ar.get().toString());
        System.out.println(ar.get().name + ar.get().age);
        Map<String,Integer> map = new HashMap<String,Integer>();
        for(int i = 0; i < 100; i++) {
            map.put(UUID.randomUUID().toString(),1);
        }
        System.out.println(map.size());
    }

    static class User{
        String name;
        int age;

        public User(){

        }

        public User(String name,int age){
            this.age = age;
            this.name = name;
        }

    }


}
