package com.digitalpersona.onetouch.processing._impl;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.DPFPTemplateFactory;
import com.digitalpersona.onetouch.jni.JniException;
import com.digitalpersona.onetouch.jni.Matcher;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.processing.DPFPTemplateStatus;
import java.util.Vector;

public class DPFPEnrollmentFactoryImpl implements com.digitalpersona.onetouch.processing.DPFPEnrollmentFactory
{
  public DPFPEnrollmentFactoryImpl() {}
  
  public DPFPEnrollment createEnrollment()
  {
    return new EnrollmentImpl();
  }
  

  private static class EnrollmentImpl
    implements DPFPEnrollment
  {
    private int featuresNeeded;
    
    private DPFPTemplate template;
    
    private DPFPTemplateStatus templateStatus = DPFPTemplateStatus.TEMPLATE_STATUS_UNKNOWN;
    private final Vector<DPFPFeatureSet> features = new Vector();
    
    private Matcher matcher;
    
    public EnrollmentImpl()
    {
      try
      {
        matcher = new Matcher();
        featuresNeeded = Matcher.getFeaturesRequired();
      } catch (JniException localJniException) {
        throw new RuntimeException(localJniException);
      }
    }
    
    public void addFeatures(DPFPFeatureSet paramDPFPFeatureSet)
      throws DPFPImageQualityException
    {
      if ((templateStatus == DPFPTemplateStatus.TEMPLATE_STATUS_FAILED) || (templateStatus == DPFPTemplateStatus.TEMPLATE_STATUS_READY)) {
        throw new IllegalStateException();
      }
      features.add(paramDPFPFeatureSet);
      templateStatus = DPFPTemplateStatus.TEMPLATE_STATUS_INSUFFICIENT;
      
      if (features.size() == featuresNeeded) {
        byte[][] arrayOfByte = new byte[featuresNeeded][];
        for (int i = 0; i < featuresNeeded; i++)
          arrayOfByte[i] = ((DPFPFeatureSet)features.elementAt(i)).serialize();
        try {
          template = com.digitalpersona.onetouch.DPFPGlobal.getTemplateFactory().createTemplate(matcher.generateRegFeatures(arrayOfByte, 0));
          templateStatus = DPFPTemplateStatus.TEMPLATE_STATUS_READY;
        } catch (JniException localJniException) {
          template = null;
          templateStatus = DPFPTemplateStatus.TEMPLATE_STATUS_FAILED;
          
          throw new DPFPImageQualityException(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD);
        } catch (IllegalArgumentException localIllegalArgumentException) {
          template = null;
          templateStatus = DPFPTemplateStatus.TEMPLATE_STATUS_FAILED;
          throw new DPFPImageQualityException(DPFPCaptureFeedback.CAPTURE_FEEDBACK_NONE);
        }
      }
    }
    
    public void clear() {
      template = null;
      templateStatus = DPFPTemplateStatus.TEMPLATE_STATUS_UNKNOWN;
      features.clear();
    }
    
    public int getFeaturesNeeded() {
      return Math.max(featuresNeeded - features.size(), 0);
    }
    
    public DPFPTemplate getTemplate() {
      return template;
    }
    
    public DPFPTemplateStatus getTemplateStatus() {
      return templateStatus;
    }
  }
}
