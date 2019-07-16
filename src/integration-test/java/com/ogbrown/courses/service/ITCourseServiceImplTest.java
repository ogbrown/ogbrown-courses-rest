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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogbrown.courses.model.dto.BasicCourseOfferingInfoDto;
import com.ogbrown.courses.model.dto.CourseDto;
import com.ogbrown.courses.model.dto.PageDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ITCourseServiceImplTest {
    @Autowired
    CourseService service;

    @Lazy
    @Autowired
    ObjectMapper mapper;

    @Test
    public void testCourseSome2() {
        CourseDto course = service.getCourse("some2");
        String output;

        try {
            output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(course);
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("Some Course 2", course.getTitle());
        assertEquals("SOME2-456", course.getCurrentCourseLabel());
        assertEquals("tbb-fname", course.getCurrentInstructor().getFirstName());
        assertEquals("Beginning Book", course.getTextBooks().iterator().next().getTitle());
        assertEquals("some1", course.getPrerequisitesShortNames().toLowerCase());
        assertTrue(course.getLessonPlanMap().values().iterator().next().iterator().next().getLesson().contains("lesson plan"));
        assertEquals(LocalDate.of(2017,10,24),course.getCourseOfferings().iterator().next().getStart());

    }
    @Test
    public void testBasicCourseInfoSome2() {
        List<BasicCourseOfferingInfoDto> courses = service.getBasicCoursesInfo();
        String output;

        try {
            output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(courses);
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        assertEquals("Some Course 2", courses.getTitle());
//        assertEquals("SOME2-456", courses.getCurrentCourseLabel());
//        assertEquals("tbb-fname", courses.getCurrentInstructor().getFirstName());
//        assertEquals("Beginning Book", courses.getTextBooks().iterator().next().getTitle());
//        assertEquals("some1", courses.getPrerequisitesShortNames().toLowerCase());
//        assertTrue(courses.getLessonPlanMap().values().iterator().next().iterator().next().getLesson().contains("lesson plan"));
//        assertEquals(LocalDate.of(2017,10,24),courses.getCourseOfferings().iterator().next().getStart());

    }
}
