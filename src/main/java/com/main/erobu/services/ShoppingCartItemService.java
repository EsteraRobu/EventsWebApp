package com.main.erobu.services;

import com.main.erobu.data.entry.ShoppingCartItem;
import com.main.erobu.data.repository.ShoppingCartItemRepository;
import com.main.erobu.dto.ShoppingCartItemDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    public void delete(ShoppingCartItemDTO shoppingCartItemDTO){
        ShoppingCartItem shoppingCartItem = EntityUtils.shoppingCartItemDTOToShoppingCartItem(shoppingCartItemDTO);
        shoppingCartItemRepository.delete(shoppingCartItem);
    }

    public ShoppingCartItemDTO findById(Integer id) throws ObjectTransferException {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id).orElse(null);
        if(shoppingCartItem == null){
            throw new ObjectTransferException("Data not found");
        }
        return EntityUtils.shoppingCartItemToShoppingCartItemDTO(shoppingCartItem);
    }
}
