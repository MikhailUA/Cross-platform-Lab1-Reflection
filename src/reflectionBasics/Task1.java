package reflectionBasics;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

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

	public static void main(String[] args) throws ClassNotFoundException {
		
		//Scanner in = new Scanner(System.in);
		
		//String str = in.nextLine();
		String str = "java.lang.Integer";
		
		Class<?> cls = Class.forName(str);

		String clsPackageName = getClassPackageName(cls);
		String clsNameAndModifiers = getClassNameAndModifiers(cls);
		String clsSuperClass = getClassSuperClass(cls);
		String clsInterfaces = getClassInterFaces(cls);
		String clsListOfFields = getClassFields(cls);
		String clsConstructors = getClassConstructors(cls);
		String clsMethods = getClassMethods(cls);
		String clsAccessibleMethods = getClassAccessibleMethods(cls);
		
		System.out.println(clsNameAndModifiers + " extends "+ clsSuperClass + "\n");
		System.out.println("Implements: " + clsInterfaces);
		System.out.println("Class Fields: \n" + clsListOfFields);
		System.out.println("Class Constructors: \n" + clsConstructors);
		System.out.println("Class methods: \n"  + clsMethods);
		System.out.println("Class accessible methods: \n"  + clsAccessibleMethods);
		
	}
	
	private static String getClassAccessibleMethods(Class<?> cls) {
		String mList = "";
		Method[] methodsCls = cls.getMethods();
		
		for (Method method: methodsCls){
			mList = mList + method.toGenericString() + "\n";
		}
		return mList;	
	}

	private static String getClassMethods(Class<?> cls) {
		String mList = "";
		Method[] methodsCls = cls.getDeclaredMethods();
		
		for (Method method: methodsCls){
			mList = mList + method.toGenericString() + "\n";
		}
		return mList;
	}

	private static String getClassConstructors(Class<?> cls) {
		String constrList = "";
		Constructor<?>[] constructorsCls= cls.getDeclaredConstructors();
		
		for (Constructor<?> constructor: constructorsCls){
			constrList = constrList + constructor.toGenericString() + "\n";			
		}
		return constrList;
	}

	private static String getClassFields(Class<?> cls) {
		String fieldList = "";
		
		Field[] fields = cls.getDeclaredFields();
		
		for(Field field: fields){
			fieldList = fieldList + field.toGenericString() + "\n";
		}
		return fieldList;
	}

	private static String getClassInterFaces(Class<?> cls) {
		String interfaces = "";
		Class <?>[] interfacesCls = cls.getInterfaces();
		for(Class interfaceCls: interfacesCls){
			interfaces = interfaces + interfaceCls.getName() + "\n";
		}
		return interfaces;
	}

	private static String getClassSuperClass(Class<?> cls) {
		Class<?> baseCls = cls.getSuperclass();
		return baseCls.getName();
	}

	public static String getClassPackageName(Class cls){
		return cls.getPackage().toString();
	}
	
	public static String getClassNameAndModifiers(Class cls){
		return cls.toGenericString();
	}
	
	
}
