package com.digitalpersona.onetouch;

import com.digitalpersona.onetouch.capture.DPFPCaptureFactory;
import com.digitalpersona.onetouch.processing.DPFPEnrollmentFactory;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtractionFactory;
import com.digitalpersona.onetouch.processing.DPFPSampleConversion;
import com.digitalpersona.onetouch.readers.DPFPReadersCollectionFactory;
import com.digitalpersona.onetouch.verification.DPFPVerificationFactory;













public final class DPFPGlobal
{
  private static DPFPCaptureFactory captureFactory;
  private static DPFPFeatureSetFactory featureSetFactory;
  private static DPFPSampleFactory sampleFactory;
  private static DPFPTemplateFactory templateFactory;
  private static DPFPSampleConversion sampleConversion;
  private static DPFPEnrollmentFactory enrollmentFactory;
  private static DPFPFeatureExtractionFactory featureExtractionFactory;
  private static DPFPReadersCollectionFactory readersCollectionFactory;
  private static DPFPVerificationFactory verificationFactory;
  
  static
  {
    String str1 = "com.digitalpersona.onetouch.capture._impl.DPFPCaptureFactoryImpl";
    String str2 = "com.digitalpersona.onetouch._impl.DPFPFeatureSetFactoryImpl";
    String str3 = "com.digitalpersona.onetouch.verification._impl.DPFPVerificationFactoryImpl";
    String str4 = "com.digitalpersona.onetouch.readers._impl.DPFPReadersCollectionFactoryImpl";
    String str5 = "com.digitalpersona.onetouch.processing._impl.DPFPFeatureExtractionFactoryImpl";
    String str6 = "com.digitalpersona.onetouch.processing._impl.DPFPEnrollmentFactoryImpl";
    String str7 = "com.digitalpersona.onetouch.processing._impl.DPFPSampleConversionImpl";
    String str8 = "com.digitalpersona.onetouch._impl.DPFPTemplateFactoryImpl";
    String str9 = "com.digitalpersona.onetouch._impl.DPFPSampleFactoryImpl";
    try
    {
      captureFactory = (DPFPCaptureFactory)Class.forName(str1).newInstance();
      featureSetFactory = (DPFPFeatureSetFactory)Class.forName(str2).newInstance();
      sampleFactory = (DPFPSampleFactory)Class.forName(str9).newInstance();
      templateFactory = (DPFPTemplateFactory)Class.forName(str8).newInstance();
      sampleConversion = (DPFPSampleConversion)Class.forName(str7).newInstance();
      enrollmentFactory = (DPFPEnrollmentFactory)Class.forName(str6).newInstance();
      featureExtractionFactory = (DPFPFeatureExtractionFactory)Class.forName(str5).newInstance();
      readersCollectionFactory = (DPFPReadersCollectionFactory)Class.forName(str4).newInstance();
      verificationFactory = (DPFPVerificationFactory)Class.forName(str3).newInstance();
    } catch (InstantiationException localInstantiationException) {
      localInstantiationException.printStackTrace();
    } catch (IllegalAccessException localIllegalAccessException) {
      localIllegalAccessException.printStackTrace();
    } catch (ClassNotFoundException localClassNotFoundException) {
      localClassNotFoundException.printStackTrace();
    }
  }
  




  public static DPFPCaptureFactory getCaptureFactory()
  {
    return captureFactory;
  }
  




  public static DPFPFeatureSetFactory getFeatureSetFactory()
  {
    return featureSetFactory;
  }
  




  public static DPFPTemplateFactory getTemplateFactory()
  {
    return templateFactory;
  }
  




  public static DPFPSampleFactory getSampleFactory()
  {
    return sampleFactory;
  }
  




  public static DPFPSampleConversion getSampleConversionFactory()
  {
    return sampleConversion;
  }
  




  public static DPFPEnrollmentFactory getEnrollmentFactory()
  {
    return enrollmentFactory;
  }
  




  public static DPFPFeatureExtractionFactory getFeatureExtractionFactory()
  {
    return featureExtractionFactory;
  }
  




  public static DPFPVerificationFactory getVerificationFactory()
  {
    return verificationFactory;
  }
  




  public static DPFPReadersCollectionFactory getReadersFactory()
  {
    return readersCollectionFactory;
  }
  
  public DPFPGlobal() {}
}
