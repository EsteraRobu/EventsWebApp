package com.main.erobu.services;

import com.main.erobu.data.entry.TicketCategory;
import com.main.erobu.data.repository.TicketCategoryRepository;
import com.main.erobu.dto.TicketCategoryDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketCategoryService {

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    public TicketCategoryDTO findByCategoryName(String categoryName) throws ObjectTransferException {
        TicketCategory ticketCategory = ticketCategoryRepository.findByCategory(categoryName);
        if(ticketCategory ==null){
            throw new ObjectTransferException("Data not found");
        }
        return EntityUtils.ticketCategoryToTicketCategoryDTO(ticketCategory);
    }
}
