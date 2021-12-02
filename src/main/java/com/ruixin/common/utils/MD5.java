package com.ruixin.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码加密类
 */
public class MD5 {

    /**
     * 密码加密
     * @param userName
     * @param password
     * @return
     */
    public static String md5(String userName,String password){
        String hashAlgorithmName = "MD5";//加密方式
        ByteSource salt = ByteSource.Util.bytes(userName);//以账号作为盐值
        Object result = new SimpleHash(hashAlgorithmName,password,salt,1024);
        return result.toString();
    }

    public static void main(String[] args){
        System.err.println(md5("admin","admin"));
    }
}
