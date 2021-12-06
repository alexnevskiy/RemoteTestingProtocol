package com.polytech.protocol.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoteTestingMessage {
    private Header header = new Header();
    private List<ResourceRecord> resourceRecords = new ArrayList<>();

    public RemoteTestingMessage() {}

    public RemoteTestingMessage(byte[] bytes) {
        header = new Header(Arrays.copyOfRange(bytes, 0, 5));
        resourceRecords = getResourceRecordsFromByteArray(bytes);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<ResourceRecord> getResourceRecords() {
        return resourceRecords;
    }

    public void setResourceRecords(List<ResourceRecord> resourceRecords) {
        this.resourceRecords = resourceRecords;
    }

    private List<ResourceRecord> getResourceRecordsFromByteArray(byte[] bytes) {
        List<ResourceRecord> resourceRecordsList = new ArrayList<>(header.getRrCount());
        int position = 6;
        for (int i = 0; i < header.getRrCount(); i++) {
            ResourceRecord resourceRecord = new ResourceRecord(Arrays.copyOfRange(bytes, position, bytes.length));
            resourceRecordsList.add(resourceRecord);
            position += resourceRecord.getLength() + 2;
        }
        return resourceRecordsList;
    }
}
