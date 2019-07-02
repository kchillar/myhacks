package com.ajoy.client.codegen.view.settings;

import java.util.Comparator;

import com.ajoy.model.codegen.ClasspathEntry;

public class ClasspathEntryComparator implements Comparator<ClasspathEntry>
{

	@Override
	public int compare(ClasspathEntry o1, ClasspathEntry o2) 
	{
		if(o1.getPath() == null && o1.getPath() == null)			
			return 0;
		else if(o1.getPath() == null && o1.getPath() != null)
			return 1;
		else if(o1.getPath() != null && o1.getPath() == null)
			return -1;
		else
			return o1.getPath().compareTo(o2.getPath());

	}

}
