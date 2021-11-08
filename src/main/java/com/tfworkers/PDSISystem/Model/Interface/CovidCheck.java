package com.tfworkers.PDSISystem.Model.Interface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "E-Health")
public interface CovidCheck {

    @GetMapping("/patient/covid/status")
    public ResponseEntity<Boolean> covidStatus();
}
