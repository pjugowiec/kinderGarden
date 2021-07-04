package com.unit.common.util;

import com.common.util.ResponseUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;


class ResponseUtilTest {

    private static final String APP_HEADER = "application";
    private static final String FORCE_DOWNLOAD = "force-download";

    @Test
    @DisplayName("Create download file headers - Should create headers")
    void createDownloadFileHeaders_ShouldCreateHeaders() {
        HttpHeaders result = ResponseUtil.createDownloadFileHeaders("TEST_FILE");

        assertEquals(new MediaType(APP_HEADER, FORCE_DOWNLOAD), result.getContentType());
        assertEquals("attachment; filename=\"TEST_FILE\"", result.getFirst(HttpHeaders.CONTENT_DISPOSITION));
        assertEquals(CONTENT_DISPOSITION, result.getFirst(ACCESS_CONTROL_EXPOSE_HEADERS));
    }
}
