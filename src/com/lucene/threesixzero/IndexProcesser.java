package com.lucene.threesixzero;
/* 
 * Title: 	IndexProcesser
 * 
 * Description:  创建处理文档的索引类
 * 
 * Version:
 * 
 * Date：2012-7-9
 *
 * Copyright by xueshijun 
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
 


public class IndexProcesser {
	
	//索引位置
	private String INDEX_STORE_PATH="E:\\test_index";
	
	public void createIndex(String inputDir){
		 final File docDir = new File(inputDir);
		 if (!docDir.exists() || !docDir.canRead()) {
			 System.out.println(docDir.getAbsolutePath()+ ":目录不存在或者没有读取权限!");
			 System.exit(1);
		 } 
		try{
			
			//MMAnalyzer作为分词工具创建一个IndexWriter
			IndexWriter writer=new IndexWriter(INDEX_STORE_PATH,new StandardAnalyzer(),true);
			
			//writer.setMaxFieldLength(1); 
			
			System.out.println("正在建立索引 '" + INDEX_STORE_PATH + "'...");

			File filesDir=new File(inputDir);
			//取得所有需要建立索引的文件数组
			File [] files=filesDir.listFiles();
			
			if (filesDir.canRead()) {
				if (filesDir.isDirectory()) {
					// an IO error could occur
	                if (files != null) { 
						//遍历数组
						for(int i=0;i<files.length;i++){
							//获取文件名
							String fileName=files[i].getName();
							if(fileName.substring(fileName.lastIndexOf(".")).equals(".txt")){
			 
								Document doc=new Document();
								//为文件名创建Field
								Field field=new Field("filename",files[i].getName(),
										Field.Store.YES,
										Field.Index.TOKENIZED);
								doc.add(field);
								//为文件内容创建Field
									  field=new Field("content",loadFileToString(files[i]),
										Field.Store.YES,
										Field.Index.TOKENIZED);
								doc.add(field); 
								
								//把Document加入IndexWriter
								writer.addDocument(doc); 
							}
						} 
	                }
	            }
		    }
			
			System.out.println("优化...");
			writer.optimize();

			writer.close();//必须的关，不然缓存写不到硬盘，且文件夹还被锁了！
		}
		catch(IOException ex){
//			ex.printStackTrace();
		    System.out.println(" caught a " + ex.getClass() + "\n with message: " + ex.getMessage());
		}
	}

	/*
	 * 获取文件中的内容
	 */
	public String loadFileToString(File file){
	    if(file==null || file.length()<1)return null; 
		try{
			BufferedReader br=new BufferedReader(new FileReader(file));
			StringBuffer sb=new StringBuffer();
			String line =br.readLine();
			while(line!=null){
				sb.append(line);
				line=br.readLine(); 
			}
			br.close();
			return sb.toString(); 
		}
		catch(IOException ex){
//			ex.printStackTrace(); 
			System.out.println("文件读取失败:"+file.getName()); 
			return null;
		}  
	} 
	
	
	public static void main(String [] args){

		System.out.println("===========Begin!===========");
		IndexProcesser processor=new IndexProcesser();
		processor.createIndex("E:\\test");
		System.out.println("===========End!===========");
	}
	
	public Document createfnm(){
		Document doc=new Document();
		Field field=new Field("name1","smi smith steve ted teddy terry",Field.Store.YES,Field.Index.TOKENIZED);
		doc.add(field);
		field=new Field("name2","what are you doing",Field.Store.YES,Field.Index.TOKENIZED);
		doc.add(field);
		field=new Field("name3","how do you do",Field.Store.YES,Field.Index.TOKENIZED);
		doc.add(field);
		return doc;
	}
}


