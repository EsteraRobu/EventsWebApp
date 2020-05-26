package com.main.erobu.services;

import com.main.erobu.data.entry.Client;
import com.main.erobu.data.entry.Editor;
import com.main.erobu.data.entry.Event;
import com.main.erobu.data.entry.EventCategory;
import com.main.erobu.data.repository.ClientRepository;
import com.main.erobu.data.repository.EventCategoryRepository;
import com.main.erobu.data.repository.EventRepository;
import com.main.erobu.dto.ClientDTO;
import com.main.erobu.dto.EditorDTO;
import com.main.erobu.dto.EventDTO;
import com.main.erobu.dto.TicketDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    @Autowired
    private TicketService ticketService;

    private static final Integer NR_OF_RECOMANDED_EVENTS_TO_SHOW = 3;

    public List<EventDTO> findAll() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> eventList = eventRepository.findAll();
        Double startingPrice;
        EventDTO eventDTO;
        for (Event event : eventList) {
            startingPrice = computeStartingPrice(event);
            eventDTO = EntityUtils.eventToEventDTO(event);
            eventDTO.setStartingPrice(startingPrice);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    public List<EventDTO> findAllActive() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> eventList = eventRepository.findByDateStartAfter(new Timestamp(System.currentTimeMillis()));
        Double startingPrice;
        EventDTO eventDTO;
        for (Event event : eventList) {
            startingPrice = computeStartingPrice(event);
            eventDTO = EntityUtils.eventToEventDTO(event);
            eventDTO.setStartingPrice(startingPrice);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    private Double computeStartingPrice(Event event) {
        List<TicketDTO> ticketDTOList = ticketService.findByEvent(EntityUtils.eventToEventDTO(event));
        Double startingPrice = Double.MAX_VALUE;
        for (TicketDTO ticketDTO : ticketDTOList) {
            if (Objects.isNull(ticketDTO.getPrice())) {
                ticketDTO.setPrice(0.00);
            }
            if (ticketDTO.getPrice() < startingPrice) {
                startingPrice = ticketDTO.getPrice();
            }
        }
        return startingPrice;
    }

    public List<EventDTO> findByName(String name) {
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> eventList = eventRepository.findByName(name);
        Double startingPrice;
        EventDTO eventDTO;
        for (Event event : eventList) {
            startingPrice = computeStartingPrice(event);
            eventDTO = EntityUtils.eventToEventDTO(event);
            eventDTO.setStartingPrice(startingPrice);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    public List<EventDTO> findByEditor(EditorDTO editorDTO) {
        Editor editor = EntityUtils.editorDTOToEditor(editorDTO);
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> eventList = eventRepository.findByEditor(editor);
        Double startingPrice;
        EventDTO eventDTO;
        for (Event event : eventList) {
            startingPrice = computeStartingPrice(event);
            eventDTO = EntityUtils.eventToEventDTO(event);
            eventDTO.setStartingPrice(startingPrice);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    public EventDTO findById(Integer eventId) throws ObjectTransferException {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            throw new ObjectTransferException("Data not found");
        }
        Double startingPrice = computeStartingPrice(event);
        EventDTO eventDTO = EntityUtils.eventToEventDTO(event);
        eventDTO.setStartingPrice(startingPrice);
        return eventDTO;
    }

    public void save(EventDTO eventDTO) {
        Event event = EntityUtils.eventDTOToEvent(eventDTO);
        eventRepository.save(event);
    }

    public void delete(EventDTO eventDTO) {
        Event event = EntityUtils.eventDTOToEvent(eventDTO);
        eventRepository.delete(event);
    }

    public HashMap<String, List<EventDTO>> getEventsFromFavoriteCategory(ClientDTO clientDTO) {
        List<Event> favevents = new ArrayList<>();
        HashMap<String, List<EventDTO>> stringEventDTOMap = new HashMap<>();
        Client client = EntityUtils.clientDTOToClient(clientDTO);
        List<EventCategory> event = eventCategoryRepository.findAll();
        List<EventCategory> eventCategories = new ArrayList<>();
        for (EventCategory eventCategory : event) {
            if (eventCategory.getClients().stream()
                    .map(Client::getUsername)
                    .collect(Collectors.toList()).contains(client.getUsername())) {
                eventCategories.add(eventCategory);
            }
        }
        for (EventCategory eventCategory : eventCategories) {
            List<EventDTO> eventDTOS = new ArrayList<>();
            if (Objects.nonNull(eventRepository.findByEventCategory(eventCategory))) {
                favevents = eventRepository.findByEventCategory(eventCategory);
                for (Event event1 : favevents) {
                    eventDTOS.add(EntityUtils.eventToEventDTO(event1));
                }
            }
            stringEventDTOMap.put(eventCategory.getCategory(), eventDTOS);
        }
        return stringEventDTOMap;
    }

    public HashMap<String, List<EventDTO>> getRecomnadedEvents(HashMap<String, List<EventDTO>> eveStringListMap) {
        HashMap<String, List<EventDTO>> stringListHashMap = new HashMap<>();
        eveStringListMap.forEach((k, v) -> {
            Collections.sort(v, Comparator.comparing(s -> s.getVisualizationNo()));
            Collections.reverse(v);
            stringListHashMap.put(k, v.stream().limit(NR_OF_RECOMANDED_EVENTS_TO_SHOW).collect(Collectors.toList()));
        });
        return stringListHashMap;
    }
}
