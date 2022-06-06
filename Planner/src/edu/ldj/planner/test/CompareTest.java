package edu.ldj.planner.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ldj.planner.compare.CompareClass;
import edu.ldj.planner.compare.CompareDescription;
import edu.ldj.planner.compare.CompareDueDate;
import edu.ldj.planner.compare.CompareName;
import edu.ldj.planner.compare.ComparePriority;
import edu.ldj.planner.compare.CompareReverseClass;
import edu.ldj.planner.compare.CompareReverseDescription;
import edu.ldj.planner.compare.CompareReverseDueDate;
import edu.ldj.planner.compare.CompareReverseName;
import edu.ldj.planner.compare.CompareReversePriority;
import edu.ldj.planner.compare.CompareReverseTime;
import edu.ldj.planner.compare.CompareTime;
import edu.ldj.planner.compare.Filter;
import edu.ldj.planner.task.EasyDate;
import edu.ldj.planner.task.Task;

public class CompareTest {
	Task t1;
	Task t2;

	@Before
	public void setUp() throws Exception {
		t1 = new Task();
		t2 = new Task();
	}

	@Test
	public void testCompareClass() {
		CompareClass<Task> compare = new CompareClass<Task>();
		t1.setTaskClass("Class");
		t2.setTaskClass("Class");
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTaskClass("Clas");
		assertTrue(compare.compare(t1, t2) >= 1);
		t2.setTaskClass("Classs");
		assertTrue(compare.compare(t1, t2) < 0);
	}

	@Test
	public void testCompareReverseClass() {
		CompareReverseClass<Task> compare = new CompareReverseClass<Task>();
		t1.setTaskClass("Class");
		t2.setTaskClass("Class");
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTaskClass("Clas");
		assertTrue(compare.compare(t1, t2) < 0);
		t2.setTaskClass("Classs");
		assertTrue(compare.compare(t1, t2) >= 1);
	}

	@Test
	public void testCompareDescription() {
		CompareDescription<Task> compare = new CompareDescription<Task>();
		t1.setTaskDetails("Details");
		t2.setTaskDetails("Details");
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTaskDetails("Detail");
		assertTrue(compare.compare(t1, t2) >= 1);
		t2.setTaskDetails("Detailss");
		assertTrue(compare.compare(t1, t2) < 0);
	}

	@Test
	public void testCompareReverseDescription() {
		CompareReverseDescription<Task> compare = new CompareReverseDescription<Task>();
		t1.setTaskDetails("Details");
		t2.setTaskDetails("Details");
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTaskDetails("Detail");
		assertTrue(compare.compare(t1, t2) < 0);
		t2.setTaskDetails("Detailss");
		assertTrue(compare.compare(t1, t2) >= 1);
	}

	@Test
	public void testCompareDueDate() {
		CompareDueDate<Task> compare = new CompareDueDate<Task>();
		t1.setDueDate(new EasyDate(2022, 03, 06));
		t2.setDueDate(new EasyDate(2022, 03, 06));
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setDueDate(new EasyDate(2022, 03, 05));
		assertTrue(compare.compare(t1, t2) >= 1);
		t2.setDueDate(new EasyDate(2022, 03, 07));
		assertTrue(compare.compare(t1, t2) < 0);

		assertEquals(compare.compareDate(t1, new EasyDate(2022, 03, 06)), 0);
		assertTrue(compare.compareDate(t1, new EasyDate(2022, 03, 05)) >= 1);
		assertTrue(compare.compareDate(t1, new EasyDate(2022, 03, 07)) < 0);
	}

	@Test
	public void testCompareReverseDueDate() {
		CompareReverseDueDate<Task> compare = new CompareReverseDueDate<Task>();
		t1.setDueDate(new EasyDate(2022, 03, 06));
		t2.setDueDate(new EasyDate(2022, 03, 06));
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setDueDate(new EasyDate(2022, 03, 05));
		assertTrue(compare.compare(t1, t2) < 0);
		t2.setDueDate(new EasyDate(2022, 03, 07));
		assertTrue(compare.compare(t1, t2) >= 1);
	}

	@Test
	public void testCompareName() {
		CompareName<Task> compare = new CompareName<Task>();
		t1.setTaskName("Name");
		t2.setTaskName("Name");
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTaskName("Nam");
		assertTrue(compare.compare(t1, t2) >= 1);
		t2.setTaskName("Namee");
		assertTrue(compare.compare(t1, t2) < 0);
	}

