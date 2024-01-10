package com.scripfinder.module2.dao;

import java.util.List;

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
public class MonthDataDAOImpl implements MonthDataDAO {
    private static final Gson g = new Gson();

    @Autowired
    @Qualifier("mainSessionFactory")
    private SessionFactory mainSessionFactory;

    @Override
    public String save(String scripName, List<Candle> candles) {
        try{
            Session session = this.mainSessionFactory.getCurrentSession();
            ProcedureCall call = session.createStoredProcedureCall("SaveScripMonthData");
            call.registerParameter("scripName", String.class, ParameterMode.IN);
            call.registerParameter("candles", String.class, ParameterMode.IN);
            call.registerParameter("statusMessage", String.class, ParameterMode.OUT);

            call.setParameter("scripName", scripName);
            call.setParameter("candles", g.toJson(candles));

            ProcedureOutputs procedureOutputs = call.getOutputs();
            String status = (String) procedureOutputs.getOutputParameterValue("statusMessage");

            return status;

        } catch (Exception e) {
            throw e;
        }
    }
}


