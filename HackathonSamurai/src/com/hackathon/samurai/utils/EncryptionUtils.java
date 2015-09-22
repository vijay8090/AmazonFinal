package com.hackathon.samurai.utils;


import java.util.Random;

/**
 * 
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 20, 2015
 * @java Version : Java 8
 */
public class EncryptionUtils
{

    private EncryptionUtils()
    {
    }

    public static String decryptString(String str, String password)
    {
        if(!password.equalsIgnoreCase(password))
            return null;
        else
            return decryptString(str);
    }

    public static String decryptString(String str)
    {
        String decoded = str;
        try
        {
            String decryptedStr = deCrypt(decoded);
            return decryptedStr;
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("EncryptionUtils .decryptString exception  ")).append(e.getMessage()).toString());
        }
        return null;
    }

    public static String encryptString(String str)
    {
        try
        {
            String encrypted = enCrypt(str);
            return encrypted;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String aArgs[])
    {

    	String enc = enCrypt("vijaysamurai");    	
    	System.out.println(enc);
    	//String enc1 = "c+uhW1GOclRZ)XCQ%Fuk\"02caluX+%XW";
    	String dec = deCrypt(enc);
    	System.out.println(dec);
    	
    }

    public static String deCrypt(String inStr)
    {
        try
        {
            inStr = inStr.substring(prefixLength, inStr.length() - suffixLength);
            StringBuffer output = new StringBuffer();
            for(int i = 0; i < inStr.length(); i++)
            {
                char c = inStr.charAt(i);
                int charCode = c;
                charCode++;
                output.append((char)charCode);
            }

            return output.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String enCrypt(String inStr)
    {
        StringBuffer output = new StringBuffer();
        for(int i = 0; i < inStr.length(); i++)
        {
            char c = inStr.charAt(i);
            int charCode = c;
            charCode--;
            output.append((char)charCode);
        }

        return (new StringBuilder(String.valueOf(genRandomString(prefixLength)))).append(output).append(genRandomString(suffixLength)).toString();
    }

    public static String genRandomString(int length)
    {
        char chars[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890#$%*()+~".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++)
        {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    public static final String CLASSNAME = "EncryptionUtils ";
    private static int suffixLength = 7;
    private static int prefixLength = 17;
    private static final String password = "pisoft ";

}


