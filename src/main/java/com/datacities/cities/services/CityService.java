package com.datacities.cities.services;

import java.util.ArrayList;
import java.util.*;

import com.datacities.cities.models.CityModel;
import com.datacities.cities.repositories.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public ArrayList<CityModel> obtenerCiudades() {
        return (ArrayList<CityModel>) cityRepository.findAll();
    }

    public CityModel guardarCiudad(CityModel ciudad) {
        return cityRepository.save(ciudad);
    }

    public Optional<CityModel> obtenerPorId(Long id) {
        return cityRepository.findById(id);
    }

    // public boolean eliminarCiudad(Long id) {
    //     try {
    //         cityRepository.deleteById(id);
    //         return true;
    //     } catch(Exception err) {
    //         return false;
    //     }
    // }

    //Método para establecer el score de acuerdo a los parámetros
    public ArrayList<CityModel> obtenerSugerencias(String name, Double latitude, Double longitude) {
        ArrayList<CityModel> citiesSuggested = cityRepository.busquedaName(name);
        Double diffLatitude = 0.0;
        Double diffLongitude = 0.0;
        int score;

        for (CityModel cityModel : citiesSuggested) {
            score = 10;
            diffLatitude = latitude - cityModel.getLatitude();
            diffLongitude = longitude - cityModel.getLongitude();
            if(!(name.toUpperCase()).equals((cityModel.getName()).toUpperCase())) {
                score -= 5; 
            }
            if(diffLatitude > 5 || diffLatitude < -5) {
                score -= 3;
            }
            if(diffLongitude > 5 || diffLongitude < -5) {
                score -= 2;
            }
            
            cityModel.setScore(score * .1);
        } 

        //Ordenar de mayor a menor puntuación (score)
        Collections.sort(citiesSuggested, Comparator.comparing(CityModel::getScore).reversed());
        return citiesSuggested;         
    }

    public ArrayList<CityModel> obtenerSugerencia(String name) {
        return cityRepository.busquedaName(name);
    }
}
