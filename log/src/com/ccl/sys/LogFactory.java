package com.ccl.sys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * author changlong chen
 * 2018-2-14
 */
public class LogFactory {
	private static Map<String,String> propMap = LogProperty.getProperty();
	private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
	private static Map<String,Object> threadMap = new HashMap<String,Object>();
	public static Log getLog(Class<?> cls)
	{
		if(null == propMap)
		{
			propMap = LogProperty.getProperty();
		}
		Log log = new Log(cls,propMap.get("logLevel"),queue);
		return log;
	}
	public static Map<String, String> getPropMap() {
		return propMap;
	}
	public static ConcurrentLinkedQueue<String> getQueue() {
		return queue;
	}
	public static void startFrame()
	{
		InputThread input = new InputThread();
		CheckThread check = new CheckThread(input);
		threadMap.put("input", input);
		threadMap.put("check", check);
		input.start();
		check.start();
	}
	public static void setInput(InputThread input)
	{
		threadMap.put("input", input);
	}
	public static void endFrame()
	{
		InputThread input = (InputThread)threadMap.get("input");
		CheckThread check = (CheckThread)threadMap.get("check");
		input.end();
		check.end();
	}
}
