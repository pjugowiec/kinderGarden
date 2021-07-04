package com.report.service;

import com.report.domain.model.excel.ReportModel;

public interface ReportService {

    ReportModel generateEmployeeReport(final Long employeeId);
}
