package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.EnumMap;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;




















class FingerControl
  extends JLabel
{
  private DPFPFingerIndex finger;
  private int state;
  private static final String format = "/images/finger_%d_%d.png";
  private static final EnumMap<DPFPFingerIndex, ImageIcon[]> fingerIcons = new EnumMap(DPFPFingerIndex.class);
  



  private static final EnumMap<DPFPFingerIndex, Point> fingerLocations = new EnumMap(DPFPFingerIndex.class);
  
  private static final int[] fingerMnemonics = { 49, 50, 51, 52, 53, 54, 55, 56, 57, 48 };
  



  private static final int SELECTION_MASK = 1;
  



  private static final int ENROLLMENT_MASK = 2;
  


  private static ResourceBundle labels = ResourceBundle.getBundle("dpfpui");
  
  static
  {
    try
    {
      for (DPFPFingerIndex localDPFPFingerIndex : DPFPFingerIndex.values()) {
        int k = localDPFPFingerIndex.ordinal();
        ImageIcon[] arrayOfImageIcon = new ImageIcon[4];
        for (int m = 0; m < arrayOfImageIcon.length; m++) {
          arrayOfImageIcon[m] = new ImageIcon(ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream(String.format("/images/finger_%d_%d.png", new Object[] { Integer.valueOf(k), Integer.valueOf(m) }))));
        }
        fingerIcons.put(localDPFPFingerIndex, arrayOfImageIcon);
      }
      

      fingerLocations.put(DPFPFingerIndex.LEFT_PINKY, new Point(20, 20));
      fingerLocations.put(DPFPFingerIndex.LEFT_RING, new Point(40, 0));
      fingerLocations.put(DPFPFingerIndex.LEFT_MIDDLE, new Point(64, 0));
      fingerLocations.put(DPFPFingerIndex.LEFT_INDEX, new Point(80, 20));
      fingerLocations.put(DPFPFingerIndex.LEFT_THUMB, new Point(96, 80));
      fingerLocations.put(DPFPFingerIndex.RIGHT_THUMB, new Point(152, 80));
      fingerLocations.put(DPFPFingerIndex.RIGHT_INDEX, new Point(168, 20));
      fingerLocations.put(DPFPFingerIndex.RIGHT_MIDDLE, new Point(188, 0));
      fingerLocations.put(DPFPFingerIndex.RIGHT_RING, new Point(220, 0));
      fingerLocations.put(DPFPFingerIndex.RIGHT_PINKY, new Point(248, 20));
    } catch (IOException localIOException) {
      throw new RuntimeException(localIOException);
    }
  }
  




  public FingerControl(DPFPFingerIndex paramDPFPFingerIndex)
  {
    finger = paramDPFPFingerIndex;
    state = 0;
    
    setLocation((Point)fingerLocations.get(paramDPFPFingerIndex));
    Dimension localDimension = new Dimension(((ImageIcon[])fingerIcons.get(paramDPFPFingerIndex))[0].getIconWidth(), ((ImageIcon[])fingerIcons.get(paramDPFPFingerIndex))[0].getIconHeight());
    setSize(localDimension);
    setPreferredSize(localDimension);
    setMinimumSize(localDimension);
    setMaximumSize(localDimension);
    

    updateImage();
  }
  


  private void updateImage()
  {
    setIcon(((ImageIcon[])fingerIcons.get(finger))[state]);
  }
  




  public void setSelected(boolean paramBoolean)
  {
    state = (paramBoolean ? state | 0x1 : state & 0xFFFFFFFE);
    updateImage();
  }
  




  public void setEnrolled(boolean paramBoolean)
  {
    int i = isEnrolled() != paramBoolean ? 1 : 0;
    state = (paramBoolean ? state | 0x2 : state & 0xFFFFFFFD);
    updateImage();
    if ((isValid()) && (isVisible()) && (i != 0)) {
      String str1 = paramBoolean ? labels.getString("IDS_FP_ENROLLED_TITLE") : labels.getString("IDS_FP_DELETED_TITLE");
      String str2 = paramBoolean ? String.format(labels.getString("IDS_FP_ENROLLED"), new Object[] { Utilities.fingerprintName(finger) }) : String.format(labels.getString("IDS_FP_DELETED"), new Object[] { Utilities.fingerprintName(finger) });
      

      BalloonControl.popup(this, str2, str1);
    }
  }
  




  public boolean isSelected()
  {
    return (state & 0x1) != 0;
  }
  




  public boolean isEnrolled()
  {
    return (state & 0x2) != 0;
  }
}
