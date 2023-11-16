package mesander.com.TechItEasy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TelevisionController {



    @GetMapping("/televisions")
    public String getAllTelevisions() {
        return "Dit is een lijst met alle televisies";
    }

    @PostMapping("/televisions")
    public void postTelevision(@RequestParam String television) {

    }
}
