package com.sms.uk.skripsi.module.document.dtos;

import lombok.Data;

@Data
public class DocumentVerificationRequest {

    private String uuid;
    private Boolean isVerified;
    private String note;

}
