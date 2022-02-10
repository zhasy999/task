package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1")
public class Controller {
    private static List<Model> models = Collections.synchronizedList(new ArrayList<>() {{
        add(new Model(1, 1, 55));
        add(new Model(1, 2, 8));
        add(new Model(1, 3, 15));
        add(new Model(1, 2, 78));
        add(new Model(1, 3, 78));
        add(new Model(3, 1, 45));
        add(new Model(1, 2, 23));
        add(new Model(2, 3, 7));
        add(new Model(2, 3, 22));
        add(new Model(3, 2, 29));
        add(new Model(3, 1, 20));
    }});


    @GetMapping("/userinfo/{userId}")
    public List<Model> getUsers(@PathVariable Integer userId) {
        return models.stream().filter(e -> e.getUserId().equals(userId))
                .sorted(Comparator.comparingInt(Model::getResult)
                        .thenComparingInt(Model::getLevelId).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

    @GetMapping("/levelinfo/{levelId}")
    public List<Model> getLevel(@PathVariable Integer levelId) {
        return models.stream().filter(e -> e.getLevelId().equals(levelId))
                .sorted(Comparator.comparingInt(Model::getResult)
                        .thenComparing(Model::getUserId).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

    @PutMapping("/setinfo")
    public void saveInfo(@RequestBody Model model) {
        models.add(model);
        log.info("Info for user: " + model.getUserId() + " added");
    }
}
