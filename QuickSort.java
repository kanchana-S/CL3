/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quick;

/**
 *
 * @author Kanchana
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class QuickSort {
	
	void quicksort(ArrayList<Integer> a, int beg,int last)
	{
	int i=beg;
	int j=last;
	int mid=(beg+last)/2;
	int pivot=a.get(mid);
	
	while(i<=j)
	{
		while(a.get(i)<pivot)
		{
			i++;
		}
		while(a.get(j)>pivot)
		{
			j--;
		}
		if(i<=j)
		{
		int temp=a.get(i);
		a.set(i, a.get(j));
		a.set(j, temp);
		i++;
		j--;
		}
		}
	
	if(beg<j)
	{
		quicksort(a,beg,j);
	}
	if(i<last)
	{
		quicksort(a,i,last);
	}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		QuickSort q = new QuickSort() ;
		

		ArrayList<String> ar = new ArrayList<>();
		ArrayList<Integer> numbers = new ArrayList<>(ar.size()) ;
		
		try {

			File fXmlFile = new File("C:\\Users\\Kanchana\\Documents\\NetBeansProjects\\Quick\\src\\quick\\input.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Input");
			NodeList nList1 = doc.getElementsByTagName("value");
			 int len=nList1.getLength();

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
                    
					for (int i = 0; i < nList1.getLength(); i++)
					{
					
					ar.add(eElement.getElementsByTagName("value").item(i).getTextContent());
					
					}
				}
			}
                    for (String ar1 : ar) {
                        numbers.add(Integer.parseInt(ar1));
                    }
			
			
		    } catch (ParserConfigurationException | SAXException | IOException | DOMException | NumberFormatException e) {
		    }
		for(int i = 0; i < ar.size(); i++) {
			  System.out.println(numbers.get(i));   
			}
		
		q.quicksort(numbers,0,ar.size()-1);
		System.out.println("sorted elements:");
		for(int k=0;k<ar.size();k++)
		{
			 System.out.println(numbers.get(k));
			
		}
		
		  }

	}