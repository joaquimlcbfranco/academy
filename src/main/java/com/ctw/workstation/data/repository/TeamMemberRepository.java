package com.ctw.workstation.data.repository;

import com.ctw.workstation.data.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class TeamMemberRepository implements PanacheRepository<TeamMember> {
}
