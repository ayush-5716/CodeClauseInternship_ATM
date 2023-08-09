package com.codeclause.atm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.codeclause.atm.entities.user_ent;

import jakarta.transaction.Transactional;

public interface user_entRepository extends CrudRepository<user_ent, Long> {

    @Query(nativeQuery = true,value="select * from kilo where ACC_NUMBER= :accNumber")
    public List<user_ent> givePin(@Param("accNumber") Long accNumber);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="update kilo set AMOUNT= :amount where acc_number= :acc_number")
    public void updateAmount(@Param("amount") Long amount,@Param("acc_number") Long acc_number);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="update kilo set AMOUNT= :amount where acc_number= :acc_number")
    public void loseAmount(@Param("amount") Long amount,@Param("acc_number") Long acc_number);
}
