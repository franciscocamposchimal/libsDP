package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCaptureFactory;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.processing.DPFPTemplateStatus;
import com.digitalpersona.onetouch.readers.DPFPReaderDescription;
import com.digitalpersona.onetouch.readers.DPFPReaderImpressionType;
import com.digitalpersona.onetouch.readers.DPFPReadersCollection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.beans.PropertyChangeEvent;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.swing.text.JTextComponent;

class CapturePanel extends JPanel
{
  private final DPFPSwingUtilities swingUtilities = new DPFPSwingUtilities();
  
  private final transient JTextComponent prompt = Utilities.getXTextPane();
  private final transient SensorControl sensorControl = new SensorControl();
  private final transient MiniHandsControl miniHandsControl = new MiniHandsControl();
  private final transient ExplanationControl explanationControl = new ExplanationControl();
  private final transient CancelControl cancelControl = new CancelControl();
  private final transient ScoreboardControl scoreboardControl = new ScoreboardControl();
  
  private transient DPFPCapture capture = DPFPGlobal.getCaptureFactory().createCapture();
  DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();
  protected transient boolean finishing = false;
  protected transient boolean paused = false;
  private transient DPFPReaderImpressionType impressionType = DPFPReaderImpressionType.READER_IMPRESSION_TYPE_AREA;
  
  private transient String serialNumber;
  private transient DPFPFingerIndex finger;
  private static ResourceBundle labels = ResourceBundle.getBundle("dpfpui");
  

  public CapturePanel()
  {
    setLayout(new java.awt.BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setOpaque(false);
    
    prompt.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    add(prompt, "First");
    
    JPanel localJPanel1 = Utilities.getXBox();
    localJPanel1.add(sensorControl);
    localJPanel1.add(javax.swing.Box.createRigidArea(new Dimension(10, 10)));
    localJPanel1.add(miniHandsControl);
    
    JPanel localJPanel2 = Utilities.getXBox();
    localJPanel2.add(scoreboardControl);
    
    JPanel localJPanel3 = Utilities.getXBorder();
    localJPanel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    localJPanel3.add(localJPanel2, "First");
    explanationControl.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    localJPanel3.add(explanationControl, "Center");
    localJPanel3.add(cancelControl, "Last");
    
    add(localJPanel1, "Before");
    add(localJPanel3, "Center");
    
    cancelControl.addCancelListener(new CancelListener()
    {
      public void enrollmentCancelled(CancelEvent paramAnonymousCancelEvent) {
        paused = true;
        if (javax.swing.JOptionPane.showConfirmDialog(CapturePanel.this, String.format(CapturePanel.labels.getString("IDS_CANCEL_PROMPT"), new Object[] { Utilities.fingerprintName(finger) }), CapturePanel.labels.getString("IDS_CANCEL_TITLE"), 0) == 0)
        {



          cancel();
        } else {
          paused = false;
        }
        
      }
    });
    addHierarchyListener(new java.awt.event.HierarchyListener()
    {
      public void hierarchyChanged(HierarchyEvent paramAnonymousHierarchyEvent) {
        if ((paramAnonymousHierarchyEvent.getID() == 1400) && ((paramAnonymousHierarchyEvent.getChangeFlags() & 0x4) != 0L) && (!isShowing()))
        {


          stop(); }
      }
    });
    addPropertyChangeListener(new java.beans.PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent paramAnonymousPropertyChangeEvent) {
        String str = paramAnonymousPropertyChangeEvent.getPropertyName();
        if (str.equals("foreground")) {
          if (prompt != null) prompt.setForeground((Color)paramAnonymousPropertyChangeEvent.getNewValue());
          if (explanationControl != null) explanationControl.setForeground((Color)paramAnonymousPropertyChangeEvent.getNewValue());
          if (cancelControl != null) cancelControl.setForeground((Color)paramAnonymousPropertyChangeEvent.getNewValue());
        }
        else if (str.equals("font")) {
          if (prompt != null) prompt.setFont((Font)paramAnonymousPropertyChangeEvent.getNewValue());
          if (explanationControl != null) explanationControl.setFont((Font)paramAnonymousPropertyChangeEvent.getNewValue());
          if (cancelControl != null) { cancelControl.setFont((Font)paramAnonymousPropertyChangeEvent.getNewValue());
          }
          
        }
        
      }
    });
    capture.addDataListener(new com.digitalpersona.onetouch.capture.event.DPFPDataListener()
    {
      public void dataAcquired(final DPFPDataEvent paramAnonymousDPFPDataEvent)
      {
        if ((finishing) || (paused)) {
          return;
        }
        swingUtilities.invokeLater(new Runnable() {
          public void run() { if ((serialNumber == null) && 
              (paramAnonymousDPFPDataEvent.getSource() != null)) {
              try {
                CapturePanel.this.updateImpressionType((String)paramAnonymousDPFPDataEvent.getSource());
              }
              catch (Exception localException) {}
            }
            
            DPFPSample localDPFPSample = paramAnonymousDPFPDataEvent.getSample();
            try {
              if (localDPFPSample == null) {
                sensorControl.showFailed();
                return;
              }
              sensorControl.showTouched();
              
              DPFPFeatureExtraction localDPFPFeatureExtraction = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
              DPFPFeatureSet localDPFPFeatureSet = localDPFPFeatureExtraction.createFeatureSet(localDPFPSample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
              enrollment.addFeatures(localDPFPFeatureSet);
              scoreboardControl.setScore(scoreboardControl.getScore() + 1);
              explanationControl.displayQuality(impressionType, DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD);
              if (enrollment.getTemplateStatus() == DPFPTemplateStatus.TEMPLATE_STATUS_READY) {
                CaptureEvent localCaptureEvent = new CaptureEvent(CapturePanel.this, 1, finger, enrollment.getTemplate());
                for (CaptureListener localCaptureListener : (CaptureListener[])getListeners(CaptureListener.class)) {
                  localCaptureListener.fingerCaptured(localCaptureEvent);
                }
              }
            } catch (DPFPImageQualityException localDPFPImageQualityException) {
              sensorControl.showFailed();
              explanationControl.displayQuality(impressionType, localDPFPImageQualityException.getCaptureFeedback());
              if (enrollment.getTemplateStatus() == DPFPTemplateStatus.TEMPLATE_STATUS_FAILED) {
                reset();
                showPopup(finger, String.format(CapturePanel.labels.getString("IDS_BAD_IMAGES"), new Object[] { Utilities.fingerName(finger) }), CapturePanel.labels.getString("IDS_REG_FAILED"));
              }
              else {
                showPopup(finger, Utilities.getFeedbackString(impressionType, localDPFPImageQualityException.getCaptureFeedback()), CapturePanel.labels.getString("IDS_SCAN_BAD_IMAGE"));
              }
            }
          }
        });
      }
    });
  }
  
