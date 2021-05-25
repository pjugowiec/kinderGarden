package com.raport.controller;

import com.raport.domain.enums.FileType;
import com.raport.services.GenerateFileService;
import javassist.NotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/generate")
@CrossOrigin(origins = "*")
public class GenerateFileController {

//    private final GenerateFileService generateFileService;
//
//    public GenerateFileController(GenerateFileService generateFileService) {
//
//        this.generateFileService = generateFileService;
//    }
//
//    @PostMapping("/{employeeId}")
//    public ResponseEntity<InputStreamResource> generateFile(@RequestParam final FileType fileType, @PathVariable  final Long employeeId) throws NotFoundException {
//        ByteArrayInputStream in = this.generateFileService.generateExcel(employeeId);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
//
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .headers(headers)
//                .body(new InputStreamResource(in));
//    }
}
