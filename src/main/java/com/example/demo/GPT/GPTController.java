package com.example.demo.GPT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/gpt")
public class GPTController {

    @Autowired
    private GPTService openAIService;

    @PostMapping("/prompt")
    public String completeChat(@RequestBody String prompt) {
        return openAIService.getChatCompletion(prompt);
    }

    @PostMapping("/locations")
    public String recommendOtherLocations(@RequestBody ArrayList<String> locations) {
        String prompt = "I like the following locations: " + String.join(", ", locations) + ". What other locations would you recommend?";
        return openAIService.getChatCompletion(prompt);
    }

}
