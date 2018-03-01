package com.ccl.sys;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * author changlong chen
 * 2018-2-14
 */
public class LogProperty {
	private static Map<String,String> propMap;
	public static Map<String,String> getProperty()
	{
		propMap = new HashMap<String, String>();
		readXML();
		return propMap;
	}
	private static void readXML()
	{
		try
		{
			File file = new File("src/logconfig.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nodeList = document.getChildNodes().item(0).getChildNodes();
			for(int i=0,j=nodeList.getLength();i<j;i++)
			{
				Node node = nodeList.item(i);
				System.out.println(node.getNodeName());
				if(node.getNodeName().equals("fileNumber"))
				{
					NamedNodeMap map = node.getAttributes();
					Node node1 = map.getNamedItem("value");
					propMap.put("fileNumber", node1.getNodeValue());
				}
				if(node.getNodeName().equals("filePath"))
				{
					NamedNodeMap map = node.getAttributes();
					Node node1 = map.getNamedItem("value");
					propMap.put("filePath", node1.getNodeValue());
				}
				if(node.getNodeName().equals("logLevel"))
				{
					NamedNodeMap map = node.getAttributes();
					Node node1 = map.getNamedItem("value");
					propMap.put("logLevel", node1.getNodeValue());
				}
				if(node.getNodeName().equals("fileSize"))
				{
					NamedNodeMap map = node.getAttributes();
					Node node1 = map.getNamedItem("value");
					propMap.put("fileSize", node1.getNodeValue());
				}
				if(node.getNodeName().equals("appName"))
				{
					NamedNodeMap map = node.getAttributes();
					Node node1 = map.getNamedItem("value");
					propMap.put("appName", node1.getNodeValue());
				}
				if(node.getNodeName().equals("fileName"))
				{
					NamedNodeMap map = node.getAttributes();
					Node node1 = map.getNamedItem("value");
					propMap.put("fileName", node1.getNodeValue());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
