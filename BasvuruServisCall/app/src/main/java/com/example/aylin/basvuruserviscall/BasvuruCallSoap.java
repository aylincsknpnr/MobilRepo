package com.example.aylin.basvuruserviscall;

/**
 * Created by aylin on 27.04.2016.
 */

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by aylin on 26.04.2016.
 */
public class BasvuruCallSoap {
    Object s=null;
    ArrayList<String> basvuruList=new ArrayList<String>();
    public final String SOAP_ACTION2 = "http://tempuri.org/Get_Basvurular_Y";

    public  final String OPERATION_NAME2 = "Get_Basvurular_Y";

    public  final String WSDL_TARGET_NAMESPACE2 = "http://tempuri.org/";

    public  final String SOAP_ADDRESS2 = "http://213.14.73.146:82/gelincik/Basvurular_WebService.asmx";
    public BasvuruCallSoap()
    {
    }
    public String CallBasvuru(Integer x,Integer y)
    {
        SoapObject request2 = new SoapObject(WSDL_TARGET_NAMESPACE2,OPERATION_NAME2);

        PropertyInfo pi2=new PropertyInfo();
        pi2.setName("user_id");
        pi2.setValue(x);
        pi2.setType(Integer.class);
        request2.addProperty(pi2);
        pi2=new PropertyInfo();
        pi2.setName("basvurutip");
        pi2.setValue(y);
        pi2.setType(Integer.class);
        request2.addProperty(pi2);

        SoapSerializationEnvelope envelope2 = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope2.dotNet = true;

        envelope2.setOutputSoapObject(request2);

        HttpTransportSE httpTransport2 = new HttpTransportSE(SOAP_ADDRESS2);

        Object response=null;
        Object response3=null;
        try
        {
            httpTransport2.call(SOAP_ACTION2, envelope2);
            SoapObject result = (SoapObject) envelope2.getResponse();
            int count = result.getPropertyCount();
            System.out.println(count+":count");
          /*  SoapObject primitive2= (SoapObject) envelope2.getResponse();
            s=primitive2;



            if  (primitive2 != null) {
response3="1";
              //  response2=primitive;
                SoapObject diffgram2 = (SoapObject) primitive2.getProperty("diffgram");
             //   System.out.println(diffgram2+":eses");
               SoapObject NewDataSet = (SoapObject) diffgram2.getProperty("DocumentElement");
                SoapObject RepInfo = (SoapObject) NewDataSet.getProperty("BASVURULARı");
                for (int i = 0; i < RepInfo.getPropertyCount(); i++) {
                    PropertyInfo info = new PropertyInfo();
                    RepInfo.getPropertyInfo(i, info);
                    System.out.println("repp...." + RepInfo.getProperty(i).toString());
                    basvuruList.add(RepInfo.getProperty(i).toString());

            }
                //  response2=basvuruList;
              /*  //Adı

                response=x.get(2);
                response2=x;
                //sicil
                //response=x.get(3);

        }*/

         }
        catch (Exception exception2)
        {
           // return exception2.toString();
            System.out.println(exception2.toString());
         //   response=exception.toString();
        }
        System.out.println(s+":priimmii"+"baslist:"+basvuruList);
        return  "1";

    }
}
