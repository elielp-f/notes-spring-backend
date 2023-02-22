package com.notes.notes.repositories;

import com.notes.notes.modals.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, String> {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);
}
