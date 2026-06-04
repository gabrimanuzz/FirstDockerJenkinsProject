package com.test.testproject;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DragonController {

    private List<Dragon> dragons = new ArrayList<>(List.of(
            new Dragon("Smaug", "rosso", 500),
            new Dragon("Alduin", "nero", 1000),
            new Dragon("Drogon", "nero", 5),
            new Dragon("Viserion", "bianco", 5)
    ));


    @GetMapping("/draghi")
    public List<Dragon> getDraghi() {
        return dragons;
    }

    @GetMapping("/draghi/{nome}")
    public Dragon getDrago(@PathVariable String nome) {
        return dragons.stream()
                .filter(d -> d.getName().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/draghi")
    public Dragon addDragon(@RequestBody Dragon dragon) {
        dragons.add(dragon);
        return dragon;
    }
}