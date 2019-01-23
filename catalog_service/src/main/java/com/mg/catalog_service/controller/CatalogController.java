package com.mg.catalog_service.controller;

import com.mg.catalog_service.model.Catalog;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;


@RefreshScope
@RestController
@Slf4j
public class CatalogController {

    @Value("${app.id}")
    private String instance;

    @Value("${owner.name}")
    private String ownerName;


    private Map<Integer, Catalog> catalogMap = new HashMap<>();


    @PostConstruct
    public void init() {
        catalogMap.put(1, new Catalog(1, "Linda Black Golden Icon Jean Gömlek", "Dar Kesim"));
        catalogMap.put(2, new Catalog(2, "Eliza Indigo Zımba Detaylı Jean Gömlek", "Genis Kesim"));
        catalogMap.put(3, new Catalog(3, "Elize Gold Tencel Jean Gömlek", "Baska bir aciklama"));
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "/all")
    public Collection<Catalog> getAll() {
        int i = new Random().nextInt(10);
        log.info(i + " RANDOM");
        if(i > 5){
            throw new RuntimeException();
        }
        log.info(instance + " cagirildi");
        return catalogMap.values();
    }

    @RequestMapping(value = "/{id}")
    public Catalog getCatalog(@PathVariable("id") Integer id) {
        return catalogMap.get(id);
    }

    @RequestMapping(value = "/")
    public String getInstanceId() {
        return "{ \"running_instance_id\": \"" + instance + "\", \"owner\": \"" + ownerName + "\"}";
    }

    public Collection<Catalog> fallback(){
        Collection catalogs = new ArrayList<>();
        catalogs.add(new Catalog(1, "CATALOG YOK", "DESCRIPTION YOK"));
        return catalogs;
    }
}
