package com.digitalpersona.onetouch.jni;

public class FeatureExtractionResult {
  public byte[] data;
  public int imageQuality;
  public int featuresQuality;
  
  public FeatureExtractionResult(byte[] paramArrayOfByte, int paramInt1, int paramInt2) { data = paramArrayOfByte;
    imageQuality = paramInt1;
    featuresQuality = paramInt2;
  }
}
