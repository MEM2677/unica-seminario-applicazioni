/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.apsadmin.buspass;

import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.Buspass;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.IBuspassManager;



import com.agiletec.apsadmin.system.ApsAdminSystemConstants;
import com.agiletec.apsadmin.system.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuspassAction extends BaseAction {

	private static final Logger _logger =  LoggerFactory.getLogger(BuspassAction.class);

	public String newBuspass() {
		try {
			this.setStrutsAction(ApsAdminSystemConstants.ADD);
		} catch (Throwable t) {
			_logger.error("error in newBuspass", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String edit() {
		try {
			Buspass buspass = this.getBuspassManager().getBuspass(this.getId());
			if (null == buspass) {
				this.addActionError(this.getText("error.buspass.null"));
				return INPUT;
			}
			this.populateForm(buspass);
			this.setStrutsAction(ApsAdminSystemConstants.EDIT);
		} catch (Throwable t) {
			_logger.error("error in edit", t);
			return FAILURE;
		}
		return SUCCESS;
	}

	public String save() {
		try {
			Buspass buspass = this.createBuspass();
			int strutsAction = this.getStrutsAction();
			if (ApsAdminSystemConstants.ADD == strutsAction) {
				this.getBuspassManager().addBuspass(buspass);
			} else if (ApsAdminSystemConstants.EDIT == strutsAction) {
				this.getBuspassManager().updateBuspass(buspass);
			}
		} catch (Throwable t) {
			_logger.error("error in save", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String trash() {
		try {
			Buspass buspass = this.getBuspassManager().getBuspass(this.getId());
			if (null == buspass) {
				this.addActionError(this.getText("error.buspass.null"));
				return INPUT;
			}
			this.populateForm(buspass);
			this.setStrutsAction(ApsAdminSystemConstants.DELETE);
		} catch (Throwable t) {
			_logger.error("error in trash", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			if (this.getStrutsAction() == ApsAdminSystemConstants.DELETE) {
				this.getBuspassManager().deleteBuspass(this.getId());
			}
		} catch (Throwable t) {
			_logger.error("error in delete", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String view() {
		try {
			Buspass buspass = this.getBuspassManager().getBuspass(this.getId());
			if (null == buspass) {
				this.addActionError(this.getText("error.buspass.null"));
				return INPUT;
			}
			this.populateForm(buspass);
		} catch (Throwable t) {
			_logger.error("error in view", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	private void populateForm(Buspass buspass) throws Throwable {
		this.setId(buspass.getId());
		this.setName(buspass.getName());
		this.setLname(buspass.getLname());
		this.setAge(buspass.getAge());
		this.setType(buspass.getType());
	}
	
	private Buspass createBuspass() {
		Buspass buspass = new Buspass();
		buspass.setId(this.getId());
		buspass.setName(this.getName());
		buspass.setLname(this.getLname());
		buspass.setAge(this.getAge());
		buspass.setType(this.getType());
		return buspass;
	}
	

	public int getStrutsAction() {
		return _strutsAction;
	}
	public void setStrutsAction(int strutsAction) {
		this._strutsAction = strutsAction;
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
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

	public int getAge() {
		return _age;
	}
	public void setAge(int age) {
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
	
	private int _strutsAction;
	private int _id;
	private String _name;
	private String _lname;
	private int _age;
	private String _type;
	
	private IBuspassManager _buspassManager;
	
}