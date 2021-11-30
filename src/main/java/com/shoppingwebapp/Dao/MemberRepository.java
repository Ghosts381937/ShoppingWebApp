package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Model.Member;
import org.springframework.data.repository.CrudRepository;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MemberRepository extends CrudRepository<Member, Integer> {
    Iterable<Member> findByUsername(String username);
}
