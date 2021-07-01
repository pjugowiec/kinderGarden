package com.report.repository;

import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;
import com.report.domain.model.employee.EmployeeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT new com.report.domain.model.employee.EmployeeTable( " +
            "e.id as id," +
            "e.name as name," +
            "e.lastname as lastname," +
            "e.regularPost as regularPost," +
            "e.countOfVacation as countOfVacation," +
            "e.countOfChildrenCare as countOfChildrenCare," +
            "e.position as position" +
            ") FROM #{#entityName} e")
    List<EmployeeTable> findAllEmployees();

    @Query("SELECT new com.report.domain.model.employee.EmployeeForm(" +
            " e.id as id," +
            " e.name as name," +
            " e.lastname as lastname," +
            " e.regularPost as regularPost," +
            " e.countOfVacation as countOfVacation," +
            " e.countOfChildrenCare as countOfChildrenCare," +
            " e.position as position" +
            " ) FROM #{#entityName} e" +
            " WHERE e.id = :id")
    Optional<EmployeeForm> findEmployeeById(@Param("id") final Long id);

    @Query(value = "select * from employees e " +
            " inner join dates d on (d.employee_id = e.id)" +
            " where e.id  = :id and date_part('year', d.date) = :year", nativeQuery = true)
    Optional<EmployeeEntity> findEmployeeWithDatesById(@Param("id") final Long id, @Param("year") final Integer year);
}
