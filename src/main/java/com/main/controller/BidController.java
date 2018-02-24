package com.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.dao.BidDAO;
import com.main.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    public String index() {
        return "From Bid controller";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BidDAO create(@RequestBody BidDAO request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        BidDAO bidDAO =  mapper.convertValue(request, BidDAO.class);
        return bidService.bid(bidDAO);
    }

    @RequestMapping(value = "/findAll")
    public List<BidDAO> findAll() {
        return bidService.findAll();
    }

}
