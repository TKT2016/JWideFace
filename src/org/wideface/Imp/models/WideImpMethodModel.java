package org.wideface.Imp.models;

import org.wideface.WidefaceException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class WideImpMethodModel {
    public Method FaceMethod;
    public Method CallMethod;
    public Method[] ErrorCallMethods;
    public Method[] RepeateCallMethods;

    /*
    public boolean isValidity()
    {
        if(FaceMethod==null) return false;
        if(CallMethod==null) return false;
        return true;
    }*/

    public WidefaceException getWidefaceException()
    {
        if(FaceMethod!=null && CallMethod!=null)
        {
            return null;
        }

        if(ErrorCallMethods!=null && ErrorCallMethods.length>0)
        {
            String errNames =getMethodNames(ErrorCallMethods);
            return  new WidefaceException( errNames + " 's return type or parameters type is not same to  "+FaceMethod.getName());
        }
        else
        {
            String errNames =getMethodNames(ErrorCallMethods);
            return  new WidefaceException( errNames + " repeat to  "+FaceMethod.getName());
        }
    }

    private String getMethodNames(Method[] methods)
    {
        StringBuffer buff = new StringBuffer();
        int size = methods.length;
        for(int i=0;i<size;i++)
        {
            String methodName = methods[i].getName();
            buff.append(methodName);
            if(i<size)
            {
                buff.append(",");
            }
        }
        return buff.toString();
    }

}
