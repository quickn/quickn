package cn.json;

import cn.json.annotation.AnnotationImp;
import cn.json.annotation.MethodAnnotation;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by json2 on 2017/3/5.
 */
public class AnnotationTest extends TestCase {

    @Test
    public void testAdd(){
        System.out.println("test");
    }
    @Test
    public void testDel(){
        List<Method> list  = Arrays.asList(AnnotationImp.class.getDeclaredMethods());
        for(Method method :list){
            for(Annotation annotation:method.getDeclaredAnnotations()){
                MethodAnnotation name = method.getAnnotation(MethodAnnotation.class);
                System.out.println(name.age());
                System.out.println(annotation.annotationType());
                System.out.println(annotation.toString());
            }
        }

    }
}
