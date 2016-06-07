package com.example.aylin.serviskontrol;

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
    public final String SOAP_ACTION2 = "http://tempuri.org/Get_BuroDavalarim_SAYILAR_Y";

    public  final String OPERATION_NAME2 = "Get_BuroDavalarim_SAYILAR_Y";

    public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

    public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Davalarim_WebService.asmx";
    public CallSoap()
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

                    countArray[i]=count;
                    tariharray[i]=tarihSaat;
                    tipArray[i]=tip;

                    x.add(countArray[i]);
                    x.add(tariharray[i]);
                    x.add(tipArray[i]+"\n");
                }
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


}

/*
for(int j=0; j<NewDataSet.getPropertyCount(); j++){
                        PropertyInfo info2=new PropertyInfo();
                        RepInfo.getPropertyInfo(j,info2);
                        System.out.println("repınfo..."+RepInfo.getProperty(j).toString());
                    }
 */