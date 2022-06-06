package edu.ldj.planner.test;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

import edu.ldj.planner.task.EasyDate;
import edu.ldj.planner.task.Task;

public class TaskTest {
	Task t = new Task();

	@Before
	public void setUp() throws Exception {
		t = new Task(1, new EasyDate(2022, 2, 1), 2.0, "t1", "Details", "taskClass");
	}

	@Test
	public void testToFile() {
		String expected = String.format( "%d\t%d\t%d\t%d\t%d\t%s\t%d\t%s\t%f\t%d\t%s\n",
				/* 1 */ t.getPriority(),
				/* 2 */ t.getDueDate().getYear(),
				/* 3 */ t.getDueDate().getMonth(),
				/* 4 */ t.getDueDate().getDay(),
				/* 5 */ ( new StringTokenizer( t.getTaskName().trim(), " " ) ).countTokens(),
				/* 6 */ t.getTaskName().trim(),
				/* 7 */ ( new StringTokenizer( t.getTaskDetails().trim(), " " ) ).countTokens(),
				/* 8 */ t.getTaskDetails().trim(),
				/* 9 */ t.getTimeEstimate(),
				/* 10 */ ( new StringTokenizer( t.getTaskClass().trim(), " " ) ).countTokens(),
				/* 11 */ t.getTaskClass().trim() );
		assertEquals(expected, t.toFile());
	}

	@Test
	public void testToString() {
		t.setDueDate(new EasyDate(2022, 06, 03));
		t.setPriority(2);
		t.setTaskClass("Class");
		t.setTaskDetails("Details");
		t.setTaskName("Name");
		t.setTimeEstimate(3);
		
		String newName = t.getTaskName();
		if( newName.length() > 12 ) {
			newName = newName.substring( 0, 9 ) + "...";
		}
		String newDetails = t.getTaskDetails();
		if( newDetails.length() > 30 ) {
			newDetails = newDetails.substring( 0, 27 ) + "...";
		}
		String newClassName = t.getTaskClass();
		if( newClassName.length() > 12 ) {
			newClassName = newClassName.substring( 0, 9 ) + "...";
		}
		String doubleString = String.valueOf( t.getTimeEstimate() );
		if( doubleString.length() > 5 ) {
			doubleString = doubleString.substring( 0, 5 );
		}
		newDetails = newDetails.replace( "\n", " " );
		String exp = String.format( "%8d | %11s | %12s | %30s | %5s | %12s", t.getPriority(), t.getDueDate().toString(), newName, newDetails, doubleString, newClassName );
		assertEquals(t.toString(),exp);
		

	}
}
