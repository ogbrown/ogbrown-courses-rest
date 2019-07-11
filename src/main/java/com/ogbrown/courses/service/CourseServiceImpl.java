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


import com.ogbrown.courses.model.dto.*;
import com.ogbrown.courses.repository.CourseRepository;
import com.ogbrown.courses.repository.CourseSessionRepository;
import com.ogbrown.courses.repository.InstructorRepository;
import com.ogbrown.courses.service.converter.BasicCourseOfferingInfoModelConverter;
import com.ogbrown.courses.service.converter.CourseModelConverter;
import com.ogbrown.courses.service.converter.CouseSessionModelConverter;
import com.ogbrown.courses.service.converter.InstructorModelConverter;
import com.ogbrown.courses.utility.time.DaysOfWeekUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
    private CourseSessionRepository courseSessionRepository;
    @Autowired
    private InstructorRepository instructorRepository;
	@Autowired
    private NoClassDateService noClassDateService;
	@PersistenceContext
    protected EntityManager entityManager;

	
	public CourseServiceImpl() {

    }
	@Transactional(readOnly = true)
	@Override
	public List<BasicCourseOfferingInfoDto> getBasicCoursesInfo() {
		List<BasicCourseOfferingInfoDto> allBasicCourseOfferings = new ArrayList<>();
		getCourses().stream().map(BasicCourseOfferingInfoModelConverter::toDto).collect(Collectors.toList())
				.forEach(bcoList -> allBasicCourseOfferings.addAll(bcoList));
		return allBasicCourseOfferings;
	}
    @Transactional(readOnly = true)
	@Override
	public List<CourseDto> getCourses() {
		List<CourseDto> allCourses = courseRepository.findAll().stream()
			.map(CourseModelConverter::toDto).collect(Collectors.toList());


		List<CourseDto> courses = allCourses.stream().filter(c1->!c1.getId().equals(CoursePageDto.ALL_COURSES))
				.collect(Collectors.toList());
		log.trace("{}",courses.toString());

		Iterator<CourseDto> courseIter = courses.iterator();
		while (courseIter.hasNext()) {
			CourseDto course = courseIter.next();
			if (course.getId() == CoursePageDto.ALL_COURSES) {
			    courseIter.remove();
			    continue;
			}
			
			sortCourseOfferings(course);
			course.setActive(true);
		}

		Collections.sort(courses, (c1, c2) -> {
			LocalDate c1Start = c1.getCourseOfferings().get(0).getStart();
			LocalDate c2Start = c2.getCourseOfferings().get(0).getStart();
			return c2Start.compareTo(c1Start);
		});
        List<CourseDto> activeCourses = new ArrayList<CourseDto>();
        List<CourseDto> futureCourses = new ArrayList<CourseDto>();
        List<CourseDto> pastCourses = new ArrayList<CourseDto>();
        List<CourseDto> inactiveCourses = new ArrayList<CourseDto>();
        for (CourseDto c: courses) {
            if (c.isActive() == true && courseInFuture(c) == true) {
                futureCourses.add(c);
            } else if (courseInPast(c) == true) {
                pastCourses.add(c);
            } else if (c.isActive() == true) {
                activeCourses.add(c);
            } else {
                inactiveCourses.add(c);
            }
        }
        Collections.reverse(futureCourses);
        courses = new ArrayList<CourseDto>();
        courses.addAll(activeCourses);
        courses.addAll(futureCourses);
        courses.addAll(pastCourses);
        courses.addAll(inactiveCourses);
		return courses;
	}
    private boolean courseInFuture(CourseDto c) {
        LocalDate compareToDate = LocalDate.now();
        if (c.getCourseOfferings().get(0).getStart().compareTo(compareToDate) > 0) {
            return true;
        }
        
        return false;
    }
    private boolean courseInPast(CourseDto c) {
        LocalDate compareToDate = LocalDate.now();
        if (c.getCourseOfferings().get(0).getEnd().compareTo(compareToDate) < 0) {
            return true;
        }
        
        return false;
    }
    private void sortCourseOfferings(CourseDto course) {
        List<CourseOfferingDto> courseOfferings;
        courseOfferings = course.getCourseOfferings();
        if (course.getCourseOfferings() != null && !course.getCourseOfferings().isEmpty()) {
			for (CourseOfferingDto courseOffering : courseOfferings) {
				courseOffering.setDaysOfWeekArray(DaysOfWeekUtility.getDaysOfWeekArray(courseOffering.getDaysOfWeek()));
			}
			Collections.sort(courseOfferings, (o1, o2) -> o2.getStart().compareTo(o1.getStart()));
			course.setCourseOfferings(courseOfferings);
		}
    }
	@Transactional(readOnly = true)
	@Override
	public Map<String, CourseDto> getCoursesMap() {
		List<CourseDto> courses = courseRepository.findAll().stream()
				.map(CourseModelConverter::toDto).collect(Collectors.toList());
		List<CourseOfferingDto> courseOfferings;
		Map<String, CourseDto> coursesMap = new HashMap<String,CourseDto>();
		Iterator<CourseDto> courseIter = courses.iterator();
		while (courseIter.hasNext()) {
			CourseDto course = courseIter.next();
			courseOfferings = course.getCourseOfferings();
			Collections.sort(courseOfferings, (o1, o2) -> o2.getStart().compareTo(o1.getStart()));
			course.setCourseOfferings(courseOfferings);
			coursesMap.put(course.getUrlSlug(), course);
		}
		entityManager.detach(courses);
		
		return coursesMap;
	}
	public CourseDto getCourse(Long id) {
		CourseDto course = CourseModelConverter.toDto(courseRepository.findById(id).get());
        return course;
    }
	@Transactional(readOnly = true)
	@Cacheable({"courses", "coursesessions", "courseofferings", "lessonplans"})
    @Override
	public CourseDto getCourse(String urlSlug) {
		CourseDto course = CourseModelConverter.toDto(courseRepository.findByUrlSlug(urlSlug));
	    sortCourseOfferings(course);
	    course.setCurrentSchedule(getCourseSchedule(course.getCurrentCourseOffering()));
	    if (course.getCurrentCourseOffering().getInstructors().size() == 0 ){
	        InstructorDto inst = InstructorModelConverter.toDto(instructorRepository.findById(InstructorDto.DEFAULT_INSTRUCTOR_ID).get());
	        course.getCurrentCourseOffering().getInstructors().add(inst);
	    }
	    SortedMap<Short, List<LessonPlanDto>> lessonPlanMap = new TreeMap<Short, List<LessonPlanDto>>();
	    List<CourseSessionDto> cslist = courseSessionRepository.findByCourse(CourseModelConverter.toEntity(course)).stream().map(CouseSessionModelConverter::toDto).collect(Collectors.toList());
	    for (CourseSessionDto cs : cslist) {
    	    List<LessonPlanDto> lessonPlan = cs.getLessonPlan();
    	    lessonPlanMap.put(cs.getSessionNumber(), lessonPlan);
	    }
	    course.setLessonPlanMap(lessonPlanMap);
	    
	    
	    return course;
	}
	

    @Transactional(readOnly = true)
    @Override
    public List<LocalDate> getCourseSchedule(CourseOfferingDto courseOffering) {
	    List<LocalDate> resultArray = new ArrayList<>();
	    courseOffering.setDaysOfWeekArray(DaysOfWeekUtility.getDaysOfWeekArray(courseOffering.getDaysOfWeek()));
	    int meetingDatesPerWeek = courseOffering.getDaysOfWeekArray().length;
	    resultArray.add(courseOffering.getStart());
	    DayOfWeek firstDayOfWeekOfCourse = courseOffering.getStart().getDayOfWeek();

	    for (int session=1; session < courseOffering.getSessionCount(); ) {
	        for (int meetingInWeek = 0; meetingInWeek < meetingDatesPerWeek; meetingInWeek++ ) {
	            if (meetingDatesPerWeek > 1) {
					while (courseOffering.getDaysOfWeekArray()[meetingInWeek] != firstDayOfWeekOfCourse && resultArray.size() == 1) {
						meetingInWeek++;
					}
					if (meetingDatesPerWeek > 1 && meetingInWeek < meetingDatesPerWeek && resultArray.size() == 1) {
						meetingInWeek++;
					}
				}
	            if (session == courseOffering.getSessionCount()) break;
	            LocalDate nextDate = getNextMeetingDate(resultArray.get(session - 1), courseOffering.getDaysOfWeekArray()[meetingInWeek]);
	            resultArray.add(nextDate);
	            session++;
	        }
	        
	    }
	    Iterator<LocalDate> iter = resultArray.iterator();
	    List<LocalDate> additionalDates = new ArrayList<LocalDate>();
	    int deleteDatesCount = 0;
	    while (iter.hasNext()) {
	        LocalDate checkDate = iter.next();
	        if (NoClassDateServiceImpl.containsDate(checkDate)) {
	            LocalDate lastDate = resultArray.get(resultArray.size()-1);
	            DayOfWeek lastDayOfWeek = lastDate.getDayOfWeek();
	            if (courseOffering.getDaysOfWeekArray().length == 1) {
	                additionalDates.add(getNextMeetingDate(lastDate, lastDayOfWeek));
	            }
	            deleteDatesCount++;
	        } else {
	            additionalDates.add(checkDate);
	        }
	        
	    }
	    Collections.sort(additionalDates);
	    if (! additionalDates.get(additionalDates.size()-1).equals(courseOffering.getEnd())) {
	        log.info("{} not equal to end date of {}. {} date deleted.", additionalDates.get(additionalDates.size()-1)
					, courseOffering.getEnd(), deleteDatesCount);
	    }
	    return additionalDates;
	}
	private LocalDate getNextMeetingDate(LocalDate classDate, DayOfWeek dayOfWeek) {
	    return classDate.with(TemporalAdjusters.next(dayOfWeek));
	}

}
