package com.ceon.ippie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class IpPieController {

    @Autowired
    private IpPieService ipPieService;

    @GetMapping("/northcountries")
    public ResponseEntity<Object> getIpData(@RequestParam("ip") String[] params) {
        if(params.length > 5) {
            return ResponseEntity.unprocessableEntity().contentType(MediaType.TEXT_PLAIN).body("Number of ips in request are limited from 1 to 5.");
        }
        List<String> northernCountries = List.of(params).stream()
                .map(ipPieService::getIpData)
                .filter(IpData::isNorthernHemisphere)
                .map(IpData::getCountryName)
                .distinct()
                .collect(Collectors.toList());
        Map<String, List<String>> response = new HashMap<>();
        response.put("northcountries", northernCountries);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
