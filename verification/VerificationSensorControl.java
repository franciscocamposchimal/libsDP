package com.digitalpersona.onetouch.ui.swing;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;













class VerificationSensorControl
  extends JLabel
{
  private static final String[] filenames = { "/images/finger_default.png", "/images/finger_matched.png", "/images/finger_unknown.png", "/images/finger_disabled.png" };
  

  private static final ImageIcon[] icons;
  
  private static final int ANIMATION_TIMEOUT = 1000;
  
  private boolean bStopDelayedShowNormal = false;
  
  static {
    try {
      icons = new ImageIcon[filenames.length];
      for (int i = 0; i < icons.length; i++)
        icons[i] = new ImageIcon(ImageIO.read(DPFPVerificationControl.class.getResourceAsStream(filenames[i])));
    } catch (IOException localIOException) {
      throw new RuntimeException(localIOException);
    }
  }
  
  private final DPFPSwingUtilities swingUtilities = new DPFPSwingUtilities();
  
  public VerificationSensorControl() {
    super(icons[0]);
  }
  
  public void showMatched(final boolean paramBoolean)
  {
    new Thread(new Runnable() {
      public void run() {
        swingUtilities.invokeLater(new Runnable() {
          public void run() {
            setIcon(VerificationSensorControl.icons[1]);
          }
        });
        if (paramBoolean) {
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException localInterruptedException) {
            return;
          }
          if (bStopDelayedShowNormal) {
            bStopDelayedShowNormal = false;
            return;
          }
          swingUtilities.invokeLater(new Runnable() {
            public void run() {
              setIcon(VerificationSensorControl.icons[0]);
            }
          });
        }
      }
    }).start();
  }
  
  public void showFailed(final boolean paramBoolean)
  {
    new Thread(new Runnable() {
      public void run() {
        swingUtilities.invokeLater(new Runnable() {
          public void run() {
            setIcon(VerificationSensorControl.icons[2]);
          }
        });
        if (paramBoolean) {
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException localInterruptedException) {
            return;
          }
          if (bStopDelayedShowNormal) {
            bStopDelayedShowNormal = false;
            return;
          }
          swingUtilities.invokeLater(new Runnable() {
            public void run() {
              setIcon(VerificationSensorControl.icons[0]);
            }
          });
        }
      }
    }).start();
  }
  
  public void showNormal() {
    bStopDelayedShowNormal = false;
    swingUtilities.invokeLater(new Runnable()
    {
      public void run() { setIcon(VerificationSensorControl.icons[0]); }
    });
  }
  
  public void showDisabled() {
    bStopDelayedShowNormal = true;
    swingUtilities.invokeLater(new Runnable() {
      public void run() {
        setIcon(VerificationSensorControl.icons[3]);
      }
    });
  }
}
