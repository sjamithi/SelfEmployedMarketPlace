package com.main.repository;

import com.main.dao.BidDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<BidDAO, Long> {

}
