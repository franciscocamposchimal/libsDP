package com.digitalpersona.onetouch.ui.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.UIManager;

class BalloonControl extends javax.swing.JPanel
{
  private static final String infoIconFilename = "/images/info.png";
  private static final java.awt.image.BufferedImage infoIcon;
  private static java.awt.Color fillColor = UIManager.getColor("ToolTip.background");
  private static java.awt.Color penColor = UIManager.getColor("ToolTip.foreground");
  private final Component attachedComponent;
  
  static {
    try { infoIcon = javax.imageio.ImageIO.read(DPFPEnrollmentControl.class.getResourceAsStream("/images/info.png"));
    } catch (java.io.IOException localIOException) {
      throw new RuntimeException(localIOException);
    }
  }
  

  private final JLabel caption = new JLabel(new javax.swing.ImageIcon(infoIcon));
  private final JLabel label = new JLabel();
  private boolean suspended = false;
  
  private int columns;
  private Timer timer = new Timer(1000, new javax.swing.AbstractAction() {
    public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
      dismiss();
    }
  });
  




  public static void popup(Component paramComponent, String paramString)
  {
    popup(paramComponent, paramString, "");
  }
  
  public static void popup(Component paramComponent, String paramString1, String paramString2) {
    popup(paramComponent, paramString1, paramString2, 50);
  }
  
  public static void popup(Component paramComponent, String paramString1, String paramString2, int paramInt) {
    popup(paramComponent, paramString1, paramString2, paramInt, 10);
  }
  
  public static void popup(Component paramComponent, String paramString1, String paramString2, int paramInt1, int paramInt2) {
    BalloonControl localBalloonControl = new BalloonControl(paramComponent, paramInt1, paramInt2);
    localBalloonControl.setTitle(paramString2);
    localBalloonControl.setText(paramString1);
    localBalloonControl.setVisible(true);
  }
  
  BalloonControl(Component paramComponent, int paramInt1, int paramInt2)
  {
    attachedComponent = paramComponent;
    columns = paramInt1;
    
    setOpaque(false);
    setBorder(new BalloonBorder(paramInt2));
    setLayout(new java.awt.BorderLayout());
    
    caption.setBorder(new javax.swing.border.EmptyBorder(5, 5, 0, 5));
    caption.setFont(UIManager.getFont("ToolTip.font").deriveFont(1));
    caption.setHorizontalAlignment(2);
    label.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
    label.setFont(UIManager.getFont("ToolTip.font"));
    add(caption, "First");
    add(label, "Center");
    

    paramComponent.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentMoved(java.awt.event.ComponentEvent paramAnonymousComponentEvent) {
        if (isShowing()) {
          BalloonControl.this.updateLocation();
        }
      }
    });
    paramComponent.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsAdapter() {
      public void ancestorMoved(java.awt.event.HierarchyEvent paramAnonymousHierarchyEvent) {
        if (isShowing()) {
          BalloonControl.this.updateLocation();
        }
      }
    });
    addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent paramAnonymousMouseEvent) {
        setSuspended(false);
        dismiss();
        paramAnonymousMouseEvent.consume();
      }
      
      public void mouseEntered(MouseEvent paramAnonymousMouseEvent) { setSuspended(true); }
      
      public void mouseExited(MouseEvent paramAnonymousMouseEvent) {
        setSuspended(false);
      }
      
    });
    setVisible(false);
  }
  

  private void updateLocation()
  {
    BalloonBorder localBalloonBorder = (BalloonBorder)getBorder();
    int i = radius;
    
    Rectangle localRectangle1 = getParent().getBounds();
    Rectangle localRectangle2 = javax.swing.SwingUtilities.convertRectangle(attachedComponent.getParent(), attachedComponent.getBounds(), getParent());
    Point localPoint1 = new Point((int)localRectangle2.getCenterX(), (int)localRectangle2.getCenterY());
    Dimension localDimension1 = getPreferredSize();
    Dimension localDimension2 = getMaximumSize();
    localDimension1.setSize(Math.min(width, width), Math.min(height, height));
    
    Point localPoint2 = new Point(Math.max(x - width + i, Math.min(width - width - 1, x - i)), y - height > 0 ? Math.max(y - height, 1) : y + height < height - 1 ? Math.min(y, height - height - 1) : i);
    










    setBounds(x - i, y, width, height);
    
    spot.x = (x - x + radius);
    spot.y = (y > y ? height : 0);
    validate();
  }
  
  public void setTitle(String paramString) {
    caption.setText(paramString);
  }
  


  public void setText(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (!paramString.startsWith("<html>"))
      localStringBuilder.append("<html>");
    String str = paramString.replaceAll("<br\\s*?>", "\n");
    int i = 0;
    int j = 0;int k = str.length();
    int m = 0;int n = 0;
    while (j < k) {
      int i1 = str.charAt(j);
      while ((i1 == 60) && ((j = str.indexOf('>', j) + 1) < k))
        i1 = str.charAt(j);
      if (i1 == 10) i = 0;
      if (i1 == 32) n = j;
      i++; if (i > columns) {
        localStringBuilder.append(str.substring(m, n)).append("<br>");
        n++;j = m = n;
        i = 0;
      }
      j++;
    }
    localStringBuilder.append(str.substring(m));
    if (!paramString.endsWith("</html>"))
      localStringBuilder.append("</html>");
    label.setText(localStringBuilder.toString().replaceAll("\n", "<br>"));
  }
  
  public void setVisible(boolean paramBoolean)
  {
    if ((!isVisible()) && (paramBoolean))
    {
      JLayeredPane localJLayeredPane = JLayeredPane.getLayeredPaneAbove(attachedComponent);
      if (localJLayeredPane != null) {
        localJLayeredPane.add(this, JLayeredPane.POPUP_LAYER);
        label.setMaximumSize(new Dimension(getSizewidth / 2, getSizeheight / 2));
      }
      updateLocation();
    }
    super.setVisible(paramBoolean);
    timer.setRepeats(false);
    if (paramBoolean) timer.start(); else timer.stop();
  }
  
  public void setSuspended(boolean paramBoolean)
  {
    suspended = paramBoolean;
    if (!paramBoolean)
      setVisible(true);
  }
  
  public void dismiss() {
    if (!suspended) {
      setVisible(false);
      getParent().remove(this);
    } else {
      suspended = false;
    }
  }
  
  static class BalloonBorder implements javax.swing.border.Border {
    private java.awt.Insets insets;
    public int radius = 0;
    public Point spot;
    
    BalloonBorder(int paramInt) {
      radius = paramInt;
      insets = new java.awt.Insets(paramInt + 1, 1, paramInt + 1, 1);
      spot = new Point(radius, 0);
    }
    
    public void setSpot(Point paramPoint) {
      spot = paramPoint;
    }
    
    public java.awt.Insets getBorderInsets(Component paramComponent) {
      return insets;
    }
    
    public boolean isBorderOpaque() { return true; }
    
    public void paintBorder(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
      Rectangle localRectangle = new Rectangle(paramInt1, paramInt2 + radius, paramInt3, paramInt4 - 2 * radius);
      
      int i = spot.x < paramInt3 / 2 ? 1 : -1;
      int j = spot.y < paramInt4 / 2 ? 1 : -1;
      int k = radius * i;
      int m = (radius + 1) * j;
      
      paramGraphics.setColor(BalloonControl.fillColor);
      paramGraphics.fillRoundRect(x, y, width, height, radius, radius);
      paramGraphics.setColor(BalloonControl.penColor);
      paramGraphics.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
      

      int[] arrayOfInt1 = { spot.x, spot.x + k, spot.x };
      int[] arrayOfInt2 = { spot.y + m, spot.y + m, spot.y };
      
      paramGraphics.setColor(BalloonControl.fillColor);
      paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 3);
      paramGraphics.setColor(BalloonControl.penColor);
      paramGraphics.drawLine(arrayOfInt1[0], arrayOfInt2[0] - j, arrayOfInt1[2], arrayOfInt2[2]);
      paramGraphics.drawLine(arrayOfInt1[1], arrayOfInt2[1] - j, arrayOfInt1[2], arrayOfInt2[2]);
    }
  }
}
