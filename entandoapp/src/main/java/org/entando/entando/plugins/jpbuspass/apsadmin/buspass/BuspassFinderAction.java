/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.apsadmin.buspass;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.agiletec.aps.system.common.FieldSearchFilter;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.Buspass;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.IBuspassManager;
import com.agiletec.apsadmin.system.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuspassFinderAction extends BaseAction {

	private static final Logger _logger =  LoggerFactory.getLogger(BuspassFinderAction.class);

	public List<Integer> getBuspasssId() {
		try {
			FieldSearchFilter[] filters = new FieldSearchFilter[0];
			if (null != this.getId()) {
				//TODO add a constant into your IBuspassManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("id"), this.getId(), false);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (StringUtils.isNotBlank(this.getName())) {
				//TODO add a constant into your IBuspassManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("name"), this.getName(), true);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (StringUtils.isNotBlank(this.getLname())) {
				//TODO add a constant into your IBuspassManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("lname"), this.getLname(), true);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (null != this.getAge()) {
				//TODO add a constant into your IBuspassManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("age"), this.getAge(), false);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (StringUtils.isNotBlank(this.getType())) {
				//TODO add a constant into your IBuspassManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("type"), this.getType(), true);
				filters = this.addFilter(filters, filterToAdd);
			}
			List<Integer> buspasss = this.getBuspassManager().searchBuspasss(filters);
			return buspasss;
		} catch (Throwable t) {
			_logger.error("Error getting buspasss list", t);
			throw new RuntimeException("Error getting buspasss list", t);
		}
	}

	protected FieldSearchFilter[] addFilter(FieldSearchFilter[] filters, FieldSearchFilter filterToAdd) {
		int len = filters.length;
		FieldSearchFilter[] newFilters = new FieldSearchFilter[len + 1];
		for(int i=0; i < len; i++){
			newFilters[i] = filters[i];
		}
		newFilters[len] = filterToAdd;
		return newFilters;
	}

	public Buspass getBuspass(int id) {
		Buspass buspass = null;
		try {
			buspass = this.getBuspassManager().getBuspass(id);
		} catch (Throwable t) {
			_logger.error("Error getting buspass with id {}", id, t);
			throw new RuntimeException("Error getting buspass with id " + id, t);
		}
		return buspass;
	}


	public Integer getId() {
		return _id;
	}
	public void setId(Integer id) {
		this._id = id;
	}


	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}


	public String getLname() {
		return _lname;
	}
	public void setLname(String lname) {
		this._lname = lname;
	}


	public Integer getAge() {
		return _age;
	}
	public void setAge(Integer age) {
		this._age = age;
	}


	public String getType() {
		return _type;
	}
	public void setType(String type) {
		this._type = type;
	}


	protected IBuspassManager getBuspassManager() {
		return _buspassManager;
	}
	public void setBuspassManager(IBuspassManager buspassManager) {
		this._buspassManager = buspassManager;
	}
	
	private Integer _id;
	private String _name;
	private String _lname;
	private Integer _age;
	private String _type;
	private IBuspassManager _buspassManager;
}