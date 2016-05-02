package com.example.aylin.menulogin;

/**
 * Created by aylin on 20.04.2016.
 */
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class CallSoap
{
    ArrayList<String> x=new ArrayList<String>();
    public final String SOAP_ACTION = "http://tempuri.org/GetKullanici_Y";
    public  final String OPERATION_NAME = "GetKullanici_Y";
    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    public  final String SOAP_ADDRESS = "http://213.14.73.146:82/gelincik/Kullanici_WebService.asmx";
    public CallSoap()
    {
    }
    public String Call(String a,String b)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("kartno_yeni");
        pi.setValue(a);
        pi.setType(String.class);
        request.addProperty(pi);
        pi=new PropertyInfo();
        pi.setName("sifre_yeni");
        pi.setValue(b);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response2=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);


            SoapObject primitive= (SoapObject) envelope.getResponse();
            if  (primitive != null) {

                SoapObject diffgram = (SoapObject) primitive.getProperty("diffgram");
                SoapObject NewDataSet = (SoapObject) diffgram.getProperty("DocumentElement");
                SoapObject RepInfo = (SoapObject) NewDataSet.getProperty("BASVURULARı");
                for (int i = 0; i < RepInfo.getPropertyCount(); i++) {
                    PropertyInfo info = new PropertyInfo();
                    RepInfo.getPropertyInfo(i, info);
                    System.out.println("repp...." + RepInfo.getProperty(i).toString());
                    x.add(RepInfo.getProperty(i).toString());

                }
                response2=x;
            }
        }
        catch (Exception exception)
        {
            response2=exception.toString()+"hataaaa";
        }
        return response2.toString();
    }


}