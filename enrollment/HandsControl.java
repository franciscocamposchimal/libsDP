package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

class HandsControl extends JPanel
{
  private static final String maskFilename = "/images/hands1.png";
  private static final BufferedImage maskImage;
  private static final String palmsFilename = "/images/palms.png";
  private static final BufferedImage palmsImage;
  
  static
  {
    try
    {
      maskImage = ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream("/images/hands1.png"));
      palmsImage = ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream("/images/palms.png"));
    } catch (IOException localIOException) {
      throw new RuntimeException(localIOException);
    }
  }
  
  private EnumSet<DPFPFingerIndex> fingers = EnumSet.noneOf(DPFPFingerIndex.class);
  private EnumMap<DPFPFingerIndex, FingerControl> fingerLabels = new EnumMap(DPFPFingerIndex.class);
  
  public HandsControl()
  {
    setOpaque(false);
    setLayout(null);
    Dimension localDimension = new Dimension(maskImage.getWidth(), maskImage.getHeight());
    setMinimumSize(localDimension);
    setMaximumSize(localDimension);
    setPreferredSize(localDimension);
    
    JLabel localJLabel = new JLabel(new javax.swing.ImageIcon(palmsImage));
    localJLabel.setSize(localDimension);
    localJLabel.setLocation(0, 0);
    localJLabel.setVisible(true);
    
    add(localJLabel);
    
    for (DPFPFingerIndex localDPFPFingerIndex : DPFPFingerIndex.values()) {
      FingerControl localFingerControl = new FingerControl(localDPFPFingerIndex);
      fingerLabels.put(localDPFPFingerIndex, localFingerControl);
      add(localFingerControl);
    }
    
    addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseReleased(MouseEvent paramAnonymousMouseEvent) {
        if (!isEnabled()) return;
        Point localPoint = paramAnonymousMouseEvent.getPoint();
        int i = HandsControl.this.getFingerForPoint(localPoint);
        if (i != -1) {
          DPFPFingerIndex localDPFPFingerIndex = DPFPFingerIndex.values()[i];
          FingerTogglelEvent localFingerTogglelEvent; FingerToggleListener localFingerToggleListener; if (fingers.contains(localDPFPFingerIndex)) {
            localFingerTogglelEvent = new FingerTogglelEvent(HandsControl.this, 0, localDPFPFingerIndex);
            for (localFingerToggleListener : (FingerToggleListener[])getListeners(FingerToggleListener.class))
              localFingerToggleListener.fingerRemoved(localFingerTogglelEvent);
          } else {
            localFingerTogglelEvent = new FingerTogglelEvent(HandsControl.this, 1, localDPFPFingerIndex);
            for (localFingerToggleListener : (FingerToggleListener[])getListeners(FingerToggleListener.class)) {
              localFingerToggleListener.fingerAdded(localFingerTogglelEvent);
            }
          }
        }
      }
    });
    addMouseMotionListener(new java.awt.event.MouseMotionListener()
    {
      private int previousFinger = -1;
      
      public void mouseDragged(MouseEvent paramAnonymousMouseEvent) {}
      
      public void mouseMoved(MouseEvent paramAnonymousMouseEvent) {
        if (!isEnabled()) return;
        Point localPoint = paramAnonymousMouseEvent.getPoint();
        int i = HandsControl.this.getFingerForPoint(localPoint);
        if (previousFinger == i) {
          return;
        }
        if (previousFinger != -1) {
          ((FingerControl)fingerLabels.get(DPFPFingerIndex.values()[previousFinger])).setSelected(false);
        }
        previousFinger = i;
        
        if (i != -1) {
          setCursor(Cursor.getPredefinedCursor(12));
          ((FingerControl)fingerLabels.get(DPFPFingerIndex.values()[i])).setSelected(true);
        } else {
          setCursor(Cursor.getPredefinedCursor(0));
        }
        repaint();
      }
    });
  }
  






  public EnumSet<DPFPFingerIndex> getEnrolledFingers()
  {
    return fingers;
  }
  




  public void setEnrolledFingers(EnumSet<DPFPFingerIndex> paramEnumSet)
  {
    fingers = paramEnumSet;
    for (DPFPFingerIndex localDPFPFingerIndex : DPFPFingerIndex.values())
      ((FingerControl)fingerLabels.get(localDPFPFingerIndex)).setEnrolled(paramEnumSet.contains(localDPFPFingerIndex));
  }
  
  public void showPopup(DPFPFingerIndex paramDPFPFingerIndex, String paramString1, String paramString2) {
    BalloonControl.popup((Component)fingerLabels.get(paramDPFPFingerIndex), paramString1, paramString2);
  }
  
  public void addFingerToggleListener(FingerToggleListener paramFingerToggleListener) {
    listenerList.add(FingerToggleListener.class, paramFingerToggleListener);
  }
  
  public void removeFingerToggleListener(FingerToggleListener paramFingerToggleListener) {
    listenerList.remove(FingerToggleListener.class, paramFingerToggleListener);
  }
  
  private int getFingerForPoint(Point paramPoint) {
    if ((x < 0) || (x >= maskImage.getWidth()) || (y < 0) || (y >= maskImage.getHeight()))
      return -1;
    int i = maskImage.getRGB(x, y);
    int j = (i & 0xF0) >> 4;
    if (((i & 0xF) == 0) && ((i & 0xFF) == (i >> 8 & 0xFF)) && ((i & 0xFF) == (i >> 16 & 0xFF)) && (j >= 1) && (j <= 10))
    {


      return j - 1;
    }
    return -1;
  }
}
