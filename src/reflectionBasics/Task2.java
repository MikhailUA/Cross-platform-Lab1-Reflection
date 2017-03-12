package reflectionBasics;

import java.lang.reflect.*;
import java.util.Scanner;


public class Task2 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> cls =  Integer.class;	
		
		Constructor[] ctors = cls.getDeclaredConstructors();
		
		for(Constructor c: ctors){
			System.out.println(c.toGenericString());
		}
		
		System.out.println("Введите порядковый номер конструктура: ");
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
				
		Type[] cTypes = ctors[n].getGenericParameterTypes();
		
		for (Type t: cTypes){
			System.out.println("Type name: " + t.getTypeName());
		}
		
		Object obj = ctors[n].newInstance(String.valueOf(5));
		
		System.out.println(obj.toString());
		
		Method[] methods = cls.getMethods();
		
		int i = 0;
		for (Method method: methods){
			System.out.println(i++ +" "+ method.toGenericString());
		}
		
		System.out.println("Введите порядковый номер метода: ");
		n = scn.nextInt();
		
		System.out.println(methods[n].invoke(obj));
		
		ReflectionAnalyzer objToString = new ReflectionAnalyzer();
		System.out.println(objToString.toString(obj));
		// записать в строку с | разделителями
		// StringTokenizer
	}

}
