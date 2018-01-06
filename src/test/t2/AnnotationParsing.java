package test.t2;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.wideface.*;

public class AnnotationParsing {
    public static void main(String[] args) {
        ShowClassInfo(AnnotationParsing.class);

    }
    public static void ShowClassInfo(Class<?> classObj) {
        try {

            for (Method method : classObj
                    .getClassLoader()
                    .loadClass(("test.t2.AnnotationExample"))
                    .getMethods()) {
                Annotation[] annos = method.getDeclaredAnnotations();
                if(annos.length>0) {
                    System.out.println("--------------------------- ");
                    System.out.println(" Method:'" + method);
                    // iterates all the annotations available in the method
                    for (Annotation anno : annos) {
                        System.out.println(anno);
                    }
                    //System.out.println("------------ " );
                    // checks if MethodInfo annotation is present for the method
                /*
                if (method.isAnnotationPresent(MethodInfo.class)) {
                    try {
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);

                            System.out.println("Method with revision "+ methodAnno.revision());

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }

                */if (method.isAnnotationPresent(FaceCode.class)) {
                    try {
                        // iterates all the annotations available in the method
                       // for (Annotation anno : method.getDeclaredAnnotations()) {
                       //     System.out.println("Annotation in Method ''+ method + '' : " + anno);
                       // }
                        FaceCode methodAnno = method.getAnnotation(FaceCode.class);
                            System.out.println(" @@ FaceCode.value  = "+ methodAnno.value()[0]);

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

