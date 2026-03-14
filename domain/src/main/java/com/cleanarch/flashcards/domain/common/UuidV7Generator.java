package com.cleanarch.flashcards.domain.common;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Utility class to generate UUID v7.
 * UUID v7 is time-ordered, which is better for database indexing.
 */
public final class UuidV7Generator {
    private static final SecureRandom RANDOM = new SecureRandom();

    private UuidV7Generator() {
    }

    public static UUID generate() {
        byte[] value = new byte[16];
        RANDOM.nextBytes(value);

        long timestamp = System.currentTimeMillis();

        // timestamp (48 bits)
        value[0] = (byte) ((timestamp >> 40) & 0xFF);
        value[1] = (byte) ((timestamp >> 32) & 0xFF);
        value[2] = (byte) ((timestamp >> 24) & 0xFF);
        value[3] = (byte) ((timestamp >> 16) & 0xFF);
        value[4] = (byte) ((timestamp >> 8) & 0xFF);
        value[5] = (byte) (timestamp & 0xFF);

        // version (4 bits) - 0111 (7)
        value[6] = (byte) ((value[6] & 0x0F) | 0x70);

        // variant (2 bits) - 10 (RFC 4122)
        value[8] = (byte) ((value[8] & 0x3F) | 0x80);

        long msb = 0;
        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (value[i] & 0xFF);
        }

        long lsb = 0;
        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (value[i] & 0xFF);
        }

        return new UUID(msb, lsb);
    }
}
