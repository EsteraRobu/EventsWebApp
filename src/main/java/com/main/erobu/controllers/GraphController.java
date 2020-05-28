package com.main.erobu.controllers;

import com.main.erobu.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class GraphController {
    @Autowired
    private EventService eventService;
    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) {
        Map<String, Integer> surveyMap = new LinkedHashMap<>();
        if (Objects.nonNull(eventService.getEventsCategrySortedByVisualizations())) {
            surveyMap = eventService.getEventsCategrySortedByVisualizations();
        }
        model.addAttribute("surveyMap", surveyMap);
        return "barGraph";
    }

    @GetMapping("/displayPieChart")
    public String pieChart(Model model) {
        model.addAttribute("pass", 50);
        model.addAttribute("fail", 50);
        return "pieChart";
    }

}
