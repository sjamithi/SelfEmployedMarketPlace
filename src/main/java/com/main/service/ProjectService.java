package com.main.service;

import com.main.dao.BidDAO;
import com.main.dao.ProjectDAO;
import com.main.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private static final String STATUS_CLOSED = "Closed";
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BidService  bidService;

    public ProjectDAO create(ProjectDAO s) {
        if (s.getLastDate().compareTo(new Date()) < 0 ) {
            s.setStatus(STATUS_CLOSED);
        }
        return projectRepository.save(s);
    }

    public List<ProjectDAO> findAll() {
        return projectRepository.findAll();
    }

    public ProjectDAO findOne(String id) {
        Long longId = Long.valueOf(id);
        return projectRepository.findOne(longId);
    }

    public ProjectDAO projectStatusById(Long projectId) {
        List<BidDAO> bids = bidService.findBidsByProjectId(projectId);
        BidDAO lowestBid = bids.stream().sorted(Comparator.comparing(BidDAO::getBidAmount)).findFirst().get();

        ProjectDAO projectDAO = projectRepository.findOne(lowestBid.getProjectId());

        return projectDAO;
    }

    public List<ProjectDAO> findAllByStatuses(String status) {
        return projectRepository.findAll().stream().filter(project ->  project.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
    }
}
