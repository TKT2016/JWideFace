package org.wideface.Imp;

import org.wideface.FaceCode;
import org.wideface.Imp.models.FaceCodeModel;
import org.wideface.Imp.models.WideImpClassModel;
import org.wideface.Imp.models.WideImpMethodModel;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class WideImpClassCreator
{
    public boolean isFace(Class<?> objClass,Class<?> faceClass)
    {
        Method[] faceMethods = faceClass.getMethods();
        for(Method method:faceMethods)
        {
            if(!isImpMethods(objClass,method))
            {
                return false;
            }
        }
        return true;
    }

    public WideImpClassModel Create(Class<?> objClass,Class<?> faceClass)
    {
        WideImpClassModel result = new WideImpClassModel();
        result.FaceClass =faceClass;
        result.ObjClass = objClass;
        result.ClassName = faceClass.getSimpleName()+objClass.getSimpleName();
        result.PackageName = objClass.getPackage().getName();
        result.WideMethods = getImpMethods(objClass,faceClass);
        return result;
    }

    private WideImpMethodModel[] getImpMethods(Class<?> objClass,Class<?> faceClass)
    {
        Method[] faceMethods = faceClass.getMethods();
        //WideImpMethodModel[] array = new WideImpMethodModel[faceMethods.length];
        ArrayList<WideImpMethodModel> list = new ArrayList<WideImpMethodModel>();
        for(Method method:faceMethods)
        {
            WideImpMethodModel model = getImpMethods(objClass,method);
            list.add(model);
        }
        return (WideImpMethodModel[])list.toArray(new WideImpMethodModel[list.size()]);
    }

    private boolean isImpMethods(Class<?> objClass,Method faceMethod)
    {
        FaceCodeModel faceCodeModel= getFaceCodeModel(faceMethod);
        WideImpMethodModel result = new WideImpMethodModel();
        result.FaceMethod =faceMethod;
        Method[] objMethods = objClass.getMethods();
        Method[] methods= searchMethod(objMethods,faceCodeModel);
        Method[] callMethods = findCallMethod(methods,faceMethod);
        if(callMethods.length==1)
        {
            return true;
        }
        return false;
    }

    private WideImpMethodModel getImpMethods(Class<?> objClass,Method faceMethod)
    {
        FaceCodeModel faceCodeModel= getFaceCodeModel(faceMethod);
        WideImpMethodModel result = new WideImpMethodModel();
        result.FaceMethod =faceMethod;
        Method[] objMethods = objClass.getMethods();
        Method[] methods= searchMethod(objMethods,faceCodeModel);
        Method[] callMethods = findCallMethod(methods,faceMethod);
        if(callMethods.length==1)
        {
            result.CallMethod = callMethods[0];
        }
        else  if(callMethods.length>1)
        {
            result.RepeateCallMethods = callMethods;
        }
        else
        {
            result.ErrorCallMethods = methods;

        }
        return result;
    }

    public Method[] findCallMethod(Method[] methods , Method faceMethod )
    {
        ArrayList<Method> list = new ArrayList<>();
        for(Method method:methods)
        {
            if(CheckRetTypeEquals(method,faceMethod))
            {
                if(CheckParameterCountEquals(method,faceMethod))
                {
                    if(CheckParameterTypesEquals(method,faceMethod))
                    {
                        list.add(method);
                    }
                }
            }
        }
        return (Method[])list.toArray(new Method[list.size()]);
    }

    private boolean CheckParameterTypesEquals(Method objMethod, Method faceMethod)
    {
        Class<?>[] objMethodParameterTypes = objMethod.getParameterTypes();
        Class<?>[] faceMethodParameterTypes = objMethod.getParameterTypes();
        int size = objMethodParameterTypes.length;
        for(int i=0;i<size;i++)
        {
            if(objMethodParameterTypes[i]!=faceMethodParameterTypes[i])
            {
                return false;
            }
        }
        return true;
    }

    private boolean CheckParameterCountEquals(Method objMethod, Method faceMethod)
    {
        int objParametersCount = objMethod.getParameterCount();
        int faceParametersCount= faceMethod.getParameterCount();
        boolean result = (objParametersCount==faceParametersCount);
        return result;
    }

    private boolean CheckRetTypeEquals(Method objMethod, Method faceMethod)
    {
        Class<?> objRetType = objMethod.getReturnType();
        Class<?> faceRetType = faceMethod.getReturnType();
        boolean result = (objRetType==faceRetType);
        return result;
    }

    private Method[] searchMethod(Method[] methods,FaceCodeModel faceCodeModel )
    {
        ArrayList<Method> list = new ArrayList<>();
        for(Method method:methods)
        {
            String methodName = method.getName();
            //if(!faceCodeModel.IgnoreCase) {
                if (LibUtil.indexOf(faceCodeModel.Names, methodName,faceCodeModel.IgnoreCase) != -1) {
                    list.add(method);
                }
            //}
        }
        return (Method[])list.toArray(new Method[list.size()]);
    }

    private FaceCodeModel getFaceCodeModel(Method faceMethod) {
        FaceCodeModel model = new FaceCodeModel();
        model.IgnoreCase=false;
        if (faceMethod.isAnnotationPresent(FaceCode.class)) {
            FaceCode faceMethodAnnotationAnno = faceMethod.getAnnotation(FaceCode.class);
            model.Names = getReqMethodName(faceMethod,faceMethodAnnotationAnno);
            model.IgnoreCase = faceMethodAnnotationAnno.ignoreCase();
        }
        else
        {
            model.Names = getReqMethodName(faceMethod,null);
        }
        return model;
    }

    private String[] getReqMethodName(Method faceMethod,FaceCode faceMethodAnnotationAnno) {
        String faceMethodName = faceMethod.getName();
        if (faceMethodAnnotationAnno != null) {
            String[] names = faceMethodAnnotationAnno.value();
            if (LibUtil.indexOf(names, faceMethodName) != -1) {
                return names;
            } else {
                String[] resut = new String[names.length + 1];
                for (int i = 0; i < names.length; i++) {
                    resut[i] = names[i];
                }
                resut[names.length] = faceMethodName;
                return resut;
            }
        } else {
            return new String[]{faceMethodName};
        }
    }
}
