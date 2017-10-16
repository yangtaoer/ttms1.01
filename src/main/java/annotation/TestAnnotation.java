package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Autowired{}
class Student{
	@Autowired
	private int age;
	private String name;
	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + "]";
	}
	
}


public class TestAnnotation {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Student s1 = new Student();
		//获取类对象
		Class<?> c1 = s1.getClass();
		//获得Student类中的所有属性
		Field fs[] = c1.getDeclaredFields();
		for(Field f:fs){
			//判断f属性是否使用了Annotation注解修饰
			if(f.isAnnotationPresent(Autowired.class)){
				//设置属性可以访问
				f.setAccessible(true);
				//为s1对象的f属性赋值
				f.set(s1,18);
				
				
			}
		}
		System.out.println(s1);
	}

}
