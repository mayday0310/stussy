package com.zk.sbt.controller;

import com.zk.sbt.entity.Developer;
import com.zk.sbt.service.interfaces.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by zk_chs on 4/22/17.
 */
@RestController
@RequestMapping(value = "/developers", produces = APPLICATION_JSON_UTF8_VALUE)
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @RequestMapping(value = "/{developerId}", method = GET)
    public ResponseEntity<Developer> findById (@PathVariable("developerId") Long developerId){
        Developer developer = developerService.findById(developerId);
        return ResponseEntity.ok(developer);
    }

    @RequestMapping(value = "/{developerName}", method = POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Developer> findByName (@PathVariable("developerName") String developerName){
        Developer developer = developerService.findByName(developerName);
        return ResponseEntity.ok(developer);
    }

}
