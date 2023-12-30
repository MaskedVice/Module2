package com.scripfinder.module2.dao;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.scripfinder.module2.dto.Candle;

@Repository
public class ScripDayDataDAOImpl implements ScripDayDataDAO {

    private static final Gson g = new Gson();

    @Autowired
    @Qualifier("mainSessionFactory")
    private SessionFactory mainSessionFactory;

    @Override
    public String save(String scripName, Candle candle) {
         try{
            Session session = this.mainSessionFactory.getCurrentSession();
            ProcedureCall call = session.createStoredProcedureCall("SaveScripDayData");
            // Register input parameters
            call.registerParameter("scripName", String.class, ParameterMode.IN);
            call.registerParameter("candles", String.class, ParameterMode.IN);
            // Register and set the output parameter
            call.registerParameter("statusMessage", String.class, ParameterMode.OUT);

            // Set input parameters
            call.setParameter("scripName", scripName);
            call.setParameter("candle", g.toJson(candle));

            // Execute the stored procedure
            ProcedureOutputs procedureOutputs = call.getOutputs();
            String status = (String) procedureOutputs.getOutputParameterValue("statusMessage");

            return status;

        } catch (Exception e) {
            throw e;
        }
    }
    
}
