package com.chukcheck.api.controller;

import com.chukcheck.api.docs.EnumDocs;
import com.chukcheck.core.dto.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class CommonController {

    @GetMapping("/enums")
    public BaseResponse<?> enumReadAll() {
        return new BaseResponse<>(new EnumDocs());
    }
}
