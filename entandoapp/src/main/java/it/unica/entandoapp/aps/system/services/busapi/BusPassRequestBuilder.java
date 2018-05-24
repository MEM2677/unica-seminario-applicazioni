/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.entandoapp.aps.system.services.busapi;

import org.apache.http.client.methods.HttpRequestBase;
import org.entando.entando.plugins.jprestapi.aps.core.RequestBuilder;
import org.entando.entando.plugins.jprestapi.aps.core.helper.RequestHelper;

/**
 *
 * @author Entando
 */
public class BusPassRequestBuilder extends RequestBuilder {

    /**
     * Questo metodo non fa altro che preporre il base URL all'indirizzo
     * della GET, POST etc. prima di ogni chiamata
     * @param verb
     * @throws Throwable 
     */
    @Override
    protected void setupRequest(HttpRequestBase verb) throws Throwable {
         RequestHelper.addBaseUrl(verb, BASE_URL);
    }
    
    // Indirizzo del microservizio
    public final static String BASE_URL = "http://127.0.0.1:8090";
}
