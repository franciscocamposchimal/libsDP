package com.digitalpersona.onetouch.processing;

import com.digitalpersona.onetouch.DPFPSample;
import java.awt.Image;

public abstract interface DPFPSampleConversion
{
  public abstract Image createImage(DPFPSample paramDPFPSample);
  
  public abstract byte[] convertToANSI381(DPFPSample paramDPFPSample);
}
