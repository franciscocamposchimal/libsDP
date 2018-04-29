package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.readers.DPFPReaderImpressionType;
import java.util.ResourceBundle;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

class Utilities
{
  Utilities() {}
  
  private static ResourceBundle labels = ResourceBundle.getBundle("dpfpui");
  
  public static JPanel getXBox() {
    JPanel localJPanel = new JPanel();
    localJPanel.setLayout(new javax.swing.BoxLayout(localJPanel, 1));
    localJPanel.setOpaque(false);
    return localJPanel;
  }
  
  public static JPanel getXBorder() {
    JPanel localJPanel = new JPanel(new java.awt.BorderLayout());
    localJPanel.setOpaque(false);
    return localJPanel;
  }
  
















  public static JEditorPane getXTextPane() { return getXTextPane(""); }
  
  public static JEditorPane getXTextPane(String paramString) {
    JEditorPane localJEditorPane = new JEditorPane("text/html", paramString);
    localJEditorPane.setOpaque(false);
    localJEditorPane.setEditable(false);
    
    localJEditorPane.setFont(javax.swing.UIManager.getFont("Panel.font"));
    localJEditorPane.putClientProperty("JEditorPane.honorDisplayProperties", Boolean.TRUE);
    return localJEditorPane;
  }
  
  public static String getFeedbackString(DPFPReaderImpressionType paramDPFPReaderImpressionType, com.digitalpersona.onetouch.DPFPCaptureFeedback paramDPFPCaptureFeedback)
  {
    if (paramDPFPReaderImpressionType == DPFPReaderImpressionType.READER_IMPRESSION_TYPE_AREA) {
      switch (1.$SwitchMap$com$digitalpersona$onetouch$DPFPCaptureFeedback[paramDPFPCaptureFeedback.ordinal()]) {
      case 1:  return labels.getString("IDS_CC_QUALITY_0");
      case 2:  return labels.getString("IDS_CC_QUALITY_2");
      case 3:  return labels.getString("IDS_CC_QUALITY_3");
      case 4:  return labels.getString("IDS_CC_QUALITY_4");
      case 5:  return labels.getString("IDS_CC_QUALITY_5"); }
      return labels.getString("IDS_CC_QUALITY_U");
    }
    
    switch (1.$SwitchMap$com$digitalpersona$onetouch$DPFPCaptureFeedback[paramDPFPCaptureFeedback.ordinal()]) {
    case 1: 
      return labels.getString("IDS_SWIPE_QUALITY_SCAN_SUCCESS");
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
      return labels.getString("IDS_SWIPE_QUALITY_SCAN_CENTER");
    case 9: 
    case 10: 
    case 11: 
    case 12: 
      return labels.getString("IDS_SWIPE_QUALITY_SCAN_JOINT");
    case 13: 
    case 14: 
    case 15: 
    case 16: 
      return labels.getString("IDS_SWIPE_QUALITY_SCAN_CENTER");
    case 17: 
      return labels.getString("IDS_SWIPE_QUALITY_TOO_FAST");
    case 18: 
      return labels.getString("IDS_SWIPE_QUALITY_TOO_SLOW");
    }
    return "";
  }
  

  public static String fingerName(DPFPFingerIndex paramDPFPFingerIndex)
  {
    return labels.getString("IDS_FINGER_" + paramDPFPFingerIndex.ordinal());
  }
  
  public static String fingerprintName(DPFPFingerIndex paramDPFPFingerIndex) { return labels.getString("IDS_FINGERPRINT_" + paramDPFPFingerIndex.ordinal()); }
}
