package com.ctw.workstation.data.repository;

import com.ctw.workstation.data.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * TeamDatabase to store the team data
 */
@ApplicationScoped
public final class TeamRepository implements PanacheRepository<Team> {
}
