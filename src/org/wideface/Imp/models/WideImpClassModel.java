package org.wideface.Imp.models;

public class WideImpClassModel {
    public String PackageName;
    public String ClassName;

    public String GetFullName()
    {
        return PackageName+"."+ClassName;
    }

    public String ObjFieldName;
    public Class<?> ObjClass;
    public Class<?> FaceClass;

    public WideImpMethodModel[] WideMethods;

    public WideImpClassModel()
    {
        //PackageName="org.JWideface.__emited";
        ObjFieldName="__DATA";
    }
}
