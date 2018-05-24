/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpbuspass.aps.system.services.buspass.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;
import org.entando.entando.aps.system.services.api.model.ListResponse;

@XmlSeeAlso({JAXBBuspass.class})
public class BuspassListResponseResult extends AbstractApiResponseResult {
    
    @XmlElement(name = "items", required = false)
    public ListResponse<JAXBBuspass> getResult() {
        if (this.getMainResult() instanceof Collection) {
            List<JAXBBuspass> buspasss = new ArrayList<JAXBBuspass>();
            buspasss.addAll((Collection<JAXBBuspass>) this.getMainResult());
            ListResponse<JAXBBuspass> entity = new ListResponse<JAXBBuspass>(buspasss) {};
            return entity;
        }
        return null;
    }

}