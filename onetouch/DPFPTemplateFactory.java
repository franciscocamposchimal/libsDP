package com.digitalpersona.onetouch;

public abstract interface DPFPTemplateFactory
{
  public abstract DPFPTemplate createTemplate();
  
  public abstract DPFPTemplate createTemplate(byte[] paramArrayOfByte)
    throws IllegalArgumentException;
}
