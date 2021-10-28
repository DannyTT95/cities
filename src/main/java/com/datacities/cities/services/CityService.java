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
        int diffName = 0;

        for (CityModel cityModel : citiesSuggested) {
            diffName = name.length() - cityModel.getName().length();
            diffLatitude = latitude - cityModel.getLatitude();
            diffLongitude = longitude - cityModel.getLongitude();
            
            cityModel.setScore((evaluarScore(diffName, diffLatitude, diffLongitude)) / 10);
        } 

        //Ordenar de mayor a menor puntuación (score)
        Collections.sort(citiesSuggested, Comparator.comparing(CityModel::getScore).reversed());
        return citiesSuggested;         
    }

    public double evaluarScore(int diffName, Double diffLatitude, Double diffLongitud) {
        double score = 10;

        if(diffName < 0) {
            diffName *= -1;
        }
        if(diffLatitude < 0) {
            diffLatitude *= -1;
        }
        if(diffLongitud < 0) {
            diffLongitud *= -1;
        }

        Double diff = (diffName + diffLatitude + diffLongitud) / 3;
        diff = (double) Math.round(diff);

        return score - diff;
    }

    public ArrayList<CityModel> obtenerSugerencia(String name) {
        return cityRepository.busquedaName(name);
    }
}
