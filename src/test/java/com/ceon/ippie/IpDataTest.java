package com.ceon.ippie;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.ceon.ippie.TestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IpDataTest {

    private static IpData nullIsland;
    private static IpData northern;
    private static IpData southern;

    @BeforeAll
    public static void setUp() {
        nullIsland = nullIsland();
        northern = unitedStates1();
        southern = brazil();
    }

    @AfterAll
    public static void tearDown() {
        nullIsland = null;
        northern = null;
        southern = null;
    }

    @Test
    public void testIsNorthernHemisphereWhenLatitudeIsZero() {
        assertFalse(nullIsland.isNorthernHemisphere());
    }

    @Test
    public void testIsNorthernHemisphereWhenLatitudeIsPositive() {
        assertTrue(northern.isNorthernHemisphere());
    }

    @Test
    public void testIsNorthernHemisphereWhenLatitudeIsNegative() {
        assertFalse(southern.isNorthernHemisphere());
    }

    @Test
    public void testIsSouthernHemisphereWhenLatitudeIsZero() {
        assertFalse(nullIsland.isSouthernHemisphere());
    }

    @Test
    public void testIsSouthernHemisphereWhenLatitudeIsPositive() {
        assertFalse(northern.isSouthernHemisphere());
    }

    @Test
    public void testIsSouthernHemisphereWhenLatitudeIsNegative() {
        assertTrue(southern.isSouthernHemisphere());
    }
}
