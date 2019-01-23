package com.mg.front_end.controller;

import com.mg.front_end.model.Catalog;
import com.mg.front_end.service.CatalogService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(value = "/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @HystrixCommand(fallbackMethod = "fallbackCatalog")
    @GetMapping("")
    public List<Catalog> allCatalogs(){
        return catalogService.getCatalogs();
    }

    @GetMapping("/{id}")
    public Catalog catalogById(@PathVariable("id") Integer id){
        return catalogService.getCatalogById(id);
    }

    public List<Catalog> fallbackCatalog(){
        List<Catalog> catalogs = new ArrayList<>();
        catalogs.add(new Catalog(1, "CATALOG YOK", "DESCRIPTION YOK"));
        return catalogs;
    }
}
