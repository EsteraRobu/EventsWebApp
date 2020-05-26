package com.main.erobu.controllers;

import com.main.erobu.dto.*;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.forms.SearchForm;
import com.main.erobu.security.WebSecurityConfig;
import com.main.erobu.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EditorService publisherService;

    @Autowired
    private EventCategoryService eventCategoryService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketCategoryService ticketCategoryService;

    @GetMapping({"", "event", "/event/all"})
    public ModelAndView getEventList(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    List<EventDTO> eventDTOList = eventService.findByEditor(publisherDTO);

                    modelAndView.setViewName("publisher/event_list");
                    modelAndView.addObject("eventDTOList", eventDTOList);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    modelAndView.setViewName("/error");
                }
            }
        }
        return modelAndView;
    }

    @GetMapping({"/event/add"})
    public ModelAndView addEventGet(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    List<EventCategoryDTO> eventCategoryDTOList = eventCategoryService.findAll();
                    EventDTO eventDTO = new EventDTO.Builder()
                            .editorDTO(publisherDTO)
                            .startingPrice(0d)
                            .create();

                    modelAndView.setViewName("publisher/event_add");
                    modelAndView.addObject("eventDTO", eventDTO);
                    modelAndView.addObject("eventCategoryDTOList", eventCategoryDTOList);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    modelAndView.setViewName("/error");
                }
            }
        }
        return modelAndView;
    }

    @GetMapping({"/event/edit/{eventId}"})
    public ModelAndView editEventGet(@PathVariable("eventId") Integer eventId, Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EventDTO eventDTO = eventService.findById(eventId);
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    if (!eventDTO.getEditorDTO().getId().equals(publisherDTO.getId())) {
                        return modelAndView;
                    }
                    List<TicketDTO> ticketDTOList = ticketService.findByEvent(eventDTO);
                    modelAndView.setViewName("publisher/event_edit");
                    modelAndView.addObject("eventDTO", eventDTO);
                    modelAndView.addObject("ticketDTOList", ticketDTOList);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    modelAndView.setViewName("/error");
                }
            }
        }
        return modelAndView;
    }

    @PostMapping({"/event/edit"})
    public String editEventPost(@ModelAttribute("eventDTO") EventDTO eventDTO, Principal principal) {

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    eventDTO.setEditorDTO(publisherDTO);
                    if (!eventDTO.getEditorDTO().getId().equals(publisherDTO.getId())) {
                        return "redirect:/login";
                    }
                    eventService.save(eventDTO);
                    return "redirect:/publisher/event";
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    return "redirect:/error";
                }
            }
        }
        return "redirect:/index";
    }

    @GetMapping({"/ticket/edit/{ticketId}"})
    public ModelAndView editTicket(@PathVariable("ticketId") Integer eventId, Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    TicketDTO ticketDTO = ticketService.findById(eventId);
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    if (!ticketDTO.getEventDTO().getEditorDTO().getId().equals(publisherDTO.getId())) {
                        return modelAndView;
                    }
                    modelAndView.setViewName("publisher/ticket_edit");
                    modelAndView.addObject("ticketDTO", ticketDTO);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    modelAndView.setViewName("/error");
                }
            }
        }
        return modelAndView;
    }

    @GetMapping({"/ticket/add/{eventId}"})
    public ModelAndView addTicketGet(@PathVariable("eventId") Integer eventId, Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    EventDTO eventDTO = eventService.findById(eventId);
                    if (!eventDTO.getEditorDTO().getId().equals(publisherDTO.getId())) {
                        return modelAndView;
                    }
                    TicketCategoryDTO ticketCategoryDTO = new TicketCategoryDTO.Builder().id(0).create();
                    TicketDTO ticketDTO = new TicketDTO.Builder()
                            .id(0)
                            .ticketCategoryDTO(ticketCategoryDTO)
                            .eventDTO(eventDTO)
                            .create();
                    modelAndView.setViewName("publisher/ticket_add");
                    modelAndView.addObject("ticketDTO", ticketDTO);
                    modelAndView.addObject("eventDTO", eventDTO);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    modelAndView.setViewName("/error");
                }
            }
        }
        return modelAndView;
    }

    @PostMapping({"/ticket/add/{eventId}"})
    public String addTicketPost(@PathVariable("eventId") Integer eventId, @ModelAttribute("ticketDTO") TicketDTO ticketDTO, Principal principal) {


        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    EventDTO eventDTO = eventService.findById(eventId);
                    ticketDTO.setId(0);
                    ticketDTO.setEventDTO(eventDTO);
                    TicketCategoryDTO ticketCategoryDTO = ticketCategoryService.findByCategoryName(ticketDTO.getTicketCategoryDTO().getCategory());
                    ticketDTO.getTicketCategoryDTO().setId(0);
                    if (ticketCategoryDTO != null) {
                        ticketDTO.setTicketCategoryDTO(ticketCategoryDTO);
                    }
                    if (!ticketDTO.getEventDTO().getEditorDTO().getId().equals(publisherDTO.getId())) {
                        return "redirect:/login";
                    }
                    ticketService.save(ticketDTO);
                    return "redirect:/publisher/event";
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    return "redirect:/error";
                }
            }
        }
        return "redirect:/login";
    }

    @PostMapping({"/ticket/edit"})
    public String editTicketPost(@ModelAttribute("ticketDTO") TicketDTO ticketDTO, Principal principal) {


        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    TicketCategoryDTO ticketCategoryDTO = ticketCategoryService.findByCategoryName(ticketDTO.getTicketCategoryDTO().getCategory());
                    TicketDTO oldTicketDTO = ticketService.findById(ticketDTO.getId());
                    ticketDTO.setEventDTO(oldTicketDTO.getEventDTO());
                    ticketDTO.getTicketCategoryDTO().setId(0);
                    if (ticketCategoryDTO != null) {
                        ticketDTO.setTicketCategoryDTO(ticketCategoryDTO);
                    }
                    if (!ticketDTO.getEventDTO().getEditorDTO().getId().equals(publisherDTO.getId())) {
                        return "redirect:/login";
                    }
                    ticketService.save(ticketDTO);
                    return "redirect:/publisher/event";
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    return "redirect:/error";
                }
            }
        }
        return "redirect:/login";
    }

    @PostMapping({"/event/add"})
    public String addEventPost(@ModelAttribute("eventDTO") @Valid EventDTO eventDTO, BindingResult bindingResultEventDTO, Principal principal) {
        System.out.println(bindingResultEventDTO.getErrorCount());
        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                //TODO create image
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    EventCategoryDTO eventCategoryDTO = eventCategoryService.findByCategoryName("Sport");
                    eventDTO.setId(0);
                    eventDTO.setEditorDTO(publisherDTO);
                    eventDTO.setStartingPrice(0d);
                    eventDTO.setEventCategoryDTO(eventCategoryDTO);
                    eventService.save(eventDTO);
                    return "redirect:/publisher/event";
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    return "redirect:/error";
                }
            }
        }

        return "redirect:/login";
    }


    @GetMapping({"/edit"})
    public ModelAndView editPublisherInfoGet(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                try {
                    EditorDTO publisherDTO = publisherService.findByUsername(principal.getName());
                    modelAndView.setViewName("publisher/publisher_edit");
                    modelAndView.addObject("publisherDTO", publisherDTO);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    modelAndView.setViewName("/error");
                }
            }
        }
        return modelAndView;
    }

    @PostMapping({"/edit"})
    public String editPublisherInfoPost(@ModelAttribute("publisherDTO") EditorDTO publisherDTO, Principal principal) {

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.EDITOR_ROLE)) {
                publisherService.save(publisherDTO);
                return "redirect:/publisher/event";
            }
        }
        return "redirect:/index";
    }

}
