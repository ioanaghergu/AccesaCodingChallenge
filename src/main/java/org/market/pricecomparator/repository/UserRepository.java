package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void saveUserToDatabase(User user);
    List<User> getAllUsers();
}
