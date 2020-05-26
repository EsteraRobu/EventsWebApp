package com.main.erobu.controllers;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping(value = {"/","/home"})
    public ModelAndView login() {
//        EditorDTO editor = new EditorDTO();
//        editor.setActive(1);
//        editor.setId(1);
//        editor.setUsername("eu");
//        editor.setName("estera");
//        editor.setPassword("1234");
//        Editor publisher = EntityUtils.editorDTOToEditor(editor);
//        editorRepository.save(publisher);
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        AdministratorDTO editor = new AdministratorDTO();
//        editor.setActive(1);
//        editor.setId(1231);
//        editor.setUsername("terra");
//        editor.setFirstName("terra");
//        editor.setPassword(encoder.encode("terra"));
//        editor.setRoleId(1);
//        Administrator publisher = EntityUtils.administratorDTOToAdministrator(editor);
//        administratorRepository.save(publisher);
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }
}
