package com.example.demo.userrole;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, String> {

	Optional<UserInfo> findByUsername(String username);

}
