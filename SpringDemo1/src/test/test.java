package proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class test {

    @Test
    public void test(){
        final Target target = new Target();

        //构建增强器
        final Enhancer enhancer = new Enhancer();

        //设置父类
        enhancer.setSuperclass(target.getClass());

        //设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("111");

                Object invoke = method.invoke(target, objects);

                System.out.println("222");

                return invoke;

            }
        });

        //创建代理对象
        Target o = (Target) enhancer.create();

        o.print();

    }
}
