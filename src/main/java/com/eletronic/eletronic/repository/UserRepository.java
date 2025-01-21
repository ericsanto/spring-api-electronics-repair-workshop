package com.eletronic.eletronic.repository;

import com.eletronic.eletronic.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
