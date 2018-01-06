package org.wideface;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.wideface.Imp.DoubleKeyMap;
import org.wideface.Imp.WideImpClassCreator;
import org.wideface.Imp.WidefaceClassFactory;
import org.wideface.Imp.models.WideImpClassModel;
import java.lang.reflect.InvocationTargetException;

public class JWideFace {
    private static DoubleKeyMap<Class<?>,Class<?>,Class<?>> widefaceCache = new DoubleKeyMap<Class<?>,Class<?>,Class<?>>();
    private static DoubleKeyMap<Object,Class<?>,Object> instanceCache = new DoubleKeyMap<Object,Class<?>,Object>();
    private static DoubleKeyMap<Class<?>,Class<?>,Boolean> isFaceCache = new DoubleKeyMap<Class<?>,Class<?>,Boolean>();

    public static boolean isFace(Object obj, Class<?> faceClass)
    {
        return isFace(obj.getClass() ,  faceClass);
    }

    public static boolean isFace(Class<?> objClass, Class<?> faceClass)
    {
        if(!faceClass.isInterface())
        {
            return false;
        }
        if(widefaceCache.contains(faceClass,objClass))
        {
            return true;
        }
        if(isFaceCache.contains(faceClass,objClass))
        {
            Boolean b = isFaceCache.get(faceClass,objClass);
            return b.booleanValue();
        }
        WideImpClassCreator wideImpClassCreator= new WideImpClassCreator();
        boolean result = wideImpClassCreator.isFace(objClass,faceClass);
        isFaceCache.put(faceClass,objClass,new Boolean(result));
        return result;
    }

    public static <T> T markFace(Object obj, Class<T> faceClass)
            throws WidefaceException
    {
        if(!faceClass.isInterface())
        {
            throw new WidefaceException("faceClass is not interface");
        }
        Class<?> newClass = getWidefaceClass(obj.getClass(),faceClass);
        if(newClass==null) return null;
        Object newObj = getObjInstance(obj,newClass);
        return (T)newObj;
    }

    public static Object getObjInstance(Object datObj, Class<?> widefaceClass)
            throws WidefaceException
    {
        if(instanceCache.contains(datObj,widefaceClass))
        {
            return instanceCache.get(datObj,widefaceClass);
        }
        else
        {
            Object newObj = newObj(datObj,widefaceClass);
            instanceCache.put(datObj,widefaceClass,newObj);
            return newObj;
        }
    }

    private static Object newObj(Object datObj, Class<?> newClass) throws WidefaceException
    {
        try {
            Object newObj = newClass.getConstructor(datObj.getClass()).newInstance(datObj);
            return newObj;
        }
        catch (NoSuchMethodException e)
        {
            throw new WidefaceException("constructor error",e);
        }
        catch (SecurityException e)
        {
            throw new WidefaceException("Security problem",e);
        }
        catch (InvocationTargetException e)
        {
            throw new WidefaceException("invocation target exception",e);
        }
        catch (IllegalAccessException e)
        {
            return null;
        }
        catch (InstantiationException e)
        {
            return null;
        }
    }

    private static Class<?> getWidefaceClass(Class<?> objClass, Class<?> faceClass) throws WidefaceException
    {
        if(widefaceCache.contains(faceClass,objClass))
        {
            return widefaceCache.get(faceClass,objClass);
        }
        else
        {
            Class<?> newClass= createWidefaceClass(objClass,faceClass);
            if(newClass!=null)
            {
                widefaceCache.put(faceClass,objClass,newClass);
            }
            return newClass;
        }
    }

    private static Class<?> createWidefaceClass(Class<?> objClass, Class<?> faceClass)  throws WidefaceException
    {
        WideImpClassCreator wideImpClassCreator= new WideImpClassCreator();
        WidefaceClassFactory widefaceFactory = new WidefaceClassFactory();

        WideImpClassModel wideImpClassModel = wideImpClassCreator.Create(objClass ,faceClass);
        Class<?> newClass =null;
        try {
            newClass = widefaceFactory.mark(wideImpClassModel);
            return newClass;
        }
        catch (NotFoundException e)
        {
            throw new WidefaceException("Not Found Exception :"+e.getMessage(),e);
        }
        catch (CannotCompileException e)
        {
            throw new WidefaceException("Cannot Compile Exception :"+e.getMessage(),e);
        }
    }
}
