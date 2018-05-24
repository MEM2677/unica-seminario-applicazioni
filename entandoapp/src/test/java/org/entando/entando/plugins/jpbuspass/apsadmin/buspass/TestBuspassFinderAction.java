/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.apsadmin.buspass;

import java.util.HashMap;
import java.util.Map;
import org.entando.entando.plugins.jpbuspass.apsadmin.JpbuspassApsAdminBaseTestCase;
import com.opensymphony.xwork2.Action;

public class TestBuspassFinderAction extends JpbuspassApsAdminBaseTestCase {
	
	public void testList() throws Throwable {
		String result = this.executeAction("list", null);
		assertEquals(Action.SUCCESS, result);
	}

	public void testSearch() throws Throwable {
		//TODO complete test
		Map<String, String> params = new HashMap<String, String>();
		String result = this.executeAction("search", params);
		assertEquals(Action.SUCCESS, result);
	}
	
	private String executeAction(String action, Map<String, String> params) throws Throwable {
		this.setUserOnSession("admin");
		this.initAction(NS, action);
		if (null != params) {
			this.addParameters(params);
		}
		String result = this.executeAction();
		return result;
	}

	private static final String NS = "/do/jpbuspass/Buspass";

}
