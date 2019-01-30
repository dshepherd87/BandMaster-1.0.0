/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author David Shepherd
 */
public class StringUtil {
    public static String pad(String s, int length){
        if(s.length() < length){
            StringBuilder sb = new StringBuilder(s);
            while(sb.length() < length){
                sb.append(" ");
            }
            return sb.toString();
        }else{
            return s.substring(0, length);
        }
    }
}
