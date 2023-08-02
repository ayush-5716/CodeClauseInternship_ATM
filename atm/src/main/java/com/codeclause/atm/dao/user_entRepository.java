package com.codeclause.atm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.codeclause.atm.entities.user_ent;

public interface user_entRepository extends CrudRepository<user_ent, Long> {

    @Query(nativeQuery = true,value="select * from kilo where acc_number= :accNumber")
    public List<user_ent> givePin(@Param("accNumber") Long accNumber);
}
