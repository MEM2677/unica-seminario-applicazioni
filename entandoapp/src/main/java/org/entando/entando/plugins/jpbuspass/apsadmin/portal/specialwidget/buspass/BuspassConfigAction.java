/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.apsadmin.portal.specialwidget.buspass;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.agiletec.aps.system.services.lang.Lang;
import com.agiletec.apsadmin.portal.specialwidget.SimpleWidgetConfigAction;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.IBuspassManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuspassConfigAction extends SimpleWidgetConfigAction {

	private static final Logger _logger =  LoggerFactory.getLogger(BuspassConfigAction.class);
	
	protected String extractInitConfig() {
		String result = super.extractInitConfig();
		String id = this.getWidget().getConfig().getProperty("id");
		if (StringUtils.isNotBlank(id)) {
			this.setId(new Integer(id));
		}
		return result;
	}

	public List<Integer> getBuspasssId() {
		try {
			List<Integer> buspasss = this.getBuspassManager().searchBuspasss(null);
			return buspasss;
		} catch (Throwable t) {
			_logger.error("error in getBuspasssId", t);
			throw new RuntimeException("Error getting buspasss list", t);
		}
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}

	protected IBuspassManager getBuspassManager() {
		return _buspassManager;
	}
	public void setBuspassManager(IBuspassManager buspassManager) {
		this._buspassManager = buspassManager;
	}

	private int _id;
	private IBuspassManager _buspassManager;
}

