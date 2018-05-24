/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.event;

import com.agiletec.aps.system.common.notify.ObserverService;

public interface BuspassChangedObserver extends ObserverService {
	
	public void updateFromBuspassChanged(BuspassChangedEvent event);
	
}
