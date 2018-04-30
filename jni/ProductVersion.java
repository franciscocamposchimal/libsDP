package com.digitalpersona.onetouch.jni;

public class ProductVersion
{
  public int major;
  public int minor;
  public int revision;
  public int build;
  
  public ProductVersion(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    major = paramInt1;
    minor = paramInt2;
    revision = paramInt3;
    build = paramInt4;
  }
  























  public ProductVersion() {}
  























  public boolean equals(Object paramObject)
  {
    return (super.equals(paramObject)) || (((paramObject instanceof ProductVersion)) && (major == major) && (minor == minor) && (build == build));
  }
}
