package com.polytech.protocol.remotetesting.model;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ResourceRecord {
    private short length = 0;
    private byte[] data = new byte[0];

    public ResourceRecord() {}

    public ResourceRecord(byte[] bytes) {
        length = Util.convertToShort(Arrays.copyOfRange(bytes, 0, 1));
        data = Arrays.copyOfRange(bytes, 2, length + 2);
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataString() {
        return new String(data, StandardCharsets.UTF_8);
    }
}
