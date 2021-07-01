package com.report.domain.entity;

import com.report.domain.enums.DateType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DateType type;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

}
