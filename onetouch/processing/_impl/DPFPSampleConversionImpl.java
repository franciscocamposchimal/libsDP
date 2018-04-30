package com.digitalpersona.onetouch.processing._impl;

import com.digitalpersona.onetouch.DPFPSample;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DPFPSampleConversionImpl implements com.digitalpersona.onetouch.processing.DPFPSampleConversion
{
  public DPFPSampleConversionImpl() {}
  
  private static class FD_IMAGE
  {
    public int uSize;
    public int uReserved1;
    public int uDataType;
    public int uReserved2;
    public long DeviceId;
    public long DeviceType;
    public int iDataAcqProgress;
    public int uReserved3;
    public int uOffsetToImage;
    public int uMajor;
    public int uMinor;
    public int uBuild;
    public int uDataType1;
    public int uImageType;
    public int iWidth;
    public int iHeight;
    public int iXdpi;
    public int iYdpi;
    public int uBPP;
    public int uPadding;
    public int uSignificantBpp;
    public int uPolarity;
    public int uRGBcolorRepresentation;
    public int uPlanes;
    public int uExtensionSize;
    public int uReserved4;
    
    private FD_IMAGE(ByteBuffer paramByteBuffer)
    {
      uSize = paramByteBuffer.getInt();
      uReserved1 = paramByteBuffer.getInt();
      uDataType = paramByteBuffer.getInt();
      uReserved2 = paramByteBuffer.getInt();
      DeviceId = paramByteBuffer.getLong();
      DeviceType = paramByteBuffer.getLong();
      iDataAcqProgress = paramByteBuffer.getInt();
      uReserved3 = paramByteBuffer.getInt();
      uOffsetToImage = paramByteBuffer.getInt();
      uMajor = paramByteBuffer.getInt();
      uMinor = paramByteBuffer.getInt();
      uBuild = paramByteBuffer.getInt();
      uDataType1 = paramByteBuffer.getInt();
      uImageType = paramByteBuffer.getInt();
      iWidth = paramByteBuffer.getInt();
      iHeight = paramByteBuffer.getInt();
      iXdpi = paramByteBuffer.getInt();
      iYdpi = paramByteBuffer.getInt();
      uBPP = paramByteBuffer.getInt();
      uPadding = paramByteBuffer.getInt();
      uSignificantBpp = paramByteBuffer.getInt();
      uPolarity = paramByteBuffer.getInt();
      uRGBcolorRepresentation = paramByteBuffer.getInt();
      uPlanes = paramByteBuffer.getInt();
      uExtensionSize = paramByteBuffer.getInt();
      uReserved4 = paramByteBuffer.getInt();
    }
  }
  
  private static class ANSIImageHeader {
    public int nDataLength;
    public byte nFingerPosition;
    public byte nViewsCount;
    public byte nViewsNumber;
    public byte nImageQuality;
    public byte nImpressionType;
    public short nHorizontalLineLength;
    public short nVerticalLineLength;
    public byte nReserved;
    
    private ANSIImageHeader() {}
    
    public void writeToBuffer(ByteBuffer paramByteBuffer) { paramByteBuffer.putInt(nDataLength);
      paramByteBuffer.put(nFingerPosition);
      paramByteBuffer.put(nViewsCount);
      paramByteBuffer.put(nViewsNumber);
      paramByteBuffer.put(nImageQuality);
      paramByteBuffer.put(nImpressionType);
      paramByteBuffer.putShort(nHorizontalLineLength);
      paramByteBuffer.putShort(nVerticalLineLength);
      paramByteBuffer.put(nReserved);
    }
  }
  
  private static class int48 {
    short high;
    int low;
    
    private int48() {}
    
    public void writeToBuffer(ByteBuffer paramByteBuffer) { paramByteBuffer.putShort(high);
      paramByteBuffer.putInt(low);
    }
  }
  
  private static class ANSIRecordHeader
  {
    public byte[] szFormatIdFIR = { 70, 73, 82, 0 };
    public byte[] szVersionNumber = { 48, 49, 48, 0 };
    public DPFPSampleConversionImpl.int48 nRecordLength = new DPFPSampleConversionImpl.int48(null);
    public int nCBEFFProductID;
    public short nDeviceID;
    public short nImageAcquisitionLevel;
    public byte nImageCount;
    public byte nScaleUnits;
    public short nScanResolutionX;
    
    private ANSIRecordHeader() {}
    public short nScanResolutionY;
    public short nImageResolutionX;
    public short nImageResolutionY;
    
    public void writeToBuffer(ByteBuffer paramByteBuffer)
    {
      paramByteBuffer.put(szFormatIdFIR);
      paramByteBuffer.put(szVersionNumber);
      nRecordLength.writeToBuffer(paramByteBuffer);
      paramByteBuffer.putInt(nCBEFFProductID);
      paramByteBuffer.putShort(nDeviceID);
      paramByteBuffer.putShort(nImageAcquisitionLevel);
      paramByteBuffer.put(nImageCount);
      paramByteBuffer.put(nScaleUnits);
      paramByteBuffer.putShort(nScanResolutionX);
      paramByteBuffer.putShort(nScanResolutionY);
      paramByteBuffer.putShort(nImageResolutionX);
      paramByteBuffer.putShort(nImageResolutionY);
      paramByteBuffer.put(nPixelDepth);
      paramByteBuffer.put(nImageCompression);
      paramByteBuffer.putShort(nReserved); }
    
    public byte nPixelDepth;
    public byte nImageCompression;
    public short nReserved; }
  
  public Image createImage(DPFPSample paramDPFPSample) { byte[] arrayOfByte1 = paramDPFPSample.serialize();
    ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte1);
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    FD_IMAGE localFD_IMAGE = new FD_IMAGE(localByteBuffer, null);
    





    byte[] arrayOfByte2 = new byte[iWidth * iHeight];
    System.arraycopy(arrayOfByte1, uOffsetToImage, arrayOfByte2, 0, arrayOfByte2.length);
    DataBufferByte localDataBufferByte = new DataBufferByte(arrayOfByte2, iWidth * iHeight);
    
    ColorSpace localColorSpace = ColorSpace.getInstance(1003);
    int[] arrayOfInt = { 8 };
    ComponentColorModel localComponentColorModel = new ComponentColorModel(localColorSpace, arrayOfInt, false, true, 1, 0);
    java.awt.image.SampleModel localSampleModel = localComponentColorModel.createCompatibleSampleModel(iWidth, iHeight);
    java.awt.image.WritableRaster localWritableRaster = java.awt.image.Raster.createWritableRaster(localSampleModel, localDataBufferByte, null);
    
    AffineTransform localAffineTransform = new AffineTransform();
    localAffineTransform.rotate(3.141592653589793D, iWidth / 2.0D, iHeight / 2.0D);
    AffineTransformOp localAffineTransformOp = new AffineTransformOp(localAffineTransform, 1);
    
    return localAffineTransformOp.filter(new BufferedImage(localComponentColorModel, localWritableRaster, false, null), null);
  }
  
  public byte[] convertToANSI381(DPFPSample paramDPFPSample) {
    byte[] arrayOfByte1 = paramDPFPSample.serialize();
    ByteBuffer localByteBuffer1 = ByteBuffer.wrap(arrayOfByte1);
    localByteBuffer1.order(ByteOrder.LITTLE_ENDIAN);
    FD_IMAGE localFD_IMAGE = new FD_IMAGE(localByteBuffer1, null);
    
    int i = 0;
    int j = iWidth * iHeight;
    int k = j + 14;
    int m = k + 36;
    byte[] arrayOfByte2 = new byte[m];
    ByteBuffer localByteBuffer2 = ByteBuffer.wrap(arrayOfByte2);
    localByteBuffer2.order(ByteOrder.BIG_ENDIAN);
    
    ANSIRecordHeader localANSIRecordHeader = new ANSIRecordHeader(null);
    
    nRecordLength.high = 0;
    nRecordLength.low = m;
    


    nCBEFFProductID = (0x33FE00 | i);
    
    nDeviceID = ((short)(int)DeviceId);
    nImageAcquisitionLevel = 0;
    nImageCount = 1;
    nScaleUnits = 1;
    
    nScanResolutionX = ((short)iXdpi);
    nScanResolutionY = ((short)iYdpi);
    nImageResolutionX = ((short)iXdpi);
    nImageResolutionY = ((short)iYdpi);
    nPixelDepth = ((byte)uBPP);
    nImageCompression = 0;
    nReserved = 0;
    
    ANSIImageHeader localANSIImageHeader = new ANSIImageHeader(null);
    
    nDataLength = k;
    nFingerPosition = 1;
    nViewsCount = 1;
    nViewsNumber = 1;
    nImageQuality = -2;
    nImpressionType = 0;
    nHorizontalLineLength = ((short)iWidth);
    nVerticalLineLength = ((short)iHeight);
    nReserved = 0;
    

    localANSIRecordHeader.writeToBuffer(localByteBuffer2);
    localANSIImageHeader.writeToBuffer(localByteBuffer2);
    localByteBuffer2.put(arrayOfByte1, uOffsetToImage, j);
    
    return arrayOfByte2;
  }
}
