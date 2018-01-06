package test.t5;

import org.wideface.JWideFace;


public class MainTest {
    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception
    {
        RealCar rc1 = new RealCar();
        ToyCar tc2 = new ToyCar();
        MotorCar mc3 = new MotorCar();
        WaterCar wc4 = new WaterCar();

        Car c1 = JWideFace.markFace(rc1, Car.class);
        dosome(c1);

        Car c2 = JWideFace.markFace(tc2, Car.class);
        dosome(c2);

        Car c3 = JWideFace.markFace(mc3, Car.class);
        dosome(c3);

        Car c4 = JWideFace.markFace(wc4, Car.class);
        dosome(c4);
    }

    public static void dosome(Car car)
    {
        car.move();
        car.say();
    }
}
