package com.devsuperior.bds04.controllers;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    private EventService eventService;
    
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDto) {
        eventDto = eventService.createEvent(eventDto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventDto.getId())
                .toUri();
        
        return ResponseEntity.created(uri).body(eventDto);
    }
    
    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAllEvents(Pageable pageable) {
        var eventsDto = eventService.findAllEvents(pageable);
        return ResponseEntity.ok().body(eventsDto);
    }
}
