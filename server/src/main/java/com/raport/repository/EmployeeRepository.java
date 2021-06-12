package com.raport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raport.domain.entity.EmployeeEntity;
import com.raport.domain.model.employee.EmployeeTable;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT new com.raport.domain.model.employee.EmployeeTable( " +
            "e.id as id," +
            "e.name as name," +
            "e.lastName as lastname," +
            "e.regularPost as regularPost," +
            "e.countOfVacation as countOfVacation," +
            "e.countOfChildrenCare as countOfChildrenCare," +
            "e.position as position" +
            ") FROM #{#entityName} e")
    List<EmployeeTable> findAllEmployees();

}
