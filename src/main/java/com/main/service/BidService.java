package com.main.service;

import com.main.dao.BidDAO;
import com.main.dao.BuyerDAO;
import com.main.dao.ProjectDAO;
import com.main.repository.BidRepository;
import com.main.repository.BuyerRepository;
import com.main.repository.ProjectRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BidService {
    private static final String STATUS_CLOSED = "Closed";
    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BidRepository bidRepository;

    public BidDAO bid(BidDAO bidDAO) throws Exception {
        ProjectDAO project = null;
        BuyerDAO buyer = null;
        try {
             project = projectRepository.findOne(bidDAO.getProjectId());
        } catch (ObjectNotFoundException E) {
            System.out.println("Project not found by id: " +  bidDAO.getProjectId());
        }
        try {
            buyer = buyerRepository.findOne(bidDAO.getBuyerId());
        } catch (ObjectNotFoundException E) {
            System.out.println("Buyer not found by Id: " + bidDAO.getBuyerId());
        }

        bidDAO.setBidAmount(buyer.getPrice());
        Date bidDate = new Date();
        Date projectLastDate = project.getLastDate();
        long temp = projectLastDate.getTime();
        if (projectLastDate.getTime() - bidDate.getTime() < 0) {
            throw new Exception("Project is closed");
        } else {
            bidDAO.setBidDate(bidDate);
        }

        if (project.getBudget() < bidDAO.getBidAmount()) {
            throw new Exception("Bid amount is higher than project budget");
        }
        BidDAO persistedBidDao = bidRepository.save(bidDAO);

        List<BidDAO> bids = this.findBidsByProjectId(bidDAO.getProjectId());
        BidDAO lowestBid = bids.stream().sorted(Comparator.comparing(BidDAO::getBidAmount)).findFirst().get();

        project.setBidAmount(lowestBid.getBidAmount());
        project.setWinner(lowestBid.getBuyerId());

        if (project.getLastDate().compareTo(new Date()) < 0 ) {
            project.setStatus(STATUS_CLOSED);
        }
        projectRepository.save(project);

        return persistedBidDao;
    }

    public List<BidDAO> findAll() {
        return bidRepository.findAll();
    }

    public List<BidDAO> findBidsByProjectId(Long projectId) {
        return this.findAll().stream().filter(bid -> bid.getProjectId().equals(projectId)).collect(Collectors.toList());
    }

}
