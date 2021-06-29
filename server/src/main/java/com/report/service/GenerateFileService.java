package com.report.service;

import java.io.ByteArrayOutputStream;

public interface GenerateFileService {

    ByteArrayOutputStream generateExcel(final Long employeeId);
}
