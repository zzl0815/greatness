package com.zzl.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.zzl.bean.Title;

public interface TitleService {
	String saveTitle(Title title);
	List<Title> queryTile(Pageable pageable);
	Title findTitleById(Long id);
	Title updateReadPersonById(Title title,Long id );
}
