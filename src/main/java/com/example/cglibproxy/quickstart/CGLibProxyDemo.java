package com.example.cglibproxy.quickstart;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author : KaelvihN
 * @date : 2023/8/22 16:31
 */
public class CGLibProxyDemo {
    public static void main(String[] args) {
        Target proxy = (Target) Enhancer.create(Target.class,
                (MethodInterceptor) (obj, method, params, methodProxy) -> {
                    //功能增强
                    System.out.println("Before...");
                    //用反射调用目标
                    Object result = method.invoke(new Target(), params);
//                    // 内部没使用反射，需要目标（spring 的选择）
//                    Object result = methodProxy.invoke(new Target(), params);
//                    // 内部没使用反射，需要代理
//                    Object result = methodProxy.invokeSuper(obj, params);
                    return result;
                });
        proxy.foo();
    }
}
