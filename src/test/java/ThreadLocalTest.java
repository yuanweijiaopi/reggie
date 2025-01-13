import org.junit.jupiter.api.Test;

/**
 * @ClassName ThreadLocalTest
 * @Description TODO
 * @Author aql
 * @Date 2025/1/13 14:17
 * @Version 1.0
 **/
public class ThreadLocalTest {

    @Test
    public void test() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal.set("张三");
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
        },"white").start();

        new Thread(() -> {
            threadLocal.set("李四");
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
        },"black").start();

        new Thread(() -> {
            threadLocal.set("王二麻");
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
        },"yellow").start();
    }
}
