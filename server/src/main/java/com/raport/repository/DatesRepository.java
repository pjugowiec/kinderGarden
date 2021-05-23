package com.raport.repository;

import com.raport.domain.entity.Dates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DatesRepository extends JpaRepository<Dates, Long> {

    @Query(value = "SELECT * FROM dates d WHERE d.employee_id = :employeeId", nativeQuery = true)
    List<Dates> findByEmployeeId(@Param("employeeId") long id);

    @Query(value = "DELETE FROM dates d WHERE d.date = :date AND d.employee_id = :employeeId ", nativeQuery = true)
    void delete (@Param("date") Date date, @Param("employeeId") long employeeId);

    void deleteByEmployeeId(long employeeId);

    long countByEmployeeId(long employeeId);
}
