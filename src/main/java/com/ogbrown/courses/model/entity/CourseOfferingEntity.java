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

package com.ogbrown.courses.model.entity;

import com.ogbrown.courses.model.dto.CourseOfferingStatusDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "course_offering")
public class CourseOfferingEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int courseNumber;
	private int termNumber;
	private String term;
	private String location;
	private String room;
	private LocalDate start;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate end;
	private String daysOfWeek;
	private int sessionCount;
	private float contEdHours;
	@ManyToMany(targetEntity = InstructorEntity.class, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "courseoffering_instructors")
	@JoinColumns({
        @JoinColumn(name="COURSEOFFERING_ID", referencedColumnName="ID"),
        @JoinColumn(name="INSTRUCTOR_ID", referencedColumnName="ID")
    })
	private List<InstructorEntity> instructors;
	private Integer seats = 16;
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private CourseOfferingStatusDto courseOfferingStatus;
	//Optional: Few use cases... one is for the schedule...
	@Transient
	private DayOfWeek[] daysOfWeekArray;
	//Optional: Few use cases... one is for the schedule...
    @Transient
    private String daysOfWeekCsv="";


    @Override
	public String toString() {
		return "CourseOfferingDto [id=" + id + ", courseNumber=" + courseNumber + ", termNumber=" + termNumber + ", term="
				+ term + ", location=" + location + ", room=" + room + ", start=" + start + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", end=" + end + ", daysOfWeek=" + daysOfWeek + ", sessionCount="
				+ sessionCount + ", contEdHours=" + contEdHours + ", instructors=" + instructors + ", seats="
				+ seats + ", status=" + courseOfferingStatus + "]";
	}



	

}
