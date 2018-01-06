package org.wideface.Imp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectUtil
{
    public static Class<?> getClass(String classFullName)
    {
        try {
            Class<?> cls = Class.forName(classFullName);
            return cls;
        }catch (ClassNotFoundException e)
        {
            return null;
        }
    }
    /*
    public static TypeInfo getTypeInfo(Class<?> classobj)
    {
        TypeInfo typeInfo = new TypeInfo();
        typeInfo.Class = classobj;
        typeInfo.Name = classobj.getSimpleName();
        typeInfo.FullName = classobj.getTypeName();
        typeInfo.PackageName = classobj.getPackage().getName();
        typeInfo.SuperClass = classobj.getSuperclass();
        typeInfo.Fields = getFields(classobj);
        typeInfo.Constructors = getConstructors(classobj);
        typeInfo.Methods = getMethods(classobj);
        typeInfo.Interfaces = getInterfaces(classobj);
        return typeInfo;
    }*/

    public static Class<?>[] getInterfaces(Class<?> classobj){
        Class<?>[] interfaces =classobj.getInterfaces();
        return  interfaces;
    }

    public static Field[] getFields(Class<?> classobj){
        Field[] fields =classobj.getFields();//classobj.getDeclaredFields();
        return  fields;
        /*for(int i = 0; i <= fields.length - 1; i++){
            modifier = Modifier.toString(fields[i].getModifiers());
            type = fields[i].getType().getName();
            name = fields[i].getName();
            System.out.println("    " + modifier + " " + type + " " + name + ";");
        } */
    }

    public static  Method[] getMethods(Class<?> classobj){
        Method [] methods =classobj.getMethods();//classobj.getDeclaredMethods();
        return  methods;
        /*String modifier = "";
        String returnType = "";
        String name = "";
        Class [] paraClasses = null;
        StringBuffer paraType = new StringBuffer();*/

        /* for(int i = 0; i <= methods.length - 1; i++){
            modifier = Modifier.toString(methods[i].getModifiers());
            returnType = methods[i].getReturnType().getName();
            name = methods[i].getName();
            paraClasses = methods[i].getParameterTypes();
            for(int j = 0; j <= paraClasses.length - 1; j++){
                if(j != paraClasses.length - 1){
                    paraType.append(paraClasses[j].getName() + ", ");
                }
                else{
                    paraType.append(paraClasses[j].getName());
                }
            }
            System.out.println("    " + modifier + " " + returnType + " " + name + "(" + paraType.toString() + ")");
        }
        */
    }

    public static Constructor[] getConstructors(Class<?> classobj){
        Constructor[] constructors = classobj.getConstructors();
        return constructors;
        /*
        String modifier = "";
        String name = "";
        Class [] paraClasses = null;
        StringBuffer paraType = new StringBuffer();
        for(int i = 0; i <= constructors.length - 1; i++){
            modifier = Modifier.toString(constructors[i].getModifiers());
            name = constructors[i].getName();
            paraClasses = constructors[i].getParameterTypes();
            for(int j = 0; j <= paraClasses.length - 1; j++){
                if(j != paraClasses.length - 1){
                    paraType.append(paraClasses[j].getName() + ", ");
                }
                else{
                    paraType.append(paraClasses[j].getName());
                }
            }
            System.out.println("    " + modifier + " " + name + "(" + paraType.toString() + ")");
        }
        */
    }
}
