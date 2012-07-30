/**
 * 
 */
package test;
/** 

 * java ���� , �������ͼƬ������ͼƬ����ͼ 

 */ 

import java.net.URL; 

import java.awt.Container; 

import java.awt.Dimension; 

import java.awt.Graphics2D; 

import java.awt.Image; 

import java.awt.MediaTracker; 

import java.awt.Rectangle; 

import java.awt.RenderingHints; 

import java.awt.Robot; 

import java.awt.Toolkit; 

import java.awt.image.BufferedImage; 

import java.io.BufferedInputStream; 

import java.io.BufferedOutputStream; 

import java.io.OutputStream; 

import java.io.FileOutputStream; 

import java.io.File; 

 

import javax.imageio.ImageIO; 

 

import com.sun.image.codec.jpeg.JPEGCodec; 

import com.sun.image.codec.jpeg.JPEGEncodeParam; 

import com.sun.image.codec.jpeg.JPEGImageEncoder; 

 

/**
 * @author Superman
 *
 */
public class InternetImage {
	/**
	 * 
	 */
	public InternetImage() {
		// TODO Auto-generated constructor stub
	}
	
    public static void mymain(String[] args) { 

    	GetPicture.snatchPicture("http://price.360buyimg.com/gp1000195393,3.png", "C://7755p2c"); 

    	// ��ȡ��ǰ��Ļ����Ϊ c �̸�Ŀ¼�µ� screen.jpg ͼƬ 

//    	GetPicture.snatchScreen ( "c://screen.jpg" ); 

	       // ���� c �̸�Ŀ¼�µ� screen.jpg ͼƬ������һ���� 128 ���� 102 ������ͼ screenSmall.jpg �������� c �̸�Ŀ¼���� 

    	GetPicture.createThumbnail ( "D://screen.jpg" , 128, 102, 10, "D://screenSmall.jpg" ); 

	       System. out .println( "successful" ); 

	    } 

	public static class GetPicture { 

	    public GetPicture() { 

	    }  
	
	  

	    /** 

	      * ץȡ��ǰ��Ļ create date:2008- 7- 28 author:Administrator 

	      * 
	      * @param path 

	      * @throws Exception 

	      */ 

	    public static void snatchScreen(String path) { 

	       Robot robot; 

	       try { 

	           robot = new Robot(); 

	           Dimension d = Toolkit.getDefaultToolkit ().getScreenSize(); 

	           Rectangle rect = new Rectangle(0, 0, d. width , d. height ); 

	           BufferedImage image = robot.createScreenCapture(rect); 

	           ImageIO.write (image, "jpg" , new File(path)); 

	  

	       } catch (Exception e) { 

	           System. out .println( "Failed to snatch screen ... " ); 

	           e.printStackTrace(); 

	       } 

	  

	    } 

	  

	    /** 

	      * java ����ͼƬ������ͼ create date:2009- 5- 27 author:Administrator 

	      * 
	      * @param filename 

	      *             ԴͼƬ 

	      * @param thumbWidth 

	      *             ����ͼ�� 

	      * @param thumbHeight 

	      *             ����ͼ�� 

	      * @param quality 

	      *             ͼƬ���� 

	      * @param outFilename 

	      *             ����ͼ�ļ��� 

	      * 
	      */ 

	    private static void createThumbnail(String filename, int thumbWidth,int thumbHeight, int quality, String outFilename) { 

	  

	       // load image from filename 

	       Image image = Toolkit.getDefaultToolkit ().getImage(filename); 

	       MediaTracker mediaTracker = new MediaTracker( new Container()); 

	       mediaTracker.addImage(image, 0); 

	       try { 

	           mediaTracker.waitForID(0); 

	           // use this to test for errors at this point: 

	           // System.out.println(mediaTracker.isErrorAny()); 

	           // determine thumbnail size from WIDTH and HEIGHT 

	           double thumbRatio = ( double ) thumbWidth / ( double ) thumbHeight; 

	           int imageWidth = image.getWidth( null ); 

	           int imageHeight = image.getHeight( null ); 

	           double imageRatio = ( double ) imageWidth / ( double ) imageHeight; 

	           if (thumbRatio < imageRatio) { 

	              thumbHeight = ( int ) (thumbWidth / imageRatio); 

	           } else { 

	              thumbWidth = ( int ) (thumbHeight * imageRatio); 

	           } 

	           // draw original image to thumbnail image object and 

	           // scale it to the new size on-the-fly 

	           BufferedImage thumbImage = new BufferedImage(thumbWidth, 

	                  thumbHeight, BufferedImage. TYPE_INT_RGB ); 

	           Graphics2D graphics2D = thumbImage.createGraphics(); 

	           graphics2D.setRenderingHint(RenderingHints. KEY_INTERPOLATION , 

	                  RenderingHints. VALUE_INTERPOLATION_BILINEAR ); 

	           graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null ); 

	           // save thumbnail image to outFilename 

	           BufferedOutputStream out = new BufferedOutputStream( 

	                  new FileOutputStream(outFilename)); 

	  

	           JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder (out); 

	           JPEGEncodeParam param = encoder 

	                  .getDefaultJPEGEncodeParam(thumbImage); 

	           quality = Math.max (0, Math.min (quality, 100)); 

	           param.setQuality(( float ) quality / 100.0f, false ); 

	           encoder.setJPEGEncodeParam(param); 

	           encoder.encode(thumbImage); 

	           out.close(); 

	       } catch (Exception e) { 

	           // TODO Auto-generated catch block 

	           e.printStackTrace(); 

	       } 

	  

	    } 

	  

	    /** 

	      * ʵ�ִ���վ����ͼƬ create date:2008- 7- 28 author:Administrator 

	      * 
	      * @param urlPath 

	      * @param picPathName 

	      */ 

	    public static void snatchPicture(String urlPath, String picPathName) { 

	       try { 

	           URL url = new URL(urlPath); 

	           BufferedInputStream bis = new BufferedInputStream(url.openStream()); 

	           byte [] bytes = new byte [2048]; 

	           OutputStream bos = new FileOutputStream( new File(picPathName)); 

	           int len; 

	           while ((len = bis.read(bytes)) > 0) { 

	              bos.write(bytes, 0, len); 

	           } 

	  

	           bis.close(); 

	           bos.flush(); 

	           bos.close(); 

	       } catch (Exception e) { 

	           System. out .println( " ץȡͼƬʧ�� ... " ); 

	           e.printStackTrace(); 

	       } 

	    } 

	  

	} 

}
