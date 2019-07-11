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


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "page")
public class PageEntity implements Cloneable, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	@Column(unique = true)
	private String urlSlug;
	private String folderUrlSlug;
	private String metaDescription;
	private String contentHeader;
	@ElementCollection
	private Set<String> metaKeywords;
	@Column(columnDefinition="TEXT")
	private String content;
	private Boolean published = false;
	private Boolean protectedPage = false;
	@Column(columnDefinition="SMALLINT")
	private Short pageOrd;
	@ManyToMany(targetEntity = PageEntity.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "children_parents_pages", joinColumns = { @JoinColumn(name = "childid") }, inverseJoinColumns = { @JoinColumn(name = "parentid") })
	private List<PageEntity> parentPages;
	@ManyToMany(targetEntity = PageEntity.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "parents_children_pages", joinColumns = { @JoinColumn(name = "childid") }, inverseJoinColumns = { @JoinColumn(name = "parentid") })
	private List<PageEntity> childPages;
	private String prevPageLinkOverride;
	@Transient
	private PageEntity previousPage;
	private String nextPageLinkOverride;
	@Transient
	private PageEntity nextPage;
	@ManyToMany(targetEntity = CourseSessionEntity.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "coursesessions_pages", joinColumns = { @JoinColumn(name = "pageid") }, inverseJoinColumns = { @JoinColumn(name = "courseid"), @JoinColumn(name = "sessionnumber")})
	private Set<CourseSessionEntity> courseSessions;
	private String notes;


}
