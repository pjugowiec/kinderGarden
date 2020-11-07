package com.cardIndex.services;

import javassist.NotFoundException;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

public interface GenerateFileService {

    ByteArrayInputStream generateExcel(long employeeId, String name) throws NotFoundException;
    void generatePDF(long userId);
}
