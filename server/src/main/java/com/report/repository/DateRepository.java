package com.report.repository;

import com.report.domain.model.DateData;
import org.springframework.data.jpa.repository.JpaRepository;

import com.report.domain.entity.DateEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface DateRepository extends JpaRepository<DateEntity, Long> {

    @Query(value = "SELECT new com.report.domain.model.DateData(" +
            " e.id as id," +
            " e.date as date," +
            " e.type as type)" +
            " FROM #{#entityName} e" +
            " WHERE e.employee.id = :employeeId" +
            " ORDER BY e.date ASC")
    Collection<DateData> findDatesByEmployeeId(@Param("employeeId") final Long employeeId);

    @Modifying
    @Query(value = "DELETE FROM #{#entityName} e " +
            " WHERE e.employee.id = :employeeId")
    void deleteDatesByEmployeeId(final Long employeeId);
}
