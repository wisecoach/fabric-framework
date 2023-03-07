package com.wisecoach.entity.co;

import java.io.Serializable;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午3:31
 * {@code @version:} 1.0.0
 */
public class AssetCO implements Serializable, Cloneable {
    private String assetID;

    private String color;

    private int size;

    private String owner;

    private int appraisedValue;

    public AssetCO(String assetID, String color, int size, String owner, int appraisedValue) {
        this.assetID = assetID;
        this.color = color;
        this.size = size;
        this.owner = owner;
        this.appraisedValue = appraisedValue;
    }

    public String getAssetID() {
        return assetID;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public String getOwner() {
        return owner;
    }

    public int getAppraisedValue() {
        return appraisedValue;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAppraisedValue(int appraisedValue) {
        this.appraisedValue = appraisedValue;
    }
}
