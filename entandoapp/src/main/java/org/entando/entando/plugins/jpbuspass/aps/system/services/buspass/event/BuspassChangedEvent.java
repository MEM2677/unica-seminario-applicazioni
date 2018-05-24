/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.event;

import com.agiletec.aps.system.common.IManager;
import com.agiletec.aps.system.common.notify.ApsEvent;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.Buspass;


public class BuspassChangedEvent extends ApsEvent {
	
	@Override
	public void notify(IManager srv) {
		((BuspassChangedObserver) srv).updateFromBuspassChanged(this);
	}
	
	@Override
	public Class getObserverInterface() {
		return BuspassChangedObserver.class;
	}
	
	public int getOperationCode() {
		return _operationCode;
	}
	public void setOperationCode(int operationCode) {
		this._operationCode = operationCode;
	}
	
	public Buspass getBuspass() {
		return _buspass;
	}
	public void setBuspass(Buspass buspass) {
		this._buspass = buspass;
	}

	private Buspass _buspass;
	private int _operationCode;
	
	public static final int INSERT_OPERATION_CODE = 1;
	public static final int REMOVE_OPERATION_CODE = 2;
	public static final int UPDATE_OPERATION_CODE = 3;

}
