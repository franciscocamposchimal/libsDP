package com.digitalpersona.onetouch.processing._impl;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.jni.FeatureExtractionResult;
import com.digitalpersona.onetouch.jni.FeatureExtractor;
import com.digitalpersona.onetouch.jni.JniException;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;

public class DPFPFeatureExtractionFactoryImpl implements com.digitalpersona.onetouch.processing.DPFPFeatureExtractionFactory
{
  public DPFPFeatureExtractionFactoryImpl() {}
  
  public DPFPFeatureExtraction createFeatureExtraction()
  {
    return new FeatureExtractionImpl();
  }
  

  private static class FeatureExtractionImpl
    implements DPFPFeatureExtraction
  {
    private FeatureExtractor featureExtractor;
    
    public FeatureExtractionImpl()
    {
      try
      {
        featureExtractor = new FeatureExtractor();
      } catch (JniException localJniException) {
        throw new RuntimeException(localJniException);
      }
    }
    
    public DPFPFeatureSet createFeatureSet(DPFPSample paramDPFPSample, DPFPDataPurpose paramDPFPDataPurpose)
      throws DPFPImageQualityException
    {
      if (paramDPFPDataPurpose == DPFPDataPurpose.DATA_PURPOSE_UNKNOWN) {
        throw new IllegalArgumentException();
      }
      int i = paramDPFPDataPurpose == DPFPDataPurpose.DATA_PURPOSE_VERIFICATION ? 2 : paramDPFPDataPurpose == DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT ? 0 : 0;
      


      DPFPCaptureFeedback localDPFPCaptureFeedback = DPFPCaptureFeedback.CAPTURE_FEEDBACK_NONE;
      try {
        FeatureExtractionResult localFeatureExtractionResult = featureExtractor.extractFeatures(paramDPFPSample.serialize(), i);
        localDPFPCaptureFeedback = DPFPCaptureFeedback.values()[featuresQuality];
        if (localDPFPCaptureFeedback != DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD) {
          throw new DPFPImageQualityException(localDPFPCaptureFeedback);
        }
        return com.digitalpersona.onetouch.DPFPGlobal.getFeatureSetFactory().createFeatureSet(data);
      }
      catch (JniException localJniException)
      {
        throw new DPFPImageQualityException(DPFPCaptureFeedback.CAPTURE_FEEDBACK_NONE);
      } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
        throw new DPFPImageQualityException(DPFPCaptureFeedback.CAPTURE_FEEDBACK_NONE);
      } catch (IllegalArgumentException localIllegalArgumentException) {
        throw new DPFPImageQualityException(DPFPCaptureFeedback.CAPTURE_FEEDBACK_NONE);
      }
    }
  }
}
