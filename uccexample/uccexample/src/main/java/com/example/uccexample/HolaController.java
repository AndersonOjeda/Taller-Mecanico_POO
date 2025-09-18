package com.example.uccexample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    private Assistant uccAssistant;

    public HolaController(Assistant uccAssistant) {
        this.uccAssistant = uccAssistant;
    }

    @GetMapping("/")
        public String hi(){
            return this.uccAssistant.chat();
        }

}
