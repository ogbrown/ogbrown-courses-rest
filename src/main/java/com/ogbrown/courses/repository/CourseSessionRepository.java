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
import com.ogbrown.courses.model.entity.CourseSessionEntity;
import com.ogbrown.courses.model.entity.CourseSessionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true, timeout = 1)
public interface CourseSessionRepository extends JpaRepository<CourseSessionEntity, CourseSessionPk> {

	@Override
	@Transactional(timeout = 10, readOnly = true)
	public Optional<CourseSessionEntity> findById(CourseSessionPk courseSessionPk);
	
	List<CourseSessionEntity> findByCourse(CourseEntity course);

	@Query(value="SELECT pageid FROM coursesessions_pages csp WHERE csp.courseid = (:courseId) AND csp.sessionnumber = (:sessionNumber)",
			nativeQuery=true )
	public Iterable<BigInteger> findPageIdByCourseAndSessionNumber(@Param("courseId") Long courseId, @Param("sessionNumber") Short sessionNumber);
		
}
