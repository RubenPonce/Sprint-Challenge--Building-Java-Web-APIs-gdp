package com.lambdaschool.javawebsc.controller;

import com.lambdaschool.javawebsc.JavawebscApplication;
import com.lambdaschool.javawebsc.model.GDP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController

public class GDPController {

    @GetMapping(value="/country/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable long id){
        GDP country = JavawebscApplication.gdpList.findGDP((g)->g.getId()==id);
    return new ResponseEntity<>(country, HttpStatus.OK);
    }
    @GetMapping(value="/country/stats/median")
    public ResponseEntity<?> getMedianGDP(){
        ArrayList<GDP> gdplist = JavawebscApplication.gdpList.gdpList;
        gdplist.sort((g1,g2)-> g2.getGdp() - g1.getGdp());
        return new ResponseEntity<>(gdplist.get(gdplist.size()/2), HttpStatus.OK);
    }
    @GetMapping(value="/country/economy")
    public ResponseEntity<?> getMostToLeastGDP(){
        ArrayList<GDP> gdplist = JavawebscApplication.gdpList.gdpList;
        gdplist.sort((g1,g2)-> g2.getGdp() - g1.getGdp());
        return new ResponseEntity<>(gdplist, HttpStatus.OK);
    }
    @GetMapping(value="/names")
    public ResponseEntity<?> sortAlphabetically(){
        ArrayList<GDP> gdplist = JavawebscApplication.gdpList.gdpList;
        gdplist.sort((g1,g2)-> g1.getName().compareToIgnoreCase(g2.getName()));
        return new ResponseEntity<>(gdplist, HttpStatus.OK);

    }
//gdpList
@GetMapping(value = "/economy/table")
public ModelAndView showDogTable() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("gdp");
    mav.addObject("gdpList", JavawebscApplication.gdpList.gdpList);
    return mav;
}
}
