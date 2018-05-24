/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.init.servdb;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Buspass.TABLE_NAME)
public class Buspass {
	
	public Buspass() {}
	
	@DatabaseField(columnName = "id", 
		dataType = DataType.INTEGER, 
		 canBeNull=false, id = true)
	private int _id;
	
	@DatabaseField(columnName = "name", 
		dataType = DataType.STRING, 
		width=30,  canBeNull=false)
	private String _name;
	
	@DatabaseField(columnName = "lname", 
		dataType = DataType.STRING, 
		width=30,  canBeNull=false)
	private String _lname;
	
	@DatabaseField(columnName = "age", 
		dataType = DataType.INTEGER, 
		 canBeNull=false)
	private int _age;
	
	@DatabaseField(columnName = "type", 
		dataType = DataType.STRING, 
		width=30,  canBeNull= true)
	private String _type;
	

public static final String TABLE_NAME = "jpbuspass_buspass";
}
