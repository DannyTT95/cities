package com.datacities.cities.repositories;

import java.util.ArrayList;

import com.datacities.cities.models.CityModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityModel, Long> {
    public abstract ArrayList<CityModel> findByLatitude(Double latitude);

    // public abstract ArrayList<CityModel> findByNameContaining(String name);

    @Query(value = "SELECT c FROM CityModel c WHERE c.name LIKE %?1%")
    public abstract ArrayList<CityModel> busquedaName(String name);

    // @Query(
    //     value = "SELECT * FROM city WHERE city.name LIKE %:cadena%",
    //     nativeQuery = true
    // )
    // public abstract ArrayList<CityModel> busquedaName(@Param("cadena") String cadena);
}
