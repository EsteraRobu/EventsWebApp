package com.main.erobu.controllers;

import com.main.erobu.dto.*;
import com.main.erobu.entity.CartItem;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.forms.SearchForm;
import com.main.erobu.security.WebSecurityConfig;
import com.main.erobu.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("")
public class GuestController {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EditorService editorService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private EventCategoryService eventCategoryService;


    @GetMapping({"/event", "/event/all"})
    public ModelAndView viewAllEvents(Principal principal) {

        List<EventDTO> eventDTOList = eventService.findAllActive();
        List<EventDTO> events = eventDTOList;
        SearchForm searchForm = new SearchForm();
        ModelAndView modelAndView = new ModelAndView("guest/event_list");
        Map<String, List<EventDTO>> recomandedEvents = new HashMap<>();
        try {
            ClientDTO clientDTO = clientService.findByUsername(principal.getName());
            recomandedEvents = eventService.getRecomnadedEvents(eventService.
                    getEventsFromFavoriteCategory(clientDTO));
            modelAndView.addObject("eventsFromFavoriteCategory", eventService.getEventsFromFavoriteCategory(clientDTO));

            modelAndView.addObject("recomandedEvents",recomandedEvents);

        } catch (ObjectTransferException e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("/error");
        }
        List<EventCategoryDTO> eventCategories = eventCategoryService.findAll();

        Map<String, List<EventDTO>> locationEvents = new HashMap<>();
        if (!CollectionUtils.isEmpty(eventCategories)) {
            for (EventDTO event : eventDTOList) {
                List<EventDTO> eventsToAdd = new ArrayList<>();
                for (EventDTO eventHelper : events) {
                    if (event.getLocation().equals(eventHelper.getLocation())) {
                        eventsToAdd.add(eventHelper);
                    }
                }
                locationEvents.put(event.getLocation(), eventsToAdd);
            }
        }
        modelAndView.addObject("eventCategories", eventCategories);
        modelAndView.addObject("eventDTOList", eventDTOList);
        modelAndView.addObject("searchForm", searchForm);
        return modelAndView;
    }

    @GetMapping({"/profile/editor/{editorId}"})
    public ModelAndView editorProfile(@PathVariable("editorId") Integer editorId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("guest/editor_profile");
        try {
            EditorDTO editorDTO = editorService.findById(editorId);
            List<EventDTO> eventDTOList = eventService.findByEditor(editorDTO);
            SearchForm searchForm = new SearchForm();
            modelAndView.addObject("eventDTOList", eventDTOList);
            modelAndView.addObject("editorDTO", editorDTO);
            modelAndView.addObject("searchForm", searchForm);
        } catch (ObjectTransferException e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("/error");
        }
        return modelAndView;
    }

    @GetMapping({"/event/{eventId}"})
    public ModelAndView viewAllEvents(@PathVariable("eventId") Integer eventId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("guest/event_details");
        try {
            EventDTO eventDTO = eventService.findById(eventId);
            List<TicketDTO> ticketDTOList = ticketService.findByEvent(eventDTO);
            if (Objects.isNull(eventDTO.getVisualizationNo())) {
                eventDTO.setVisualizationNo(1);
            } else {
                eventDTO.setVisualizationNo(eventDTO.getVisualizationNo() + 1);
            }
            eventService.save(eventDTO);
            SearchForm searchForm = new SearchForm();
            CartItem cartItem = new CartItem();

            modelAndView.addObject("eventDTO", eventDTO);
            modelAndView.addObject("ticketDTOList", ticketDTOList);
            modelAndView.addObject("searchForm", searchForm);
            modelAndView.addObject("cartItem", cartItem);
        } catch (ObjectTransferException e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("/error");
        }
        return modelAndView;
    }

    @PostMapping({"/ticket/buy/{ticketId}"})
    public String buyTicket(@ModelAttribute("CartItem") CartItem cartItem, @PathVariable("ticketId") Integer ticketId,
                            Principal principal) {
        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                try {
                    ClientDTO clientDTO = clientService.findByUsername(principal.getName());
                    TicketDTO ticketDTO = ticketService.findById(ticketId);
                    ShoppingCartDTO shoppingCartDTO = shoppingCartService.findByClient(clientDTO);
                    ShoppingCartItemDTO shoppingCartItemDTO = new ShoppingCartItemDTO.Builder().id(0)
                            .shoppingCartId(shoppingCartDTO.getId()).quantity(cartItem.getQuantity())
                            .ticketDTO(ticketDTO).create();
                    shoppingCartService.addItem(shoppingCartDTO, shoppingCartItemDTO);

                    return "redirect:/client/cart";
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    return "redirect:/error";
                }
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/search")
    public ModelAndView searchEvents(@ModelAttribute("searchForm") SearchForm searchForm, Principal principal) {
        List<EventDTO> eventDTOList = eventService.findByName(searchForm.getSearchField());
        searchForm = new SearchForm();
        ModelAndView modelAndView = new ModelAndView("guest/event_list");
        modelAndView.addObject("eventDTOList", eventDTOList);
        modelAndView.addObject("searchForm", searchForm);
        return modelAndView;
    }

}
