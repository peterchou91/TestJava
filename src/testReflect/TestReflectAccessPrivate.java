package testReflect;

import java.lang.reflect.Field;
import java.lang.reflect.ReflectPermission;

/**
 * Created by zhouqing on 17/3/4.
 */
public class TestReflectAccessPrivate {
    public static void main(String[] args) throws Exception{
        SecurityManager sm = new SecurityManager();
        sm.checkPermission(new ReflectPermission("supressAccessChecks"));
        A a = new A();
        Class aClazz = a.getClass();
        Field bFlied = aClazz.getDeclaredField("b");
        bFlied.setAccessible(true);
        System.out.println(bFlied.get(a));


    }


}
