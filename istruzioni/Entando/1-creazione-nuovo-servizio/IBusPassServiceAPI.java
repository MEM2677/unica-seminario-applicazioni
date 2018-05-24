/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.entandoapp.aps.system.services.busapi;

import com.agiletec.aps.system.exception.ApsSystemException;

/**
 *
 * @author Entando
 */
public interface IBusPassServiceAPI {
    
    public final static String BEAN_ID = "busPassService";
    
    /**
     * Check whether the buss pass needed is a Junior or not
     * @param age
     * @return
     * @throws ApsSystemException 
     */
    public boolean isJuniorPass(int age) throws ApsSystemException;
    
}
