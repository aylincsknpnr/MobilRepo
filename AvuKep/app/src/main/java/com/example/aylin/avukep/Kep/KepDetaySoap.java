package com.example.aylin.avukep.Kep;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 30.06.2016.
 */
public class KepDetaySoap {


        public static String [] kd_icerik1;
        public static String [] kd_icerik2;
        public static String [] kd_id;
        public final String SOAP_ACTION2 = "http://tempuri.org/Get_Avukat_KEP_Listesi_Detay";

        public  final String OPERATION_NAME2 = "Get_Avukat_KEP_Listesi_Detay";

        public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

        public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
        public KepDetaySoap() {}
        public String Call(String a, int b)
        {
            Object response2=null;
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);
            PropertyInfo pi2 = new PropertyInfo();
            pi2 = new PropertyInfo();
            pi2.setName("avukattc");
            pi2.setValue(a);
            pi2.setType(String.class);
            request.addProperty(pi2);

            pi2 = new PropertyInfo();
            pi2.setName("kepinboxid");
            pi2.setValue(b);
            pi2.setType(int.class);
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

                    String [] icerk1Array=new String[primitive.getPropertyCount()+1];
                    String [] icerik2Array=new String[primitive.getPropertyCount()+1];
                    String [] idArray=new String[primitive.getPropertyCount()+1];

                    for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {
                        SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                        String icerik1=info.getProperty("icerikbir").toString();
                        String icerik2=info.getProperty("icerikiki").toString();
                        String id=info.getProperty("kep_inbox_id").toString();

                        icerk1Array[i]=icerik1;
                        icerik2Array[i]=icerik2;
                        idArray[i]=id;

                        kd_icerik1=icerk1Array;
                        kd_icerik2=icerik2Array;
                        kd_id=idArray;

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
        public String [] returnIcerik1(){
            return kd_icerik1;
        }
        public String [] returnIcerik2(){
            return kd_icerik2;
        }
        public String [] returnId(){
            return kd_id;
        }
    }

