package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.event.EventListenerList;

public class DPFPEnrollmentControl extends JPanel implements Serializable
{
  private static final long serialVersionUID = 752362817229178959L;
  private static final String HANDS_PAGE = "Hands";
  private static final String CAPTURE_PAGE = "Capture";
  private final DPFPSwingUtilities swingUtilities = new DPFPSwingUtilities();
  private transient HandsPanel handsPanel = new HandsPanel();
  private transient CapturePanel capturePanel = new CapturePanel();
  private transient JPanel center = new JPanel(new CardLayout());
  
  static final int PREFFERED_WIDTH = 480;
  static final int PREFFERED_HEIGHT = 320;
  static final String DPFPUI_PROPERTIES = "dpfpui";
  static final String ENROLLED_FINGERS_PROPERTY = "enrolledFingers";
  static final String MAX_ENROLLED_FINGER_COUNT = "maxEnrollFingerCount";
  static final String READER_SERIAL_NUMBER_PROPERTY = "readerSerialNumber";
  private static ResourceBundle labels = ResourceBundle.getBundle("dpfpui");
  
  static class TopPanel extends JPanel {
    private static final String topFingerName = "/images/topfinger.png";
    private static final ImageIcon topFingerIcon;
    
    static {
      try {
        topFingerIcon = new ImageIcon(ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream("/images/topfinger.png")));
      } catch (IOException localIOException) {
        throw new RuntimeException(localIOException);
      }
    }
    
