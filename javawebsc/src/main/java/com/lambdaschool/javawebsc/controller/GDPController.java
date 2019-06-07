package com.lambdaschool.javawebsc.controller;

import com.lambdaschool.javawebsc.JavawebscApplication;
import com.lambdaschool.javawebsc.exception.ResourceNotFoundException;
import com.lambdaschool.javawebsc.model.GDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController

public class GDPController {
    private static final Logger logger = LoggerFactory.getLogger(GDPController.class);
    @GetMapping(value="/country/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable long id, HttpServletRequest res){
        logger.info(res.getRequestURI() + " accessed");
        if(JavawebscApplication.gdpList.findGDP((g)->g.getId()==id) == null){
            logger.info(res.getRequestURI() + " threw an error");

            throw new ResourceNotFoundException("No country found with "+ id + " id");
        }
        GDP country = JavawebscApplication.gdpList.findGDP((g)->g.getId()==id);
    return new ResponseEntity<>(country, HttpStatus.OK);
    }
    @GetMapping(value="/country/stats/median")
    public ResponseEntity<?> getMedianGDP(HttpServletRequest res){
        logger.info(res.getRequestURI() + " accessed");

        ArrayList<GDP> gdplist = JavawebscApplication.gdpList.gdpList;
        gdplist.sort((g1,g2)-> g2.getGdp() - g1.getGdp());
        return new ResponseEntity<>(gdplist.get(gdplist.size()/2), HttpStatus.OK);
    }
    @GetMapping(value="/country/economy")
    public ResponseEntity<?> getMostToLeastGDP(HttpServletRequest res){
        logger.info(res.getRequestURI() + " accessed");

        ArrayList<GDP> gdplist = JavawebscApplication.gdpList.gdpList;
        gdplist.sort((g1,g2)-> g2.getGdp() - g1.getGdp());
        return new ResponseEntity<>(gdplist, HttpStatus.OK);
    }
    @GetMapping(value="/names")
    public ResponseEntity<?> sortAlphabetically(HttpServletRequest res){
        logger.info(res.getRequestURI() + " accessed");

        ArrayList<GDP> gdplist = JavawebscApplication.gdpList.gdpList;
        gdplist.sort((g1,g2)-> g1.getName().compareToIgnoreCase(g2.getName()));
        return new ResponseEntity<>(gdplist, HttpStatus.OK);

    }
//gdpList
@GetMapping(value = "/economy/table")
public ModelAndView showDogTable(HttpServletRequest res) {
    logger.info(res.getRequestURI() + " accessed");

    ModelAndView mav = new ModelAndView();
    mav.setViewName("gdp");
    mav.addObject("gdpList", JavawebscApplication.gdpList.gdpList);
    return mav;
}
@GetMapping(value="/error")
    public ResponseEntity<?> catchErrors(HttpServletRequest res){
    logger.info(res.getRequestURI() + "threw an error");

    throw new ResourceNotFoundException("Your requested resource doesn't exist, check for typo in URL");
}
}
