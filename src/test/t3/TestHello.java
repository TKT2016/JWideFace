package test.t3;
import javassist.*;


public class TestHello {
    public static void main(String[] args) throws Exception {
        TestHello testHello= new  TestHello();
        testHello.WriteHello();
    }

    public  void WriteHello() throws Exception {
        ClassPool cp = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(this.getClass());
        cp.insertClassPath(classPath);
        CtClass cc = cp.get("test.t3.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }
}