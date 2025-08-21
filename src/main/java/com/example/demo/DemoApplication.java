package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
@Controller
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setDefaultProperties(Map.of("server.port", "9091"));
        app.run(args);
    }

    @GetMapping("/")
    @ResponseBody
    public String showForm() {
        return """
            <html>
                <body>
                    <h2>Add & Multiply Two Numbers</h2>
                    <form method="post" action="/calculate">
                        Number 1: <input type="number" name="a" required><br><br>
                        Number 2: <input type="number" name="b" required><br><br>
                        <input type="submit" value="Calculate">
                    </form>
                </body>
            </html>
        """;
    }

    @PostMapping("/calculate")
    @ResponseBody
    public String calculate(@RequestParam int a, @RequestParam int b) {
        int sum = a + b;
        int product = a * b;
        return """
            <html>
                <body>
                    <h2>Results</h2>
                    <p>Sum of %d and %d = %d</p>
                    <p>Product of %d and %d = %d</p>
                    <a href="/">Try again</a>
                </body>
            </html>
        """.formatted(a, b, sum, a, b, product);
    }
}
