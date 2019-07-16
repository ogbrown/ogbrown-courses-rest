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

package com.ogbrown.courses.repository;

import com.ogbrown.courses.model.entity.CourseEntity;
import com.ogbrown.courses.model.entity.CourseLabelEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("jenkins")
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ITCourseLabelRespositoryTest {
    @Autowired
    private CourseLabelRepository repository;

    @Test
    public void testGetCourseLabel() {
        List<CourseLabelEntity> courseLabels = repository.findAll();
        courseLabels.forEach(c->System.out.println(c));
        assertTrue(courseLabels != null);
        assertTrue(courseLabels.size() > 0);
    }

    @Test
    public void testFindOneByCourseOrderByModifiedDesc() {
        CourseEntity course = CourseEntity.builder()
                .id(1L)
                .build();
        CourseLabelEntity entity = repository.findOneByCourseOrderByModifiedDesc(course);
        System.out.println(entity);
        assertEquals("SOME1-123", entity.getLabel());
        CourseEntity course2 = CourseEntity.builder()
                .id(3L)
                .build();
        entity = repository.findOneByCourseOrderByModifiedDesc(course2);
        System.out.println(entity);
        assertEquals("SOME2-456", entity.getLabel());
    }

}
