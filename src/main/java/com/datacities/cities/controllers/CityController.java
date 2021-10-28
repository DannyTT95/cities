package com.datacities.cities.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.datacities.cities.models.CityModel;
import com.datacities.cities.services.CityService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suggestions")
public class CityController {
    @Autowired
    CityService cityService;

    // @GetMapping()
    // public ArrayList<CityModel> obtenerCiudades(){
    //     return cityService.obtenerCiudades();
    // }

    @PostMapping()
    public CityModel guardarCiudad(@RequestBody CityModel ciudad) {
        return this.cityService.guardarCiudad(ciudad);
    }

    @GetMapping( path = "/{id}")
    public Optional<CityModel> obtenerCiudadPorId(@PathVariable("id") Long id) {
        return this.cityService.obtenerPorId(id);
    }

    // @DeleteMapping( path = "/{id}")
    // public String eliminarCiudad(@PathVariable("id") Long id) {
    //     boolean ok = this.cityService.eliminarCiudad(id);
    //     if(ok) {
    //         return "Se eliminó la ciudad " + id;
    //     } else {
    //         return "No se pudo eliminar la ciudad " + id;
    //     }
    // }

    @GetMapping(params = {"q"})
    public ArrayList<CityModel> obtenerCiudadSugerida(@RequestParam String q) {
        return this.cityService.obtenerSugerencia(q);
    }

    @GetMapping(params = {"q","latitude","longitude"})
    public ArrayList<CityModel> obtenerCiudadesSugeridas(@RequestParam String q, @RequestParam String latitude, @RequestParam String longitude) {
        return this.cityService.obtenerSugerencias(q, Double.parseDouble(latitude), Double.parseDouble(longitude));
    }
}
