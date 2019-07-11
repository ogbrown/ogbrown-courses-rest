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

package com.ogbrown.courses.service.helper;

import com.ogbrown.courses.model.dto.MenuItemDto;
import com.ogbrown.courses.model.dto.PageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;


@Component("menuViewHelper")
public class MenuViewHelperImpl implements MenuViewHelper {
	final static Logger logger = LoggerFactory.getLogger(MenuViewHelperImpl.class);
	public final String DEFAULT_LINK_TITLE = "SbOGB.com Courses";
	public final String DEFAULT_LINK_CONTENT_HEADER = "All Courses";
	public final String DEFAULT_LINK_URL_SLUG = "";
	public final short DEFAULT_LINK_MENU_ORDER = (short) 0;
	public final Long DEFAULT_LINK_ID = 0L;

	@Override
	public List<MenuItemDto> getMenuItems(List<PageDto> pages) {
		List<MenuItemDto> menuItemDtos = new ArrayList<MenuItemDto>();
		// MenuItemDto menuItem = new MenuItemDto(pages.get(0));
		for (PageDto p : pages) {
			menuItemDtos.add(new MenuItemDto(p));
		}
		Collections.sort(menuItemDtos, new Comparator<MenuItemDto>() {

			@Override
			public int compare(MenuItemDto o1, MenuItemDto o2) {
				return o1.getMenuOrder() - o2.getMenuOrder();
			}
		});
		return menuItemDtos;
	}

	@Override
	public List<MenuItemDto> getTopMenuItems(List<PageDto> pages) {
		List<MenuItemDto> menuItemDtos = getMenuItems(pages);
		Iterator<MenuItemDto> menuItemIterator = menuItemDtos.iterator();
		while (menuItemIterator.hasNext()) {
			MenuItemDto m = menuItemIterator.next();
			if (m.getUrlSlug().isEmpty()) {
				menuItemIterator.remove();
				break;
			}
		}
		return menuItemDtos;
	}
	@Override
	public List<MenuItemDto> getBottomMenuItems(List<PageDto> pages) {
		List<MenuItemDto> menuItemDtos = getMenuItems(pages);
		MenuItemDto topOfPage = new MenuItemDto();
		topOfPage.setUrlSlug("#page");
		topOfPage.setTitle("Back to top of page");
		topOfPage.setContentHeader("Top");
		topOfPage.setMenuOrder((short)0);
		menuItemDtos.add(0,topOfPage);
		Iterator<MenuItemDto> menuItemIterator = menuItemDtos.iterator();
		while (menuItemIterator.hasNext()) {
			MenuItemDto m = menuItemIterator.next();
			if (m.getUrlSlug().isEmpty()) {
				menuItemIterator.remove();
				continue;
			} else if (m.getUrlSlug().equalsIgnoreCase("contact-us")) {
				menuItemIterator.remove();
				continue;
			} else if (m.getUrlSlug().equalsIgnoreCase("about-us")) {
				menuItemIterator.remove();
				continue;
			}
		}
		return menuItemDtos;
	}

	@Override
	public MenuItemDto getParentLink(String courseUrl, PageDto pagePersistedObj) {
		MenuItemDto parentLink = null;
		if (null == pagePersistedObj.getParentPages() || pagePersistedObj.getParentPages().size() == 0) {
			parentLink = new MenuItemDto();
			logger.trace(pagePersistedObj.getTitle() + " has " + pagePersistedObj.getParentPages().size()
					+ " parent links.");
			parentLink.setTitle(DEFAULT_LINK_TITLE);
			parentLink.setContentHeader(DEFAULT_LINK_CONTENT_HEADER);
			parentLink.setUrlSlug(DEFAULT_LINK_URL_SLUG);
			parentLink.setMenuOrder(DEFAULT_LINK_MENU_ORDER);
			parentLink.setId(DEFAULT_LINK_ID);
		} else {
			parentLink = new MenuItemDto(pagePersistedObj.getParentPages().get(0));
//			if (courseUrl.contains("/" + parentLink.getUrlSlug() + "/")) {
//				parentLink.setUrlSlug(courseUrl.substring(0, courseUrl.length() - 1));
//			} else {
//				
//				parentLink.setUrlSlug(courseUrl + "/" + parentLink.getUrlSlug());
//			}
			List<PageDto> tmpPages = pagePersistedObj.getParentPages();
			MenuItemDto tmpParentLink = parentLink;
			String linkRelativeToParent = "";
			while (tmpPages != null) {
				linkRelativeToParent = "/" + tmpParentLink.getUrlSlug() + linkRelativeToParent;
				tmpPages = tmpPages.get(0).getParentPages();
				if (tmpPages != null) {
					tmpParentLink = new MenuItemDto(tmpPages.get(0));
				}
			}
			parentLink.setUrlSlug(courseUrl + linkRelativeToParent + "/");
		}
		return parentLink;
	}

	@Override
	public MenuItemDto getPreviousLink(String courseUrl, PageDto pageDataObj) {
		MenuItemDto leftLink = null;
		if (null == pageDataObj.getPreviousPage()) {
			return null;
		} else {
			leftLink = new MenuItemDto(pageDataObj.getPreviousPage());
		}
		if (null != courseUrl) {
			leftLink.setUrlSlug(getParentLink(courseUrl, pageDataObj).getUrlSlug() + leftLink.getUrlSlug() + "/");
		}
		return leftLink;
	}

	@Override
	public MenuItemDto getNextLink(String courseUrl, PageDto pageDataObj) {
		MenuItemDto rightLink = null;
		if (null == pageDataObj.getNextPage()) {
			return null;
		} else {
			rightLink = new MenuItemDto(pageDataObj.getNextPage());
		}
		if (null != courseUrl) {
			rightLink.setUrlSlug(
					getParentLink(courseUrl, pageDataObj).getUrlSlug() + rightLink.getUrlSlug() + "/");
		}
		return rightLink;
	}

	@Override
	public List<MenuItemDto> getChildPagesMenuItems(PageDto page) {
		List<MenuItemDto> menuItemDtos = new ArrayList<MenuItemDto>();
		// MenuItemDto menuItem = new MenuItemDto(pages.get(0));
		for (PageDto p : page.getChildPages()) {
			menuItemDtos.add(new MenuItemDto(p));
		}
		if (menuItemDtos.size() > 1) {
			Collections.sort(menuItemDtos, new Comparator<MenuItemDto>() {

				@Override
				public int compare(MenuItemDto o1, MenuItemDto o2) {
					return o1.getMenuOrder() - o2.getMenuOrder();
				}
			});
		}
		return menuItemDtos;
	}
}
