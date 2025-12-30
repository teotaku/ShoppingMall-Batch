package shoppingmall.ecommerce.batch.domain.util;

import shoppingmall.ecommerce.batch.domain.product.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;

public class ReflectionUtils {


    public static List<String> getFieldNames(Class<?> clazz) {

        List<String> filedNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
                if(!isStatic(field.getModifiers()))
            filedNames.add(field.getName());
        }
        return filedNames;
    }
}
