package com.test.testproject;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DragonController {

    private final DragonRepository repository;

    public DragonController(DragonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/draghi")
    public List<Dragon> getDraghi() {
        return repository.findAll();
    }

    @GetMapping("/draghi/{id}")
    public Dragon getDrago(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/draghi")
    public Dragon addDrago(@RequestBody Dragon dragon) {
        return repository.save(dragon);
    }

    @DeleteMapping("/draghi/{id}")
    public void deleteDrago(@PathVariable Long id) {
        repository.deleteById(id);
    }
}