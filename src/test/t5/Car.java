package test.t5;

import org.wideface.FaceCode;

public interface Car {
    void move();

    @FaceCode(value = {"say","beep","Say","cry"}, ignoreCase = true)
    void say();
}
