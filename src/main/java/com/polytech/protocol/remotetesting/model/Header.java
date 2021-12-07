package com.polytech.protocol.remotetesting.model;

import java.util.Arrays;

public class Header {
    private boolean cs = false;
    private byte mode = 0;
    private byte rCode = 0;
    private byte id = 0;
    private int rrCount = 0;

    public Header() {}

    public Header(byte[] bytes) {
        if (bytes.length < 6) {
            throw new IllegalArgumentException("The number of bytes does not match 6.");
        }

        cs = Util.getBit(bytes[0], 0);
        mode = Util.getByte(bytes[0], 1, 4);
        rCode = Util.getByte(bytes[0], 5, 7);
        id = bytes[1];
        rrCount = Util.convertToInt(Arrays.copyOfRange(bytes, 2, 5));
    }

    public boolean isServer() {
        return cs;
    }

    public void setCs(boolean cs) {
        this.cs = cs;
    }

    public byte getMode() {
        return mode;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public byte getRCode() {
        return rCode;
    }

    public void setRCode(byte rCode) {
        this.rCode = rCode;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public int getRrCount() {
        return rrCount;
    }

    public void setRrCount(int rrCount) {
        this.rrCount = rrCount;
    }
}
