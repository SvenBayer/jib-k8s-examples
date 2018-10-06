package blog.svenbayer.jib.k8s.limits.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/")
    public ResponseEntity<String> helloJib() {
        return ResponseEntity.ok("Hello JIB");
    }
}
