package sample;

import org.wideface.FaceCode;
import org.wideface.JWideFace;

/*生物*/
interface Biology
{
    String getDNA();
}

interface Duck
{
    @FaceCode(value = {"quack","say"}, ignoreCase = true )
    void quack();
    void swim();
}

interface Ikey
{
    @FaceCode(value = {"getName","getSn"})
    String getKey();
}

public class SampleTest1 {

    public static void main(String[] args) throws Exception {
        /* ElectricDuck */
        ElectricDuck electricDuck = new ElectricDuck("amd2017");
        testFace(electricDuck);

        /* GameDuck */
        GameDuck gameDuck = new GameDuck("gameduck2018");
        testFace(gameDuck);

        /* RealDuck */
        RealDuck realDuck = new RealDuck("tang duck");
        testFace(realDuck);

        /* RealFish */
        RealFish realfish = new RealFish();
        testFace(realfish);

    }

    private static void testFace(Object obj) throws Exception
    {
        String objClassName = obj.getClass().getSimpleName();
        System.out.println("************* Test "+ objClassName +" *************");

        if(JWideFace.isFace(obj,Biology.class))
        {
            Biology biology4 = JWideFace.markFace(obj, Biology.class);
            System.out.println(biology4.getDNA());
        }
        else
        {
            System.out.println(objClassName +" is not Biology");
        }

        if(JWideFace.isFace(obj,Duck.class))
        {
            Duck duck4 = JWideFace.markFace(obj, Duck.class);
            duck4.quack();
            duck4.swim();
        }
        else
        {
            System.out.println(objClassName +" is not Duck");
        }

        if(JWideFace.isFace(obj,Ikey.class))
        {
            Ikey ikey4 = JWideFace.markFace(obj, Ikey.class);
            System.out.println("key is '"+ ikey4.getKey()+"' ");
        }
        else
        {
            System.out.println(objClassName +" is not Ikey");
        }
    }
}
