package com.example.rgs;

public class Complaints {
    private String documentId;
    private String userid;
    private String landmark;
    private String address;
    private String complaint;
    private String problem;

    public Complaints() {

    }

    public Complaints(String userid, String landmark, String address, String complaint, String problem) {
        this.userid=userid;
        this.landmark=landmark;
        this.address=address;
        this.complaint=complaint;
        this.problem=problem;
    }

    public String getUserid() {
        return userid;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getAddress() {
        return address;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getProblem() {
        return problem;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }
}
