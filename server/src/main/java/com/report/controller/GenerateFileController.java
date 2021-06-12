package com.report.controller;

import org.springframework.web.bind.annotation.*;

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
