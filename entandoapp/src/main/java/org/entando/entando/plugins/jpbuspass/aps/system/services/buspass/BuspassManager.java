/*
*
* <Your licensing text here>
*
*/
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass;

import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.event.BuspassChangedEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.api.JAXBBuspass;
import org.entando.entando.aps.system.services.api.IApiErrorCodes;
import org.entando.entando.aps.system.services.api.model.ApiException;

import com.agiletec.aps.system.common.FieldSearchFilter;
import com.agiletec.aps.system.common.AbstractService;
import com.agiletec.aps.system.exception.ApsSystemException;
import com.agiletec.aps.system.services.keygenerator.IKeyGeneratorManager;
import it.unica.entandoapp.aps.system.services.busapi.IBusPassServiceAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuspassManager extends AbstractService implements IBuspassManager {
    
    private static final Logger _logger =  LoggerFactory.getLogger(BuspassManager.class);
    
    private String JUNIOR = "JUNIOR";
    private String ADULT = "STANDARD";
    
    @Override
    public void init() throws Exception {
        _logger.debug("{} ready.", this.getClass().getName());
    }
    
    @Override
    public Buspass getBuspass(int id) throws ApsSystemException {
        Buspass buspass = null;
        try {
            buspass = this.getBuspassDAO().loadBuspass(id);
        } catch (Throwable t) {
            _logger.error("Error loading buspass with id '{}'", id,  t);
            throw new ApsSystemException("Error loading buspass with id: " + id, t);
        }
        
        try {
            
            if (apiService.isJuniorPass(buspass.getAge())) {
                buspass.setType(JUNIOR);
            } else {
                buspass.setType(ADULT);
            }
        } catch (Throwable t) {
            _logger.error("failed to determine the type of the bus pass");
            if (null != buspass) {
                buspass.setType("UNDETERMINED");
            }
        }
        return buspass;
    }
    
    @Override
    public List<Integer> getBuspasss() throws ApsSystemException {
        List<Integer> buspasss = new ArrayList<Integer>();
        try {
            buspasss = this.getBuspassDAO().loadBuspasss();
        } catch (Throwable t) {
            _logger.error("Error loading Buspass list",  t);
            throw new ApsSystemException("Error loading Buspass ", t);
        }
        return buspasss;
    }
    
    @Override
    public List<Integer> searchBuspasss(FieldSearchFilter filters[]) throws ApsSystemException {
        List<Integer> buspasss = new ArrayList<Integer>();
        try {
            buspasss = this.getBuspassDAO().searchBuspasss(filters);
        } catch (Throwable t) {
            _logger.error("Error searching Buspasss", t);
            throw new ApsSystemException("Error searching Buspasss", t);
        }
        return buspasss;
    }
    
    @Override
    public void addBuspass(Buspass buspass) throws ApsSystemException {
        try {
            int key = this.getKeyGeneratorManager().getUniqueKeyCurrentValue();
            buspass.setId(key);
            this.getBuspassDAO().insertBuspass(buspass);
            this.notifyBuspassChangedEvent(buspass, BuspassChangedEvent.INSERT_OPERATION_CODE);
        } catch (Throwable t) {
            _logger.error("Error adding Buspass", t);
            throw new ApsSystemException("Error adding Buspass", t);
        }
    }
    
    @Override
    public void updateBuspass(Buspass buspass) throws ApsSystemException {
        try {
            this.getBuspassDAO().updateBuspass(buspass);
            this.notifyBuspassChangedEvent(buspass, BuspassChangedEvent.UPDATE_OPERATION_CODE);
        } catch (Throwable t) {
            _logger.error("Error updating Buspass", t);
            throw new ApsSystemException("Error updating Buspass " + buspass, t);
        }
    }
    
    @Override
    public void deleteBuspass(int id) throws ApsSystemException {
        try {
            Buspass buspass = this.getBuspass(id);
            this.getBuspassDAO().removeBuspass(id);
            this.notifyBuspassChangedEvent(buspass, BuspassChangedEvent.REMOVE_OPERATION_CODE);
        } catch (Throwable t) {
            _logger.error("Error deleting Buspass with id {}", id, t);
            throw new ApsSystemException("Error deleting Buspass with id:" + id, t);
        }
    }
    
    
    /**
     * GET http://localhost:8080/<portal>/api/rs/en/buspasss?
     * @param properties
     * @return
     * @throws Throwable
     */
    public List<JAXBBuspass> getBuspasssForApi(Properties properties) throws Throwable {
        List<JAXBBuspass> list = new ArrayList<JAXBBuspass>();
        List<Integer> idList = this.getBuspasss();
        if (null != idList && !idList.isEmpty()) {
            Iterator<Integer> buspassIterator = idList.iterator();
            while (buspassIterator.hasNext()) {
                int currentid = buspassIterator.next();
                Buspass buspass = this.getBuspass(currentid);
                if (null != buspass) {
                    list.add(new JAXBBuspass(buspass));
                }
            }
        }
        return list;
    }
    
    /**
     * GET http://localhost:8080/<portal>/api/rs/en/buspass?id=1
     * @param properties
     * @return
     * @throws Throwable
     */
    public JAXBBuspass getBuspassForApi(Properties properties) throws Throwable {
        String idString = properties.getProperty("id");
        int id = 0;
        JAXBBuspass jaxbBuspass = null;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new ApiException(IApiErrorCodes.API_PARAMETER_VALIDATION_ERROR, "Invalid Integer format for 'id' parameter - '" + idString + "'", Response.Status.CONFLICT);
        }
        Buspass buspass = this.getBuspass(id);
        if (null == buspass) {
            throw new ApiException(IApiErrorCodes.API_VALIDATION_ERROR, "Buspass with id '" + idString + "' does not exist", Response.Status.CONFLICT);
        }
        jaxbBuspass = new JAXBBuspass(buspass);
        return jaxbBuspass;
    }
    
    /**
     * POST Content-Type: application/xml http://localhost:8080/<portal>/api/rs/en/buspass
     * @param jaxbBuspass
     * @throws ApiException
     * @throws ApsSystemException
     */
    public void addBuspassForApi(JAXBBuspass jaxbBuspass) throws ApiException, ApsSystemException {
        if (null != this.getBuspass(jaxbBuspass.getId())) {
            throw new ApiException(IApiErrorCodes.API_VALIDATION_ERROR, "Buspass with id " + jaxbBuspass.getId() + " already exists", Response.Status.CONFLICT);
        }
        Buspass buspass = jaxbBuspass.getBuspass();
        this.addBuspass(buspass);
    }
    
    /**
     * PUT Content-Type: application/xml http://localhost:8080/<portal>/api/rs/en/buspass
     * @param jaxbBuspass
     * @throws ApiException
     * @throws ApsSystemException
     */
    public void updateBuspassForApi(JAXBBuspass jaxbBuspass) throws ApiException, ApsSystemException {
        if (null == this.getBuspass(jaxbBuspass.getId())) {
            throw new ApiException(IApiErrorCodes.API_VALIDATION_ERROR, "Buspass with id " + jaxbBuspass.getId() + " does not exist", Response.Status.CONFLICT);
        }
        Buspass buspass = jaxbBuspass.getBuspass();
        this.updateBuspass(buspass);
    }
    
    /**
     * DELETE http://localhost:8080/<portal>/api/rs/en/buspass?id=1
     * @param properties
     * @throws ApiException
     * @throws ApsSystemException
     */
    public void deleteBuspassForApi(Properties properties) throws Throwable {
        String idString = properties.getProperty("id");
        int id = 0;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new ApiException(IApiErrorCodes.API_PARAMETER_VALIDATION_ERROR, "Invalid Integer format for 'id' parameter - '" + idString + "'", Response.Status.CONFLICT);
        }
        this.deleteBuspass(id);
    }
    
    private void notifyBuspassChangedEvent(Buspass buspass, int operationCode) {
        BuspassChangedEvent event = new BuspassChangedEvent();
        event.setBuspass(buspass);
        event.setOperationCode(operationCode);
        this.notifyEvent(event);
    }
    
    
    protected IKeyGeneratorManager getKeyGeneratorManager() {
        return _keyGeneratorManager;
    }
    public void setKeyGeneratorManager(IKeyGeneratorManager keyGeneratorManager) {
        this._keyGeneratorManager = keyGeneratorManager;
    }
    
    public void setBuspassDAO(IBuspassDAO buspassDAO) {
        this._buspassDAO = buspassDAO;
    }
    protected IBuspassDAO getBuspassDAO() {
        return _buspassDAO;
    }
    
    public IBusPassServiceAPI getApiService() {
        return apiService;
    }
    
    public void setApiService(IBusPassServiceAPI apiService) {
        this.apiService = apiService;
    }
        
    private IBusPassServiceAPI apiService;
    
    private IKeyGeneratorManager _keyGeneratorManager;
    private IBuspassDAO _buspassDAO;
}
