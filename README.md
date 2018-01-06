#JWideface(大饼脸)技术介绍
在程序开发中，我们会有一些别人开发的类，有相近的方法，我们要对它们进行处理而又不能对这些类进行修改。这时用wideface(大饼脸)技术可以很方便的处理。
比如有一个类A，实例是a，它有方法 void run()；另一个类B，实例是b，它有方法void exec();还有一个类C，实例是c，它有方法 void move()。如果按一般方法要写
```java
a.run();
b.exec();
c.move();
```
现在用wideface技术开发。首先定义一个接口Interface1，定义一个方法void go();
在该方法上添加标注
```java
@FaceCode(value = {"run","exec","move"} )
void go();
```
表示有方法名叫"run","exec","move"，返回值void ,参数和go一个的类都是Interface1
用方法调用
```java
Interface1 a2 = JWideFace.markFace(a, Interface1.class);
a2.go();
Interface1 b2 = JWideFace.markFace(b, Interface1.class);
b2.go();
Interface1 c2 = JWideFace.markFace(c, Interface1.class);
c2.go();
```
a2.go()调用的实际还是a.run()，b2.go()调用的实际还是b.exec()，c2.go()调用的实际还是c.move()。
这样就做到了对这些类的统一处理。


#JWideface(大饼脸)包使用方法
1.添加引用javassist.jar(在/lib内)和JWideFace.jar(在/out/artifacts/JWideFace_jar内)两个文件
2.新建一个interface，定义方法
3.导入FaceCode
```java
import org.wideface.FaceCode;
```

在方法上添加FaceCode标注
```java
@FaceCode(value = {"<name1>","<name2>",...}, ignoreCase = true )
```
如果接口的方法名和实际类的方法名一致，可以不写在value中

value的值是要映射的方法名称，映射的返回值和参数类型顺序要和接口中定义的方法一致
ignoreCase是是否忽略大小写，默认否

3.在使用的类中导入JWideFace
```java
import org.wideface.JWideFace;
```

4.JWideFace.isFace
```java
boolean isFace(Object obj, Class<?> faceClass)
```
判断obj的类型是否符合这个接口

5.JWideFace.markFace
```java
public static <T> T markFace(Object obj, Class<T> faceClass) throws WidefaceException
```
给obj创建接口包装类，不符合条件返回null

**src/sample/SampleTest1.java 中有详细例子**




