package com.report.domain.model.excel;

import lombok.Builder;

import java.io.ByteArrayOutputStream;

@Builder
public class ReportModel {

    public final String fileName;
    public final ByteArrayOutputStream file;
}
