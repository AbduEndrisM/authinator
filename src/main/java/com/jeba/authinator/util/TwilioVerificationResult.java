package com.jeba.authinator.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwilioVerificationResult {

    private final String id;
    private final String[] errors;
    private final boolean valid;


    public TwilioVerificationResult(String id) {
        this.id = id;
        this.errors = new String[]{};
        this.valid = true;
    }

    public TwilioVerificationResult(String[] errors) {
        this.errors = errors;
        this.id = "";
        this.valid = false;
    }


}


