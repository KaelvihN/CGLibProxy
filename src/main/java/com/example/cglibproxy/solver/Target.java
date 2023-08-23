package com.example.cglibproxy.solver;

/**
 * @author : KaelvihN
 * @date : 2023/8/22 17:29
 */
public class Target {
    public void save() {
        System.out.println("Target save()");
    }

    public void save(int i) {
        System.out.println("Target Save(int)");
    }

    public void save(long i) {
        System.out.println("Target save(long)");
    }
}
