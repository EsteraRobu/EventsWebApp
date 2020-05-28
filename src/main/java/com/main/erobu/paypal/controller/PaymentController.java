package com.main.erobu.paypal.controller;

import com.main.erobu.dto.ClientDTO;
import com.main.erobu.dto.ShoppingCartDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.paypal.service.PayPalService;
import com.main.erobu.services.ClientService;
import com.main.erobu.services.OrderService;
import com.main.erobu.services.ShoppingCartService;
import com.main.erobu.services.SmtpMailSender;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class PaymentController {
	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";

	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SmtpMailSender smtpMailSender;
	@Autowired
	private PayPalService paypalService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderService orderService;

	private final String SUBJECT="Events.Diversity:Your Oreder has been placed succesfully";

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay() {
		return "redirect:/client/cart";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
                             Principal principal) {
		try {
			ClientDTO clientDTO = clientService.findByUsername(principal.getName());
			ShoppingCartDTO shoppingCartDTO = shoppingCartService.findByClient(clientDTO);
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				smtpMailSender.send(clientDTO.getEmail(), SUBJECT, "Your order has been placed successfuly, now you can se it on your orders on our site! Enjoy the moment!",clientDTO.getFirstName());
				orderService.placeOrder(shoppingCartDTO);
				return "redirect:/client/order";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
	catch (ObjectTransferException | TransactionException e) {
		e.printStackTrace();
		return "redirect:/error";
	} catch (MessagingException e) {
			e.printStackTrace();
		}

		return "redirect:/client/order";
	}
}
