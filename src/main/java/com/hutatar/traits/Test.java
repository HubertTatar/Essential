package com.hutatar.traits;

interface AA {
    String test();
}

interface BB {
    String test();
}

public class Test implements AA, BB {
    @Override
    public String test() {
        return "w";
    }

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.test());
    }
}
