package com.ceon.ippie;

public class TestData {
    static final String US_IP_1 = "8.8.0.0";
    static final String US_IP_2 = "8.8.8.8";
    static final String BR_IP = "177.0.0.0";
    static final String JP_IP = "180.0.0.0";
    static final String CO_IP = "190.0.0.0";

    static IpData nullIsland() {
        IpData nullIsland = new IpData();
        nullIsland.setCountryName("Null Island");
        nullIsland.setLatitude(0);
        nullIsland.setLongitude(0);
        return nullIsland;
    }

    static IpData unitedStates1() {
        IpData US_1 = new IpData();
        US_1.setIp(US_IP_1);
        US_1.setCountryName("United States");
        US_1.setCountryCode("US");
        US_1.setLatitude(37.751);
        US_1.setLongitude(-97.822);
        return US_1;
    }

    static IpData unitedStates2() {
        IpData US_2 = unitedStates1();
        US_2.setIp(US_IP_2);
        return US_2;
    }

    static IpData brazil() {
        IpData BR = new IpData();
        BR.setIp(BR_IP);
        BR.setCountryName("Brazil");
        BR.setCountryCode("BR");
        BR.setLatitude(-8.765);
        BR.setLongitude(-63.9115);
        return BR;
    }

    static IpData japan() {
        IpData JP = new IpData();
        JP.setIp(JP_IP);
        JP.setCountryName("Japan");
        JP.setCountryCode("JP");
        JP.setLatitude(35.8566);
        JP.setLongitude(139.6416);
        return JP;
    }

    static IpData colombia() {
        IpData CO = new IpData();
        CO.setIp(CO_IP);
        CO.setCountryName("Colombia");
        CO.setCountryCode("CO");
        CO.setLatitude(6.1838);
        CO.setLongitude(-75.5976);
        return CO;
    }
}
