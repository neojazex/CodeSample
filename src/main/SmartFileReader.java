package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that reads in a given file, and given the class can populate its fields
 * via reflection
 * 
 * @author Jason
 * 
 */
public class SmartFileReader
{
	private String splitChar = "\t";
	
	private Map<String, Method> fieldSetMethods;
	
	
	/**
	 * Given the File to read from and the object type, the reader interprets
	 * the first row in the txt/scv file as field names of the given class, then
	 * via reflection creates the objects from the data stored in the file,
	 * returning them as a list
	 * @param <T>
	 * 
	 * @param fileToRead
	 * @param clazz
	 * @return
	 */
	public <T extends Object> List<T> readInto(File fileToRead, Class<T> clazz)
	{
		List<T> toReturn = new ArrayList<T>();
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileToRead);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String[] header = bufferReader.readLine().split(splitChar);
			
			fieldSetMethods = findSetMethods(header, clazz);

			String nextLine = bufferReader.readLine();
			
			while(nextLine != null && !nextLine.trim().isEmpty() )
			{
				String[] values = nextLine.split(splitChar);
				
				toReturn.add(createObjectFromValues(header, values, clazz));

				nextLine = bufferReader.readLine();
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	/**
	 * Can only be called if {@link readInto} has already been called
	 * <p>
	 * returns a list of String representin the header fields for the previous
	 * LoadInto call
	 * 
	 * @return
	 */
	public List<String> getHeaderNames()
	{
		if(fieldSetMethods == null)
		{
			throw new IllegalStateException("Cannot return headers until readInto is called");
		}
		
		return new ArrayList<String>(fieldSetMethods.keySet());
	}
	
	/**
	 * Method that creates a T object based on the given class and populates it
	 * with the given values, using the headers to call each method to set the
	 * values
	 * 
	 * @param header
	 * @param values
	 * @param clazz
	 * @return
	 */
	private <T extends Object> T createObjectFromValues(String[] header, String[] values, Class<T> clazz)
	{		
		T toReturn = null;
		try {
			toReturn = (T) clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
		for(int i = 0 ; i < header.length; ++i)
		{
			Method toUse = fieldSetMethods.get(header[i]);
			
			if(toUse != null)
			{
				try {
					Class[] types = toUse.getParameterTypes();
					
					if(types[0].isAssignableFrom(Integer.TYPE))
					{
						int parsedValue = Integer.parseInt(values[i]);
						toUse.invoke(toReturn, parsedValue);
					}
					else
					{
						toUse.invoke(toReturn, values[i]);
					}
				}
				catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		return toReturn;
	}
	
	/**
	 * For the given header String Array this method uses the given class-
	 * searchers for a set Methods for each, and adds then to a Map for later
	 * use in {@link createObjectFromValues}
	 * 
	 * @param header
	 * @param clazz
	 * @return
	 */
	private <T extends Object> Map<String, Method> findSetMethods(String[] header, Class<T> clazz)
	{
		Map<String, Method> toReturn = new LinkedHashMap<String, Method>();
		
		for(String fieldName: Arrays.asList(header))
		{
			Method foundMethod = null;
			for(String prefix : Arrays.asList("set", "setTeam"))
			{
				for(Class type : Arrays.asList(String.class, Integer.TYPE))
				{
					try {
						foundMethod = clazz.getMethod(prefix + fieldName, type);
					}
					catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					
					if(foundMethod != null)
					{
						toReturn.put(fieldName, foundMethod);
						break;
					}
				}
				
				if(foundMethod != null)
				{
					break;
				}
			}
		}
		
		return toReturn;
	}

}