    public TopPanel() {
      SpringLayout localSpringLayout = new SpringLayout();
      setLayout(localSpringLayout);
      
      setBorder(BorderFactory.createEtchedBorder());
      setBackground(Color.WHITE);
      
      JLabel localJLabel1 = new JLabel("<html><font size='5'>" + DPFPEnrollmentControl.labels.getString("IDS_WTITLE") + "</font></html>");
      JLabel localJLabel2 = new JLabel("<html><font size='2'>" + DPFPEnrollmentControl.labels.getString("IDS_SUBTITLE") + "</font></html>");
      JLabel localJLabel3 = new JLabel(topFingerIcon);
      
      add(localJLabel1);
      localSpringLayout.putConstraint("West", localJLabel1, 8, "West", this);
      localSpringLayout.putConstraint("North", localJLabel1, 4, "North", this);
      
      add(localJLabel2);
      localSpringLayout.putConstraint("West", localJLabel2, 20, "West", localJLabel1);
      localSpringLayout.putConstraint("North", localJLabel2, 4, "South", localJLabel1);
      
      add(localJLabel3);
      localSpringLayout.putConstraint("East", localJLabel3, -8, "East", this);
      localSpringLayout.putConstraint("North", localJLabel3, 2, "North", this);
      localSpringLayout.putConstraint("South", this, 4, "South", localJLabel3);
    }
  }
  


  public DPFPEnrollmentControl()
  {
    setPreferredSize(new Dimension(480, 320));
    
    center.setOpaque(false);
    center.add(handsPanel, "Hands");
    center.add(capturePanel, "Capture");
    
    setLayout(new BorderLayout());
    add(new TopPanel(), "First");
    add(center, "Center");
    
    center.setBorder(BorderFactory.createEtchedBorder());
    

    handsPanel.addFingerToggleListener(new FingerToggleListener()
    {
      public void fingerAdded(FingerTogglelEvent paramAnonymousFingerTogglelEvent) {
        capturePanel.start(paramAnonymousFingerTogglelEvent.getFinger());
      }
      
      public void fingerRemoved(FingerTogglelEvent paramAnonymousFingerTogglelEvent) {
        EnumSet localEnumSet = handsPanel.getEnrolledFingers();
        DPFPFingerIndex localDPFPFingerIndex = paramAnonymousFingerTogglelEvent.getFinger();
        if (JOptionPane.showConfirmDialog(DPFPEnrollmentControl.this, String.format(DPFPEnrollmentControl.labels.getString("IDS_PROMPT_DELETE_FP"), new Object[] { Utilities.fingerprintName(localDPFPFingerIndex) }), DPFPEnrollmentControl.labels.getString("IDS_CANCEL_TITLE"), 0) == 0)
        {


          localEnumSet.remove(localDPFPFingerIndex);
          
          DPFPEnrollmentEvent localDPFPEnrollmentEvent = new DPFPEnrollmentEvent(this, 1, localDPFPFingerIndex);
          try {
            for (DPFPEnrollmentListener localDPFPEnrollmentListener : (DPFPEnrollmentListener[])getListeners(DPFPEnrollmentListener.class))
              localDPFPEnrollmentListener.fingerDeleted(localDPFPEnrollmentEvent);
          } catch (DPFPEnrollmentVetoException localDPFPEnrollmentVetoException) {
            localDPFPEnrollmentEvent.setPerformed(false);
          } finally {
            if (localDPFPEnrollmentEvent.getStopCapture())
              capturePanel.stop();
            if (localDPFPEnrollmentEvent.getPerformed()) {
              handsPanel.setEnrolledFingers(localEnumSet);
            } else
              localEnumSet.add(localDPFPFingerIndex);
            if (!localDPFPEnrollmentEvent.getPerformed()) {
              DPFPEnrollmentControl.this.showPopup(localDPFPFingerIndex, String.format(DPFPEnrollmentControl.labels.getString("IDS_FP_NOT_DELETED"), new Object[] { Utilities.fingerprintName(localDPFPFingerIndex) }), DPFPEnrollmentControl.labels.getString("IDS_FP_NOT_DELETED_TITLE"));
            }
            
          }
          
        }
        
      }
      

    });
    capturePanel.addCaptureListener(new CaptureListener()
    {
      public void fingerCaptured(CaptureEvent paramAnonymousCaptureEvent) {
        EnumSet localEnumSet = handsPanel.getEnrolledFingers();
        DPFPFingerIndex localDPFPFingerIndex = paramAnonymousCaptureEvent.getFinger();
        localEnumSet.add(localDPFPFingerIndex);
        
        DPFPEnrollmentEvent localDPFPEnrollmentEvent = new DPFPEnrollmentEvent(this, 0, localDPFPFingerIndex, paramAnonymousCaptureEvent.getTemplate());
        try {
          for (DPFPEnrollmentListener localDPFPEnrollmentListener : (DPFPEnrollmentListener[])getListeners(DPFPEnrollmentListener.class))
            localDPFPEnrollmentListener.fingerEnrolled(localDPFPEnrollmentEvent);
        } catch (DPFPEnrollmentVetoException localDPFPEnrollmentVetoException) {
          localDPFPEnrollmentEvent.setPerformed(false);
        } finally {
          if (localDPFPEnrollmentEvent.getStopCapture())
            capturePanel.stop();
          if (localDPFPEnrollmentEvent.getPerformed()) {
            handsPanel.setEnrolledFingers(localEnumSet);
          } else
            localEnumSet.remove(localDPFPFingerIndex);
          if (!localDPFPEnrollmentEvent.getPerformed()) {
            DPFPEnrollmentControl.this.showPopup(localDPFPFingerIndex, String.format(DPFPEnrollmentControl.labels.getString("IDS_FP_NOT_ENROLLED"), new Object[] { Utilities.fingerprintName(localDPFPFingerIndex) }), DPFPEnrollmentControl.labels.getString("IDS_FP_NOT_ENROLLED_TITLE"));
            


            capturePanel.reset();
          }
        }
      }
      
      public void fingerCancelled(CaptureEvent paramAnonymousCaptureEvent) {
        DPFPEnrollmentControl.this.showPage("Hands");
      }
      
      public void captureStopped() {
        DPFPEnrollmentControl.this.showPage("Hands");

      }
      


    });
    capturePanel.getCapture().addReaderStatusListener(new DPFPReaderStatusListener()
    {

      public void readerConnected(DPFPReaderStatusEvent paramAnonymousDPFPReaderStatusEvent)
      {
        swingUtilities.invokeLater(new Runnable() {
          public void run() {
            DPFPEnrollmentControl.this.showPage("Capture");
          }
        });
      }
      
      public void readerDisconnected(DPFPReaderStatusEvent paramAnonymousDPFPReaderStatusEvent) { swingUtilities.invokeLater(new Runnable() {
          public void run() {
            capturePanel.cancel();
            DPFPEnrollmentControl.this.showPopup(capturePanel.getFinger(), DPFPEnrollmentControl.labels.getString("IDS_REG_DISCONNECT"), DPFPEnrollmentControl.labels.getString("IDS_REG_CANCELLED"));
          }
          

        }); }
    });
    addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent paramAnonymousPropertyChangeEvent) {
        String str = paramAnonymousPropertyChangeEvent.getPropertyName();
        if (str.equals("enabled")) {
          if (handsPanel != null) handsPanel.setEnabled(((Boolean)paramAnonymousPropertyChangeEvent.getNewValue()).booleanValue());
          if (capturePanel != null) capturePanel.setEnabled(((Boolean)paramAnonymousPropertyChangeEvent.getNewValue()).booleanValue());
        }
        else if (str.equals("foreground")) {
          if (handsPanel != null) handsPanel.setForeground((Color)paramAnonymousPropertyChangeEvent.getNewValue());
          if (capturePanel != null) capturePanel.setForeground((Color)paramAnonymousPropertyChangeEvent.getNewValue());
        }
        else if (str.equals("font")) {
          if (handsPanel != null) handsPanel.setFont((Font)paramAnonymousPropertyChangeEvent.getNewValue());
          if (capturePanel != null) capturePanel.setFont((Font)paramAnonymousPropertyChangeEvent.getNewValue());
        }
      }
    });
    showPage("Hands");
  }
  
  private void showPage(String paramString) {
    CardLayout localCardLayout = (CardLayout)center.getLayout();
    localCardLayout.show(center, paramString);
  }
  
  private void showPopup(DPFPFingerIndex paramDPFPFingerIndex, String paramString1, String paramString2) {
    if (capturePanel.isStopped()) {
      showPage("Hands");
      handsPanel.showPopup(paramDPFPFingerIndex, paramString1, paramString2);
    } else {
      showPage("Capture");
      capturePanel.showPopup(paramDPFPFingerIndex, paramString1, paramString2);
    }
  }
  




  public EnumSet<DPFPFingerIndex> getEnrolledFingers()
  {
    return handsPanel.getEnrolledFingers();
  }
  




  public void setEnrolledFingers(EnumSet<DPFPFingerIndex> paramEnumSet)
  {
    EnumSet localEnumSet = getEnrolledFingers();
    handsPanel.setEnrolledFingers(paramEnumSet);
    firePropertyChange("enrolledFingers", localEnumSet, getEnrolledFingers());
  }
  




  public int getMaxEnrollFingerCount()
  {
    return handsPanel.getMaxEnrollFingerCount();
  }
  




  public void setMaxEnrollFingerCount(int paramInt)
  {
    int i = getMaxEnrollFingerCount();
    handsPanel.setMaxEnrollFingerCount(paramInt);
    firePropertyChange("maxEnrollFingerCount", i, getMaxEnrollFingerCount());
  }
  




  public String getReaderSerialNumber()
  {
    return capturePanel.getReaderSerialNumber();
  }
  





  public void setReaderSerialNumber(String paramString)
  {
    String str = getReaderSerialNumber();
    capturePanel.stop();
    capturePanel.setReaderSerialNumber(paramString);
    firePropertyChange("readerSerialNumber", str, getReaderSerialNumber());
  }
  




  public void addEnrollmentListener(DPFPEnrollmentListener paramDPFPEnrollmentListener)
  {
    listenerList.add(DPFPEnrollmentListener.class, paramDPFPEnrollmentListener);
  }
  




  public void removeEnrollmentListener(DPFPEnrollmentListener paramDPFPEnrollmentListener)
  {
    listenerList.remove(DPFPEnrollmentListener.class, paramDPFPEnrollmentListener);
  }
}
