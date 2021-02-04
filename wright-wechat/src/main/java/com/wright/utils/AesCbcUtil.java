package com.wright.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
 
/**
 * <p>
 * AES-128-CBC 加密方式
 * 注：
 * AES-128-CBC可以自己定义“密钥”和“偏移量“。
 * AES-128是jdk自动生成的“密钥”。
 */
public class AesCbcUtil {
 
 
    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }
 
    
    
    
    
    
    
    /**
     * AES解密针对微信小程序
     *
     * @param data           //密文，被加密的数据
     * @param key            //秘钥
     * @param iv             //偏移量
     * @param encodingFormat //解密后的结果需要进行的编码
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {
//        initialize();
 
        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data);
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key);
        //偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
 
 
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
 
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
 
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
 
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
 
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
 
        return null;
    }
    
    
    
    public static String encrypt(String data, String key, String iv, String encodingFormat) throws Exception {
//      initialize();

      //被加密的数据
      byte[] dataByte = Base64.decodeBase64(data);
      //加密秘钥
      byte[] keyByte = Base64.decodeBase64(key);
      //偏移量
      byte[] ivByte = Base64.decodeBase64(iv);


      try {
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

          SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

          AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
          parameters.init(new IvParameterSpec(ivByte));

          cipher.init(Cipher.ENCRYPT_MODE, spec, parameters);// 初始化

          byte[] resultByte = cipher.doFinal(dataByte);
          if (null != resultByte && resultByte.length > 0) {
              String result = new String(resultByte, encodingFormat);
              return result;
          }
          return null;
      } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
      } catch (NoSuchPaddingException e) {
          e.printStackTrace();
      } catch (InvalidParameterSpecException e) {
          e.printStackTrace();
      } catch (InvalidKeyException e) {
          e.printStackTrace();
      } catch (InvalidAlgorithmParameterException e) {
          e.printStackTrace();
      } catch (IllegalBlockSizeException e) {
          e.printStackTrace();
      } catch (BadPaddingException e) {
          e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      return null;
  }
 
    
    //初始向量（偏移）  
    public static final String VIPARA = "aabbccddeeffgghh";   //AES 为16bytes. DES 为8bytes  
      
    //编码方式  
    public static final String bm = "UTF-8";  
      
    //私钥  （密钥）
    private static final String ASE_KEY="aabbccddeeffgghh";   //AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。  
      
    /** 
     * 加密 
     *  
     * @param cleartext 加密前的字符串
     * @return 加密后的字符串
     */  
    public static String encrypt(String cleartext) {  
      
        //------------------------------------------AES加密-------------------------------------

        //加密方式： AES128(CBC/PKCS5Padding) + Base64, 私钥：aabbccddeeffgghh  
        try {  
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());  
            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES  
            SecretKeySpec key = new SecretKeySpec(ASE_KEY.getBytes(), "AES");  
            //实例化加密类，参数为加密方式，要写全  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //PKCS5Padding比PKCS7Padding效率高，PKCS7Padding可支持IOS加解密  
             //初始化，此方法可以采用三种方式，按加密算法要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec  
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);  

             //------------------------------------------base64编码-------------------------------------

            //加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE,7bit等等。此处看服务器需要什么编码方式  
            //byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));  
            //return new BASE64Encoder().encode(encryptedData);  

             //上下等同，只是导入包不同

             //加密后的字节数组
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));
            //对加密后的字节数组进行base64编码
            byte[] base64Data = org.apache.commons.codec.binary.Base64.encodeBase64(encryptedData);
            //将base64编码后的字节数组转化为字符串并返回
            return new String(base64Data);

            //------------------------------------------/base64编码-------------------------------------

        } catch (Exception e) {  
            e.printStackTrace();  
            return "";   
        }  
       //------------------------------------------/AES加密-------------------------------------
    }  
  
    /** 
     * 解密 
     *  
     * @param encrypted 解密前的字符串（也就是加密后的字符串）
     * @return 解密后的字符串（也就是加密前的字符串）
     */  
    public static String decrypt(String encrypted) {  

        //---------------------------------------AES解密----------------------------------------

        try {  

            //---------------------------------------base64解码---------------------------------------

            //byte[] byteMi = new BASE64Decoder().decodeBuffer(encrypted); 
 
            //上下等同，只是导入包不同

            //将字符串转化为base64编码的字节数组
            byte[] encryptedBase64Bytes = encrypted.getBytes();
            //将base64编码的字节数组转化为在加密之后的字节数组
            byte[] byteMi = org.apache.commons.codec.binary.Base64.decodeBase64(encryptedBase64Bytes);

           //---------------------------------------/base64解码---------------------------------------
     
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());  
            SecretKeySpec key = new SecretKeySpec(  
                    ASE_KEY.getBytes(), "AES");  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
            //与加密时不同MODE:Cipher.DECRYPT_MODE  
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);  
            byte[] decryptedData = cipher.doFinal(byteMi);  
            return new String(decryptedData, bm);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
       //---------------------------------------/AES解密----------------------------------------
    }  
}