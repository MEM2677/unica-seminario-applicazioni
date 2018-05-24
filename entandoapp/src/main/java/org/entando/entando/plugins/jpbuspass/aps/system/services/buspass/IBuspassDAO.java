/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass;

import java.util.List;

import com.agiletec.aps.system.common.FieldSearchFilter;

public interface IBuspassDAO {

	public List<Integer> searchBuspasss(FieldSearchFilter[] filters);
	
	public Buspass loadBuspass(int id);

	public List<Integer> loadBuspasss();

	public void removeBuspass(int id);
	
	public void updateBuspass(Buspass buspass);

	public void insertBuspass(Buspass buspass);
	

}