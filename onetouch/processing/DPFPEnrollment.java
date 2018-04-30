package com.digitalpersona.onetouch.processing;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPTemplate;

public abstract interface DPFPEnrollment
{
  public abstract void addFeatures(DPFPFeatureSet paramDPFPFeatureSet)
    throws DPFPImageQualityException;
  
  public abstract void clear();
  
  public abstract int getFeaturesNeeded();
  
  public abstract DPFPTemplate getTemplate();
  
  public abstract DPFPTemplateStatus getTemplateStatus();
}
