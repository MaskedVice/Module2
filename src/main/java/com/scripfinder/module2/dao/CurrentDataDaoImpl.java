package com.scripfinder.module2.dao;


import java.util.Map;
import javax.persistence.ParameterMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.scripfinder.module2.dto.InstrumentData;

@Repository
public class CurrentDataDaoImpl implements CurrentDataDAO {

    @Autowired
    @Qualifier("mainSessionFactory")
    private SessionFactory mainSessionFactory;

    @Override
    public String save(Map<String, InstrumentData> data) {
         try{
            Session session = this.mainSessionFactory.getCurrentSession();
            final int[] i = new int[]{0,0,0};
            data.values().parallelStream().forEach(x-> {
                try {
                    if(x.getOhlc() != null && (x.getOhlc().getOpen() > 0 || x.getOhlc().getHigh() > 0 || x.getOhlc().getLow() > 0 || x.getOhlc().getClose() > 0)) {
                         ProcedureCall call = session.createStoredProcedureCall("InsertCurrentData");
                        call.registerParameter("scripName", String.class, ParameterMode.IN).bindValue(x.getInstrument_token().split("\\|")[1].toLowerCase());
                        call.registerParameter("openPrice", Double.class, ParameterMode.IN).bindValue(x.getOhlc().getOpen());
                        call.registerParameter("highPrice", Double.class, ParameterMode.IN).bindValue(x.getOhlc().getHigh());
                        call.registerParameter("lowPrice", Double.class, ParameterMode.IN).bindValue(x.getOhlc().getLow());
                        call.registerParameter("closePrice", Double.class, ParameterMode.IN).bindValue(x.getOhlc().getClose());
                        call.registerParameter("timeStampCur", String.class, ParameterMode.IN).bindValue(x.getTimeStamp());
                        call.registerParameter("statusMessage", String.class, ParameterMode.OUT);

                        ProcedureOutputs procedureOutputs = call.getOutputs();
                        String status = (String) procedureOutputs.getOutputParameterValue("statusMessage");
                        if(status.equals("Success")){
                            i[0]++;
                        } else if (status.equals("Table Absent")){
                            i[1]++;
                        } else {
                            System.out.println(status);
                        }
                    }
                    else{
                        i[2]++;
                        if(x.getOhlc() != null){
                            System.out.println(x.getOhlc().getOpen() + " " + x.getOhlc().getHigh() + " " + x.getOhlc().getLow() + " " + x.getOhlc().getClose());
                        }
                            
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return i[0] + " scrips inserted, " + i[1] + " tables absent " + (i[2]) + " had null or 0 values";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
