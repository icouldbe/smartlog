package com.ccl.sys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * author changlong chen
 * 2018-2-14
 */
public class InputThread extends Thread {
	private static Map<String,String> propMap = LogFactory.getPropMap();
	private static ConcurrentLinkedQueue<String> queue = LogFactory.getQueue();
	public boolean keep = true;
	private static int index = 0;
	private File file;
	@Override
	public void run() {
		try
		{
			file = new File(propMap.get("filePath")+propMap.get("appName")+index+".log");
			FileWriter fileWriter = new FileWriter(file,true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			while(keep)
			{
				if(!queue.isEmpty())
				{
					System.out.println(queue.size());
					writer.write(queue.poll());
					writer.flush();
				}
			}
			writer.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			//File file1;
			//e.printStackTrace(new FileWriter(file1).);
		}
	}
	public File getFile()
	{
		return file;
	}
	public void end()
	{
		this.keep = false;
		System.out.println("end");
	}
}
