package shoppingmall.ecommerce.batch.domain.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilsTest {


    private static class TestClass {
        private String stringField;
        private int intField;
        public static final String CONSTANT = "constant";
    }

    @Test
    void testGetFieldNames() {
        List<String> filedNames = ReflectionUtils.getFieldNames(TestClass.class);

        assertThat(filedNames).hasSize(2)
                .containsExactly("stringField", "intField")
                .doesNotContain("CONSTANT");

    }

}