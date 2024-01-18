package com.example.demo.GPT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gpt")
public class GPTController {

    @Autowired
    private GPTService openAIService;

    @PostMapping("/prompt")
    public String completeChat(@RequestBody String prompt) {
        return openAIService.getChatCompletion(prompt);
    }
}
