package InnerClass;

/**
 * Created by zhouqing on 16/12/4.
 */
interface Destination{
    String readLabel();
}

interface Contents{
    int value();
}

class Parcel4{
    public Parcel4(){
        g();
    }

    public void g(){
        System.out.println("Parcel4 g()");
    }

    int u = 0;
    static int staticU = 0;

    public static String f(){
        System.out.println("f()");
        return "";
    }

    private class PContents implements Contents{
        private int i = 11;
        public int value(){
            u = 9;
            staticU = 9;
            return i;
        }

    }

    protected class PDestination implements Destination{
        private String label;
        private PDestination(String whereto){
            label = whereto;
        }

        public String readLabel(){
            return label;
        }
    }

    public Destination destination(String s){
        return new PDestination(s);
    }

    public Contents contents(){
        return new Contents() {
            @Override
            public int value() {
                return 0;
            }
        };
        //return new PContents();
    }

}

class SubParcel4 extends Parcel4{
    public static String f(){
        System.out.println("sub f()");
        return "";
    }

    public void g(){
        System.out.println("SubParcel4 g()");
    }

}

public class TestParcel {
    public static void main(String[] args){
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("a");
        SubParcel4.f();
        Parcel4.f();

        Parcel4 p4 = new Parcel4();
        p4.f();
System.out.println("====");
        Parcel4 pp = new SubParcel4();
        SubParcel4 sp4 = new SubParcel4();
        pp.f();
        sp4.f();

    }
}
