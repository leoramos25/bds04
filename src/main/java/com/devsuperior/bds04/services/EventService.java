package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    
    @Transactional
    public EventDTO createEvent(EventDTO eventDTO) {
        var event = new Event();
        
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setUrl(eventDTO.getUrl());
        event.setCity(new City(eventDTO.getCityId(), null));
        
        event = eventRepository.save(event);
        
        return new EventDTO(event);
    }
    
    @Transactional(readOnly = true)
    public Page<EventDTO> findAllEvents(Pageable pageable) {
        var events = eventRepository.findAll(pageable);
        return events.map(EventDTO::new);
    }
}