	@Test
	public void testCompareReverseName() {
		CompareReverseName<Task> compare = new CompareReverseName<Task>();
		t1.setTaskName("Name");
		t2.setTaskName("Name");
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTaskName("Nam");
		assertTrue(compare.compare(t1, t2) < 0);
		t2.setTaskName("Namee");
		assertTrue(compare.compare(t1, t2) >= 1);
	}

	@Test
	public void testComparePriority() {
		ComparePriority<Task> compare = new ComparePriority<Task>();
		t1.setPriority(1);
		t2.setPriority(1);
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setPriority(0);
		assertTrue(compare.compare(t1, t2) >= 1);
		t2.setPriority(2);
		assertTrue(compare.compare(t1, t2) < 0);
	}

	@Test
	public void testCompareReversePriority() {
		CompareReversePriority<Task> compare = new CompareReversePriority<Task>();
		t1.setPriority(1);
		t2.setPriority(1);
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setPriority(0);
		assertTrue(compare.compare(t1, t2) < 0);
		t2.setPriority(2);
		assertTrue(compare.compare(t1, t2) >= 1);
	}

	@Test
	public void testCompareTime() {
		CompareTime<Task> compare = new CompareTime<Task>();
		t1.setTimeEstimate(1);
		t2.setTimeEstimate(1);
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTimeEstimate(0);
		assertTrue(compare.compare(t1, t2) >= 1);
		t2.setTimeEstimate(2);
		assertTrue(compare.compare(t1, t2) < 0);
	}

	@Test
	public void testCompareReverseTime() {
		CompareReverseTime<Task> compare = new CompareReverseTime<Task>();
		t1.setTimeEstimate(1);
		t2.setTimeEstimate(1);
		assertEquals(compare.compare(t1, t2), 0, 0);
		t2.setTimeEstimate(0);
		assertTrue(compare.compare(t1, t2) < 0);
		t2.setTimeEstimate(2);
		assertTrue(compare.compare(t1, t2) >= 1);
	}

	@Test
	public void testFilter() {
		Filter filter = new Filter();
		assertTrue(filter.meetsFilter(t1));
	}

	@Test
	public void testFilterPriority() {
		Filter filter = new Filter();
		t1.setPriority(0);
		filter.setPriorityMin("1");
		assertFalse(filter.meetsFilter(t1));
		filter.setPriorityMax("-1");
		assertFalse(filter.meetsFilter(t1));

	}
	@Test
	public void testFilterDuration() {
		Filter filter = new Filter();
		t1.setTimeEstimate(1);
		filter.setDurationMin("2");
		assertFalse(filter.meetsFilter(t1));
		filter.setDurationMax("0");
		assertFalse(filter.meetsFilter(t1));

	}
	@Test
	public void testFilterName() {
		Filter filter = new Filter();
		t1.setTaskName("Name");
		filter.setNameContains("Aaaa");
		assertFalse(filter.meetsFilter(t1));
		filter.setNameNotContains("a");
		assertFalse(filter.meetsFilter(t1));

	}
	@Test
	public void testFilterDescription() {
		Filter filter = new Filter();
		t1.setTaskDetails("Desc");
		filter.setDescriptionContains("Aaaa");
		assertFalse(filter.meetsFilter(t1));
		filter.setDescriptionNotContains("Des");
		assertFalse(filter.meetsFilter(t1));

	}
	@Test
	public void testFilterClass() {
		Filter filter = new Filter();
		t1.setTaskClass("Class");
		filter.setClassContains("Aaaa");
		assertFalse(filter.meetsFilter(t1));
		filter.setClassNotContains("ss");
		assertFalse(filter.meetsFilter(t1));

	}
	@Test
	public void testFilterDate() {
		Filter filter = new Filter();
		t1.setDueDate(new EasyDate(2022,11,10));
		filter.setDateSetting(false);
		filter.setNumDaysAhead("40");
		assertFalse(filter.meetsFilter(t1));

		filter.setDateSetting(true);
		filter.setDateAfter(new EasyDate(2023,02,01));
		assertFalse(filter.meetsFilter(t1));
		filter.setDateBefore(new EasyDate(2021,01,01));
		assertFalse(filter.meetsFilter(t1));

	}

}
