package com.main.erobu.util;

import com.main.erobu.data.entry.*;
import com.main.erobu.dto.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class EntityUtils {

    public static Editor editorDTOToEditor(EditorDTO editorDTO) {
        return new Editor.Builder()
                .id(editorDTO.getId())
                .name(editorDTO.getName())
                .BirthDate(editorDTO.getBirthDate())
                .email(editorDTO.getEmail())
                .username(editorDTO.getUsername())
                .password(editorDTO.getPassword())
                .active(editorDTO.getActive())
                .address(editorDTO.getAddress())
                .phone(editorDTO.getPhone())
                .description(editorDTO.getDescription())
                .create();
    }

    public static EditorDTO editorToEditorDTO(Editor editor) {
        return new EditorDTO.Builder()
                .id(editor.getId())
                .name(editor.getName())
                .birthDate(editor.getBirthDate())
                .email(editor.getEmail())
                .username(editor.getUsername())
                .password(editor.getPassword())
                .active(editor.getActive())
                .address(editor.getAddress())
                .phone(editor.getPhone())
                .description(editor.getDescription())
                .create();
    }

    public static Administrator administratorDTOToAdministrator(AdministratorDTO admnin) {
        return new Administrator.Builder()
                .id(admnin.getId())
                .firstName(admnin.getFirstName())
                .lastName(admnin.getLastName())
                .birthDate(admnin.getBirthDate())
                .email(admnin.getEmail())
                .username(admnin.getUsername())
                .password(admnin.getPassword())
                .active(admnin.getActive())
                .address(admnin.getAddress())
                .phone(admnin.getPhone())
                .create();
    }

    public static AdministratorDTO administratorToAdministratorDTO(Administrator adminDTO) {
        return new AdministratorDTO.Builder()
                .id(adminDTO.getId())
                .firstName(adminDTO.getFirstName())
                .lastName(adminDTO.getLastName())
                .birthDate(adminDTO.getBirthDate())
                .email(adminDTO.getEmail())
                .username(adminDTO.getUsername())
                .password(adminDTO.getPassword())
                .active(adminDTO.getActive())
                .address(adminDTO.getAddress())
                .phone(adminDTO.getPhone())
                .create();
    }

    public static EventCategoryDTO eventCategoryToEventCategoryDTO(EventCategory eventCategory) {
        ClientDTO clientDto = new ClientDTO();
//        if (Objects.nonNull(eventCategory.getClient())) {
//            clientToClientDTO(eventCategory.getClient());
//
        return new EventCategoryDTO.Builder()
                .id(eventCategory.getId())
                .category(eventCategory.getCategory())
                .create();
    }

    public static EventCategory eventCategoryDTOToEventCategory(EventCategoryDTO eventCategoryDTO) {

        return new EventCategory.Builder()
                .id(eventCategoryDTO.getId())
                .category(eventCategoryDTO.getCategory())
                .create();
    }

    public static EventDTO eventToEventDTO(Event event) {
        EventCategoryDTO eventCategoryDTO = eventCategoryToEventCategoryDTO(event.getEventCategory());
        EditorDTO editorDTO = editorToEditorDTO(event.getEditor());
        return new EventDTO.Builder()
                .id(event.getId())
                .editorDTO(editorDTO)
                .name(event.getName())
                .dateStart(new Date(event.getDateStart().getTime()))
                .dateEnd(new Date(event.getDateEnd().getTime()))
                .location(event.getLocation())
                .description(event.getDescription())
                .eventCategoryDTO(eventCategoryDTO)
                .visualizationNo(event.getVisualizationNo())
                .create();
    }

    public static Event eventDTOToEvent(EventDTO eventDTO) {
        EventCategory eventCategory = eventCategoryDTOToEventCategory(eventDTO.getEventCategoryDTO());
        Editor editor = editorDTOToEditor(eventDTO.getEditorDTO());

        return new Event.Builder()
                .id(eventDTO.getId())
                .editorDTO(editor)
                .name(eventDTO.getName())
                .dateStart(new Timestamp(eventDTO.getDateStart().getTime()))
                .dateEnd(new Timestamp(eventDTO.getDateEnd().getTime()))
                .location(eventDTO.getLocation())
                .description(eventDTO.getDescription())
                .eventCategory(eventCategory)
                .visualizationNo(eventDTO.getVisualizationNo())
                .create();
    }

    public static TicketCategoryDTO ticketCategoryToTicketCategoryDTO(TicketCategory ticketCategory) {
        return new TicketCategoryDTO.Builder()
                .id(ticketCategory.getId())
                .category(ticketCategory.getCategory())
                .create();
    }

    public static TicketCategory ticketCategoryDTOToTicketCategory(TicketCategoryDTO ticketCategoryDTO) {
        return new TicketCategory.Builder()
                .id(ticketCategoryDTO.getId())
                .category(ticketCategoryDTO.getCategory())
                .create();
    }

    public static TicketDTO ticketToTicketDTO(Ticket ticket) {
        TicketCategoryDTO ticketCategoryDTO = ticketCategoryToTicketCategoryDTO(ticket.getTicketCategory());
        EventDTO eventDTO = eventToEventDTO(ticket.getEvent());
        return new TicketDTO.Builder()
                .id(ticket.getId())
                .eventDTO(eventDTO)
                .ticketCategoryDTO(ticketCategoryDTO)
                .quantity(ticket.getQuantity())
                .price(ticket.getPrice())
                .create();
    }

    public static Ticket ticketDTOToTicket(TicketDTO ticketDTO) {
        TicketCategory ticketCategory = ticketCategoryDTOToTicketCategory(ticketDTO.getTicketCategoryDTO());
        Event event = eventDTOToEvent(ticketDTO.getEventDTO());
        return new Ticket.Builder()
                .id(ticketDTO.getId())
                .event(event)
                .ticketCategory(ticketCategory)
                .quantity(ticketDTO.getQuantity())
                .price(ticketDTO.getPrice())
                .create();
    }

    public static ClientDTO clientToClientDTO(Client client) {
        Set<EventCategoryDTO> eventCategoriesList = new HashSet<>();
        Set<EventCategory> eventCategory = client.getEventcategories();
        for (EventCategory eventCategoryDTO : eventCategory) {
            eventCategoriesList.add(EntityUtils.eventCategoryToEventCategoryDTO(eventCategoryDTO));
        }
        return new ClientDTO.Builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .birthDate(client.getBirthDate())
                .email(client.getEmail())
                .username(client.getUsername())
                .password(client.getPassword())
                .active(client.getActive())
                .address(client.getAddress())
                .phone(client.getPhone())
                .create();
    }

    public static Client clientDTOToClient(ClientDTO clientDTO) {

        Client client = new Client.Builder()
                .id(clientDTO.getId())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .birthDate(clientDTO.getBirthDate())
                .email(clientDTO.getEmail())
                .username(clientDTO.getUsername())
                .password(clientDTO.getPassword())
                .active(clientDTO.getActive())
                .address(clientDTO.getAddress())
                .phone(clientDTO.getPhone())
                .create();


        return client;
    }

    public static OrderDetails orderDetailsDTOToOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        return new OrderDetails.Builder()
                .id(orderDetailsDTO.getId())
                .date(orderDetailsDTO.getDate())
                .paymentMethod(orderDetailsDTO.getPaymentMethod())
                .deliveryMethod(orderDetailsDTO.getDeliveryMethod())
                .create();
    }

    public static OrderDetailsDTO orderDetailsToOrderDetailsDTO(OrderDetails orderDetails) {
        return new OrderDetailsDTO.Builder()
                .id(orderDetails.getId())
                .date(orderDetails.getDate())
                .paymentMethod(orderDetails.getPaymentMethod())
                .deliveryMethod(orderDetails.getDeliveryMethod())
                .create();
    }

    public static OrderStatus orderStatusDTOToOrderStatus(OrderStatusDTO orderStatusDTO) {
        return new OrderStatus.Builder()
                .id(orderStatusDTO.getId())
                .status(orderStatusDTO.getStatus())
                .create();
    }

    public static OrderStatusDTO orderStatusToOrderStatusDTO(OrderStatus orderStatus) {
        return new OrderStatusDTO.Builder()
                .id(orderStatus.getId())
                .status(orderStatus.getStatus())
                .create();
    }

    public static OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO) {
        Ticket ticket = ticketDTOToTicket(orderItemDTO.getTicketDTO());
        return new OrderItem.Builder()
                .id(orderItemDTO.getId())
                .idOrder(orderItemDTO.getIdOrder())
                .quantity(orderItemDTO.getQuantity())
                .ticket(ticket)
                .create();
    }

    public static OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem) {
        TicketDTO ticketDTO = ticketToTicketDTO(orderItem.getTicket());
        return new OrderItemDTO.Builder()
                .id(orderItem.getId())
                .idOrder(orderItem.getIdOrder())
                .quantity(orderItem.getQuantity())
                .ticketDTO(ticketDTO)
                .create();
    }

    public static Order orderDTOToOrder(OrderDTO orderDTO) {
        Client client = clientDTOToClient(orderDTO.getClientDTO());
        OrderDetails orderDetails = orderDetailsDTOToOrderDetails(orderDTO.getOrderDetailsDTO());
        OrderStatus orderStatus = orderStatusDTOToOrderStatus(orderDTO.getOrderStatusDTO());
        Set<OrderItem> orderItemList = new HashSet<>();
        Set<OrderItemDTO> orderItemDTOSet = orderDTO.getOrderItemDTOS();
        for (OrderItemDTO orderItemDTO : orderItemDTOSet) {
            orderItemList.add(EntityUtils.orderItemDTOToOrderItem(orderItemDTO));
        }
        return new Order.Builder()
                .id(orderDTO.getId())
                .client(client)
                .orderDetails(orderDetails)
                .orderStatus(orderStatus)
                .orderItems(orderItemList)
                .create();
    }

    public static OrderDTO orderToOrderDTO(Order order) {
        ClientDTO clientDTO = clientToClientDTO(order.getClient());
        OrderDetailsDTO orderDetailsDTO = orderDetailsToOrderDetailsDTO(order.getOrderDetails());
        OrderStatusDTO orderStatusDTO = orderStatusToOrderStatusDTO(order.getOrderStatus());
        Set<OrderItem> orderItemList = order.getOrderItems();
        Set<OrderItemDTO> orderItemDTOSet = new HashSet<>();
        for (OrderItem orderItem : orderItemList) {
            orderItemDTOSet.add(EntityUtils.orderItemToOrderItemDTO(orderItem));
        }
        return new OrderDTO.Builder()
                .id(order.getId())
                .clientDTO(clientDTO)
                .orderDetailsDTO(orderDetailsDTO)
                .orderStatusDTO(orderStatusDTO)
                .orderItemDTOS(orderItemDTOSet)
                .create();
    }

    public static ShoppingCartItem shoppingCartItemDTOToShoppingCartItem(ShoppingCartItemDTO shoppingCartItemDTO) {
        Ticket ticket = ticketDTOToTicket(shoppingCartItemDTO.getTicketDTO());
        return new ShoppingCartItem.Builder()
                .id(shoppingCartItemDTO.getId())
                .shoppingCartId(shoppingCartItemDTO.getShoppingCartId())
                .quantity(shoppingCartItemDTO.getQuantity())
                .ticket(ticket)
                .create();
    }

    public static ShoppingCartItemDTO shoppingCartItemToShoppingCartItemDTO(ShoppingCartItem shoppingCartItem) {
        TicketDTO ticketDTO = ticketToTicketDTO(shoppingCartItem.getTicket());
        return new ShoppingCartItemDTO.Builder()
                .id(shoppingCartItem.getId())
                .shoppingCartId(shoppingCartItem.getShoppingCartId())
                .quantity(shoppingCartItem.getQuantity())
                .ticketDTO(ticketDTO)
                .create();
    }

    public static ShoppingCart shoppingCartDTOToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        Client client = clientDTOToClient(shoppingCartDTO.getClientDTO());
        Set<ShoppingCartItem> shoppingCartItemList = new HashSet<>();
        Set<ShoppingCartItemDTO> shoppingCartItemDTOList = shoppingCartDTO.getShoppingCartItemDTOS();
        for (ShoppingCartItemDTO shoppingCartItemDTO : shoppingCartItemDTOList) {
            shoppingCartItemList.add(shoppingCartItemDTOToShoppingCartItem(shoppingCartItemDTO));
        }
        return new ShoppingCart.Builder()
                .id(shoppingCartDTO.getId())
                .client(client)
                .shoppingCartItems(shoppingCartItemList)
                .create();
    }

    public static ShoppingCartDTO shoppingCartToShoppingCartDTO(ShoppingCart shoppingCart) {
        ClientDTO clientDTO = clientToClientDTO(shoppingCart.getClient());
        Set<ShoppingCartItemDTO> shoppingCartItemDTOSet = new HashSet<>();
        Set<ShoppingCartItem> shoppingCartItemSet = shoppingCart.getShoppingCartItems();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemSet) {
            shoppingCartItemDTOSet.add(shoppingCartItemToShoppingCartItemDTO(shoppingCartItem));
        }
        return new ShoppingCartDTO.Builder()
                .id(shoppingCart.getId())
                .clientDTO(clientDTO)
                .shoppingCartItems(shoppingCartItemDTOSet)
                .create();
    }

    public static OrderItemDTO shoppingCartItemDTOToOrderItemDTO(ShoppingCartItemDTO shoppingCartItemDTO) {
        return new OrderItemDTO.Builder()
                .id(0)
                .idOrder(0)
                .quantity(shoppingCartItemDTO.getQuantity())
                .ticketDTO(shoppingCartItemDTO.getTicketDTO())
                .create();
    }

}
