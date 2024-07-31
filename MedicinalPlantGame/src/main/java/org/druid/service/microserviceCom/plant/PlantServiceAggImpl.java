package org.druid.service.microserviceCom.plant;

import org.druid.entity.original.CompendiumPage;
import org.druid.entity.original.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PlantServiceAggImpl implements PlantServiceAgg {

    String urlStart = "http://localhost:7001/plants/";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    @Override
    public List<Plant> getAllPlants() {

        String url = urlStart + "all";

        ResponseEntity<List<Plant>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Plant>>() {});

        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        else{
            return null;
        }
    }

    @Override
    public Plant getPlantById(int id) {

        String url = urlStart + id;
        return restTemplate.getForObject(url, Plant.class, id);
    }

    @Override
    public Plant getPlantByName(String name) {

        String url = urlStart + "name/" + name;
        return restTemplate.getForObject(url, Plant.class);
    }

    @Override
    public CompendiumPage getCompendiumPageByPlantId(int plantId) {

        String url = urlStart + "compendium/" + plantId;
        return restTemplate.getForObject(url, CompendiumPage.class);
    }
}
