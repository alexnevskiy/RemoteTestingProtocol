package com.polytech.protocol.model;

import java.nio.ByteBuffer;

public class Util {
    public static boolean getBit(byte b, int bitNumber) {
        return (b >> bitNumber & 1) == 1;
    }

    public static byte getByte(byte b, int startBitNumber, int endBitNumber) {
        byte mask = 0;
        for (int i = startBitNumber; i <= endBitNumber; i++) {
            mask += Math.pow(2, i);
        }
        return (byte) (b & mask);
    }

    public static short convertToShort(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer.getShort();
    }

    public static int convertToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer.getInt();
    }
}
