package com.mg.front_end.service;

import com.mg.front_end.model.Catalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "catalog-service")
public interface CatalogService {

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<Catalog> getCatalogs();

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Catalog getCatalogById(@PathVariable("id") Integer id);
}
