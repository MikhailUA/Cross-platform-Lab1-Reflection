package reflectionBasics;

import java.lang.reflect.*;

/* 
имя пакета
модификаторы и имя класса
базовый класс
список реализованных интерфейсов
список полей
список конструкторов
список методов и их характеристики
список всех общедоступных методов класса: объявленных в этом классе и унаследованных от базового
*/

public class Task1 {

	public static void main(String[] args){
		
		String name;
		/*
		Scanner in = new Scanner(System.in);
		System.out.println("Enter class name (e.g. java.util.Date): ");
		name = in.nextLine();*/
		name = "java.lang.Double";		
		 
		try{
			Class<?> cls = Class.forName(name);
	
			String clsPackageName 		= ReflectionMethods.getClassPackageName(cls);
			String clsNameAndModifiers 	= ReflectionMethods.getClassNameAndModifiers(cls);
			String clsSuperClass 		= ReflectionMethods.getClassSuperClass(cls);
			String clsInterfaces 		= ReflectionMethods.getClassInterFaces(cls);
			String clsListOfFields 		= ReflectionMethods.getClassFields(cls);					
			String clsConstructors 		= ReflectionMethods.getClassConstructors(cls); 			
			String clsMethods 			= ReflectionMethods.getClassMethods(cls);					
			String clsAccessibleMethods = ReflectionMethods.getClassAccessibleMethods(cls);	
			
			System.out.println(clsNameAndModifiers); 
			System.out.println(clsNameAndModifiers + " extends "+ clsSuperClass + "\n");
			System.out.println("Implements: " + clsInterfaces);
			System.out.println("Class Fields: \n" + clsListOfFields);
			System.out.println("Class Constructors: \n" + clsConstructors);
			System.out.println("Class methods: \n"  + clsMethods);
			System.out.println("Class accessible methods: \n"  + clsAccessibleMethods);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

	
}
