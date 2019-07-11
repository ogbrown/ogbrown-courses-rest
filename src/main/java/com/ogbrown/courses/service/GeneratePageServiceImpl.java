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

import com.ogbrown.courses.model.dto.PageDto;
import com.ogbrown.courses.repository.CourseRepository;
import com.ogbrown.courses.repository.CourseSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service( "generatePageService" )
public class GeneratePageServiceImpl extends PageServiceImpl implements GeneratePageService {
	public static Logger logger = LoggerFactory.getLogger(GeneratePageServiceImpl.class);
	@Autowired
	protected CourseRepository courseRepository;
	@Autowired
	protected CourseSessionRepository courseSessionRepository;
//	@PersistenceContext
//    protected EntityManager entityManager;
	
	public GeneratePageServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly = true)
	@Override
	public PageDto getHomePage() {
		return readPage(1L);
	}
	
//	@Transactional(readOnly = true)
//	@Override
//	public CoursePageDto getCoursePage(String urlSlug) {
//
//
//		CourseEntity course = courseRepository.findByUrlSlug(urlSlug);
//
//		CoursePageDto coursePage = new CoursePageDto();
////		PageDto homePage = getHomePage();
//
////		entityManager.detach(coursePage);
//		coursePage.setCourseId(course.getId());
//		coursePage.setTitle(course.getTitle());
//		coursePage.setContentHeader(course.getTitle());
//		coursePage.setContent("");
//		coursePage.setUrlSlug(urlSlug);
//		coursePage.setChildPages(getCourseSessionPages(course));
//		logger.trace(coursePage.toString().replace(",", ",\n"));
//		return coursePage;
//	}


