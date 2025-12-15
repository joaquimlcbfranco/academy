package com.ctw.workstation.data.repository;

import com.ctw.workstation.data.entity.RackAsset;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class RackAssetRepository implements PanacheRepository<RackAsset> {

}
