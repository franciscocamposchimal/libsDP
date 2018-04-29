package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.util.EnumSet;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

class HandsPanel extends JPanel
{
  private static ResourceBundle labels = ResourceBundle.getBundle("dpfpui");
  
  private int maxCount;
  private HandsControl handsControl = new HandsControl();
  private JEditorPane promptLabel = Utilities.getXTextPane();
  
  public HandsPanel() {
    setOpaque(false);
    

    promptLabel.setText(labels.getString("IDS_REGPAGE_HEADER"));
    
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setLayout(new BorderLayout());
    
    JPanel localJPanel = Utilities.getXBox();
    localJPanel.add(javax.swing.Box.createRigidArea(new Dimension(10, 10)));
    localJPanel.add(handsControl);
    
    add(promptLabel, "First");
    add(localJPanel, "Center");
    
    maxCount = DPFPFingerIndex.values().length;
    
    handsControl.addFingerToggleListener(new FingerToggleListener() {
      public void fingerAdded(FingerTogglelEvent paramAnonymousFingerTogglelEvent) {
        if (getEnrolledFingers().size() < maxCount) {
          for (FingerToggleListener localFingerToggleListener : (FingerToggleListener[])getListeners(FingerToggleListener.class)) {
            try {
              localFingerToggleListener.fingerAdded(paramAnonymousFingerTogglelEvent);
            } catch (Exception localException) {}
          }
        } else {
          JOptionPane.showMessageDialog(HandsPanel.this, HandsPanel.labels.getString("IDS_MAX_FINGERS"), HandsPanel.labels.getString("IDS_WIZARD_TITLE"), 0);
        }
      }
      
      public void fingerRemoved(FingerTogglelEvent paramAnonymousFingerTogglelEvent)
      {
        for (FingerToggleListener localFingerToggleListener : (FingerToggleListener[])getListeners(FingerToggleListener.class)) {
          try {
            localFingerToggleListener.fingerRemoved(paramAnonymousFingerTogglelEvent);
          }
          catch (Exception localException) {}
        }
      }
    });
    addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent paramAnonymousPropertyChangeEvent) {
        String str = paramAnonymousPropertyChangeEvent.getPropertyName();
        if (str.equals("enabled")) {
          if (promptLabel != null) promptLabel.setEnabled(((Boolean)paramAnonymousPropertyChangeEvent.getNewValue()).booleanValue());
          if (handsControl != null) handsControl.setEnabled(((Boolean)paramAnonymousPropertyChangeEvent.getNewValue()).booleanValue());
        }
        else if (str.equals("foreground")) {
          if (promptLabel != null) promptLabel.setForeground((Color)paramAnonymousPropertyChangeEvent.getNewValue());
        }
        else if ((str.equals("font")) && 
          (promptLabel != null)) { promptLabel.setFont((Font)paramAnonymousPropertyChangeEvent.getNewValue());
        }
      }
    });
  }
  




  public EnumSet<DPFPFingerIndex> getEnrolledFingers()
  {
    return handsControl.getEnrolledFingers();
  }
  




  public void setEnrolledFingers(EnumSet<DPFPFingerIndex> paramEnumSet)
  {
    handsControl.setEnrolledFingers(paramEnumSet);
  }
  




  public int getMaxEnrollFingerCount()
  {
    return maxCount;
  }
  




  public void setMaxEnrollFingerCount(int paramInt)
  {
    maxCount = paramInt;
  }
  
  public void showPopup(DPFPFingerIndex paramDPFPFingerIndex, String paramString1, String paramString2) {
    handsControl.showPopup(paramDPFPFingerIndex, paramString1, paramString2);
  }
  
  public void addFingerToggleListener(FingerToggleListener paramFingerToggleListener) {
    listenerList.add(FingerToggleListener.class, paramFingerToggleListener);
  }
  
  public void removeFingerToggleListener(FingerToggleListener paramFingerToggleListener) {
    listenerList.remove(FingerToggleListener.class, paramFingerToggleListener);
  }
}
