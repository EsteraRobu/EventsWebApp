package com.main.erobu.controllers;

import com.main.erobu.services.ClientService;
import com.main.erobu.services.EditorService;
import com.main.erobu.dto.*;
import com.main.erobu.services.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private EditorService publisherService;

    @Autowired
    private EventCategoryService eventCategoryService;

    @GetMapping(value="/client")
    public ModelAndView registerClientGet(){
        ClientDTO clientDTO = new ClientDTO();
        ModelAndView modelAndView = new ModelAndView("register_client");
        modelAndView.addObject("clientDTO", clientDTO);
        modelAndView.addObject("multiCheckboxAllValues", eventCategoryService.getMultiCheckboxAllValues());

        return modelAndView;
    }

    @PostMapping("/client")
    public String registerClientPost(@ModelAttribute(value = "clientDTO") ClientDTO clientDTO){
       

        clientService.registerClient(clientDTO);
        return "redirect:/login";
    }

    @GetMapping(value="/publisher")
    public ModelAndView registerPublisherGet(){
        EditorDTO publisherDTO = new EditorDTO();
        ModelAndView modelAndView = new ModelAndView("register_publisher");
        modelAndView.addObject("publisherDTO", publisherDTO);
        return modelAndView;
    }

    @PostMapping("/publisher")
    public String registerPublisherPost(@ModelAttribute("publisherDTO") EditorDTO publisherDTO){
        publisherService.registerEditor(publisherDTO);
        return "redirect:/login";
    }
}
