package com.airfrance.technicaltest.dao;

import com.airfrance.technicaltest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
