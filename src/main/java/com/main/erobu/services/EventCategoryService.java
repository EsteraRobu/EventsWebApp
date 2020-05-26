package com.main.erobu.services;

import com.main.erobu.data.entry.Client;
import com.main.erobu.data.entry.EventCategory;
import com.main.erobu.data.repository.EventCategoryRepository;
import com.main.erobu.dto.ClientDTO;
import com.main.erobu.dto.EventCategoryDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventCategoryService {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    public EventCategoryDTO findByCategoryName(String categoryName) throws ObjectTransferException {
        EventCategory eventCategory = eventCategoryRepository.findByCategory(categoryName);
        if (eventCategory == null) {
            throw new ObjectTransferException("Data not found");

        }
        return EntityUtils.eventCategoryToEventCategoryDTO(eventCategory);
    }
    public Set<ClientDTO> getSubscribedClientsToCategory(EventCategoryDTO eventCategoryDTO){
        EventCategory eventCategory=eventCategoryRepository.findByCategory(eventCategoryDTO.getCategory());
        Set<ClientDTO> clientDTOS = new HashSet<>();
        if (Objects.nonNull(eventCategory.getClients())){
            for(Client client: eventCategory.getClients()){
                clientDTOS.add(EntityUtils.clientToClientDTO(client));
            }
        }
        return clientDTOS;
    }
    public void save(EventCategoryDTO eventCategoryDTO){
        EventCategory eventCategory = EntityUtils.eventCategoryDTOToEventCategory(eventCategoryDTO);
        eventCategoryRepository.save(eventCategory);
    }
    public List<EventCategoryDTO> findAll() {
        List<EventCategoryDTO> eventCategoryDTOList = new ArrayList<>();
        List<EventCategory> eventCategoryList = eventCategoryRepository.findAll();
        for(EventCategory eventCategory: eventCategoryList){
            eventCategoryDTOList.add(EntityUtils.eventCategoryToEventCategoryDTO(eventCategory));
        }
        return eventCategoryDTOList;
    }
    public List<String> getMultiCheckboxAllValues(){
        return eventCategoryRepository.findAll().stream()
                .map(EventCategory::getCategory)
                .collect(Collectors.toList());

    }
}
