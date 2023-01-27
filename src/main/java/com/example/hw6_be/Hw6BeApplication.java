package com.example.hw6_be;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Hw6BeApplication {
    public static void main(String[] args) {
        SpringApplication.run(Hw6BeApplication.class, args);
    }
}

@RestController
@RequestMapping("math/examples")
@RequiredArgsConstructor
class ExampleController {
    static final String[] operators = {"/","*","-","+"};


    @GetMapping
    public ResponseEntity<List<String>> createExamples(@RequestParam int count) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Accept","application/json");

        List<String> examples = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            examples.add(generateRandomExample());
        }
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(examples);
    }

    private String generateRandomExample() {
        StringBuilder example = new StringBuilder();
        example.append((int) (Math.random() * 100));
        int a = (int) (Math.random() * 3);
        example.append(operators[a]);
        example.append((int) (Math.random() * 100));
        return String.valueOf(example);
    }


}

