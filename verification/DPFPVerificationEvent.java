package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import java.util.EventObject;









public class DPFPVerificationEvent
  extends EventObject
{
  private static final long serialVersionUID = 5737396022710270291L;
  private final DPFPFeatureSet featureSet;
  private boolean matched = false;
  private boolean stopCapture = true;
  





  public DPFPVerificationEvent(Object paramObject, DPFPFeatureSet paramDPFPFeatureSet)
  {
    super(paramObject);
    featureSet = paramDPFPFeatureSet;
  }
  




  public DPFPFeatureSet getFeatureSet()
  {
    return featureSet;
  }
  





















  public void setMatched(boolean paramBoolean)
  {
    matched = paramBoolean;
  }
  
  public boolean getMatched() {
    return matched;
  }
  














  public void setStopCapture(boolean paramBoolean)
  {
    stopCapture = paramBoolean;
  }
  
  public boolean getStopCapture() {
    return stopCapture;
  }
}
