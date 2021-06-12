package com.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.report.domain.entity.DateEntity;

public interface DatesRepository extends JpaRepository<DateEntity, Long> {

//    @Query(value = "SELECT * FROM dates d WHERE d.employee_id = :employeeId", nativeQuery = true)
//    List<Dates> findByEmployeeId(@Param("employeeId") long id);
//
//    @Query(value = "DELETE FROM dates d WHERE d.date = :date AND d.employee_id = :employeeId ", nativeQuery = true)
//    void delete (@Param("date") Date date, @Param("employeeId") long employeeId);
//
//    void deleteByEmployeeId(long employeeId);
//
//    long countByEmployeeId(long employeeId);
}
