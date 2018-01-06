package test.t3;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

public class JavaassistDemo2 {
    public static void main(String[] args) throws Exception {

        //获取类池
        ClassPool pool =  ClassPool.getDefault();

        //创建类
        CtClass user = pool.makeClass("test.t3.User");

        //创建属性
        CtField f1 = CtField.make("private String name; ", user);
        CtField f2 = CtField.make("private int age; ", user);
        user.addField(f1);
        user.addField(f2);

        //创建set和get方法
        CtMethod m1 = CtMethod.make("public String getName() {return name;}", user);
        CtMethod m2 = CtMethod.make("public void setName(String name) {this.name = name;}", user);
        CtMethod m3 = CtMethod.make("public int getAge() {return age;}", user);
        CtMethod m4 = CtMethod.make("public void setAge(int age) {this.age = age;}", user);
        user.addMethod(m1);
        user.addMethod(m2);
        user.addMethod(m3);
        user.addMethod(m4);

        //创建构造方法
        CtConstructor cc = new CtConstructor(new CtClass[]{pool.get("java.lang.String"),CtClass.intType}, user);
        user.addConstructor(cc);

        //生成字节码到硬盘
        //user.writeFile("D://");

        Class<?> clazz = user.toClass();
        Object obj = clazz.newInstance();
        clazz.getMethod("setName", String.class).invoke(obj, "Jack");
        clazz.getMethod("run").invoke(obj);

        obj = clazz.getConstructor(String.class).newInstance();
        Object nameobj =  clazz.getMethod("getName").invoke(obj);
        System.out.println("执行结果" + nameobj);

    }
}
