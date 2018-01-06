package org.wideface;

public class WidefaceException extends Exception {
    public WidefaceException(String msg) {
        super(msg);
    }
    public WidefaceException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }
}
