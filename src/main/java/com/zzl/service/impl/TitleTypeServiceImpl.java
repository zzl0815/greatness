package com.zzl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzl.bean.TitleType;
import com.zzl.bean.common.Common;
import com.zzl.repository.TitleTypeRepository;
import com.zzl.service.TitleTypeService;
@Service
public class TitleTypeServiceImpl implements TitleTypeService {
	@Autowired
	TitleTypeRepository titleTypeRepository;
	
	@Override
	public String saveTitleType(TitleType type) {
		TitleType title = titleTypeRepository.save(type);
		return title.getId()+"";
	}

	@Override
	public List<TitleType> findByUserId(Long userId) {
		return titleTypeRepository.findByuserId(userId);
	}
}
