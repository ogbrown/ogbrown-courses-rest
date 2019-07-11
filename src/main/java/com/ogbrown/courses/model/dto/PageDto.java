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

package com.ogbrown.courses.model.dto;

import com.ogbrown.courses.model.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PageDto implements Page, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String urlSlug;
	private String folderUrlSlug;
	private String metaDescription;
	private String contentHeader;
	private String metaKeywordsCsv;
	private String content;
	private boolean published = false;
	private boolean protectedPage = false;
	private short pageOrd;
	private List<PageDto> parentPages;
	private List<PageDto> childPages;
	private String prevPageLinkOverride;
	private PageDto previousPage;
	private String nextPageLinkOverride;
	private PageDto nextPage;
	private Set<CourseSessionDto> courseSessions;
	private String notes;

	public PageDto(Page p) {
		id = p.getId();
		title = p.getTitle();
		urlSlug = p.getUrlSlug();
		folderUrlSlug = p.getFolderUrlSlug();
		metaDescription = p.getMetaDescription();
		contentHeader = p.getContentHeader();
		metaKeywordsCsv = p.getMetaKeywordsCsv();
		content = p.getContent();
		published = p.isPublished();
		protectedPage = p.isProtectedPage();
		pageOrd = p.getPageOrd();
		parentPages = p.getParentPages();
		childPages = p.getChildPages();
		prevPageLinkOverride = p.getPrevPageLinkOverride();
		previousPage = p.getPreviousPage();
		nextPageLinkOverride = p.getNextPageLinkOverride();
		nextPage = p.getNextPage();
		courseSessions = p.getCourseSessions();
		notes = p.getNotes();
	}
	public void publish() {
		published = true;
	}
	public void draft() {
		published = false;
	}

	@Override
	public String toString() {
		String startOfString = "PageDto [id=" + id + ", title=" + title + ", urlSlug=" + urlSlug + ", folderUrlSlug=" + folderUrlSlug +
		        ", metaDescription=" + metaDescription 
				+ ", contentHeader=" + contentHeader + ", metaKeywords=[" + metaKeywordsCsv + "], content=" + content
				+ ", protectedPage=" + protectedPage + ", published=" + published + ", pageOrd=" + pageOrd + ", parentPages=" + parentPages
				+ ", childPages=" + childPages + ", prevPageLinkOverride=" + prevPageLinkOverride + ", previousPage=";
		if (this.previousPage == null) {
			startOfString += "null";
		} else {
			startOfString += this.previousPage.getContentHeader();
		};
		String middleOfString =  ", nextPageLinkOverride=" + nextPageLinkOverride + ", nextPage=";
		if (this.nextPage == null) {
			middleOfString += "null";
		} else {
			middleOfString += this.nextPage.getContentHeader();
		};
		String endOfString = ", courseSessions=" + courseSessions + ", notes=" + notes + "]";
		return startOfString + middleOfString + endOfString;
	}

	
}
