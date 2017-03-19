package reflectionBasics;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionMethods {

	public static String getName(Class cls){
		return cls.isArray() ? cls.getSimpleName() : cls.getName();
	}
	
	public static String getClassAccessibleMethods(Class<?> cls) {
		String mList = "";
		Method[] methodsCls = cls.getMethods();
		
		for (Method method: methodsCls){
			mList += method.toGenericString() + "\n";
		}
		return mList;	
	}

	public static String getClassMethods(Class<?> cls) {
		String mList = "";
		Method[] methodsCls = cls.getDeclaredMethods();
		
		for (Method method: methodsCls){
			String name = method.getName();
			Class retType = method.getReturnType();
			
			String modifiers = Modifier.toString(method.getModifiers());
			mList = mList.concat(modifiers + " " + retType.getName() + " " + name);
			
			mList = mList.concat("(");
			Class [] paramTypes = method.getParameterTypes();
			for (int i = 0 ; i < paramTypes.length; i++){
				if (i > 0) mList = mList.concat(", ");
				mList += getName(paramTypes[i]);				
			}
			mList = mList.concat(")");
			mList = mList.concat("\n");
			
			//mList = mList + method.toGenericString() + "\n";
		}
		return mList;
	}
	
	public static String getClassConstructors( Class <?> cls ) {
		String mList = "";
		Constructor[] methodsCls = cls.getDeclaredConstructors();
		
		for (Constructor method: methodsCls){			
			String name = method.getName();
			
			String modifiers = Modifier.toString(method.getModifiers());
			mList = mList.concat(modifiers + " " + name);
			
			mList = mList.concat("(");
			Class [] paramTypes = method.getParameterTypes();
			for (int i = 0 ; i < paramTypes.length; i++){
				if (i > 0) mList = mList.concat(", ");
				mList += getName(paramTypes[i]);				
			}
			mList = mList.concat(")");
			mList = mList.concat("\n");
			
			//mList = mList + method.toGenericString() + "\n";
		}
		return mList;
	}

	public static String getClassFields(Class<?> cls) {
		String fieldList = "";
		
		Field[] fields = cls.getDeclaredFields();
		
		for(Field field: fields){
			String name = field.getName();
			Class type = field.getType();
			String typeName = getName(type);
			String modifiers = Modifier.toString(field.getModifiers());
			fieldList += modifiers + " " + typeName + " " + name + "\n";
			
			//fieldList += field.toGenericString() + "\n";
		}
		return fieldList;
	}
	
	public static String getClassInterFaces(Class<?> cls) {
		String interfaces = "";
		Class <?>[] interfacesCls = cls.getInterfaces();
		for(Class<?> interfaceCls: interfacesCls){
			interfaces += interfaceCls.getName() + "\n";
		}
		return interfaces;
	}

	public static String getClassSuperClass(Class<?> cls) {
		Class<?> baseCls = cls.getSuperclass();
		return baseCls.getName();
	}

	public static String getClassPackageName(Class<?> cls){
		return cls.getPackage().toString();
	}
	
	public static String getClassNameAndModifiers(Class<?> cls){
		Integer mod = cls.getModifiers();
		String className = cls.getName();
		String modifiers = Modifier.toString(mod);
		String output = modifiers + " " + className; 
		return output;
	}

	
}
