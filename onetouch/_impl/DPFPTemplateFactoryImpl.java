package com.digitalpersona.onetouch._impl;

import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.DPFPTemplateFactory;


public class DPFPTemplateFactoryImpl
  implements DPFPTemplateFactory
{
  public DPFPTemplateFactoryImpl() {}
  
  public DPFPTemplate createTemplate()
  {
    return new TemplateImpl(null);
  }
  
  public DPFPTemplate createTemplate(byte[] paramArrayOfByte)
    throws IllegalArgumentException
  {
    TemplateImpl localTemplateImpl = new TemplateImpl(null);
    localTemplateImpl.deserialize(paramArrayOfByte);
    return localTemplateImpl;
  }
  
  private static class TemplateImpl
    extends DPFPDataImpl
    implements DPFPTemplate
  {
    private TemplateImpl() {}
  }
}
