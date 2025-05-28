package kmg.fund.infrastructure.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import kmg.fund.infrastructure.types.msg.KmgFundGenMsgTypes;

/**
 * KMG 基盤メッセージ例外テスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "static-method",
})
public class KmgFundMsgExceptionTest {

    /**
     * テスト用の設定クラス
     */
    @Configuration
    @ComponentScan(basePackages = "kmg.fund.infrastructure")
    static class TestConfig {

        /**
         * メッセージソースを返す<br>
         *
         * @since 0.1.0
         *
         * @return メッセージソース
         */
        @Bean
        ResourceBundleMessageSource messageSource() {

            final ResourceBundleMessageSource result = new ResourceBundleMessageSource();
            result.setBasenames("kmg-fund-messages");
            result.setDefaultEncoding("UTF-8");
            return result;

        }
    }

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public KmgFundMsgExceptionTest() {

        // 処理なし
    }

    /**
     * テストの前準備
     */
    @SuppressWarnings({
        "unused", "resource"
    })
    @BeforeEach
    public void setUp() {

        // Springのアプリケーションコンテキストを初期化
        new AnnotationConfigApplicationContext(TestConfig.class);

    }

    /**
     * コンストラクタのテスト<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testConstructor() {

        // テストデータ
        final KmgFundGenMsgTypes messageTypes = KmgFundGenMsgTypes.KMGFUND_GEN24000;
        final Object[]           messageArgs  = {
            "test"
        };
        final Throwable          cause        = new RuntimeException("テスト例外");

        // テスト実行
        final KmgFundMsgException exception1 = new KmgFundMsgException(messageTypes);
        final KmgFundMsgException exception2 = new KmgFundMsgException(messageTypes, messageArgs);
        final KmgFundMsgException exception3 = new KmgFundMsgException(messageTypes, cause);
        final KmgFundMsgException exception4 = new KmgFundMsgException(messageTypes, messageArgs, cause);

        // 検証
        Assertions.assertNotNull(exception1);
        Assertions.assertNotNull(exception2);
        Assertions.assertNotNull(exception3);
        Assertions.assertNotNull(exception4);
        Assertions.assertEquals(messageTypes, exception1.getMessageTypes());
        Assertions.assertEquals(messageTypes, exception2.getMessageTypes());
        Assertions.assertEquals(messageTypes, exception3.getMessageTypes());
        Assertions.assertEquals(messageTypes, exception4.getMessageTypes());
        Assertions.assertArrayEquals(messageArgs, exception2.getMessageArgs());
        Assertions.assertArrayEquals(messageArgs, exception4.getMessageArgs());
        Assertions.assertEquals(cause, exception3.getCause());
        Assertions.assertEquals(cause, exception4.getCause());

    }
}
