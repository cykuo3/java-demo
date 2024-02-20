package demo;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    static class OOMObject{
        static final String value = "1234567890";
    }
    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
