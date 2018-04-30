package com.digitalpersona.onetouch.ui.swing;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.InvocationEvent;
import java.lang.reflect.InvocationTargetException;




public class DPFPSwingUtilities
{
  public DPFPSwingUtilities() {}
  
  public final EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
  
  public void invokeLater(Runnable paramRunnable) {
    eventQueue.postEvent(new InvocationEvent(Toolkit.getDefaultToolkit(), paramRunnable));
  }
  
  public void invokeAndWait(Runnable paramRunnable) throws InterruptedException, InvocationTargetException {
    if (EventQueue.isDispatchThread()) {
      throw new Error("Cannot call invokeAndWait from the event dispatcher thread");
    }
    

    Object local1AWTInvocationLock = new Object() {};
    InvocationEvent localInvocationEvent = new InvocationEvent(Toolkit.getDefaultToolkit(), paramRunnable, local1AWTInvocationLock, true);
    
    synchronized (local1AWTInvocationLock) {
      eventQueue.postEvent(localInvocationEvent);
      local1AWTInvocationLock.wait();
    }
    
    ??? = localInvocationEvent.getThrowable();
    if (??? != null) {
      throw new InvocationTargetException((Throwable)???);
    }
  }
}
