package com.main.erobu.controllers;

import com.main.erobu.data.entry.Administrator;
import com.main.erobu.data.repository.AdministratorRepository;
import com.main.erobu.dto.AdministratorDTO;
import com.main.erobu.security.WebSecurityConfig;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


@Controller
@RequestMapping()
public class LoginController {
    @Autowired
    private AdministratorRepository administratorRepository;

    @GetMapping(value = {"","/login"})
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
        AdministratorDTO editor = new AdministratorDTO();
        editor.setActive(1);
        editor.setId(1231);
        editor.setUsername("admin");
        editor.setFirstName("admin1234");
        editor.setPassword(encoder.encode("terra"));
        editor.setRoleId(1);
        Administrator publisher = EntityUtils.administratorDTOToAdministrator(editor);
        administratorRepository.save(publisher);
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    /**
     * Shared zone index.
     */
    @GetMapping("index")
    public String sharedIndex() {

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                return "redirect:/event";
            } else if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                return "redirect:/publisher";}
            else if (grantedAuthority.getAuthority().equals(WebSecurityConfig.ADMIN_ROLE)) {
                return "redirect:/admin";
            }
        }
        return "redirect:/event";
    }

    /**
     * Login form with error.
     */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
       /* EditorDTO editor = new EditorDTO();
        editor.setActive(1);
        editor.setId(1);
        editor.setUsername("eu");
        editor.setName("estera");
        editor.setPassword("1234");
        Editor publisher = EntityUtils.editorDTOToEditor(editor);
        editorRepository.save(publisher);*/
        model.addAttribute("loginError", true);
        return "login";
    }

    /**
     * Error page.
     */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }


    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "access-denied";
    }

}
