import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午4:13
 * {@code @version:} 1.0.0
 */


public class Test {

    static class A {
        public String getA() {
            return "a";
        }
    }

    public static void main(String[] args) {
        A a = new A();
        Enhancer enhancerB = new Enhancer();
        enhancerB.setSuperclass(a.getClass());
        enhancerB.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return proxy.invokeSuper(obj, args) + "b";
            }
        });
        A ab = (A) enhancerB.create();
        System.out.println(ab.getA());
        Enhancer enhancerC = new Enhancer();
        enhancerC.setSuperclass(ab.getClass());
        enhancerC.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return proxy.invokeSuper(obj, args) + "c";
            }
        });
        A abc = (A) enhancerC.create();
        System.out.println(abc.getA());
    }
}
