/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services;

import org.entando.entando.plugins.jpbuspass.aps.JpbuspassBaseTestCase;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.IBuspassManager;

public class TestBuspassManager extends JpbuspassBaseTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.init();
	}
	
	public void testGetBuspass() {
		//TODO complete test
		assertNotNull(this._buspassManager);
	}

	public void testGetBuspasss() {
		//TODO complete test
		assertNotNull(this._buspassManager);
	}
	
	public void testSearchBuspasss() {
		//TODO complete test
		assertNotNull(this._buspassManager);
	}

	public void testAddBuspass() {
		//TODO complete test
		assertNotNull(this._buspassManager);
	}

	public void testUpdateBuspass() {
		//TODO complete test
		assertNotNull(this._buspassManager);
	}

	public void testDeleteBuspass() {
		//TODO complete test
		assertNotNull(this._buspassManager);
	}
	
	private void init() {
		//TODO add the spring bean id as constant
		this._buspassManager = (IBuspassManager) this.getService("jpbuspassBuspassManager");
	}
	
	private IBuspassManager _buspassManager;
}

