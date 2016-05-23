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
                if  (primitive != null) {
                    System.out.println(primitive);
                    SoapObject diffgram = (SoapObject) primitive.getProperty("diffgram");
                    SoapObject NewDataSet = (SoapObject) diffgram.getProperty("DocumentElement");
                    SoapObject RepInfo = (SoapObject) NewDataSet.getProperty("BUROBILGILERIM_Y");
                    System.out.println(NewDataSet.getPropertyCount());
                    for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {

                        x.add(NewDataSet.getProperty(i).toString().replace("anyType"," ").replace("{", " ").replace("}", " "));
                        System.out.println(x.get(0));
                        System.out.println(x);
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
