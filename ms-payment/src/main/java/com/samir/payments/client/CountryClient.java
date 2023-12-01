package com.samir.payments.client;

import com.samir.payments.model.client.CountryDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-country", url = "http://localhost:8088/v1/countries")
public interface CountryClient {
    @GetMapping()
    List<CountryDto> getAvailableCountries(@RequestParam String currency);
}

