package com.example.rentme_backend_morgan.business.service.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.Candidates;

@FeignClient(
        name = "google-service",
        path = "api/v1/create-candidates"
//        url = "http://localhost:8085"
//        path = "http://localhost:8085"
)
public interface MicroServiceGoogle {

    @GetMapping
    Candidates createCandidates(@RequestParam("p1") String p1,
                                @RequestParam("p2") String p2,
                                @RequestParam("p3") String p3,
                                @RequestParam("p4") Integer p4);

}