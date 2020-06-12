package com.main.erobu.controllers;

import com.main.erobu.security.WebSecurityConfig;
import com.main.erobu.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
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
        Boolean ok=true;
        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                ok=true;}
            else if (grantedAuthority.getAuthority().equals(WebSecurityConfig.ADMIN_ROLE)) {
               ok=false;
            }
        }
        if (ok.equals(Boolean.TRUE)){
            return "barGraph";
        }
        else return "barGraphAdm";
    }

    @GetMapping("/displayPieChart")
    public String pieChart(Model model) {
        model.addAttribute("pass", 50);
        model.addAttribute("fail", 50);
        return "pieChart";
    }

}
