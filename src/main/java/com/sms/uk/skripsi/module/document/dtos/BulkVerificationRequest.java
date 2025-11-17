package com.sms.uk.skripsi.module.document.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BulkVerificationRequest {
    private String userUuid;
    private List<DocumentVerificationRequest> documents;
}
