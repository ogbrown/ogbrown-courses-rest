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


import com.ogbrown.courses.model.entity.PageEntity;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ITPageRepositoryTest {
	@Autowired
	private PageRepository pageRepository;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void findCommonPagesTest() {
		List<PageEntity> pages = pageRepository.findByUrlSlugContaining("common-");
		System.out.println("List of Pages: " + pages.toString().replaceAll("\\], Page \\[", "]\nPageDto ["));
		for (PageEntity p: pages) System.out.println("***************************\n" + p.getUrlSlug() + " Parents:" + (p.getParentPages().size()>0?p.getParentPages().get(0).getUrlSlug():"none"));
		assertTrue(pages.size() > 0);
	}
	
	@Test
	public void findPagesByListOfIdsTest() {
	    Long[] ids = new Long[] {9L,10L,11L,12L,13L,14L,15L,16L,17L,18L};
	    List<Long> idsList = Arrays.asList(ids);
//	    Sort sort = new Sort(Direction.ASC, "pageOrder");
	    Iterable<PageEntity> pages = pageRepository.findByIdInOrderByPageOrdAsc(idsList);
		System.out.println(pages);
	    assertTrue(pages != null);
		assertTrue(pages.iterator().hasNext());
	}

}
