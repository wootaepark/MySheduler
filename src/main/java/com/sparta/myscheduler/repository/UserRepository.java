package com.sparta.myscheduler.repository;

import com.sparta.myscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

}
