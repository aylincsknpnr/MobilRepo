package com.example.aylin.avukep.Kep;

import com.example.aylin.avukep.BuroAvukat.BuroAvukatFragment;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 29.06.2016.
 */
public class KepSoap {

        public static String [] k_gon;
        public static String [] k_gont;
        public static String [] k_tebt;
        public final String SOAP_ACTION2 = "http://tempuri.org/Get_Avukat_KEP_Listesi";

        public  final String OPERATION_NAME2 = "Get_Avukat_KEP_Listesi";

        public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

        public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
        public KepSoap() {}
        public String Call(String a)
        {
            Object response2=null;
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

                    String [] gonArray=new String[primitive.getPropertyCount()+1];
                    String [] gontarihArray=new String[primitive.getPropertyCount()+1];
                    String [] tebtarihArray=new String[primitive.getPropertyCount()+1];

                    for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {
                        SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                        String gon=info.getProperty("kepFrom").toString();
                        String gont=info.getProperty("kepSendDate").toString();
                        String tebt=info.getProperty("kepReceivedDate").toString();

                        gonArray[i]=gon;
                        gontarihArray[i]=gont;
                        tebtarihArray[i]=tebt;
                       
                         k_gon=gonArray;
                        k_gont=gontarihArray;
                        k_tebt=tebtarihArray;
                        response=x;
                    }
                    response="x";
                }
            }
            catch (Exception exception)
            {
                response=exception.toString();
                System.out.println( exception.toString());
            }
            return response.toString();
        }
   public String [] returnGonderen(){
        return k_gon;
    }
    public String [] returnGondate(){
        return k_gont;
    }
    public String [] returnTebdate(){
        return k_tebt;
    }
    }
