/*
 * Copyright (c) 2017 - 2019 Oswald G. Brown, III
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ogbrown.courses.service;


import com.ogbrown.courses.model.dto.MenuItemDto;
import com.ogbrown.courses.model.dto.PageDto;
import com.ogbrown.courses.repository.PageRepository;
import com.ogbrown.courses.service.converter.PageModelConverter;
import com.ogbrown.courses.service.helper.MenuViewHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service( "pageService" )
@Transactional(readOnly = true)
public class PageServiceImpl implements PageService {
	@Autowired
	MenuViewHelper menuViewHelper;

	@Autowired
	protected PageRepository pageRepository;

	public PageServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageDto readPage(Long pageId) {
		return PageModelConverter.toDto(pageRepository.findById(pageId).get());
	}

	@Override
	public List<PageDto> getPagesHavingNoParentPage() {
		return pageRepository.findByTopParentPages().stream().map(PageModelConverter::toDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public PageDto getPageForUriSlug(String urlSlug) {
		return PageModelConverter.toDto(pageRepository.findByUrlSlug(urlSlug));
	}
	

	@Override
	public List<PageDto> getPagesHavingNoCourseSession() {
		return pageRepository.findByCourseSessionsIsNull().stream().map(PageModelConverter::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<MenuItemDto> getSiteTopMenu() {
		return menuViewHelper.getTopMenuItems(getPagesHavingNoCourseSession());
	}

	@Override
	public List<MenuItemDto> getSiteBottomMenu() {
		return menuViewHelper.getBottomMenuItems(getPagesHavingNoCourseSession());
	}


}
