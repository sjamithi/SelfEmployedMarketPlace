package com.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.dao.BuyerDAO;
import com.main.dao.ProjectDAO;
import com.main.service.BuyerService;
import com.main.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    public String index() {
        return "From Buyer controller";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BuyerDAO create(@RequestBody  BuyerDAO request) {
        ObjectMapper mapper = new ObjectMapper();
        BuyerDAO buyerDAO =  mapper.convertValue(request, BuyerDAO.class);
        return buyerService.create(buyerDAO);
    }

    @RequestMapping(value = "/findOne")
    public BuyerDAO findOneById(String id) {
        return buyerService.findOne(id);
    }

    @RequestMapping(value = "/findAll")
    public List<BuyerDAO> findAll() {
        return buyerService.findAll();
    }
}
