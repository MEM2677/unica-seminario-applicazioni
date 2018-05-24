/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.agiletec.aps.system.common.AbstractSearcherDAO;
import com.agiletec.aps.system.common.FieldSearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuspassDAO extends AbstractSearcherDAO implements IBuspassDAO {

	private static final Logger _logger =  LoggerFactory.getLogger(BuspassDAO.class);

	@Override
	protected String getTableFieldName(String metadataFieldKey) {
		return metadataFieldKey;
	}
	
	@Override
	protected String getMasterTableName() {
		return "jpbuspass_buspass";
	}
	
	@Override
	protected String getMasterTableIdFieldName() {
		return "id";
	}
	
	@Override
	protected boolean isForceCaseInsensitiveLikeSearch() {
		return true;
	}

	@Override
	public List<Integer> searchBuspasss(FieldSearchFilter[] filters) {
		List buspasssId = null;
		try {
			buspasssId  = super.searchId(filters);
		} catch (Throwable t) {
			_logger.error("error in searchBuspasss",  t);
			throw new RuntimeException("error in searchBuspasss", t);
		}
		return buspasssId;
	}

	@Override
	public List<Integer> loadBuspasss() {
		List<Integer> buspasssId = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			conn = this.getConnection();
			stat = conn.prepareStatement(LOAD_BUSPASSS_ID);
			res = stat.executeQuery();
			while (res.next()) {
				int id = res.getInt("id");
				buspasssId.add(id);
			}
		} catch (Throwable t) {
			_logger.error("Error loading Buspass list",  t);
			throw new RuntimeException("Error loading Buspass list", t);
		} finally {
			closeDaoResources(res, stat, conn);
		}
		return buspasssId;
	}
	
	@Override
	public void insertBuspass(Buspass buspass) {
		PreparedStatement stat = null;
		Connection conn  = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			this.insertBuspass(buspass, conn);
 			conn.commit();
		} catch (Throwable t) {
			this.executeRollback(conn);
			_logger.error("Error on insert buspass",  t);
			throw new RuntimeException("Error on insert buspass", t);
		} finally {
			this.closeDaoResources(null, stat, conn);
		}
	}

	public void insertBuspass(Buspass buspass, Connection conn) {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(ADD_BUSPASS);
			int index = 1;
			stat.setInt(index++, buspass.getId());
 			stat.setString(index++, buspass.getName());
 			stat.setString(index++, buspass.getLname());
			stat.setInt(index++, buspass.getAge());
 			if(StringUtils.isNotBlank(buspass.getType())) {
				stat.setString(index++, buspass.getType());				
			} else {
				stat.setNull(index++, Types.VARCHAR);
			}
			stat.executeUpdate();
		} catch (Throwable t) {
			_logger.error("Error on insert buspass",  t);
			throw new RuntimeException("Error on insert buspass", t);
		} finally {
			this.closeDaoResources(null, stat, null);
		}
	}

	@Override
	public void updateBuspass(Buspass buspass) {
		PreparedStatement stat = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			this.updateBuspass(buspass, conn);
 			conn.commit();
		} catch (Throwable t) {
			this.executeRollback(conn);
			_logger.error("Error updating buspass {}", buspass.getId(),  t);
			throw new RuntimeException("Error updating buspass", t);
		} finally {
			this.closeDaoResources(null, stat, conn);
		}
	}

	public void updateBuspass(Buspass buspass, Connection conn) {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE_BUSPASS);
			int index = 1;

 			stat.setString(index++, buspass.getName());
 			stat.setString(index++, buspass.getLname());
			stat.setInt(index++, buspass.getAge());
 			if(StringUtils.isNotBlank(buspass.getType())) {
				stat.setString(index++, buspass.getType());				
			} else {
				stat.setNull(index++, Types.VARCHAR);
			}
			stat.setInt(index++, buspass.getId());
			stat.executeUpdate();
		} catch (Throwable t) {
			_logger.error("Error updating buspass {}", buspass.getId(),  t);
			throw new RuntimeException("Error updating buspass", t);
		} finally {
			this.closeDaoResources(null, stat, null);
		}
	}

	@Override
	public void removeBuspass(int id) {
		PreparedStatement stat = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			this.removeBuspass(id, conn);
 			conn.commit();
		} catch (Throwable t) {
			this.executeRollback(conn);
			_logger.error("Error deleting buspass {}", id, t);
			throw new RuntimeException("Error deleting buspass", t);
		} finally {
			this.closeDaoResources(null, stat, conn);
		}
	}
	
	public void removeBuspass(int id, Connection conn) {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(DELETE_BUSPASS);
			int index = 1;
			stat.setInt(index++, id);
			stat.executeUpdate();
		} catch (Throwable t) {
			_logger.error("Error deleting buspass {}", id, t);
			throw new RuntimeException("Error deleting buspass", t);
		} finally {
			this.closeDaoResources(null, stat, null);
		}
	}

	public Buspass loadBuspass(int id) {
		Buspass buspass = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			conn = this.getConnection();
			buspass = this.loadBuspass(id, conn);
		} catch (Throwable t) {
			_logger.error("Error loading buspass with id {}", id, t);
			throw new RuntimeException("Error loading buspass with id " + id, t);
		} finally {
			closeDaoResources(res, stat, conn);
		}
		return buspass;
	}

	public Buspass loadBuspass(int id, Connection conn) {
		Buspass buspass = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			stat = conn.prepareStatement(LOAD_BUSPASS);
			int index = 1;
			stat.setInt(index++, id);
			res = stat.executeQuery();
			if (res.next()) {
				buspass = this.buildBuspassFromRes(res);
			}
		} catch (Throwable t) {
			_logger.error("Error loading buspass with id {}", id, t);
			throw new RuntimeException("Error loading buspass with id " + id, t);
		} finally {
			closeDaoResources(res, stat, null);
		}
		return buspass;
	}

	protected Buspass buildBuspassFromRes(ResultSet res) {
		Buspass buspass = null;
		try {
			buspass = new Buspass();				
			buspass.setId(res.getInt("id"));
			buspass.setName(res.getString("name"));
			buspass.setLname(res.getString("lname"));
			buspass.setAge(res.getInt("age"));
			buspass.setType(res.getString("type"));
		} catch (Throwable t) {
			_logger.error("Error in buildBuspassFromRes", t);
		}
		return buspass;
	}

	private static final String ADD_BUSPASS = "INSERT INTO jpbuspass_buspass (id, name, lname, age, type ) VALUES (?, ?, ?, ?, ? )";

	private static final String UPDATE_BUSPASS = "UPDATE jpbuspass_buspass SET  name=?,  lname=?,  age=?, type=? WHERE id = ?";

	private static final String DELETE_BUSPASS = "DELETE FROM jpbuspass_buspass WHERE id = ?";
	
	private static final String LOAD_BUSPASS = "SELECT id, name, lname, age, type  FROM jpbuspass_buspass WHERE id = ?";
	
	private static final String LOAD_BUSPASSS_ID  = "SELECT id FROM jpbuspass_buspass";
	
}