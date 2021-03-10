package com.ceon.ippie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ceon.ippie.TestData.BR_IP;
import static com.ceon.ippie.TestData.US_IP_1;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IpPieServiceTest {
    @Spy
    private ObjectMapper objectMapper;
    @Mock
    private IpApi ipApi;
    @InjectMocks
    private IpPieService ipPieService;

    @DisplayName("given an ip address from US" +
            "when remote api is invoked with ip as param" +
            "then ip data of this ip is returned")
    @Test
    public void testGetIpPieServiceWithUSIP() {
        when(ipApi.getIpData(US_IP_1)).thenReturn("{\"ip\":\"8.8.0.0\",\"country_code\":\"US\",\"country_name\":\"United States\",\"is_in_european_union\":false,\"region_name\":\"\",\"region_code\":\"\",\"city\":\"\",\"zip_code\":\"\",\"time_zone\":\"America/Chicago\",\"latitude\":37.751,\"longitude\":-97.822,\"metro_code\":0,\"organisation\":\"LEVEL3\",\"flagUrl\":\"https://ip-api.io/images/flags/us.svg\",\"emojiFlag\":\"\uD83C\uDDFA\uD83C\uDDF8\",\"currencySymbol\":\"$\",\"currency\":\"USD,USN,USS\",\"callingCode\":\"1\",\"countryCapital\":\"Washington D.C.\",\"suspiciousFactors\":{\"isProxy\":false,\"isTorNode\":false,\"isSpam\":false,\"isSuspicious\":false}}");

        // given
        String ip = US_IP_1;

        // Add buyer
        IpData ipData = ipPieService.getIpData(ip);

        // Verify
        assertEquals(US_IP_1, ipData.getIp());
        assertEquals("United States", ipData.getCountryName());
        assertEquals("US", ipData.getCountryCode());
        assertTrue(ipData.isNorthernHemisphere());
        assertFalse(ipData.isSouthernHemisphere());
    }

    @DisplayName("given an ip address from Brazil" +
            "when remote api is invoked with ip as param" +
            "then ip data of this ip is returned")
    @Test
    public void testGetIpPieServiceWithBRIP() {
        when(ipApi.getIpData(BR_IP)).thenReturn("{\"ip\":\"177.0.0.0\",\"country_code\":\"BR\",\"country_name\":\"Brazil\",\"is_in_european_union\":false,\"region_name\":\"Rondonia\",\"region_code\":\"RO\",\"city\":\"Porto Velho\",\"zip_code\":\"78900\",\"time_zone\":\"America/Porto_Velho\",\"latitude\":-8.765,\"longitude\":-63.9115,\"metro_code\":0,\"organisation\":\"Brasil Telecom S/A - Filial Distrito Federal\",\"flagUrl\":\"https://ip-api.io/images/flags/br.svg\",\"emojiFlag\":\"\uD83C\uDDE7\uD83C\uDDF7\",\"currencySymbol\":\"R$\",\"currency\":\"BRL\",\"callingCode\":\"55\",\"countryCapital\":\"Brasília\",\"suspiciousFactors\":{\"isProxy\":false,\"isTorNode\":false,\"isSpam\":false,\"isSuspicious\":false}}");

        // given
        String ip = BR_IP;

        // Add buyer
        IpData ipData = ipPieService.getIpData(ip);

        // Verify
        assertEquals(BR_IP, ipData.getIp());
        assertEquals("Brazil", ipData.getCountryName());
        assertEquals("BR", ipData.getCountryCode());
        assertFalse(ipData.isNorthernHemisphere());
        assertTrue(ipData.isSouthernHemisphere());
    }
}
