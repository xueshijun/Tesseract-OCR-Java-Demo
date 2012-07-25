package com.xueshijun.test.orc;

import java.io.File;   
import java.io.IOException;   
import java.util.Date;

import com.ocr.OCR;
 
public class Test {

    /** *//**  
     * @param args  
     */  
    public static void main(String[] args) {   
        String path = "D:\\1.png";      
        try {  
        	int count=0;
        	while(count<10) {
	        	System.out.println("=============BEGIN================");
	        	Date beginTime=new Date();
	            String valCode = new OCR().recognizeText(new File(path), "png");      
	            System.out.println(valCode.trim());  
	            Date endTime=new Date();
	            long timeOfSearch=endTime.getTime()-beginTime.getTime();
	        	System.out.println(timeOfSearch+"ms\n=============END================");
	        	count++;
        	}
        } catch (IOException e) {      
            e.printStackTrace();      
        } catch (Exception e) {   
            e.printStackTrace();   
        }       
    }   

}
