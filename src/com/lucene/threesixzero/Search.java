package com.lucene.threesixzero;
/* 
 * Title: 	Search
 * 
 * Description:  检索索引的搜索类
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
import java.io.FileReader;
import java.io.IOException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs; 
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class Search {
	public static void main(String [] args){
		Search search=new Search();
		search.indexSearch("content","保尔");
		System.out.println("\n");
		search.stringSearch("保尔","K:\\test"); 
	}
	private String INDEX_STORE_PATH="K:\\test_index";
	//Lucene搜索
	public void indexSearch(String searchType,String searchKey){
		try{
			System.out.println("================================");
			System.out.println("==========使用索引方式搜索=======");
			System.out.println("================================");

			//根据索引位置建立IndexSearcher
			IndexSearcher searcher=new IndexSearcher(INDEX_STORE_PATH);
			//建立搜索单元，searchType代表要搜索的Field,searchKey代表关键字
			Term t=new Term(searchType,searchKey); 
			Query q=new TermQuery(t);
			
			Date beginTime=new Date();
			
			TermDocs termDocs=searcher.getIndexReader().termDocs(t);
			while(termDocs.next()){
				//输出在文档中出现关键词的次数
				System.out.println("find "+termDocs.freq()+" matches in");

				System.out.println(
						searcher.getIndexReader().document(
								termDocs.doc()).getField("filename").stringValue()); 
			} 
			
			Date endTime=new Date();
			long timeOfSearch=endTime.getTime()-beginTime.getTime();
			System.out.println("使用索引方式搜索总耗时:"+timeOfSearch+"ms"); 	
		}
		catch(IOException ex){
			ex.printStackTrace();
		}		
	}
	//String搜索
	public void stringSearch(String keyword,String searchDir){
		System.out.println("================================");
		System.out.println("=====使用字符串匹配方式搜索======");
		System.out.println("================================");

		File filesDir=new File(searchDir);
		File []files=filesDir.listFiles();
		
		Map rs=new LinkedHashMap();
		
		Date beginTime=new Date();
		
		for(int i=0;i<files.length;i++){
			int hits=0;
			try{
				BufferedReader br=new BufferedReader(new FileReader(files[i]));
				StringBuffer sb=new StringBuffer();
				String line=br.readLine();
				while(line!=null){
					sb.append(line);
					line=br.readLine();					
				}
				br.close();
				String stringToSearch=sb.toString();
				int fromIndex=-keyword.length();
				while((fromIndex=stringToSearch.indexOf(keyword,fromIndex+keyword.length())) !=-1) {
					hits++;
				}
				rs.put(files[i].getName(),new Integer(hits));
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		//输出查询结果
		Iterator it =rs.keySet().iterator();
		while(it.hasNext()){
			String fileName=(String)it.next();
			Integer hits=(Integer)rs.get(fileName);
			System.out.println("find "+hits.intValue()+" matches in "+fileName); 
		}
		Date endTime=new Date();
		long timeOfSearch=endTime.getTime()-beginTime.getTime();
		System.out.println("字符串匹配耗时:"+timeOfSearch+"ms");
			
	}
	
}
