package com.example.googleservice.controller;
import com.example.googleservice.requestDTO.RealtyObjectDto;
import com.example.googleservice.responseDTO.Candidates;
import com.example.googleservice.service.ConfigRestForGoogle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/create-candidates")
@AllArgsConstructor
public class GoogleServiceController {

    private final ConfigRestForGoogle configRestForGoogle;

    @GetMapping
    public Candidates createCandidates(@RequestParam String p1,
                                       @RequestParam String p2,
                                       @RequestParam String p3,
                                       @RequestParam Integer p4) {
        RealtyObjectDto dto = new RealtyObjectDto(p1, p2, p3, p4);
        log.info(" find address by data from dto {}", dto);
        return configRestForGoogle.createCandidates(dto);
    }

}


/*
@RequestParam String p1,
                                       @RequestParam String p2,
                                       @RequestParam Integer p3,
                                       @RequestParam String p4
 */