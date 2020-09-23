package com.capgemini.dao;

import com.capgemini.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer>{

}
