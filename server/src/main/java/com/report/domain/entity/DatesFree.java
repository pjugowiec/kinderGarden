package com.report.domain.entity;

import com.report.domain.enums.DateType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class DatesFree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date freeDate;

    @Enumerated(value = EnumType.STRING)
    private DateType dateType;
}
