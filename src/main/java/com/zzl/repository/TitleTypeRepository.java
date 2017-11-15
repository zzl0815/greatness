package com.zzl.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zzl.bean.TitleType;
public interface TitleTypeRepository extends JpaRepository<TitleType,Long> {
	List<TitleType> findByuserId(Long userId);
}
