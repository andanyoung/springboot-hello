package andanyang.springboot.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andanyang
 * @since 2021/12/10 15:24
 */
@RestController
public class HelloController {

    @GetMapping
    public String world(){
        return "HELLO WORLD";
    }
}
