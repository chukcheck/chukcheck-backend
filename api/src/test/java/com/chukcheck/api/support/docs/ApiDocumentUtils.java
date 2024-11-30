package com.chukcheck.api.support.docs;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface ApiDocumentUtils {

    static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(modifyUris()
                        .scheme("https")
                        .host("api.chukcheck.com")
                        .removePort(),
                modifyHeaders()
                        .remove("Content-Length")
                        .remove("X-CSRF-TOKEN"),
                prettyPrint());
    }

    static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(
                modifyHeaders()
                        .remove("X-Content-Type-Options")
                        .remove("X-XSS-Protection")
                        .remove("Cache-Control")
                        .remove("Pragma")
                        .remove("Expires")
                        .remove("Strict-Transport-Security")
                        .remove("X-Frame-Options")
                        .remove("Content-Length"),
                prettyPrint());
    }
}
