package com.raport.service;

import javassist.NotFoundException;

import java.io.ByteArrayInputStream;

public interface GenerateFileService {

    ByteArrayInputStream generateExcel(final Long employeeId) throws NotFoundException;
    void generatePDF(final Long userId);
}