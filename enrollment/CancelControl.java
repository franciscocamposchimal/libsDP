package com.digitalpersona.onetouch.ui.swing;

import java.util.ResourceBundle;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

class CancelControl
  extends JEditorPane
{
  private static ResourceBundle labels = ResourceBundle.getBundle("dpfpui");
  
  public CancelControl() {
    super("text/html", labels.getString("IDS_FOOTER"));
    setOpaque(false);
    setEditable(false);
    setFont(UIManager.getFont("Panel.font"));
    putClientProperty("JEditorPane.honorDisplayProperties", Boolean.TRUE);
    
    addHyperlinkListener(new HyperlinkListener() {
      public void hyperlinkUpdate(HyperlinkEvent paramAnonymousHyperlinkEvent) {
        if (paramAnonymousHyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
          CancelControl.this.notifyCancelListeners();
      }
    });
  }
  
  private void notifyCancelListeners() {
    CancelEvent localCancelEvent = new CancelEvent(this);
    for (CancelListener localCancelListener : (CancelListener[])getListeners(CancelListener.class)) {
      try {
        localCancelListener.enrollmentCancelled(localCancelEvent);
      } catch (Exception localException) {}
    }
  }
  
  void addCancelListener(CancelListener paramCancelListener) {
    listenerList.add(CancelListener.class, paramCancelListener);
  }
  
  void removeCancelListener(CancelListener paramCancelListener) {
    listenerList.remove(CancelListener.class, paramCancelListener);
  }
}
