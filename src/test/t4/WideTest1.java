package test.t4;

import org.wideface.JWideFace;

public class WideTest1 {

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception
    {
        Cface1 cface1 = new Cface1();
        Iface1 f1 = JWideFace.markFace(cface1, Iface1.class);
        f1.setName1("Mark");
        f1.setName2("Down");
        System.out.println(f1.getName());
    }
}
