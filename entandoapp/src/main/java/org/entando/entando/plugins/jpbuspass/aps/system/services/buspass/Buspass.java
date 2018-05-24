/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass;



public class Buspass {

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

	
	private int _id;
	private String _name;
	private String _lname;
	private int _age;
	private String _type;

}
