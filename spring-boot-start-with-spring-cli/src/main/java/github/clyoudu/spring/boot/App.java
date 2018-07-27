package github.clyoudu.spring.boot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@RestController
public class App {

    @RequestMapping("/{name}")
    public String hello(@PathVariable(value = "name") String name){
        return "Hello " + name;
    }

}
