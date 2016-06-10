package com.example.aylin.takvimim.Ajanda;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 06.06.2016.
 */
public class CalenderCallSoap {
    public static String [] count;
    public static String [] tip2;
    public final String SOAP_ACTION2 = "http://tempuri.org/Get_BuroDavalarim_SAYILAR_Y";

    public  final String OPERATION_NAME2 = "Get_BuroDavalarim_SAYILAR_Y";

    public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

    public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
    public CalenderCallSoap()
    {
    }
    public String Call(int a, String b, int c)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

        PropertyInfo pi2 = new PropertyInfo();
        pi2 = new PropertyInfo();
        pi2.setName("buroid");
        pi2.setValue(a);
        pi2.setType(int.class);
        request.addProperty(pi2);

        pi2 = new PropertyInfo();
        pi2.setName("avukattc");
        pi2.setValue(b);
        pi2.setType(String.class);
        request.addProperty(pi2);

        pi2 = new PropertyInfo();
        pi2.setName("istektip");
        pi2.setValue(c);
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
                //SoapObject RepInfo = (SoapObject) NewDataSet.getProperty("DAVALARIM_Y");
                String [] countArray=new String[NewDataSet.getPropertyCount()+1];
                String [] tariharray=new String[NewDataSet.getPropertyCount()+1];
                String [] tipArray =new String[NewDataSet.getPropertyCount()+1];
                for (int i = 0; i < NewDataSet.getPropertyCount(); i++) {
                    SoapObject info=(SoapObject)NewDataSet.getProperty(i);
                    System.out.println("info+" + info);
                    String count=info.getProperty("Expr1").toString();
                    String tarihSaat=info.getProperty("Column1").toString();
                    String tip=info.getProperty("Column2").toString();
                    System.out.println("tiptip"+tip);
                    countArray[i]=count;
                    tariharray[i]=tarihSaat;
                    tipArray[i]=tip;
                    x.add(tariharray[i]+"\n");
                    //x.add(tipArray[i]+"\n");
                }
                count=countArray;
                tip2=tipArray;

                response=x;
            }
        }
        catch (Exception exception)
        {
            response=exception.toString();
            System.out.println( exception.toString());
        }
        return response.toString();
    }
    public String [] returnCount(){
        return count;
    }
    public String [] returnTip(){
        return tip2;
    }
}
