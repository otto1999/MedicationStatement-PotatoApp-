package com.example.msapp;

import java.io.Serializable;
import java.util.Arrays;

public class MedicationStatement implements Serializable {
    private String id;
    private String partOf;
    private String basedOn;
    private String status;
    private String statusReason;
    private String subject;
    private String category;
    private String medication;
    private String InformationSource;
    private String reasonCode;
    private String reasonReference;
    private String note;

    public MedicationStatement(String id, String partOf, String basedOn, String status, String statusReason, String subject, String category, String medication, String informationSource, String reasonCode, String reasonReference, String note) {
        this.id = id;
        this.partOf = partOf;
        this.basedOn = basedOn;
        this.status = status;
        this.statusReason = statusReason;
        this.subject = subject;
        this.category = category;
        this.medication = medication;
        InformationSource = informationSource;
        this.reasonCode = reasonCode;
        this.reasonReference = reasonReference;
        this.note = note;
    }

    @Override
    public String toString() {
        return "MedicationStatement{" +
                "id='" + id + '\'' +
                ", partOf='" + partOf + '\'' +
                ", basedOn='" + basedOn + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", subject='" + subject + '\'' +
                ", category='" + category + '\'' +
                ", medication='" + medication + '\'' +
                ", InformationSource='" + InformationSource + '\'' +
                ", reasonCode='" + reasonCode + '\'' +
                ", reasonReference='" + reasonReference + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartOf() {
        return partOf;
    }

    public void setPartOf(String partOf) {
        this.partOf = partOf;
    }

    public String getBasedOn() {
        return basedOn;
    }

    public void setBasedOn(String basedOn) {
        this.basedOn = basedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getInformationSource() {
        return InformationSource;
    }

    public void setInformationSource(String informationSource) {
        InformationSource = informationSource;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonReference() {
        return reasonReference;
    }

    public void setReasonReference(String reasonReference) {
        this.reasonReference = reasonReference;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}