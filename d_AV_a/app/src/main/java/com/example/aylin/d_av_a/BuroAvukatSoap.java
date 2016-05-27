
package com.example.aylin.d_av_a;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 20.05.2016.
 */
public class BuroAvukatSoap {

    public final String SOAP_ACTION2 = "http://tempuri.org/Get_BuroAvukatlariBilgileri_Y";

    public  final String OPERATION_NAME2 = "Get_BuroAvukatlariBilgileri_Y";

    public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

    public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/BuroBilgilerim_WebService.asmx";
    public BuroAvukatSoap()
    {
    }
    public String Call(String a, Integer b)
    {
        Object response2=null;
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

        PropertyInfo pi2=new PropertyInfo();
        pi2.setName("avukattc");
        pi2.setValue(a);
        pi2.setType(String.class);
        request.addProperty(pi2);
        pi2=new PropertyInfo();
        pi2.setName("buroid");
        pi2.setValue(b);
        pi2.setType(Integer.class);
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
            BuroAvukatFragment baf=new BuroAvukatFragment();
            if  (primitive != null) {
                System.out.println(primitive);
                SoapObject diffgram = (SoapObject) primitive.getProperty("diffgram");
                SoapObject NewDataSet = (SoapObject) diffgram.getProperty("DocumentElement");
               // SoapObject RepInfo = (SoapObject) NewDataSet.getProperty("BUROBILGILERIM_Y");
                //System.out.println(NewDataSet.getPropertyCount());
                String [] tcArray=new String[primitive.getPropertyCount()+1];
                String [] adArray=new String[primitive.getPropertyCount()+1];
                String [] baroArray=new String[primitive.getPropertyCount()+1];
                String [] sicilArray=new String[primitive.getPropertyCount()+1];
                String [] tbbnoArray=new String[primitive.getPropertyCount()+1];
                for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {


                        SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                        String tc=info.getProperty("TcKimlikNo").toString();
                        String ad=info.getProperty("AdiSoyadi").toString();
                        String baro=info.getProperty("Baro").toString();
                        String sicil=info.getProperty("BaroSicilNo").toString();
                        String tbbno=info.getProperty("CepTel").toString();
                      //  baf.sicil.setText(sicil);
                        tcArray[i]=tc;
                        adArray[i]=ad;
                        baroArray[i]=baro;
                        sicilArray[i]=sicil;
                        tbbnoArray[i]=tbbno;
                        x.add(tcArray[i]);
                        x.add(adArray[i]);
                        x.add(baroArray[i]);
                        x.add(sicilArray[i]);
                        x.add(tbbnoArray[i]+"\n");

                    response=x;
                }
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
