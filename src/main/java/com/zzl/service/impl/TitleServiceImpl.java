package com.zzl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zzl.bean.Title;
import com.zzl.bean.common.Common;
import com.zzl.repository.TitleRepository;
import com.zzl.service.TitleService;
@Service
public class TitleServiceImpl implements TitleService {
	@Autowired
	TitleRepository titleRepository;
	@Override
	public String saveTitle(Title title) {
		titleRepository.save(title);
		return Common.SUCCESS;
	}
	@Override
	public List<Title> queryTile(Pageable pageable) {
		Page<Title> result = titleRepository.findAll(pageable);
		return result.getContent();
	}
	@Override
	public Title findTitleById(Long id) {
		Title title = titleRepository.findOne(id);
		return title;
	}
	@Override
	public Title updateReadPersonById(Title title, Long id) {
		titleRepository.updatereadPerson(title.getReadPerson(), id);
		return title;
	}
}
