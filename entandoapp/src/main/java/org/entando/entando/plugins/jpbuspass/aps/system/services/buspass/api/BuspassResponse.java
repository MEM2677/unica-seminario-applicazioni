/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponse;
import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;


@XmlRootElement(name = "response")
public class BuspassResponse extends AbstractApiResponse {
    
    @Override
    @XmlElement(name = "result", required = true)
    public BuspassResponseResult getResult() {
        return (BuspassResponseResult) super.getResult();
    }
    
    @Override
    protected AbstractApiResponseResult createResponseResultInstance() {
        return new BuspassResponseResult();
    }
    
}