//	@Override
//	public List<PageDto> getCourseSessionPages(CourseEntity course) {
//		CourseSessionPageDto courseSessionPage = null;
//		PageEntity previousPage = null;
////		PageDto nextPage = null;
//		Set<CourseSessionDto> courseSessions = null;
//		List<PageEntity> courseSessionPages = new ArrayList<PageEntity>();
//
//		CoursePageDto coursePage = new CoursePageDto();
//		coursePage.setCourseId(course.getId());
//		coursePage.setTitle(course.getTitle());
//		coursePage.setContentHeader(course.getTitle());
//		coursePage.setContent("");
//		coursePage.setUrlSlug(course.getUrlSlug());
//		List<PageEntity> parentPages = new ArrayList<PageEntity>();
//		parentPages.add(coursePage);
//
//		for (int i = 0; i < course.getNumberOfSessions(); i++) {
//			courseSessionPage = new CourseSessionPageDto();
//			courseSessions = new HashSet<CourseSessionDto>();
//			courseSessionPage.setCourseId(course.getId());
//			courseSessionPage.setSessionNum((short)(i+1));
//			courseSessionPage.setTitle(course.getShortTitle() + ": Session " + (i+1));
//			courseSessionPage.setContentHeader(course.getShortTitle() + ": Session " + (i+1) );
//			courseSessionPage.setUrlSlug(course.getUrlSlug() + "-session-" + (i+1));
//			courseSessionPage.setPageOrd((short)(i+1));
//			courseSessions.add(new CourseSessionDto(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
//			courseSessionPage.setCourseSessions(courseSessions);
//			if ( i > 0 ) {
//				courseSessionPage.setPreviousPage(previousPage);
//				courseSessionPages.get(i-1).setNextPage(courseSessionPage);
//			}
//			courseSessionPage.setParentPages(parentPages);
//			courseSessionPages.add(courseSessionPage);
//			previousPage = courseSessionPage;
//
//		}
//		courseSessionPage = new CourseSessionPageDto();
//		courseSessions = new HashSet<CourseSessionDto>();
//		courseSessionPage.setCourseId(course.getId());
//		courseSessionPage.setSessionNum((short)(course.getNumberOfSessions()+1));
//		courseSessionPage.setTitle(course.getShortTitle() + ": Additional Information");
//		courseSessionPage.setContentHeader(course.getShortTitle() + ": Addl Info");
//		courseSessionPage.setUrlSlug(course.getUrlSlug() + "-addl-info");
//		courseSessionPage.setPageOrd((short)(course.getNumberOfSessions()+1));
//		if ( course.getNumberOfSessions() > 0 ) {
//			courseSessionPage.setPreviousPage(previousPage);
//			courseSessionPages.get(courseSessionPages.size()-1).setNextPage(courseSessionPage);
//		}
//		courseSessions.add(new CourseSessionDto(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
//		courseSessionPage.setCourseSessions(courseSessions);
//		courseSessionPage.setParentPages(parentPages);
//		courseSessionPages.add(courseSessionPage);
//
//		return courseSessionPages;
//	}
//	@Override
//	public List<PageEntity> getCourseCommonPages(CourseEntity course) {
//		CourseSessionPageDto courseSessionPage = null;
//		PageEntity previousPage = null;
////		PageDto nextPage = null;
//		Set<CourseSessionDto> courseSessions = null;
//		List<PageEntity> courseSessionPages = new ArrayList<PageEntity>();
//
//		CoursePageDto coursePage = new CoursePageDto();
//		coursePage.setCourseId(course.getId());
//		coursePage.setTitle(course.getTitle());
//		coursePage.setContentHeader(course.getTitle());
//		coursePage.setContent("");
//		coursePage.setUrlSlug(course.getUrlSlug());
//		List<PageEntity> parentPages = new ArrayList<PageEntity>();
//		parentPages.add(coursePage);
//
//		for (int i = 0; i < course.getNumberOfSessions(); i++) {
//			courseSessionPage = new CourseSessionPageDto();
//			courseSessions = new HashSet<CourseSessionDto>();
//			courseSessionPage.setCourseId(course.getId());
//			courseSessionPage.setSessionNum((short)(i+1));
//			courseSessionPage.setTitle(course.getShortTitle() + ": Session " + (i+1));
//			courseSessionPage.setContentHeader(course.getShortTitle() + ": Session " + (i+1) );
//			courseSessionPage.setUrlSlug(course.getUrlSlug() + "-session-" + (i+1));
//			courseSessionPage.setPageOrd((short)(i+1));
//			courseSessions.add(new CourseSessionDto(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
//			courseSessionPage.setCourseSessions(courseSessions);
//			if ( i > 0 ) {
//				courseSessionPage.setPreviousPage(previousPage);
//				courseSessionPages.get(i-1).setNextPage(courseSessionPage);
//			}
//			courseSessionPage.setParentPages(parentPages);
//			courseSessionPages.add(courseSessionPage);
//			previousPage = courseSessionPage;
//
//		}
//		courseSessionPage = new CourseSessionPageDto();
//		courseSessions = new HashSet<CourseSessionDto>();
//		courseSessionPage.setCourseId(course.getId());
//		courseSessionPage.setSessionNum((short)(course.getNumberOfSessions()+1));
//		courseSessionPage.setTitle(course.getShortTitle() + ": Additional Information");
//		courseSessionPage.setContentHeader(course.getShortTitle() + ": Addl Info");
//		courseSessionPage.setUrlSlug(course.getUrlSlug() + "-addl-info");
//		courseSessionPage.setPageOrd((short)(course.getNumberOfSessions()+1));
//		if ( course.getNumberOfSessions() > 0 ) {
//			courseSessionPage.setPreviousPage(previousPage);
//			courseSessionPages.get(courseSessionPages.size()-1).setNextPage(courseSessionPage);
//		}
//		courseSessions.add(new CourseSessionDto(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
//		courseSessionPage.setCourseSessions(courseSessions);
//		courseSessionPage.setParentPages(parentPages);
//		courseSessionPages.add(courseSessionPage);
//
//		return courseSessionPages;
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public PageEntity getCourseSessionMenuPage(String courseUrl, int courseSessionNum) {
//
//	    List<Long> idsList = new ArrayList<Long>();
//        List<PageEntity> pages = new ArrayList<PageEntity>();
//        Sort sort = new Sort(Direction.ASC, "pageOrder");
//
//
//		CoursePageDto coursePage = getCoursePage(courseUrl);
//		CourseSessionPageDto courseSessionMenuPage = (CourseSessionPageDto) coursePage.getChildPages().get(courseSessionNum-1);
//		Iterable<BigInteger> pageIds = courseSessionRepository.findPageIdByCourseAndSessionNumber(coursePage.getCourseId(), (short)(courseSessionNum));
//		if (pageIds.iterator().hasNext() == true) {
//		    for (BigInteger bigInt : pageIds) idsList.add(bigInt.longValue());
//
//		    Iterable<PageEntity> pagesFound = pageRepository.findAll(idsList);
//
//		    for (PageEntity pageFound : pagesFound) pages.add(pageFound);
//		}
////		List<PageDto> pages = new ArrayList<PageDto>();
////		pages = pageRepository.findAll(idsList);
////		if (pages.size() > 1) {
////			Collections.sort(pages, new Comparator<PageDto>() {
////
////				@Override
////				public int compare(PageDto o1, PageDto o2) {
////					return o1.getPageOrder() - o2.getPageOrder();
////				}
////			});
////		}
//		for (int i = 0; i < pages.size(); i++) {
//			entityManager.detach(pages.get(i));
//			if (null == pages.get(i).getParentPages() ) {
//				List<PageEntity> parentPages = new ArrayList<PageEntity>();
//				parentPages.add(courseSessionMenuPage);
//				pages.get(i).setParentPages(parentPages);
//			} else if (pages.get(i).getParentPages().size() == 0) {
//				PageEntity p = courseSessionMenuPage.clone();
//				p.setCourseSessions(null);
//				pages.get(i).getParentPages().add(p);
//			}
//			if (i < pages.size()-1) {
//				pages.get(i).setNextPage(pages.get(i+1));
//			}
//			if (i > 1) {
//				pages.get(i).setPreviousPage(pages.get(i-1));
//			}
//		}
//		courseSessionMenuPage.setChildPages(pages);
//		return courseSessionMenuPage;
//	}
//
//
//	@Override
//	@Transactional(readOnly = true)
//	public PageEntity getCourseSessionPage(String courseUrl, int courseSessionNum, String urlSlug) {
//		CourseSessionPageDto courseSessionMenuPage = (CourseSessionPageDto) getCourseSessionMenuPage(courseUrl, courseSessionNum);
//		Map<String, PageEntity> urlSlugToPageMap = new HashMap<String, PageEntity>();
//		for (PageEntity p : courseSessionMenuPage.getChildPages()) {
//			urlSlugToPageMap.put(p.getUrlSlug(), p);
//		}
//		PageEntity page = urlSlugToPageMap.get(urlSlug);
//		return page;
//	}
//
//	@Override
//	public PageEntity getCourseAddlInfoPage(String courseUrl, String pageRequest) {
//		int lastPage = 9999; // arbitrary number to represent last sessionNumber
//		return getCourseSessionPage(courseUrl, lastPage, pageRequest);
//	}
	
}
