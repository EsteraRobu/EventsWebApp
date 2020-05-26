package com.main.erobu.controllers;

import com.itextpdf.text.DocumentException;
import com.main.erobu.data.repository.ClientRepository;
import com.main.erobu.data.repository.EventCategoryRepository;
import com.main.erobu.dto.*;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.forms.SearchForm;
import com.main.erobu.paypal.config.PaypalPaymentIntent;
import com.main.erobu.paypal.config.PaypalPaymentMethod;
import com.main.erobu.paypal.config.URLUtils;
import com.main.erobu.paypal.service.PayPalService;
import com.main.erobu.report.DocumentFactory;
import com.main.erobu.security.WebSecurityConfig;
import com.main.erobu.services.*;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("")
public class ClientController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventCategoryService eventCategoryService;
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private PayPalService paypalService;

    private Logger log = LoggerFactory.getLogger(getClass());

    public static final String PAYPAL_SUCCESS_URL = "pay/success";
    public static final String PAYPAL_CANCEL_URL = "pay/cancel";

    @GetMapping({"/client/order/all", "client/order"})
    public ModelAndView getAllOrders(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                ClientDTO clientDTO = null;
                try {
                    clientDTO = clientService.findByUsername(principal.getName());
                    List<OrderDTO> orderDTOList = orderService.findByClient(clientDTO);
                    modelAndView.setViewName("client/order_list");
                    modelAndView.addObject("orderDTOList", orderDTOList);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                }

            }
        }
        return modelAndView;
    }

    @GetMapping({"/client/order/{orderId}"})
    public ModelAndView getOrderDetails(@PathVariable("orderId") Integer orderId, Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                try {
                    ClientDTO clientDTO = clientService.findByUsername(principal.getName());
                    OrderDTO orderDTO = orderService.findById(orderId);
                    if (!orderDTO.getClientDTO().getId().equals(clientDTO.getId())) {
                        return modelAndView;
                    }

                    modelAndView.setViewName("client/order_details");
                    modelAndView.addObject("orderDTO", orderDTO);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                }
            }
        }
        return modelAndView;
    }

    @GetMapping({"client/edit"})
    public ModelAndView editClientInfoGet(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                try {
                    ClientDTO clientDTO = clientService.findByUsername(principal.getName());

                    modelAndView.setViewName("client/client_edit");
                    modelAndView.addObject("clientDTO", clientDTO);
                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                }
            }
        }
        return modelAndView;
    }

    @PostMapping({"client/edit"})
    public String editClientInfoPost(@ModelAttribute("clientDTO") ClientDTO clientDTO, Principal principal) {

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                Set<EventCategoryDTO> eventCategory = new HashSet<>();
//				try {
////					eventCategory.add(eventCategoryService.findByCategoryName("Sport"));
////					eventCategory.add(eventCategoryService.findByCategoryName("Muzica" ));
//				}catch (ObjectTransferException e) {
//					e.printStackTrace();
//				}

//				clientDTO.setCategories(eventCategory);
//			clientService.save(clientDTO);
                return "redirect:/client/edit";
            }
        }
        return "redirect:/index";
    }

    @GetMapping({"/client/cart"})
    public ModelAndView getCart(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        SearchForm searchForm = new SearchForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("searchForm", searchForm);

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                try {
                    ClientDTO clientDTO = clientService.findByUsername(principal.getName());
                    ShoppingCartDTO shoppingCartDTO = shoppingCartService.findByClient(clientDTO);
                    modelAndView.setViewName("client/cart_details");
                    modelAndView.addObject("shoppingCartDTO", shoppingCartDTO);

                    return modelAndView;
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                }
            }
        }
        return modelAndView;
    }

    @GetMapping({"client/cart/remove/{itemId}"})
    public String removeCartItem(@PathVariable("itemId") Integer itemId, Principal principal) {

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                try {
                    ClientDTO clientDTO = clientService.findByUsername(principal.getName());
                    ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartItemService.findById(itemId);
                    ShoppingCartDTO shoppingCartDTO = shoppingCartService.findByClient(clientDTO);
                    if (!shoppingCartDTO.getId().equals(shoppingCartItemDTO.getShoppingCartId())) {
                        return "redirect:/index";
                    }
                    shoppingCartItemService.delete(shoppingCartItemDTO);
                    return "redirect:/client/cart";
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    return "redirect:/error";
                }
            }
        }
        return "redirect:/index";
    }

    @GetMapping({"client/cart/buy"})
    public String placeOrder(HttpServletRequest request, Principal principal) {

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                try {
                    ClientDTO clientDTO = clientService.findByUsername(principal.getName());
                    ShoppingCartDTO shoppingCartDTO = shoppingCartService.findByClient(clientDTO);
                    if (!shoppingCartDTO.getClientDTO().getId().equals(clientDTO.getId())) {
                        return "redirect:/index";
                    }
                    String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
                    String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
                    Double totalToPay = 0.0;
                    String paymentDescription = "Payment for events: ";
                    Set<ShoppingCartItemDTO> shoppingCartItems = shoppingCartDTO.getShoppingCartItemDTOS();
                    for (ShoppingCartItemDTO shoppingCartItem : shoppingCartItems) {
                        totalToPay = totalToPay + shoppingCartItem.getTotalPrice();
                        paymentDescription = paymentDescription + shoppingCartItem.getEventName() + ", ";
                    }
                    Payment payment = paypalService.createPayment(totalToPay, "USD", PaypalPaymentMethod.paypal,
                            PaypalPaymentIntent.sale, paymentDescription, cancelUrl, successUrl);
                    for (com.paypal.api.payments.Links links : payment.getLinks()) {
                        if (links.getRel().equals("approval_url")) {
                            return "redirect:" + links.getHref();
                        }
                    }
                    //		orderService.placeOrder(shoppingCartDTO);
                    return "redirect:/client/order";
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                    return "redirect:/error";
                } catch (PayPalRESTException e) {
                    log.error(e.getMessage());
                    return "redirect:/error";
                }
            }
        }
        return "redirect:/index";
    }

    @GetMapping({"/client/order/download/{orderId}"})
    public void downloadTickets(@PathVariable("orderId") Integer orderId, HttpServletRequest request,
                                HttpServletResponse response, Principal principal) {

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(WebSecurityConfig.CLIENT_ROLE)) {
                try {
                    ClientDTO clientDTO = clientService.findByUsername(principal.getName());
                    OrderDTO orderDTO = orderService.findById(orderId);

                    if (!orderDTO.getClientDTO().getId().equals(clientDTO.getId())) {
                        return;
                    }
                    Set<OrderItemDTO> orderItemDTOList = orderDTO.getOrderItemDTOS();
                    try {
                        documentFactory.createOnlineTickets("PDF", orderItemDTOList);
                    } catch (FileNotFoundException | DocumentException e) {
                        e.printStackTrace();
                    }
                    Path file = Paths.get(DocumentFactory.DEFAULT_PDF_REPORT_PATH);
                    if (Files.exists(file)) {
                        response.setContentType("application/pdf");
                        response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
                        try {
                            Files.copy(file, response.getOutputStream());
                            response.getOutputStream().flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (ObjectTransferException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
