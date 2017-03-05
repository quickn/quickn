package cn.json.annotation;

import java.lang.annotation.*;

/**
 * Created by json2 on 2017/3/5.
 */

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface MyAnno {
    /**
     * 是否能为null
     * @return
     */
    boolean isCanNull() default true;

    /**
     * 是否能为空字符串
     * @return
     */
    boolean isCanEmpty() default true;

    /**
     * 是否能为0
     * @return
     */
    boolean isCanZero() default true;
}
