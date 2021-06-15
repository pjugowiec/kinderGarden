package com.report.repository;

import com.report.domain.model.DateData;
import org.springframework.data.jpa.repository.JpaRepository;

import com.report.domain.entity.DateEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface DateRepository extends JpaRepository<DateEntity, Long> {

    @Query(value = "SELECT new com.report.domain.model.DateData(" +
            " e.id as id," +
            " e.date as date," +
            " e.type as type)" +
            " FROM #{#entityName} e" +
            " WHERE e.id = :employeeId")
    Collection<DateData> findDatesByEmployeeId(@Param("employeeId") final Long employeeId);


//    @Query(value = "DELETE FROM dates d WHERE d.date = :date AND d.employee_id = :employeeId ", nativeQuery = true)
//    void delete (@Param("date") Date date, @Param("employeeId") long employeeId);
//
//    void deleteByEmployeeId(long employeeId);
//
//    long countByEmployeeId(long employeeId);
}
