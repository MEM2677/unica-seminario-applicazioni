/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.api;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.Buspass;

@XmlRootElement(name = "buspass")
@XmlType(propOrder = {"id", "name", "lname", "age", "type"})
public class JAXBBuspass {

    public JAXBBuspass() {
        super();
    }

    public JAXBBuspass(Buspass buspass) {
		this.setId(buspass.getId());
		this.setName(buspass.getName());
		this.setLname(buspass.getLname());
		this.setAge(buspass.getAge());
		this.setType(buspass.getType());
    }
    
    public Buspass getBuspass() {
    	Buspass buspass = new Buspass();
		buspass.setId(this.getId());
		buspass.setName(this.getName());
		buspass.setLname(this.getLname());
		buspass.setAge(this.getAge());
		buspass.setType(this.getType());
    	return buspass;
    }

	@XmlElement(name = "id", required = true)
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}

	@XmlElement(name = "name", required = true)
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}

	@XmlElement(name = "lname", required = true)
	public String getLname() {
		return _lname;
	}
	public void setLname(String lname) {
		this._lname = lname;
	}

	@XmlElement(name = "age", required = true)
	public int getAge() {
		return _age;
	}
	public void setAge(int age) {
		this._age = age;
	}

	@XmlElement(name = "type", required = true)
	public String getType() {
		return _type;
	}
	public void setType(String type) {
		this._type = type;
	}


	private int _id;
	private String _name;
	private String _lname;
	private int _age;
	private String _type;

}
