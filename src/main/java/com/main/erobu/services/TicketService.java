package com.main.erobu.services;

import com.main.erobu.data.entry.Event;
import com.main.erobu.data.entry.Ticket;
import com.main.erobu.data.repository.TicketRepository;
import com.main.erobu.dto.EventDTO;
import com.main.erobu.dto.TicketDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketDTO> findByEvent(EventDTO eventDTO){
        Event event = EntityUtils.eventDTOToEvent(eventDTO);
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        List<Ticket> ticketList = ticketRepository.findByEvent(event);

        for(Ticket ticket: ticketList){
            ticketDTOList.add(EntityUtils.ticketToTicketDTO(ticket));
        }
        return ticketDTOList;
    }

    public TicketDTO findById(Integer id) throws ObjectTransferException {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if(ticket == null){
            throw new ObjectTransferException("Data not found");

        }
        return EntityUtils.ticketToTicketDTO(ticket);
    }

    public void save(TicketDTO ticketDTO){
        Ticket ticket = EntityUtils.ticketDTOToTicket(ticketDTO);
        ticketRepository.save(ticket);
    }
}
