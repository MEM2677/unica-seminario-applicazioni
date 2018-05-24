/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps;


import org.entando.entando.plugins.jpbuspass.JpbuspassConfigTestUtils;

import com.agiletec.ConfigTestUtils;
import com.agiletec.aps.BaseTestCase;

public class JpbuspassBaseTestCase extends BaseTestCase {

	@Override
	protected ConfigTestUtils getConfigUtils() {
		return new JpbuspassConfigTestUtils();
	}

	
}
