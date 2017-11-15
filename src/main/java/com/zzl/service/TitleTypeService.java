package com.zzl.service;

import java.util.List;

import com.zzl.bean.TitleType;

public interface TitleTypeService {
	String saveTitleType(TitleType type);
	List<TitleType> findByUserId(Long userId);
}
