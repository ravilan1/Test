package utc.tmh.tester;

import junit.framework.TestCase;

import org.custommonkey.xmlunit.Diff;

public class XmlunitTestCase extends TestCase {
	public void testConcatenate() throws Exception {

		final String control = "<a><b attr=\"abc\"></b><john attr=\"abc\">@@@IGNORE_DIFFERENCE@@@</john></a>";
		final String test = "<a><b attr=\"abc\"></b><john attr=\"abc\">c</john></a>";

		Diff myDiff = new Diff(control, test);
		myDiff.overrideDifferenceListener(new MyDifferenceListener());

		assertTrue(myDiff.toString(), myDiff.identical());
		assertTrue(myDiff.toString(), myDiff.similar());
	}

	public void testConcatenate2() throws Exception {

		final String control = "<a><b attr=\"abc\"></b><john attr=\"abc\">@@@IGNORE_DIFFERENCE@@@</john></a>";
		final String test = "<a><b attr=\"abc\"></b><john attr=\"abc\">c</john></a>";

		Diff myDiff = new Diff(control, test);
		myDiff.overrideDifferenceListener(new MyDifferenceListener());

		assertTrue(myDiff.toString(), myDiff.identical());
		assertTrue(myDiff.toString(), myDiff.similar());
	}
}
