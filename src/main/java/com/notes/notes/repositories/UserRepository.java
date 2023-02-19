package com.notes.notes.repositories;

import com.notes.notes.modals.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
