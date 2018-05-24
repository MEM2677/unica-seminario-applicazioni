/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.api;

import javax.xml.bind.annotation.XmlElement;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;


public class BuspassResponseResult extends AbstractApiResponseResult {
    
    @Override
    @XmlElement(name = "buspass", required = false)
    public JAXBBuspass getResult() {
        return (JAXBBuspass) this.getMainResult();
    }
    
}