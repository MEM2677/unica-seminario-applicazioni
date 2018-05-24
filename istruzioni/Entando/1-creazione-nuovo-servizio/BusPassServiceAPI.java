/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.entandoapp.aps.system.services.busapi;

import com.agiletec.aps.system.common.AbstractService;
import com.agiletec.aps.system.exception.ApsSystemException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.BuspassManager;
import org.entando.entando.plugins.jprestapi.aps.core.Endpoint;
import org.entando.entando.plugins.jprestapi.aps.core.IEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Entando
 */
public class BusPassServiceAPI extends AbstractService implements IBusPassServiceAPI {

    private static final Logger _logger =  LoggerFactory.getLogger(BuspassManager.class);
    
    // nome del parametro in request
    private String PARAM_AGE = "age";

    @Override
    public void init() throws Exception {
        
    }
    
    @Override
    public boolean isJuniorPass(int age) throws ApsSystemException {
        boolean result = false;
        
        try {
            // endpoint
            Endpoint endpoint = new Endpoint(IEndpoint.httpVerb.GET, 
                    "/isJuniorPass",
                    HttpStatus.SC_OK);
            // definizione dei parametri in request
            Map<String, String> requestParams = new HashMap<>(1);
            
            requestParams.put(PARAM_AGE, String.valueOf(age));
            
            String answer = new BusPassRequestBuilder()
                    .setEndpoint(endpoint)
                    .setRequestParams(requestParams)
                    .setDebug(true)
//                    .setTestMode(true)
                    .doRequest();
            result = StringUtils.isNotBlank(answer) && Boolean.valueOf(answer);
        } catch (Throwable t) {
            throw new ApsSystemException("Error detected contacting the microservice");
        }
        return result;
    }
}
