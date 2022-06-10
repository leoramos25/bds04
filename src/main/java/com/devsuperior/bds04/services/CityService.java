package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    
    @Transactional
    public CityDTO createCity(CityDTO cityDTO) {
        var city = new City();
        city.setName(cityDTO.getName());
        city = cityRepository.save(city);
        return new CityDTO(city);
    }
}
