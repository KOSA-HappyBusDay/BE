package com.example.dto;

import java.time.LocalDate;

public class FacePictureResponse {

    private Long id;
    private byte[] forehead;
    private byte[] leftCheek;
    private byte[] rightCheek;
    private byte[] chin;
    private String AImessage;
    private LocalDate localDate;


    public FacePictureResponse(Long id, byte[] forehead, byte[] leftCheek, byte[] rightCheek, byte[] chin,
                               String AImessage, LocalDate localDate) {
        this.id = id;
        this.forehead = forehead;
        this.leftCheek = leftCheek;
        this.rightCheek = rightCheek;
        this.chin = chin;
        this.AImessage = AImessage;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getForehead() {
        return forehead;
    }

    public void setForehead(byte[] forehead) {
        this.forehead = forehead;
    }

    public byte[] getLeftCheek() {
        return leftCheek;
    }

    public void setLeftCheek(byte[] leftCheek) {
        this.leftCheek = leftCheek;
    }

    public byte[] getRightCheek() {
        return rightCheek;
    }

    public void setRightCheek(byte[] rightCheek) {
        this.rightCheek = rightCheek;
    }

    public byte[] getChin() {
        return chin;
    }

    public void setChin(byte[] chin) {
        this.chin = chin;
    }

    public String getAImessage() {
        return AImessage;
    }

    public void setAImessage(String AImessage) {
        this.AImessage = AImessage;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "FacePictureResponse{" +
                "id=" + id +
                ", AImessage='" + AImessage + '\'' +
                ", localDate=" + localDate +
                // Include other fields if necessary
                '}';
    }

}