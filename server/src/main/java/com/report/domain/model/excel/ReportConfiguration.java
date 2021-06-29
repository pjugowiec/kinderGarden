package com.report.domain.model.excel;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static com.common.util.CommonValue.PATH_TO_EXCEL_TEMPLATES;

@Data
@Component
public class ReportConfiguration {

    @Value(PATH_TO_EXCEL_TEMPLATES + "REPORT_WORKER_TEMPLATE.xlsx")
    private Resource resource;
}
