package com.example.aylin.d_av_a.Dava.Kasa;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 29.06.2016.
 */
public class GiderSoap {
        public static String [] g_aciklama;
        public static String [] g_date;
        public static String [] g_para;
        public String val1,val2,val3,val4,val5,val6,val7,val8,val9,val10,val11;
        public final String SOAP_ACTION2 = "http://tempuri.org/Get_BuroDavalarim_KASA_Y";

        public  final String OPERATION_NAME2 = "Get_BuroDavalarim_KASA_Y";

        public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

        public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
        public GiderSoap()
        {
        }
        public String Call(Integer g, String h, Integer m, Long j, Long k, Integer l )
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("buroid");
            pi2.setValue(g);
            pi2.setType(Integer.class);
            request.addProperty(pi2);

            pi2=new PropertyInfo();
            pi2.setName("avukattc");
            pi2.setValue(h);
            pi2.setType(String.class);
            request.addProperty(pi2);

            pi2=new PropertyInfo();
            pi2.setName("istektip");
            pi2.setValue(m);
            pi2.setType(Integer.class);
            request.addProperty(pi2);

            pi2=new PropertyInfo();
            pi2.setName("durusmaid");
            pi2.setValue(j);
            pi2.setType(Long.class);
            request.addProperty(pi2);

            pi2=new PropertyInfo();
            pi2.setName("birimid");
            pi2.setValue(k);
            pi2.setType(Long.class);
            request.addProperty(pi2);

            pi2=new PropertyInfo();
            pi2.setName("islemtip");
            pi2.setValue(l);
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
                    String [] AciklamaArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] ParaArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] TarihArray=new String[NewDataSet.getPropertyCount()+1];


                    for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {
                        System.out.println("prim coun:"+primitive.getPropertyCount());
                        System.out.println("dataset xount:"+ NewDataSet.getPropertyCount());
                        SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                        System.out.println("info+"+info);
                        String aciklama=info.getProperty("islem_aciklama").toString();
                        String para=info.getProperty("cikan_para").toString();
                        String tarih=info.getProperty("islem_tarih").toString();

                        AciklamaArray[i]=aciklama;
                        ParaArray[i]=para;
                        TarihArray[i]=tarih;

                        x.add(AciklamaArray[i]);
                        x.add(ParaArray[i]);
                        x.add(TarihArray[i]);

                        g_aciklama=AciklamaArray;
                        g_date=TarihArray;
                        g_para=ParaArray;
                        response="x";
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
        public String [] returnAciklamak(){
            return g_aciklama;
        }
        public String [] returnKDate(){
            return g_date;
        }
        public String [] returnPara(){
            return g_para;
        }
    }
