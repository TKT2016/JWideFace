package org.wideface.Imp;
import javassist.*;
import org.wideface.Imp.models.WideImpClassModel;
import org.wideface.Imp.models.WideImpMethodModel;
import org.wideface.WidefaceException;
import java.lang.reflect.Method;

public class WidefaceClassFactory {

    private ClassPool classPool;
    private  CtClass ctClass;
    WideImpClassModel classModel;
    CtClass faceCtClass;
    CtClass objCtClass;
    String fieldName;

    public Class<?> mark(WideImpClassModel classModel) throws CannotCompileException,NotFoundException,WidefaceException
    {
        this.classModel = classModel;
        classPool = ClassPool.getDefault();
        String newClassName = classModel.GetFullName();

        String interfaceName = classModel.FaceClass.getTypeName();
        faceCtClass = classPool.get(interfaceName);
        String objClassTypeNameName = classModel.ObjClass.getTypeName();
        objCtClass =  classPool.get(objClassTypeNameName);
        fieldName = classModel.ObjFieldName;

        ctClass = classPool.makeClass(newClassName); // 创建一个类
        ctClass.setInterfaces(new CtClass[]{faceCtClass}); // 为类型设置接口
        addField();
        addConstructor();
        if(classModel.WideMethods!=null)
        {
            for(WideImpMethodModel item:classModel.WideMethods )
            {
                addMethod(item);
            }
        }
        /*
        try
        {
            ctClass.writeFile("D://");
        }catch (IOException e)
        {   } */

        Class<?> clazz = ctClass.toClass();
        return clazz;
    }

    private void addField() throws CannotCompileException
    {
        CtField field = new CtField(objCtClass, fieldName, ctClass);
        field.setModifiers(Modifier.PRIVATE);
        ctClass.addField(field);
    }

    /* 为类设置构造器 */
    private void addConstructor() throws CannotCompileException
    {
        CtConstructor constructor = new CtConstructor(new CtClass[]{objCtClass}, ctClass);
        constructor.setModifiers(Modifier.PUBLIC);
        String bodyCode= "{this."+fieldName +"=$1;}";
        constructor.setBody(bodyCode);
        ctClass.addConstructor(constructor);
    }

    private void addMethod(WideImpMethodModel wideImpMethodModel) throws CannotCompileException,NotFoundException,WidefaceException
    {
        WidefaceException widefaceException = wideImpMethodModel.getWidefaceException();
        if(widefaceException!=null)
        {
            throw widefaceException;
        }
        Method faceMethod  =wideImpMethodModel.FaceMethod;
        String faceMethodName =faceMethod.getName();
        CtClass retCtClass =getCtClass(faceMethod.getReturnType() );
        CtClass[] parameterCtClasses =getMethodParameterCtClasss(faceMethod);

        CtMethod newMethod = new CtMethod(retCtClass , faceMethodName , parameterCtClasses, ctClass);
        newMethod.setModifiers(Modifier.PUBLIC);
        String bodyCode = emitMethodBody(retCtClass,wideImpMethodModel.CallMethod.getName(), parameterCtClasses);
        newMethod.setBody(bodyCode);
        ctClass.addMethod(newMethod);
    }

    private String emitMethodBody( CtClass retCtClass,String callMethodName, CtClass[] parameterTypes)
    {
        StringBuffer buff = new StringBuffer();
        buff.append(" { ");
        if(retCtClass!= CtClass.voidType)
        {
            buff.append(" return");
        }
        buff.append(" this."+fieldName+".");
        buff.append(callMethodName);
        buff.append(" ( ");
        int size = parameterTypes.length;
        for(int i=0;i<size;i++)
        {
            buff.append(" $"+(i+1));
            if(i<size-1)
            {
                buff.append(" , ");
            }
        }
        buff.append(" ) ");
        buff.append(" ; ");
        buff.append(" } ");
        return buff.toString();
    }

    private CtClass[] getMethodParameterCtClasss(Method method) throws NotFoundException
    {
        Class<?>[] parameterTypes = method.getParameterTypes();
        CtClass[] list = new CtClass[parameterTypes.length];
        int i=0;
        for( Class<?> item:parameterTypes) {
            CtClass ctClass = getCtClass(item);
            list[i]=ctClass;
            i++;
        }
        return list;
    }

    private CtClass getCtClass(Class<?> classObj) throws NotFoundException
    {
        return classPool.get(classObj.getTypeName());
    }
}
