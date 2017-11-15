package com.zzl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zzl.bean.User;

public interface UserRepository extends JpaRepository<User,Long>{
	User findByuserName(String name);
	User findByidentifiesCard(String identifiesCard);
	@Query("update User v set integral= :Integral where v.id= :id")
	@Modifying
	void UserUpdateIntegral(@Param("Integral")Integer Integral,@Param("id")Long id);

	@Query("update User v set vipClass= :vipClass where v.id= :id")
	@Modifying
	void UserUpdatevipClass(@Param("vipClass")Integer vipClass,@Param("id")Long id);
}
