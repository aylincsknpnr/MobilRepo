package com.example.aylin.d_av_a.Dava;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 23.05.2016.
 */
public class DavaSoap {
        public static String [] birim;
        public static String [] date;
        public static String [] dosyaN;
        public String val1,val2,val3,val4,val5,val6,val7,val8,val9,val10,val11;
        public final String SOAP_ACTION2 = "http://tempuri.org/Get_BuroDavalarim_Y";

        public  final String OPERATION_NAME2 = "Get_BuroDavalarim_Y";

        public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

        public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
        public DavaSoap()
        {
        }
        public String Call(String a, Integer b, Integer c)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("avukattc");
            pi2.setValue(a);
            pi2.setType(String.class);
            request.addProperty(pi2);
            pi2=new PropertyInfo();
            pi2.setName("istektip");
            pi2.setValue(b);
            pi2.setType(Integer.class);
            request.addProperty(pi2);
            pi2=new PropertyInfo();
            pi2.setName("buroid");
            pi2.setValue(c);
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
                    String [] birimadiArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] tarihsaatArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] dosyanoArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] dosyaTurkodArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] DosyaVekilleriArray=new String[NewDataSet.getPropertyCount()+1];
                    String [] DurumuArray=new String[NewDataSet.getPropertyCount()+1];
                    for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {
                        System.out.println("prim coun:"+primitive.getPropertyCount());
                        System.out.println("dataset xount:"+ NewDataSet.getPropertyCount());
                        SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                        System.out.println("info+"+info);
                        String birimadi=info.getProperty("BirimAdi").toString();
                        String tarihSaat=info.getProperty("TarihSaat").toString();
                        String dosyaNo=info.getProperty("DosyaNo").toString();
                        String dosyaTurKod=info.getProperty("DosyaTurKod").toString();
                        String dosyaVekilleri=info.getProperty("DosyaVekilleri").toString();
                        String durumu=info.getProperty("Durumu").toString();

                        birimadiArray[i]=birimadi;
                        tarihsaatArray[i]=tarihSaat;
                        dosyanoArray[i]=dosyaNo;
                        dosyaTurkodArray[i]=dosyaTurKod;
                        DosyaVekilleriArray[i]=dosyaVekilleri;
                        DurumuArray[i]=durumu;
                        x.add(birimadiArray[i]);
                        x.add(tarihsaatArray[i]);
                        x.add(dosyanoArray[i]);
                        x.add(dosyaTurkodArray[i]);
                        x.add(DosyaVekilleriArray[i]);
                        x.add(DurumuArray[i]+"\n");
                        birim=birimadiArray;
                        date=tarihsaatArray;
                        dosyaN=dosyanoArray;
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
    public String [] returnBirim(){
        return birim;
    }
    public String [] returnDate(){
        return date;
    }
    public String [] returnDosyaN(){
        return dosyaN;
    }
    }