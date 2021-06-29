package com.report.controller;

import com.common.util.ResponseUtil;
import com.report.service.GenerateFileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class GenerateFileController {

    private final GenerateFileService generateFileService;

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getReportByEmployee(@PathVariable("id") final Long id) {

        return ResponseEntity.ok()
                .headers(ResponseUtil.createDownloadFileHeader("TEST"))
                .body(null);

    }
}
