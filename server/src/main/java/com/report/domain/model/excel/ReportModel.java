package com.report.domain.model.excel;

import lombok.Builder;
import lombok.Data;

import java.io.ByteArrayOutputStream;

@Data
@Builder
public class ReportModel {

    private String fileName;
    private ByteArrayOutputStream file;
}
