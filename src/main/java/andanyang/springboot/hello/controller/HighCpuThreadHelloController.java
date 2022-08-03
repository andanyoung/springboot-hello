package andanyang.springboot.hello.controller;

import andanyang.springboot.hello.entity.HignCpu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andanyang
 * @since 2022/8/3 13:19
 */
@RestController
@RequestMapping("highCpuThread")
public class HighCpuThreadHelloController {

    private static List<HignCpu> cpus = new ArrayList<>();
    private static Thread highCpuThread;

    @GetMapping("start")
    public String start() {

        highCpuThread = new Thread(() -> {
            int i = 0;
            while (true) {

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread isInterrupted");
                    return;
                }
                HignCpu cpu = new HignCpu("Young、", i);

                cpus.add(cpu);
                System.out.println("high cpu size:" + cpus.size());
                i++;
            }
        });
        highCpuThread.setName("HignCpu");
        highCpuThread.start();

        return "start";
    }

    @GetMapping("stop")
    public String stop() {

        if (highCpuThread != null) {

            highCpuThread.interrupt();
        }
        return "stop";
    }
}
