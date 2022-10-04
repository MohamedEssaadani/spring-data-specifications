package com.essaadani.springspecifications.repositories;

import com.essaadani.springspecifications.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {
}
