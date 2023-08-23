package com.example.cglibproxy.solver;


/**
 * @author : KaelvihN
 * @date : 2023/8/22 18:00
 */
public class Test {
    public static void main(String[] args) {
//      test0();
//        test1();
    }

    static Target target = new Target();
    static Proxy proxy = new Proxy();


    public static void test0() {
        proxy.setMethodInterceptor((o, method, objects, methodProxy) -> {
            //方法增强
            System.out.println("Before...");
            return method.invoke(target, objects);
        });
    }

    public static void test1() {
        proxy.setMethodInterceptor((o, method, objects, methodProxy) -> {
            //方法增强
            System.out.println("Before...");
//            return  methodProxy.invoke(target, objects);
            return methodProxy.invokeSuper(o, objects);
        });
        proxy.save();
        proxy.save(1);
        proxy.save(2L);
    }

    public static void test2() {

    }

}
