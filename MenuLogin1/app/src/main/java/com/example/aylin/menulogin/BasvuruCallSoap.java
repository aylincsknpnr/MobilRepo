package com.example.aylin.menulogin;

/**
 * Created by aylin on 27.04.2016.
 */

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 26.04.2016.
 */
public class BasvuruCallSoap {
    Object s=null;
    ArrayList<String> basvuruList=new ArrayList<String>();
    public final String SOAP_ACTION2 = "http://tempuri.org/Get_Basvurular_Y";

    public  final String OPERATION_NAME2 = "Get_Basvurular_Y";

    public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

    public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Basvurular_WebService.asmx";
    public BasvuruCallSoap()
    {
    }
    public String CallBasvuru(String x,String y)
    {   SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

        PropertyInfo pi2=new PropertyInfo();
        pi2.setName("user_id");
        pi2.setValue(x);
        pi2.setType(String.class);
        request.addProperty(pi2);
        pi2=new PropertyInfo();
        pi2.setName("basvurutip");
        pi2.setValue(y);
        pi2.setType(String.class);
        request.addProperty(pi2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS2);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION2, envelope);


            SoapObject primitive= (SoapObject) envelope.getResponse();
            if  (primitive != null) {
                System.out.println(primitive);
                SoapObject diffgram = (SoapObject) primitive.getProperty("diffgram");
                SoapObject NewDataSet = (SoapObject) diffgram.getProperty("DocumentElement");
                SoapObject RepInfo = (SoapObject) NewDataSet.getProperty("BASVURULAR_Y");
                for (int i = 0; i < RepInfo.getPropertyCount(); i++) {
                    PropertyInfo info = new PropertyInfo();
                    RepInfo.getPropertyInfo(i, info);
                    System.out.println("repp...." + RepInfo.getProperty(i).toString());
                    basvuruList.add(RepInfo.getProperty(i).toString());

                }
                response=basvuruList;
            }

        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();

    }
}
