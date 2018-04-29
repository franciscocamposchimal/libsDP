package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.readers.DPFPReaderImpressionType;
import java.util.ResourceBundle;
import javax.swing.JEditorPane;
import javax.swing.UIManager;



class ExplanationControl
  extends JEditorPane
{
  private DPFPFingerIndex finger;
  private static ResourceBundle labels = ResourceBundle.getBundle("dpfpui");
  
  public ExplanationControl() {
    super("text/html", labels.getString("IDS_FOOTER"));
    setOpaque(false);
    setEditable(false);
    setFont(UIManager.getFont("Panel.font"));
    putClientProperty("JEditorPane.honorDisplayProperties", Boolean.TRUE);
  }
  
  public void displayStart(DPFPReaderImpressionType paramDPFPReaderImpressionType) {
    String str = paramDPFPReaderImpressionType == DPFPReaderImpressionType.READER_IMPRESSION_TYPE_SWIPE ? labels.getString("IDS_SWIPE_SCAN_BEGIN") : labels.getString("IDS_SCAN_BEGIN");
    


    setText(String.format(str, new Object[] { Utilities.fingerName(finger) }));
  }
  
  public void displayQuality(DPFPReaderImpressionType paramDPFPReaderImpressionType, DPFPCaptureFeedback paramDPFPCaptureFeedback) {
    setText(Utilities.getFeedbackString(paramDPFPReaderImpressionType, paramDPFPCaptureFeedback));
  }
  
  public void displayReaderStatus(int paramInt) {
    if (paramInt == 2)
      setText(labels.getString("IDS_DISCONNECT_IDENTIFY_TEXT"));
  }
  
  public void setFinger(DPFPFingerIndex paramDPFPFingerIndex) {
    finger = paramDPFPFingerIndex;
  }
}
