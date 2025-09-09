package com.nhy.urlshortner.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Base62generatorTest {

    @Test
    void testGenerateRandom_withPositiveLength() {
        int length = 8;
        String randomString = Base62generator.generateRandom(length);
        assertEquals(length, randomString.length(), "The generated string should have the specified length.");
    }

    @Test
    void testGenerateRandom_containsOnlyBase62Chars() {
        String randomString = Base62generator.generateRandom(12);
        // Regex to check if the string contains only alphanumeric characters
        assertTrue(randomString.matches("^[a-zA-Z0-9]+$"), "The string should only contain Base62 characters (a-z, A-Z, 0-9).");
    }

    @Test
    void testGenerateRandom_withNegativeLength() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base62generator.generateRandom(-1);
        });
        assertEquals("length cannot be negative.", exception.getMessage(), "Exception message should match the expected text.");
    }

    @Test
    void testGenerateRandom_producesDifferentStrings() {
        // It's statistically improbable for two calls to produce the same string
        String string1 = Base62generator.generateRandom(10);
        String string2 = Base62generator.generateRandom(10);
        assertNotEquals(string1, string2, "Two separate calls should generate different random strings.");
    }
}
