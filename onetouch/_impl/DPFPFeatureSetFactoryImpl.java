package com.digitalpersona.onetouch._impl;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPFeatureSetFactory;

public class DPFPFeatureSetFactoryImpl
  implements DPFPFeatureSetFactory
{
  public DPFPFeatureSetFactoryImpl() {}
  
  public DPFPFeatureSet createFeatureSet()
  {
    return new FeatureSetImpl(null);
  }
  
  public DPFPFeatureSet createFeatureSet(byte[] paramArrayOfByte)
    throws IllegalArgumentException
  {
    FeatureSetImpl localFeatureSetImpl = new FeatureSetImpl(null);
    localFeatureSetImpl.deserialize(paramArrayOfByte);
    return localFeatureSetImpl;
  }
  
  private static class FeatureSetImpl
    extends DPFPDataImpl
    implements DPFPFeatureSet
  {
    private FeatureSetImpl() {}
  }
}
