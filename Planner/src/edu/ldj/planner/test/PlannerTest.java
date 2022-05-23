package edu.ldj.planner.test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.ldj.planner.list.test.TaskListTest;
import edu.ldj.planner.task.test.EasyDateTest;
import edu.ldj.planner.task.test.TaskTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({TaskTest.class,
EasyDateTest.class, TaskListTest.class})

public class PlannerTest {

}

