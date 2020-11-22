package com.cardIndex.controller;

import com.cardIndex.domain.enums.FileType;
import com.cardIndex.services.GenerateFileService;
import javassist.NotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/generate")
@CrossOrigin(origins = "*")
public class GenerateFileContorller {
    private GenerateFileService generateFileService;
    public GenerateFileContorller(GenerateFileService generateFileService) {

        this.generateFileService = generateFileService;
    }

    @PostMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<InputStreamResource> generateFile(@RequestParam FileType fileType, @PathVariable  long employeeId) throws NotFoundException {
        ByteArrayInputStream in = this.generateFileService.generateExcel(employeeId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
