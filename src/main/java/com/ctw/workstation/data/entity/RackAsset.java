package com.ctw.workstation.data.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "t_rack_asset")
public class RackAsset extends BaseEntity {

    @Column(name = "asset_tag")
    private String assetTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id")
    private Rack rack;

    public RackAsset() {
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

}