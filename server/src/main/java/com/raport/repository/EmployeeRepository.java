package com.raport.repository;

import com.raport.domain.model.EmployeeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raport.domain.entity.EmployeeEntity;

import java.util.Collection;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = "SELECT e FROM #{#entityName} e")
    Collection<EmployeeTable> getEmployees();
}
