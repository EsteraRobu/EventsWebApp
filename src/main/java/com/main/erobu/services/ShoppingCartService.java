package com.main.erobu.services;

import com.main.erobu.data.entry.Client;
import com.main.erobu.data.entry.ShoppingCart;
import com.main.erobu.data.repository.ShoppingCartRepository;
import com.main.erobu.dto.ClientDTO;
import com.main.erobu.dto.ShoppingCartDTO;
import com.main.erobu.dto.ShoppingCartItemDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    public void create(ClientDTO clientDTO) {
        Client client = EntityUtils.clientDTOToClient(clientDTO);
        ShoppingCart shoppingCart = new ShoppingCart.Builder()
                .id(0)
                .client(client)
                .shoppingCartItems(new HashSet<>())
                .create();
        shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCartDTO findByClient(ClientDTO clientDTO) throws ObjectTransferException {
        Client client = EntityUtils.clientDTOToClient(clientDTO);
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByClient(client);
        if (shoppingCartList.size() <= 0) {
            throw new ObjectTransferException("Data not found");
        } else return EntityUtils.shoppingCartToShoppingCartDTO(shoppingCartList.get(0));
    }

    public ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = EntityUtils.shoppingCartDTOToShoppingCart(shoppingCartDTO);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return EntityUtils.shoppingCartToShoppingCartDTO(savedShoppingCart);
    }

    public void delete(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = EntityUtils.shoppingCartDTOToShoppingCart(shoppingCartDTO);
        shoppingCartRepository.delete(shoppingCart);
    }

    public void addItem(ShoppingCartDTO shoppingCartDTO, ShoppingCartItemDTO shoppingCartItemDTO) {
        Set<ShoppingCartItemDTO> shoppingCartItemDTOSet = shoppingCartDTO.getShoppingCartItemDTOS();
        for (ShoppingCartItemDTO itemDTO : shoppingCartItemDTOSet) {
            if (itemDTO.getId().equals(shoppingCartItemDTO.getId())) {
                itemDTO.setQuantity(itemDTO.getQuantity() + shoppingCartItemDTO.getQuantity());
                save(shoppingCartDTO);
                return;
            }
        }
        shoppingCartItemDTOSet.add(shoppingCartItemDTO);
        save(shoppingCartDTO);
    }

    public void removeAllItems(ShoppingCartDTO shoppingCartDTO) {
        Set<ShoppingCartItemDTO> shoppingCartItemDTOSet = shoppingCartDTO.getShoppingCartItemDTOS();
        for (ShoppingCartItemDTO shoppingCartItemDTO : shoppingCartItemDTOSet) {
            shoppingCartItemService.delete(shoppingCartItemDTO);
        }
    }
}
