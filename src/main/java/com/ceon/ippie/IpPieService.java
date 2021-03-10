package com.ceon.ippie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpPieService {

    @Autowired
    private IpApi ipApi;

    @Autowired
    private ObjectMapper objectMapper;

    public IpData getIpData(String ip) {
        IpData ipData = new IpData();
        String output = ipApi.getIpData(ip);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            ipData = objectMapper.readValue(output, IpData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ipData;
    }
}
