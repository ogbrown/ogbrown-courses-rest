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

package com.ogbrown.courses.api;

import com.ogbrown.courses.model.dto.CourseDto;
import com.ogbrown.courses.model.dto.MenuItemDto;
import com.ogbrown.courses.model.dto.PageDto;
import com.ogbrown.courses.service.GeneratePageService;
import com.ogbrown.courses.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/ogbrown-courses/pages")
public class ApplicationTopController {
	private final PageService pageService;
	private final GeneratePageService generatePageService;
//	@Autowired
//	private CoursePageHelper coursePageHelper;
//	@Autowired
//	@Qualifier( "courseMenuViewHelper" )
//	private MenuViewHelper courseMenuViewHelper;


	@RequestMapping("/page/{id}")
	public PageDto indexCtrl(@PathVariable("id") String id) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("index");
		List<CourseDto> courses = new ArrayList<>();//courseService.getCourses();
		PageDto page = generatePageService.getHomePage();
//		List<MenuItemDto> topMenu = pageService.getSiteTopMenu();
//		mav.addObject("topmenu", topMenu);
//		mav.addObject("page", page);
//		mav.addObject("courses", courses);
		return page;
	}

	@RequestMapping("/page/{id}/menu/{menuName}")
	public List<MenuItemDto> getMenuItems(@PathVariable("id") String pageUrl, @PathVariable("menuName") String menuName) {
		if (menuName.equalsIgnoreCase("sitetop")) {
			return pageService.getSiteTopMenu();
		}
		if (menuName.equalsIgnoreCase("sitefooter")) {
			return pageService.getSiteBottomMenu();
		}
		return pageService.getSiteTopMenu();
	}
//	@RequestMapping("/about-us/index.html")
//	public ModelAndView aboutus(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("page");
////		List<CourseDto> courses = courseService.getCourses();
//		String courseRequestUrl = request.getRequestURL().toString().replace("/about-us","");
//		PageEntity pageDataObj = pageService.getPageForUriSlug("about-us");
//		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
//		page.setTitle(page.getTitle());
//		List<PageEntity> menuPages = pageService.getPagesHavingNoCourseSession();
//		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
//		mav.addObject("topmenu", topMenu);
//		mav.addObject("page", page);
////		mav.addObject("courses", courses);
//		return mav;
//	}
//	@RequestMapping("/contact-us/index.html")
//	public ModelAndView contact(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("page");
//		String courseRequestUrl = request.getRequestURL().toString().replace("/contact-us","");
//
//		PageEntity pageDataObj = pageService.getPageForUriSlug("contact-us");
//		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
//		page.setTitle(page.getTitle());
//		List<PageEntity> menuPages = pageService.getPagesHavingNoCourseSession();
//		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
//		mav.addObject("topmenu", topMenu);
//		mav.addObject("page", page);
//		return mav;
//	}
//	@RequestMapping("/terms-and-privacy-policy/index.html")
//	public ModelAndView termsAndPolicy(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		String courseRequestUrl = request.getRequestURL().toString().replace("/terms-and-privacy-policy","");
//		mav.setViewName("page");
//		PageEntity pageDataObj = pageService.getPageForUriSlug("terms-and-privacy-policy");
//		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
//		page.setTitle(page.getTitle());
//		List<PageEntity> menuPages = pageService.getPagesHavingNoCourseSession();
//		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
//		mav.addObject("topmenu", topMenu);
//		mav.addObject("page", page);
//		return mav;
//	}



}
