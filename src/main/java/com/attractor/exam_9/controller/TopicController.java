package com.attractor.exam_9.controller;

import com.attractor.exam_9.dto.TopicDTO;
import com.attractor.exam_9.exception.PaswordNotFoundException;
import com.attractor.exam_9.model.Topic;
import com.attractor.exam_9.service.PropertiesService;
import com.attractor.exam_9.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String main(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        var topics = topicService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(topics, propertiesService.getDefaultPageSize(), model, uri);
        return "index";
    }
    @GetMapping("/addTopic")
    public String addition() {
        return "addTopic";
    }

    @PostMapping("/addTopic")
    public String formPost(Topic topic, Model model) {
        model.addAttribute("topic",topic);
        topicService.saveTopic(topic);
        return "result";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTopic(@PathVariable Integer id, Model model) {
        model.addAttribute("id",id);
        topicService.deleteTopic(id);
        return "result";
    }


    @RequestMapping("/topic/{id}")
    public String getTopicById(@PathVariable Integer id, Model model) {
        model.addAttribute("product", topicService.getTopicById(id));
        return "topic";
    }



    @GetMapping("/search={text}")
    public List<TopicDTO> search(@PathVariable String text, Pageable pageable){
        return topicService.search(text, text, pageable).getContent();
    }
    @GetMapping("/id={authorId}")
    public List<TopicDTO> getTopicsByAuthorId(@PathVariable String authorId, Pageable pageable){
        return topicService.getAllByAuthorId(Integer.parseInt(authorId), pageable).getContent();
    }

    @ExceptionHandler(PaswordNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(PaswordNotFoundException ex, Model model) {

        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }

}