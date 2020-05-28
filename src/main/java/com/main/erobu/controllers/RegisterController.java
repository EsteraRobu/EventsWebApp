package com.main.erobu.controllers;

import com.main.erobu.services.ClientService;
import com.main.erobu.services.EditorService;
import com.main.erobu.dto.*;
import com.main.erobu.services.EventCategoryService;
import com.main.erobu.services.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private EditorService publisherService;

    @Autowired
    private EventCategoryService eventCategoryService;
    @Autowired
    private SmtpMailSender smtpMailSender;

    private final String SUBJECT="Events.Diversity:Your account has been created";

    @GetMapping(value="/client")
    public ModelAndView registerClientGet(){
        ClientDTO clientDTO = new ClientDTO();
        ModelAndView modelAndView = new ModelAndView("register_client");
        modelAndView.addObject("clientDTO", clientDTO);
        modelAndView.addObject("multiCheckboxAllValues", eventCategoryService.getMultiCheckboxAllValues());

        return modelAndView;
    }

    @PostMapping("/client")
    public String registerClientPost(@ModelAttribute(value = "clientDTO") ClientDTO clientDTO) throws MessagingException {
        clientService.registerClient(clientDTO);
        smtpMailSender.send(clientDTO.getEmail(), SUBJECT, "Your account has been created. Now you can login  on our site! Enjoy!",clientDTO.getFirstName());
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
    public String registerPublisherPost(@ModelAttribute("publisherDTO") EditorDTO publisherDTO) throws MessagingException {
        publisherService.registerEditor(publisherDTO);
        smtpMailSender.send(publisherDTO.getEmail(), SUBJECT, " Your account has been created.You will get an email once it will be activated by our administrator.",publisherDTO.getName());

        return "redirect:/login";
    }
}
