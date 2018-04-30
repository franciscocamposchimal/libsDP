package com.digitalpersona.onetouch.processing;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;








public class DPFPImageQualityException
  extends Exception
{
  private static final long serialVersionUID = 1614795128000451111L;
  private DPFPCaptureFeedback captureFeedback;
  
  public DPFPImageQualityException(DPFPCaptureFeedback paramDPFPCaptureFeedback)
  {
    captureFeedback = paramDPFPCaptureFeedback;
  }
  




  public DPFPCaptureFeedback getCaptureFeedback()
  {
    return captureFeedback;
  }
}
