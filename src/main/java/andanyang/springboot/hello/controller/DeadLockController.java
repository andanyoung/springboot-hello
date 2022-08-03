package andanyang.springboot.hello.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andanyang
 * @since 2022/8/3 15:22
 */
@RestController
@RequestMapping("DeadLock")
public class DeadLockController {

    static Object o1 = new Object();//资源1
    static Object o2 = new Object();//资源2

    static Thread thread1;
    static Thread thread2;

    @GetMapping("start")
    public String start() {

        thread1 = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                synchronized (o1) { //对象互斥锁,  下面就是同步代
                    System.out.println(Thread.currentThread().getName() + " 进入 1");
                    Thread.sleep(2000);
                    synchronized (o2) { //  这里获得 li 对象的监视权
                        System.out.println(Thread.currentThread().getName() + " 进入 2");
                    }
                }
            }
        });

        thread2 = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                synchronized (o2) { //对象互斥锁,  下面就是同步代
                    System.out.println(Thread.currentThread().getName() + " 进入 3");
                    Thread.sleep(1000);
                    synchronized (o1) { //  这里获得 li 对象的监视权
                        System.out.println(Thread.currentThread().getName() + " 进入 4");
                    }
                }
            }
        });
        thread1.setName("DeadLock - thread1");
        thread1.start();
        thread2.setName("DeadLock - thread2");
        thread2.start();
        return "DeadLock start";
    }
}
