package com.wisecoach.entity.co;

import com.owlike.genson.annotation.JsonProperty;

import java.util.Objects;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午3:31
 * {@code @version:} 1.0.0
 */
public final class AssetCO {

    private final String assetID;

    private final String color;

    private final int size;

    private final String owner;

    private final int appraisedValue;

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

    public AssetCO(@JsonProperty("assetID") final String assetID, @JsonProperty("color") final String color,
                 @JsonProperty("size") final int size, @JsonProperty("owner") final String owner,
                 @JsonProperty("appraisedValue") final int appraisedValue) {
        this.assetID = assetID;
        this.color = color;
        this.size = size;
        this.owner = owner;
        this.appraisedValue = appraisedValue;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        AssetCO other = (AssetCO) obj;

        return Objects.deepEquals(
                new String[] {getAssetID(), getColor(), getOwner()},
                new String[] {other.getAssetID(), other.getColor(), other.getOwner()})
                &&
                Objects.deepEquals(
                        new int[] {getSize(), getAppraisedValue()},
                        new int[] {other.getSize(), other.getAppraisedValue()});
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssetID(), getColor(), getSize(), getOwner(), getAppraisedValue());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [assetID=" + assetID + ", color="
                + color + ", size=" + size + ", owner=" + owner + ", appraisedValue=" + appraisedValue + "]";
    }
}