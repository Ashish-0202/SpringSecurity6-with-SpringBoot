package com.ashish.SpringSecuirtyEx.Repository;

import com.ashish.SpringSecuirtyEx.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<users,Integer> {

    users findByUsername(String username);
}
