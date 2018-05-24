/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass;

import java.util.List;
import com.agiletec.aps.system.exception.ApsSystemException;

import com.agiletec.aps.system.common.FieldSearchFilter;

public interface IBuspassManager {

	public Buspass getBuspass(int id) throws ApsSystemException;

	public List<Integer> getBuspasss() throws ApsSystemException;

	public List<Integer> searchBuspasss(FieldSearchFilter filters[]) throws ApsSystemException;

	public void addBuspass(Buspass buspass) throws ApsSystemException;

	public void updateBuspass(Buspass buspass) throws ApsSystemException;

	public void deleteBuspass(int id) throws ApsSystemException;

}