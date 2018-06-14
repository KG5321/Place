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

    protected Canvas() {}

    public Canvas(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format(
                "Canvas[id=%d, width='%d', height='%d']",
                id, width, height);
    }

}
