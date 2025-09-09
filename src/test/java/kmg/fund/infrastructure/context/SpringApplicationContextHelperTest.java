package kmg.fund.infrastructure.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring アプリケーションコンテキストヘルパーのテスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "nls", "static-method",
})
public class SpringApplicationContextHelperTest {

    /**
     * テスト用のBeanクラス<br>
     */
    static class TestBean {
        // テスト用の空のBean
    }

    /**
     * テスト用の設定クラス<br>
     */
    @Configuration
    @ComponentScan(basePackages = "kmg.fund.infrastructure.context")
    static class TestConfig {

        /**
         * テスト用のBeanを生成する<br>
         *
         * @since 0.1.0
         *
         * @return テスト用のBean
         */
        @Bean
        TestBean testBean() {

            final TestBean result = new TestBean();
            return result;

        }
    }

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public SpringApplicationContextHelperTest() {

        // 処理なし
    }

    /**
     * getBean メソッドのテスト - 正常系:クラス型によるBeanの取得<br>
     *
     * @since 0.1.0
     */
    @SuppressWarnings("resource")
    @Test
    public void testGetBean_normalByClass() {

        /* 期待値の定義 */
        final TestBean expected = new TestBean();

        /* 準備 */
        final ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        context.getBean(SpringApplicationContextHelper.class);

        /* テスト対象の実行 */
        final TestBean actual = SpringApplicationContextHelper.getBean(TestBean.class);

        /* 検証の実施 */
        Assertions.assertNotNull(actual, "Beanが取得できること");
        Assertions.assertEquals(expected.getClass(), actual.getClass(), "取得したBeanの型が一致すること");

    }

    /**
     * getBean メソッドのテスト - 正常系:Bean名によるBeanの取得<br>
     *
     * @since 0.1.0
     */
    @SuppressWarnings("resource")
    @Test
    public void testGetBean_normalByName() {

        /* 期待値の定義 */
        final TestBean expected = new TestBean();

        /* 準備 */
        final ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        context.getBean(SpringApplicationContextHelper.class);

        /* テスト対象の実行 */
        final TestBean actual = SpringApplicationContextHelper.getBean("testBean");

        /* 検証の実施 */
        Assertions.assertNotNull(actual, "Beanが取得できること");
        Assertions.assertEquals(expected.getClass(), actual.getClass(), "取得したBeanの型が一致すること");

    }
}
