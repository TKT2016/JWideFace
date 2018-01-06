package sample;

/* 电动鸭 */
public class ElectricDuck {

    private String sn;

    public ElectricDuck(String sn)
    {
        this.sn=sn;
    }

    public String getSn()
    {
        return this.sn;
    }

    public void quack()
    {
        System.out.println("ElectricDuck  '"+ this.sn +"' Quaaaaaack!");
    }

    public void swim()
    {
        System.out.println("ElectricDuck  '"+ this.sn +"' Swimming!");
    }
}
