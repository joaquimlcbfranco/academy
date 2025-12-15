package com.ctw.workstation.data.repository;

import com.ctw.workstation.data.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * RackDatabase to store the rack data
 */
@ApplicationScoped
public final class RackRepository implements PanacheRepository<Rack> {


}
