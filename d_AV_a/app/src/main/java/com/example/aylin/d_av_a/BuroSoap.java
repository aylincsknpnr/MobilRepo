package com.example.aylin.d_av_a;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 18.05.2016.
 */
public class BuroSoap {
    public String val1,val2,val3,val4,val5,val6,val7,val8,val9,val10,val11;
    public final String SOAP_ACTION2 = "http://tempuri.org/Get_BuroBilgilerim_Y";

    public  final String OPERATION_NAME2 = "Get_BuroBilgilerim_Y";

    public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

    public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/BuroBilgilerim_WebService.asmx";
    public BuroSoap()
    {
    }
    public String Call(String a)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

        PropertyInfo pi2=new PropertyInfo();
        pi2.setName("avukattc");
        pi2.setValue(a);
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

            ArrayList<String> x=new ArrayList<String>();
            SoapObject primitive= (SoapObject) envelope.getResponse();
            if  (primitive != null) {
                System.out.println(primitive);
                SoapObject diffgram = (SoapObject) primitive.getProperty("diffgram");
                SoapObject NewDataSet = (SoapObject) diffgram.getProperty("DocumentElement");
                SoapObject RepInfo = (SoapObject) NewDataSet.getProperty("BUROBILGILERIM_Y");
                System.out.println(RepInfo.getProperty("ofisname")+":::::officeee");
                val1=RepInfo.getProperty("buroid").toString();
                val2=RepInfo.getProperty("ofisname").toString();
                val3=RepInfo.getProperty("adres").toString();
                val4=RepInfo.getProperty("ilce").toString();
                val5=RepInfo.getProperty("il").toString();
                val6=RepInfo.getProperty("postakodu").toString();
                val7=RepInfo.getProperty("telefon").toString();
                val8=RepInfo.getProperty("telefoniki").toString();
                val9=RepInfo.getProperty("faks").toString();
                val10=RepInfo.getProperty("vergidairesi").toString();
                val11=RepInfo.getProperty("vergino").toString();
                response=val1+" "+val2+" "+val3+" "+val4+" "+val5+" "+val6+" "+val7+" "+val8+" "+val9+" "+val10+" "+val11;
            }
        }
        catch (Exception exception)
        {
            response=exception.toString();
            System.out.println( exception.toString());
        }
        return response.toString();
    }
    }
