package com.main.erobu.dto;

import java.util.Set;


public class ShoppingCartDTO {

    private Integer id;
    private ClientDTO clientDTO;
    private Set<ShoppingCartItemDTO> shoppingCartItemDTOS;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Integer id, ClientDTO clientDTO, Set<ShoppingCartItemDTO> shoppingCartItemDTOS) {
        this.id = id;
        this.clientDTO = clientDTO;
        this.shoppingCartItemDTOS = shoppingCartItemDTOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public Set<ShoppingCartItemDTO> getShoppingCartItemDTOS() {
        return shoppingCartItemDTOS;
    }

    public void setShoppingCartItemDTOS(Set<ShoppingCartItemDTO> shoppingCartItemDTOS) {
        this.shoppingCartItemDTOS = shoppingCartItemDTOS;
    }

    public static class Builder {
        private Integer nestedId;
        private ClientDTO nestedClientDTO;
        private Set<ShoppingCartItemDTO> nestedShoppingCartItemDTOS;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder clientDTO(ClientDTO clientDTO) {
            this.nestedClientDTO = clientDTO;
            return this;
        }

        public Builder shoppingCartItems(Set<ShoppingCartItemDTO> shoppingCartItemDTOS) {
            this.nestedShoppingCartItemDTOS = shoppingCartItemDTOS;
            return this;
        }

        public ShoppingCartDTO create() {
            return new ShoppingCartDTO(nestedId, nestedClientDTO, nestedShoppingCartItemDTOS);
        }
    }
}
