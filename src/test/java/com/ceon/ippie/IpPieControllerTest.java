package com.ceon.ippie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.ceon.ippie.TestData.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(IpPieController.class)
public class IpPieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IpPieService ipPieService;

    @InjectMocks
    private IpPieController ipPieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("given 2 US ip, 1 Japan, 1 Colombia, 1 Brazil " +
            "when northcountries endpoint is visited " +
            "then US, Japan and Colombia are displayed as northern hemisphere countries")
    @Test
    public void testNorthCountriesWhen3North1South2Duplicated() throws Exception {
        when(ipPieService.getIpData(US_IP_1)).thenReturn(unitedStates1());
        when(ipPieService.getIpData(US_IP_2)).thenReturn(unitedStates2());
        when(ipPieService.getIpData(BR_IP)).thenReturn(brazil());
        when(ipPieService.getIpData(JP_IP)).thenReturn(japan());
        when(ipPieService.getIpData(CO_IP)).thenReturn(colombia());


        MvcResult mvcResult = mockMvc
                .perform(get("/northcountries")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .queryParam("ip", US_IP_1, US_IP_2, BR_IP, JP_IP, CO_IP))
                .andExpect(status().isOk())
                .andExpect(jsonPath("northcountries").value(containsInAnyOrder("United States", "Colombia", "Japan")))
                .andReturn();
    }

    @DisplayName("given params are 5 US ip and foo-bar " +
            "when northcountries endpoint is visited " +
            "then US is displayed as the northern hemisphere country")
    @Test
    public void testNorthCountriesWhen5IPsAndADummyParamRequested() throws Exception {
        when(ipPieService.getIpData(US_IP_1)).thenReturn(unitedStates1());
        when(ipPieService.getIpData(US_IP_2)).thenReturn(unitedStates2());


        MvcResult mvcResult = mockMvc
                .perform(get("/northcountries")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .queryParam("ip", US_IP_1, US_IP_2, US_IP_1, US_IP_2, US_IP_1)
                        .queryParam("foo", "bar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("northcountries").value(containsInAnyOrder("United States")))
                .andReturn();
    }

    @DisplayName("given 6 ips " +
            "when northcountries endpoint is visited " +
            "then 422 is returned")
    @Test
    public void testNorthCountriesWhen6IPsRequested() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/northcountries")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .queryParam("ip", US_IP_1, US_IP_2, BR_IP, JP_IP, CO_IP, CO_IP))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
    }

    @DisplayName("given one dummy param (foo) " +
            "when northcountries endpoint is visited " +
            "then 400 is returned")
    @Test
    public void testNorthCountriesWhenFooRequested() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/northcountries")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .queryParam("foo", "bar"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @DisplayName("given no ips " +
            "when northcountries endpoint is visited " +
            "then 400 is returned")
    @Test
    public void testNorthCountriesWhenNoIPsRequested() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/northcountries")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
