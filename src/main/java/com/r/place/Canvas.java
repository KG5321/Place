package com.r.place;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Canvas {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer width;
    private Integer height;
    byte[] data;

    public Long getId() {
        return id;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public byte getPixel(int x, int y) {
        return data[ (y * getWidth() + x)*4];
    }

    public byte[] getData() {
        return data;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWidth(Integer width) {

        this.width = width;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPixel(int x, int y, byte r, byte b, byte g) {
        data[ (y * getWidth() + x)*4 + 0] = r;
        data[ (y * getWidth() + x)*4 + 1] = g;
        data[ (y * getWidth() + x)*4 + 2] = b;
        data[ (y * getWidth() + x)*4 + 3] = (byte) 0xff;
    }

    protected Canvas() {}

    public Canvas(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.data = new byte[width * height*4];
    }

    @Override
    public String toString() {
        return String.format(
                "Canvas[id=%d, width='%d', height='%d', datalen='%d']",
                id, width, height, data.length);
    }

}
