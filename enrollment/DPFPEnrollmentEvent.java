package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.util.EventObject;





public class DPFPEnrollmentEvent
  extends EventObject
{
  private static final long serialVersionUID = -4831766057485185293L;
  private int id;
  private DPFPFingerIndex finger;
  private DPFPTemplate template;
  private boolean performed = true;
  private boolean stopCapture = true;
  






  public static final int FINGER_ENROLLED = 0;
  





  public static final int FINGER_DELETED = 1;
  






  public DPFPEnrollmentEvent(Object paramObject, int paramInt, DPFPFingerIndex paramDPFPFingerIndex, DPFPTemplate paramDPFPTemplate)
  {
    super(paramObject);
    id = paramInt;
    finger = paramDPFPFingerIndex;
    template = paramDPFPTemplate;
  }
  






  public DPFPEnrollmentEvent(Object paramObject, int paramInt, DPFPFingerIndex paramDPFPFingerIndex)
  {
    this(paramObject, paramInt, paramDPFPFingerIndex, null);
  }
  







  public int getID()
  {
    return id;
  }
  




  public DPFPTemplate getTemplate()
  {
    return template;
  }
  




  public DPFPFingerIndex getFingerIndex()
  {
    return finger;
  }
  





















  public void setPerformed(boolean paramBoolean)
  {
    performed = paramBoolean;
  }
  
  public boolean getPerformed() {
    return performed;
  }
  















  public void setStopCapture(boolean paramBoolean)
  {
    stopCapture = paramBoolean;
  }
  
  public boolean getStopCapture() {
    return stopCapture;
  }
}
