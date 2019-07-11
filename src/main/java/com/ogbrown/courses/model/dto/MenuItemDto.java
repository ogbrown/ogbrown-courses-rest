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

import lombok.Data;

@Data
public class MenuItemDto {
	private long id;
	private String title;
	private String urlSlug;
	private String contentHeader;
	private Short menuOrder;
	private Short depth;
	private String type;

	

	public MenuItemDto() {
		this.title = "";
		this.urlSlug = "";
		this.contentHeader = "";
		this.type = "custom";
	}
	
	public MenuItemDto(PageDto p) {
		this.id = p.getId();
		this.title = p.getTitle();
		this.urlSlug = ((p.getFolderUrlSlug()!=null?(p.getFolderUrlSlug()+"/"):"") + p.getUrlSlug());
		this.contentHeader = p.getContentHeader();
		this.menuOrder = p.getPageOrd();
		this.type = "page";
	}

}
