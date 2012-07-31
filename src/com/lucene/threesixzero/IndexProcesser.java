package com.lucene.threesixzero;
/* 
 * Title: 	IndexProcesser
 * 
 * Description:  ���������ĵ���������
 * 
 * Version:
 * 
 * Date��2012-7-9
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
	
	//����λ��
	private String INDEX_STORE_PATH="E:\\test_index";
	
	public void createIndex(String inputDir){
		 final File docDir = new File(inputDir);
		 if (!docDir.exists() || !docDir.canRead()) {
			 System.out.println(docDir.getAbsolutePath()+ ":Ŀ¼�����ڻ���û�ж�ȡȨ��!");
			 System.exit(1);
		 } 
		try{
			
			//MMAnalyzer��Ϊ�ִʹ��ߴ���һ��IndexWriter
			IndexWriter writer=new IndexWriter(INDEX_STORE_PATH,new StandardAnalyzer(),true);
			
			//writer.setMaxFieldLength(1); 
			
			System.out.println("���ڽ������� '" + INDEX_STORE_PATH + "'...");

			File filesDir=new File(inputDir);
			//ȡ��������Ҫ�����������ļ�����
			File [] files=filesDir.listFiles();
			
			if (filesDir.canRead()) {
				if (filesDir.isDirectory()) {
					// an IO error could occur
	                if (files != null) { 
						//��������
						for(int i=0;i<files.length;i++){
							//��ȡ�ļ���
							String fileName=files[i].getName();
							if(fileName.substring(fileName.lastIndexOf(".")).equals(".txt")){
			 
								Document doc=new Document();
								//Ϊ�ļ�������Field
								Field field=new Field("filename",files[i].getName(),
										Field.Store.YES,
										Field.Index.TOKENIZED);
								doc.add(field);
								//Ϊ�ļ����ݴ���Field
									  field=new Field("content",loadFileToString(files[i]),
										Field.Store.YES,
										Field.Index.TOKENIZED);
								doc.add(field); 
								
								//��Document����IndexWriter
								writer.addDocument(doc); 
							}
						} 
	                }
	            }
		    }
			
			System.out.println("�Ż�...");
			writer.optimize();

			writer.close();//����Ĺأ���Ȼ����д����Ӳ�̣����ļ��л������ˣ�
		}
		catch(IOException ex){
//			ex.printStackTrace();
		    System.out.println(" caught a " + ex.getClass() + "\n with message: " + ex.getMessage());
		}
	}

	/*
	 * ��ȡ�ļ��е�����
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
			System.out.println("�ļ���ȡʧ��:"+file.getName()); 
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


