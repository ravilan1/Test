package utc.tmh.tester;


import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

public class MyDifferenceListener implements DifferenceListener {

	public MyDifferenceListener() {
		super();
	}
	
	@Override
	public int differenceFound(Difference arg0) {
		int rtvl = 0;  //default return value
		if (arg0.getControlNodeDetail().getValue().equalsIgnoreCase("@@@IGNORE_DIFFERENCE@@@")) {
			rtvl = 1;
			System.out.println(arg0.getTestNodeDetail().getValue());
			//System.out.println(arg0.getTestNodeDetail().getNode().getParentNode().getNodeName());
		}
		return rtvl;
	}

	@Override
	public void skippedComparison(Node arg0, Node arg1) {
		System.out.println(arg0.toString() + arg1.toString());

	}

}
