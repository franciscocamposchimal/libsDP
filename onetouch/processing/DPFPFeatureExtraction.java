package com.digitalpersona.onetouch.processing;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPSample;

public abstract interface DPFPFeatureExtraction
{
  public abstract DPFPFeatureSet createFeatureSet(DPFPSample paramDPFPSample, DPFPDataPurpose paramDPFPDataPurpose)
    throws DPFPImageQualityException;
}
