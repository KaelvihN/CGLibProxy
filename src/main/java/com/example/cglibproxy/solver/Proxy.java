package com.example.cglibproxy.solver;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : KaelvihN
 * @date : 2023/8/22 17:50
 */
public class Proxy extends Target {

    private MethodInterceptor methodInterceptor;

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    static Method save0;
    static Method save1;
    static Method save2;
    static MethodProxy save0Proxy;
    static MethodProxy save1Proxy;
    static MethodProxy save2Proxy;

    /**
     *防止重复创建Method对象
     */
    static {
        try {
            save0 = Target.class.getMethod("save");
            save1 = Target.class.getMethod("save", int.class);
            save2 = Target.class.getMethod("save", long.class);
            /**
             * 参数解释：
             * 第一个参数：目标类
             * 第二个参数：代理类
             * 第三个参数：方法描述 ()=参数为void，(I)=参数为int,(J)=参数为long,v = 无返回值
             * 第四个参数：对应的是Target 的save方法
             * 第五个参数：对应的是saveProxy 的saveSupper方法
             */
            save0Proxy =
                    MethodProxy.create(Target.class, Proxy.class, "()v", "save", "saveSupper");
            save1Proxy =
                    MethodProxy.create(Target.class, Proxy.class, "(I)v", "save", "saveSupper");
            save2Proxy =
                    MethodProxy.create(Target.class, Proxy.class, "(J)v", "save", "saveSupper");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 带增强功能的save
     */
    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this, save0, new Object[0], save0Proxy);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this, save1, new Object[]{i}, save1Proxy);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(long i) {
        try {
            methodInterceptor.intercept(this, save2, new Object[]{i}, save2Proxy);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 带原始功能的save
     */
    public void saveSupper() {
        super.save();
    }

    public void saveSupper(int i) {
        super.save(i);
    }


    public void saveSupper(long i) {
        super.save(i);
    }
}
