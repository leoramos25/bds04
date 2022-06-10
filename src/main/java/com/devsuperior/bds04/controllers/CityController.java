package com.devsuperior.bds04.controllers;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    
    @PostMapping
    public ResponseEntity<CityDTO> createCity(@Valid @RequestBody CityDTO dto) {
        var cityDto = cityService.createCity(dto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cityDto);
    }
    
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAllCities() {
        var citiesDto = cityService.findAllCities();
        return ResponseEntity.ok().body(citiesDto);
    }
}
