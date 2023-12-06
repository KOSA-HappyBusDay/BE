package com.example.dto;

public class FacePictureResponse {

    private Long id;
    private byte[] forehead;
    private byte[] leftCheek;
    private byte[] rightCheek;
    private byte[] chin;

    public FacePictureResponse(Long id, byte[] forehead, byte[] leftCheek, byte[] rightCheek, byte[] chin) {
        this.id = id;
        this.forehead = forehead;
        this.leftCheek = leftCheek;
        this.rightCheek = rightCheek;
        this.chin = chin;
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
}
