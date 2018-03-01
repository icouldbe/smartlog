package com.ccl.sys;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * author changlong chen
 * 2018-2-14
 */
public class Log {
	private String level;
	private ConcurrentLinkedQueue<String> queue;
	private Class<?> cls;
	public Log(Class<?> cls,String level,ConcurrentLinkedQueue<String> queue)
	{
		this.cls = cls;
		this.level = level;
		this.queue = queue;
	}
	public void debug(String...strings)
	{
		if(this.level.equals("debug"))
		{
			Date date = new Date();
			String time = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+" ";
			String codeInfo = "";
			StackTraceElement[] elements = Thread.currentThread().getStackTrace();
			for(int i=0,j=elements.length;i<j;i++)
			{
				StackTraceElement element = elements[i];
				if(element.getClassName().equals(cls.getName()))
				{
					codeInfo = element.getClassName()+"."+element.getMethodName()+"("+element.getFileName()+" "+element.getLineNumber()+");\r\n";
				}
			}
			StringBuffer buffer = new StringBuffer();
			for(int i=0,j=strings.length;i<j;i++)
			{
				buffer.append(strings[i]);
			}
			buffer.append(" ");
			String info = time+buffer.toString()+codeInfo;
			queue.add(info);
		}
	}
	public void error(String...strings)
	{
		if(this.level.equals("debug") || this.level.equals("error"))
		{
			Date date = new Date();
			String time = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+" ";
			String codeInfo = "";
			StackTraceElement[] elements = Thread.currentThread().getStackTrace();
			for(int i=0,j=elements.length;i<j;i++)
			{
				StackTraceElement element = elements[i];
				if(element.getClassName().equals(cls.getName()))
				{
					codeInfo = element.getClassName()+element.getMethodName()+"("+element.getFileName()+" "+element.getLineNumber()+")\r\n";
				}
			}
			StringBuffer buffer = new StringBuffer();
			for(int i=0,j=strings.length;i<j;i++)
			{
				buffer.append(strings[i]);
			}
			buffer.append(" ");
			String info = time+buffer.toString()+codeInfo;
			queue.add(info);
		}
	}
}
