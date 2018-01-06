package sample;

/* 真正的鱼 */
public class RealFish {

    private String DNA="fish DNA";

    public void swim()
    {
        System.out.println("RealFish Swimming!");
    }

    public String getDNA()
    {
        return DNA;
    }

    /* 鱼鳞 */
    public String getScale()
    {
        return "fish scale";
    }
}
