package com.digitalpersona.onetouch.ui.swing;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



class SensorControl
  extends JLabel
{
  private static final String[] filenames = { "/images/emptySensor.png", "/images/touchedSensor.png", "/images/failedSensor.png" };
  
  private static final ImageIcon[] icons;
  private static final int ANIMATION_TIMEOUT = 1000;
  
  static
  {
    try
    {
      icons = new ImageIcon[filenames.length];
      for (int i = 0; i < icons.length; i++) {
        icons[i] = new ImageIcon(ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream(filenames[i])));
      }
    } catch (IOException localIOException) {
      throw new RuntimeException(localIOException);
    }
  }
  
  private final DPFPSwingUtilities swingUtilities = new DPFPSwingUtilities();
  
  public SensorControl() {
    super(icons[0]);
  }
  
  public void showTouched() {
    new Thread(new Runnable() {
      public void run() {
        swingUtilities.invokeLater(new Runnable() {
          public void run() {
            setIcon(SensorControl.icons[1]);
          }
        });
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException localInterruptedException) {
          return;
        }
        swingUtilities.invokeLater(new Runnable() {
          public void run() {
            setIcon(SensorControl.icons[0]);
          }
        });
      }
    }).start();
  }
  
  public void showFailed() {
    new Thread(new Runnable() {
      public void run() {
        swingUtilities.invokeLater(new Runnable() {
          public void run() {
            setIcon(SensorControl.icons[2]);
          }
        });
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException localInterruptedException) {
          return;
        }
        swingUtilities.invokeLater(new Runnable() {
          public void run() {
            setIcon(SensorControl.icons[0]);
          }
        });
      }
    }).start();
  }
}
