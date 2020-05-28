package com.main.erobu.services;

import com.main.erobu.data.entry.TicketCategory;
import com.main.erobu.data.repository.TicketCategoryRepository;
import com.main.erobu.dto.TicketCategoryDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TicketCategoryService {

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    public TicketCategoryDTO findByCategoryName(String categoryName) throws ObjectTransferException {
        TicketCategory ticketCategory = ticketCategoryRepository.findByCategory(categoryName);
        if (ticketCategory == null) {
            throw new ObjectTransferException("Data not found");
        }
        return EntityUtils.ticketCategoryToTicketCategoryDTO(ticketCategory);
    }

    public List<TicketCategoryDTO> getAllCategories() {
        List<TicketCategoryDTO> ticketCategoryDTOS = new ArrayList<>();
        if (Objects.nonNull(ticketCategoryRepository.findAll())) {
            for (TicketCategory ticketCategory : ticketCategoryRepository.findAll()) {
                ticketCategoryDTOS.add(EntityUtils.ticketCategoryToTicketCategoryDTO(ticketCategory));
            }
        }
        return ticketCategoryDTOS;
    }
}
