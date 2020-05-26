package com.main.erobu.controllers;


import com.main.erobu.data.entry.Editor;
import com.main.erobu.dto.EditorDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.forms.SearchForm;
import com.main.erobu.services.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private EditorService editorService;

    @GetMapping({"/publisher/all", "publisher", ""})
    public ModelAndView getPendingPublishers(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/admin_panel");
        SearchForm searchForm = new SearchForm();
        List<EditorDTO> publisherDTOList = editorService.findPendingEditors();
        modelAndView.addObject("publisherDTOList", publisherDTOList);
        modelAndView.addObject("searchForm", searchForm);
        return modelAndView;
    }

    @GetMapping("/publisher/activate/{publisherId}")
    public String activatePublisher(@PathVariable("publisherId") Integer publisherId, Principal principal) {
        EditorDTO publisherDTO = null;
        try {
            publisherDTO = editorService.findById(publisherId);
            publisherDTO.setActive(1);
            editorService.save(publisherDTO);
        } catch (ObjectTransferException e) {
            e.printStackTrace();
            return "redirect:/error";
        }

        return "redirect:/admin";
    }
}
