package com.example.aylin.avukep.Haber;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 15.06.2016.
 */
public class HaberSoap {

        public static String [] h_baslik;
        public static String [] h_date;
        public static String [] h_detay;
        public String val1,val2,val3,val4,val5,val6,val7,val8,val9,val10,val11;
        public final String SOAP_ACTION2 = "http://tempuri.org/Get_HaberlerDuyurular_Listesi";

        public  final String OPERATION_NAME2 = "Get_HaberlerDuyurular_Listesi";

        public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

        public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
        public HaberSoap()
        {
        }
        public String Call(Integer a)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("tip");
            pi2.setValue(a);
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
                System.out.println(primitive);
                if  (primitive != null) {
                    System.out.println(primitive);
                    SoapObject diffgram = (SoapObject) primitive.getProperty("diffgram");
                    SoapObject NewDataSet = (SoapObject) diffgram.getProperty("DocumentElement");
                    String [] IdArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] BaslikArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] TarihArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] DetayArray=new String[NewDataSet.getPropertyCount()+1];

                    for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {
                        System.out.println("prim coun:"+primitive.getPropertyCount());
                        System.out.println("dataset xount:"+ NewDataSet.getPropertyCount());
                        SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                        System.out.println("info+"+info);
                        String ıd=info.getProperty("Id").toString();
                        String baslik=info.getProperty("Baslik").toString();
                        String tarih=info.getProperty("Tarih").toString();
                        String detay=info.getProperty("Detay").toString();

                        IdArray[i]=ıd;
                        BaslikArray[i]=baslik;
                        TarihArray[i]=tarih;
                        DetayArray[i]=detay;
                        x.add(IdArray[i]);
                        x.add(BaslikArray[i]);
                        x.add(TarihArray[i]);
                        x.add(DetayArray[i]);

                        h_baslik=BaslikArray;
                        h_date=TarihArray;
                        h_detay=DetayArray;
                        response=x;
                    }
                }

            }
            catch (Exception exception)
            {
                response=exception.toString();
                System.out.println( exception.toString());
            }
            return "x";
                    //response.toString();
        }
      public String [] returnBaslik(){
            return h_baslik;
        }
        public String [] returnDDate(){
            return h_date;
        }
        public String [] returnDetay(){
            return h_detay;
        }
    }
