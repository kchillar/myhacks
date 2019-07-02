package com.ajoy.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ClassTest 
{
	private static Log log = new Log(ClassTest.class);

    List<String> stringList = new ArrayList<String>();
    List<Integer> integerList = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception
	{
		int var = 10;
		int var2 = 11;
		
			
		Class c1 = byte.class;
		Class c2 = byte[].class;		
		Class c3 = char.class;
		Class c4 = char[].class;
		Class c5 = Integer[].class;
		
		String s1 = c1.getName();
		String s2 = c2.getName();
		String s3 = c3.getName();
		String s4 = c4.getName();
		
		log.info(s1+", "+s2+", "+s3+", "+s4+", "+c5.getName()+", "+c5.isArray()+" "+c5.getComponentType().getName());
		
		List<Book> list = new ArrayList<Book>();
		Class c6 = list.getClass();
		
		s1 = c6.getName();
		s2 = c6.getTypeName();
		 
				
		log.info(s1+", "+s2+" typeVariable: "+ c6.getTypeParameters()[0].getTypeName());
		
		
		Field stringListField = ClassTest.class.getDeclaredField("stringList");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.

        Field integerListField = ClassTest.class.getDeclaredField("integerList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass); // class java.lang.Integer.
        System.out.println(integerListType.getTypeName()+" "+integerListType.getRawType().getTypeName());
	}
}

class Book
{
	private String title = "BG"; 
}
