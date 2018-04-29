package com.digitalpersona.onetouch.ui.swing;

import java.awt.Dimension;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



class ScoreboardControl
  extends JLabel
{
  private static final String[] filenames = { "/images/score0.png", "/images/score1.png", "/images/score2.png", "/images/score3.png", "/images/score4.png" };
  




  private final DPFPSwingUtilities swingUtilities = new DPFPSwingUtilities();
  
  private static final ImageIcon[] icons;
  private int score = 0;
  
  static {
    try {
      icons = new ImageIcon[filenames.length];
      for (int i = 0; i < icons.length; i++) {
        icons[i] = new ImageIcon(ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream(filenames[i])));
      }
    } catch (IOException localIOException) {
      throw new RuntimeException(localIOException);
    }
  }
  
  public ScoreboardControl() {
    super(icons[0]);
    Icon localIcon = getIcon();
    setLayout(null);
    Dimension localDimension = new Dimension(localIcon.getIconWidth(), localIcon.getIconHeight());
    setMinimumSize(localDimension);
    setMaximumSize(localDimension);
    setPreferredSize(localDimension);
    setHorizontalAlignment(10);
  }
  
  public int getScore() {
    return score;
  }
  
  public void setScore(final int paramInt) {
    score = paramInt;
    swingUtilities.invokeLater(new Runnable() {
      public void run() {
        setIcon(ScoreboardControl.icons[paramInt]);
      }
    });
  }
}
