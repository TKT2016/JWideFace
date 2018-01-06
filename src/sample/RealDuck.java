package sample;

import java.util.concurrent.RecursiveTask;

/* 真正的鸭子 */
public class RealDuck {

    private String name;
    private String DNA="duck DNA";

    public RealDuck(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }

    public void Quack()
    {
        System.out.println("RealDuck '"+ this.name +"' Quack!");
    }

    public void swim()
    {
        System.out.println("RealDuck  '"+ this.name +"'  Swimming!");
    }

    public String getDNA()
    {
        return DNA;
    }

}
