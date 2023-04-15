package com.multipart.client.multipartclient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface TestApi {

    @HttpExchange(value = "/test", method = "POST", contentType = "multipart/form-data")
    public String test(@RequestPart(name = "file") MultipartFile file, @RequestPart(name = "info") AdditionalInfo additionalInfo);

    @PostExchange(url = "/basic")
    String basic(@RequestBody AdditionalInfo additionalInfo);
}
