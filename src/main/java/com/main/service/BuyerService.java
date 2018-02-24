package com.main.service;

import com.main.dao.BuyerDAO;
import com.main.dao.ProjectDAO;
import com.main.repository.BuyerRepository;
import com.main.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;

    public BuyerDAO create(BuyerDAO s) {
        return buyerRepository.save(s);
    }

    public List<BuyerDAO> findAll() {
        return buyerRepository.findAll();
    }

    public BuyerDAO findOne(String id) {
        Long longId = Long.valueOf(id);
        return buyerRepository.findOne(longId);
    }
}
