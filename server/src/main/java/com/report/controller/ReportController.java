package com.report.controller;

import com.common.util.ResponseUtil;
import com.report.domain.model.excel.ReportModel;
import com.report.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.common.model.ResourceUrl.REPORT;

@RestController
@RequestMapping(REPORT)
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getReportByEmployee(@PathVariable("id") final Long id) {

        final ReportModel reportExcel = reportService.generateEmployeeReport(id);

        return ResponseEntity.ok()
                .headers(ResponseUtil.createDownloadFileHeaders(reportExcel.fileName + ".xlsx"))
                .body(new ByteArrayResource(reportExcel.file.toByteArray())
                );

    }
}
