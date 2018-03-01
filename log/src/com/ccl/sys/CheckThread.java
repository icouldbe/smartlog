package com.ccl.sys;

import java.io.File;
import java.util.Map;

/**
 * author changlong chen
 * 2018-2-14
 */
public class CheckThread extends Thread{
	private Map<String,String> propMap = LogFactory.getPropMap();
	private InputThread input;
	private boolean keep = true;
	@Override
	public void run() {
		String fileSize = propMap.get("fileSize");
		Long size = Long.valueOf(fileSize)*1024*1024;
		String fileName = propMap.get("fileName");
		Integer fileNumber = Integer.valueOf(propMap.get("fileNumber"));
		String filePath = propMap.get("filePath");
		String appName = propMap.get("appName");
		while(keep)
		{
			File file = input.getFile();
			if(null != file)
			{
				if(file.length() >= size)
				{
					input.end();
					for(int i = fileNumber - 1;i >= 0;i--)
					{
						File file1 = new File(filePath + appName + i + "." + fileName);
						System.out.println(filePath + appName + i + "." + fileName);
						if(file1.exists())
						{
							if(i == fileNumber - 1)
							{
								file1.delete();
							}
							else
							{
								file1.renameTo(new File(filePath + appName + (i + 1) + "." + fileName));
							}
						}
					}
					input = new InputThread();
					LogFactory.setInput(input);
					input.start();
				}
			}
			
		}
	}
	public CheckThread(InputThread input)
	{
		this.input = input;
	}
	public void end()
	{
		this.keep = false;
	}
}
