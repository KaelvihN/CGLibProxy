package com.example.cglibproxy.solver;

import org.springframework.cglib.core.Signature;

/**
 * @author : KaelvihN
 * @date : 2023/8/23 17:24
 */
public class ProxyFastClass {
    static Signature sp0 = new Signature("saveSupper", "()V");
    static Signature sp1 = new Signature("saveSupper", "(I)V");
    static Signature sp2 = new Signature("saveSupper", "(J)V");

    /**
     * 获取目标方法的编号
     * <p>
     * Target 目标类中的方法：
     * saveSupper()             0
     * saveSupper(int)          1
     * saveSupper(long)         2
     *
     * @param signature 包含方法名称、参数返回值
     * @return 方法编号
     */
    public int getIndex(Signature signature) {
        if (sp0.equals(signature)) {
            return 0;
        } else if (sp1.equals(signature)) {
            return 1;
        } else if (sp2.equals(signature)) {
            return 2;
        }
        return -1;
    }


    /**
     * 根据 getIndex() 方法返回的方法编号正常调用目标对象方法
     *
     * @param index   方法编号
     * @param proxy   目标对象
     * @param objects 调用目标对象方法需要的参数
     * @return 方法返回结果
     */
    public Object invoke(int index, Object proxy, Object[] objects) {
        if (index == 0) {
            ((Proxy) proxy).saveSupper();
            return null;
        } else if (index == 1) {
            ((Proxy) proxy).saveSupper((int) objects[0]);
            return null;
        } else if (index == 2) {
            ((Proxy) proxy).saveSupper((long) objects[0]);
            return null;
        }
        throw new RuntimeException("无此方法");
    }

    public static void main(String[] args) {
        ProxyFastClass proxyFastClass = new ProxyFastClass();

        int sP0Index = proxyFastClass.getIndex(new Signature("saveSupper", "()V"));
        proxyFastClass.invoke(sP0Index, new Proxy(), new Object[0]);


        int sP2Index = proxyFastClass.getIndex(new Signature("saveSupper", "(J)V"));
        proxyFastClass.invoke(sP2Index, new Proxy(), new Object[]{2L});
    }
}
