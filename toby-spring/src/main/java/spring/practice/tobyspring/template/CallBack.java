package spring.practice.tobyspring.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface CallBack {
    Integer doSomethingWithBufferedReader(BufferedReader br) throws IOException;
}
