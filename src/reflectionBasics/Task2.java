package reflectionBasics;

import java.lang.reflect.*;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Task2 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException, SecurityException {
				
		Object obj = createObject(Employee.class);
		String objString = null;
		
		printObjectState(obj);
		
		Scanner scn = new Scanner(System.in);
		int execute = 1;
		while(execute == 1){
			System.out.println(
					"0 ����� �� ��������� \n"
				  + "1 ����� ������� ������� \n"
				  + "2 ��������� ������ � ������ \n"
				  + "3 ������������ ������ �� ������ \n"); // �������� ��� �������� � ������������ ������
			
			int n = scn.nextInt();
			
			switch (n) {
				case 0: System.out.print("Program stopped");
						System.exit(0);
						break;
				case 1: executeObjectMethods(obj);
						break;
				case 2: ReflectionAnalyzer rS = new ReflectionAnalyzer();
						objString = rS.toString(obj);
						printObjectState(obj);
						break;
				case 3: 
						try {
							obj = objectFromString(Employee.class ,objString);
						}catch (NoSuchFieldException e){
							System.out.println("���������� ������� ������������ ������, �.�. ���� ������� �������� �� ����������� ����");
						}
						printObjectState(obj);
						break;
			}
		}
		
		// -----------------------------------------------------
	}
	
	public static void printObjectState(Object obj){
		System.out.println("��������� �������: ");
		ReflectionAnalyzer r = new ReflectionAnalyzer();
		System.out.println(r.toString(obj));
	}
	
	
	public static Object objectFromString(Class<?> cls, String objString) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		Object obj = cls.newInstance();
		StringTokenizer sTokenizer = new StringTokenizer(objString,",\n");
		
		while (sTokenizer.hasMoreTokens()){
			String fieldAndValue = sTokenizer.nextToken();
			String[] array = fieldAndValue.split("=");			
			Field field = cls.getDeclaredField(array[0]);
			field.setAccessible(true);
			String value = array[1];
			Class <?> fieldType = field.getType();

			if (fieldType == String.class){
				field.set(obj, value);
				continue;
			}
			
			if (fieldType.isPrimitive()){
				switch (fieldType.getSimpleName()){
					case "int": 	field.set(obj, Integer.valueOf(value));
								 	break;
					case "boolean": field.set(obj, Boolean.valueOf(value));
		 							break;
					case "char": 	field.set(obj, value.charAt(0));
									break;
					case "byte": 	field.set(obj, Byte.valueOf(value));
									break;
					case "short": 	field.set(obj, Short.valueOf(value));
									break;
					case "long": 	field.set(obj, Long.valueOf(value));
									break;
					case "float": 	field.set(obj, Float.valueOf(value));
									break; 
					case "double": 	field.set(obj, Double.valueOf(value));
									break; 
				}
				continue;
			}
		}
		System.out.println("\n");
		return obj;
	}
	
	
	public static void executeObjectMethods (Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException{
		int execute = 1;
		
		while (execute == 1){
			Class classObj  = obj.getClass();
			Method[] methods = classObj.getDeclaredMethods();
			
			System.out.println("��������� ������:");
			int i = 0;
			for (Method method: methods){		
				System.out.println(i++ +" "+ method.toGenericString());
			}
	
			System.out.println("������� ���������� ����� ������: ");
			Scanner scn = new Scanner(System.in);
			int n = scn.nextInt();
	
			Method method = methods[n];
			Class[] params = method.getParameterTypes();
			Object[] arguments = null;
			
			if (params.length > 0){ 
				System.out.println("��������� ������: ");
				arguments = (Object[]) collectArgs(params);		
			}
			
			System.out.println(methods[n].invoke(obj, arguments));
			
			printObjectState(obj);
			
			System.out.println("Continue 1/0?");
			execute = scn.nextInt();
		}		
	}
	
	public static Object createObject (Class<?> cls) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Constructor<?>[] ctors = cls.getDeclaredConstructors();
		int i = 0;
		for(Constructor<?> c: ctors){
			System.out.println(i++ + " " + c.toGenericString());
		}
		
		System.out.println("������� ���������� ����� ������������: ");
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		
		Constructor constrSelected = ctors[n];
		Class[] params = constrSelected.getParameterTypes();
		Object[] arguments = null;
		if (params.length > 0){
			System.out.println("��������� ������������: ");
			arguments = (Object[]) collectArgs(params);
		}
		Object obj = constrSelected.newInstance(arguments);
						
		return obj;
	}
	
	public static Object collectArgs (Class[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Object[] arguments = new Object[params.length];
		Scanner scn = new Scanner(System.in);
	
		for (int i = 0; i < params.length; i++){
			
			Class p = params[i]; 			
			System.out.println("������� " + p.getSimpleName() + " ��������:");			
			
			if ( p == String.class ){
				arguments[i] = scn.next();
				continue;
			}
			
			if ( p.isArray() ){
				Class<?> compClass = p.getComponentType();
				System.out.println("������� ���������� ����������� �������");
				int length = scn.nextInt();
				Object newArray = Array.newInstance(compClass, length);
				
				for (int k = 0; k < length; k++){
					
					if (compClass == String.class){
						System.out.println("������� �������� N" + k + " ���������� ������� " + compClass.getSimpleName());
						Array.set(newArray, k, scn.next());
						continue;
					}
					
					if (compClass.isPrimitive()){
						System.out.println("������� �������� ���������� �������");
						switch (compClass.getSimpleName()){
							case "int": 	Array.setInt(newArray, k, scn.nextInt());
										 	break;
							case "boolean": Array.setBoolean(newArray, k, scn.nextBoolean());
				 							break;
							case "char": 	Array.setChar(newArray, k, scn.next().charAt(0));
											break;
							case "byte": 	Array.setByte(newArray, k, scn.nextByte());
											break;
							case "short": 	Array.setShort(newArray, k, scn.nextShort());
											break;
							case "long": 	Array.setLong(newArray, k, scn.nextLong());
											break;
							case "float": 	Array.setFloat(newArray, k, scn.nextFloat());
											break; 
							case "double": 	Array.setDouble(newArray, k, scn.nextDouble());
											break; 
						}
						continue;
					}
					Array.set(newArray, k, createObject(compClass));										
				}
				arguments[i] = newArray;
				continue;
			}			
			
			
			if ( p.isPrimitive() ){				
				switch (p.getSimpleName()){
					case "int": 	arguments[i] = scn.nextInt();
								 	break;
					case "boolean": arguments[i] = scn.nextBoolean();
		 							break;
					case "char": 	arguments[i] = scn.next().charAt(0);
									break;
					case "byte": 	arguments[i] = scn.nextByte();
									break;
					case "short": 	arguments[i] = scn.nextShort();
									break;
					case "long": 	arguments[i] = scn.nextLong();
									break;
					case "float": 	arguments[i] = scn.nextFloat();
									break; 
					case "double": 	arguments[i] = scn.nextDouble();
									break; 
				}
				continue;
			}
			
			arguments[i] = createObject(p);			
			
		}
		
		return arguments;
	}

}