  public void start(DPFPFingerIndex paramDPFPFingerIndex) { if (!isStopped())
      return;
    finger = paramDPFPFingerIndex;
    miniHandsControl.setFinger(paramDPFPFingerIndex);
    explanationControl.setFinger(paramDPFPFingerIndex);
    
    String str = getReaderSerialNumber();
    

    if (str != null) {
      updateImpressionType(str);
    }
    
    prompt.setText(String.format(labels.getString("IDS_SCAN_HEADER"), new Object[] { Utilities.fingerName(paramDPFPFingerIndex) }));
    

    capture.setReaderSerialNumber(str);
    
    reset();
    capture.startCapture();
  }
  
  public void reset() {
    enrollment.clear();
    scoreboardControl.setScore(0);
    explanationControl.displayStart(impressionType);
  }
  





  public void stop()
  {
    if ((capture != null) && (!finishing))
    {
      finishing = true;
      swingUtilities.invokeLater(new Runnable() {
        public void run() {
          try {
            capture.stopCapture();
            
            for (CaptureListener localCaptureListener : (CaptureListener[])getListeners(CaptureListener.class))
              localCaptureListener.captureStopped();
            finishing = false;
            paused = false;
          } catch (Exception localException) {}
        }
      });
    }
  }
  
  public boolean isStopped() { return (capture == null) || (!capture.isStarted()) || (finishing); }
  

  public void cancel()
  {
    stop();
    CaptureEvent localCaptureEvent = new CaptureEvent(this, 0, finger, null);
    for (CaptureListener localCaptureListener : (CaptureListener[])getListeners(CaptureListener.class))
      localCaptureListener.fingerCancelled(localCaptureEvent);
  }
  
  public DPFPCapture getCapture() {
    return capture;
  }
  
  public DPFPFingerIndex getFinger() {
    return finger;
  }
  
  public void showPopup(DPFPFingerIndex paramDPFPFingerIndex, String paramString1, String paramString2) {
    BalloonControl.popup(scoreboardControl, paramString1, paramString2);
  }
  




  public String getReaderSerialNumber()
  {
    return serialNumber;
  }
  




  public void setReaderSerialNumber(String paramString)
  {
    serialNumber = paramString;
  }
  



  public void addCaptureListener(CaptureListener paramCaptureListener)
  {
    listenerList.add(CaptureListener.class, paramCaptureListener);
  }
  



  public void removeCaptureListener(CaptureListener paramCaptureListener)
  {
    listenerList.remove(CaptureListener.class, paramCaptureListener);
  }
  
  private void updateImpressionType(String paramString) {
    DPFPReadersCollection localDPFPReadersCollection = DPFPGlobal.getReadersFactory().getReaders();
    DPFPReaderDescription localDPFPReaderDescription = localDPFPReadersCollection.get(paramString);
    impressionType = localDPFPReaderDescription.getImpressionType();
  }
}
