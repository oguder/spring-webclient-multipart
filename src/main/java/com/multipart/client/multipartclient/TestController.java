package com.multipart.client.multipartclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    private final TestApi testApi;

    public TestController(TestApi testApi) {
        this.testApi = testApi;
    }

    @PostMapping("/test")
    public String test(@RequestPart(name = "file") MultipartFile file, @RequestPart(name = "info") AdditionalInfo additionalInfo) {
        return testApi.test(file, additionalInfo);
    }

    @PostMapping("/basic")
    public String basic(@RequestBody AdditionalInfo info) {
        return testApi.basic(info);
    }
}
