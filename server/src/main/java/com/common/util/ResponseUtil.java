package com.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseUtil {

    private static final String APP_HEADER = "application";
    private static final String FORCE_DOWNLOAD = "force-download";
    private static final String ATTACHMENT_WITH_FILE_NAME = "attachment; filename=\"%s\"";

    public static HttpHeaders createDownloadFileHeader(final String fileName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(APP_HEADER, FORCE_DOWNLOAD));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, String.format(ATTACHMENT_WITH_FILE_NAME, fileName));
        httpHeaders.add(ACCESS_CONTROL_EXPOSE_HEADERS, CONTENT_DISPOSITION);

        return httpHeaders;
    }
}
