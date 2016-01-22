package com.simon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtil {
	
	/**
	 * 过滤目录下的文件
	 * @param dir
	 * @param filePattern
	 * @return
	 */
	public static List<String> getHtmlFile(String dir, final Pattern filePattern){
		List<String> list = new ArrayList<String>();
		File dirPath = new File(dir);
		if(dirPath.isDirectory()){
			File[] files = dirPath.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if(filePattern.matcher(name).find()){
						return true;
					}
					return false;
				}
			});
			
			for(File file : files){
				list.add(file.getAbsolutePath());
			}
		}
		
		return list;
	}
	
	/**
	 * 过滤文件夹
	 * @param dir
	 * @param pattern
	 * @return
	 */
	public static List<String> getDir(String dir, Pattern pattern){
		List<String> list = new ArrayList<String>();
		File dirPath = new File(dir);
		if(dirPath.isDirectory()){
			File[] files = dirPath.listFiles(new FileFilter(){
				@Override
				public boolean accept(File pathname) {
					if(pathname.isDirectory()){
						return true;
					}
					return false;
				}
				
			});
			
			for(File file : files){
				list.add(file.getAbsolutePath());
			}
		}
		return list;
	}
	
	/**
	 * 读取文件 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String filePath) throws Exception{
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		StringBuffer sb = new StringBuffer();
		String str = "";
		while((str=bufReader.readLine()) != null){
			sb.append(str);
		}
		bufReader.close();
		
		return sb.toString();
	}
}
