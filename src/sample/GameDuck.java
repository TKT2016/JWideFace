package sample;

/* 游戏中的鸭子 */
public class GameDuck {
    private String sn;

    public GameDuck(String sn)
    {
        this.sn=sn;
    }

    public String getSn()
    {
        return this.sn;
    }

    public void say()
    {
        System.out.println("GameDuck  '"+ this.sn +"' say!");
    }

    public void swim()
    {
        System.out.println("GameDuck  '"+ this.sn +"' Swimming!");
    }
}
