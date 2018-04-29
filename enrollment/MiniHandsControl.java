package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



class MiniHandsControl
  extends JLabel
{
  private final DPFPSwingUtilities swingUtilities = new DPFPSwingUtilities();
  private static final String formatString = "/images/minihands_%d.png";
  private static final ImageIcon[] icons;
  
  static
  {
    try {
      icons = new ImageIcon[DPFPFingerIndex.values().length];
      for (int i = 0; i < icons.length; i++) {
        icons[i] = new ImageIcon(ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream(String.format("/images/minihands_%d.png", new Object[] { Integer.valueOf(i) }))));
      }
    } catch (IOException localIOException) {
      throw new RuntimeException(localIOException);
    }
  }
  
  public void setFinger(final DPFPFingerIndex paramDPFPFingerIndex) {
    swingUtilities.invokeLater(new Runnable() {
      public void run() {
        setIcon(MiniHandsControl.icons[paramDPFPFingerIndex.ordinal()]);
      }
    });
  }
  
  MiniHandsControl() {}
}
