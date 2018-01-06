package test.t4;

public class Cface1 {
    String n1="";
    String n2="";

    public void setName1(String name1)
    {
        n1=name1;
    }

    public void setName2(String name2){
        n2=name2;
    }

    public void setTexts(String s1,String s2,String s3)
    {
        System.out.println("setTexts "+s1+"," +s2+","+s3 );
    }

    public String getName()
    {
        return n1+" "+n2;
    }

    public String getTexts(String s1,String s2,String s3)
    {
        System.out.println("getTexts "+s1+"," +s2+","+s3 );
        return s1+"," +s2+","+s3;
    }
}
