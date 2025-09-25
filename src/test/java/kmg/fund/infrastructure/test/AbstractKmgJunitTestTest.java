package kmg.fund.infrastructure.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import kmg.core.infrastructure.cmn.msg.KmgCmnGenMsgTypes;
import kmg.core.infrastructure.exception.KmgMsgException;
import kmg.core.infrastructure.types.msg.KmgCoreGenMsgTypes;
import kmg.fund.infrastructure.cmn.msg.KmgFundCmnExcMsgTypes;
import kmg.fund.infrastructure.exception.KmgFundMsgException;
import kmg.fund.infrastructure.types.msg.KmgFundGenMsgTypes;

/**
 * AbstractKmgJunitTest のテスト<br>
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
public class AbstractKmgJunitTestTest extends AbstractKmgJunitTest {

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
    public AbstractKmgJunitTestTest() {

        // 処理なし
    }

    /**
     * コンストラクタ メソッドのテスト - 正常系:デフォルトコンストラクタの実行
     *
     * @since 0.1.0
     */
    @Test
    public void testAbstractKmgJunitTest_normalDefaultConstructor() {

        /* 期待値の定義 */
        // コンストラクタは正常に実行されることを期待

        /* 準備 */
        // テスト対象のインスタンスを作成

        /* テスト対象の実行 */
        @SuppressWarnings("unused")
        final AbstractKmgJunitTestTest testTarget = new AbstractKmgJunitTestTest();

        /* 検証の準備 */
        // コンストラクタが正常に実行されたことを確認

        /* 検証の実施 */
        // コンストラクタが正常に実行されることを確認（例外が発生しなかった）

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:原因クラスがnullの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_normalCauseClassIsNull() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = null;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
            expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:原因クラスありの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_normalWithCauseClass() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = RuntimeException.class;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
            expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:原因クラスなしの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_normalWithoutCauseClass() {

        /* 期待値の定義 */
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:原因クラスが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_semiCauseClassMismatch() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = IllegalArgumentException.class;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "原因クラスが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:ドメインメッセージが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_semiDomainMessageMismatch() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = RuntimeException.class;
        final String                expectedDomainMessage
                                                          = "[KMGFUND_GEN24001] YAMLにロードするファイルの読み込みに失敗しました。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "ドメインメッセージが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:メッセージタイプが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_semiMessageTypesMismatch() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = RuntimeException.class;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24001;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "メッセージタイプが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:原因クラスなしでドメインメッセージが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_semiWithoutCauseDomainMessageMismatch() {

        /* 期待値の定義 */
        final String                expectedDomainMessage
                                                          = "[KMGFUND_GEN24001] YAMLにロードするファイルの読み込みに失敗しました。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        }, "原因クラスなしでドメインメッセージが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:原因クラスなしでメッセージタイプが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgException_semiWithoutCauseMessageTypesMismatch() {

        /* 期待値の定義 */
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24001;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        }, "原因クラスなしでメッセージタイプが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:KmgFundMsgException原因クラスがnullの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_normalCauseClassIsNull() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = null;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
            expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:KmgFundMsgException原因クラスありの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_normalWithCauseClass() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = RuntimeException.class;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
            expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:KmgFundMsgException原因クラスなしの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_normalWithoutCauseClass() {

        /* 期待値の定義 */
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgFundMsgException原因クラスが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_semiCauseClassMismatch() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = IllegalArgumentException.class;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "原因クラスが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgFundMsgExceptionドメインメッセージが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_semiDomainMessageMismatch() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = RuntimeException.class;
        final String                expectedDomainMessage
                                                          = "[KMGFUND_GEN24001] YAMLにロードするファイルの読み込みに失敗しました。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "ドメインメッセージが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgFundMsgExceptionメッセージタイプが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_semiMessageTypesMismatch() {

        /* 期待値の定義 */
        final Class<?>              expectedCauseClass    = RuntimeException.class;
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24001;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException    testCause           = new RuntimeException("テスト原因");
        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "メッセージタイプが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgFundMsgException原因クラスなしでドメインメッセージが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_semiWithoutCauseDomainMessageMismatch() {

        /* 期待値の定義 */
        final String                expectedDomainMessage
                                                          = "[KMGFUND_GEN24001] YAMLにロードするファイルの読み込みに失敗しました。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        }, "原因クラスなしでドメインメッセージが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgFundMsgException原因クラスなしでメッセージタイプが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgFund_semiWithoutCauseMessageTypesMismatch() {

        /* 期待値の定義 */
        final String                expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgFundCmnExcMsgTypes expectedMessageTypes  = KmgFundGenMsgTypes.KMGFUND_GEN24001;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]            testMessageArgs     = {
            "test.yaml"
        };
        final KmgFundMsgException testActualException = new KmgFundMsgException(KmgFundGenMsgTypes.KMGFUND_GEN24000,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        }, "原因クラスなしでメッセージタイプが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:KmgMsgException原因クラスがnullの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_normalCauseClassIsNull() {

        /* 期待値の定義 */
        final Class<?>          expectedCauseClass    = null;
        final String            expectedDomainMessage = "[KMGCORE_GEN11100] test.yamlがありません。";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11100;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]        testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
            expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:KmgMsgException原因クラスありの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_normalWithCauseClass() {

        /* 期待値の定義 */
        final Class<?>          expectedCauseClass    = RuntimeException.class;
        final String            expectedDomainMessage = "[KMGCORE_GEN11100] test.yamlがありません。";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11100;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException testCause           = new RuntimeException("テスト原因");
        final Object[]         testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException  testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
            expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 正常系:KmgMsgException原因クラスなしの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_normalWithoutCauseClass() {

        /* 期待値の定義 */
        final String            expectedDomainMessage = "[KMGCORE_GEN11100] test.yamlがありません。";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11100;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]        testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（例外が発生しないことを確認）
        this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        /* 検証の準備 */
        final boolean actualNoException = true; // 例外が発生しなかった

        /* 検証の実施 */
        Assertions.assertTrue(actualNoException, "verifyKmgMsgExceptionメソッドが正常に実行されること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgMsgException原因クラスが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_semiCauseClassMismatch() {

        /* 期待値の定義 */
        final Class<?>          expectedCauseClass    = IllegalArgumentException.class;
        final String            expectedDomainMessage = "[KMGCORE_GEN11100] test.yamlがありません。";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11100;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException testCause           = new RuntimeException("テスト原因");
        final Object[]         testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException  testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "原因クラスが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgMsgExceptionドメインメッセージが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_semiDomainMessageMismatch() {

        /* 期待値の定義 */
        final Class<?>          expectedCauseClass    = RuntimeException.class;
        final String            expectedDomainMessage
                                                      = "[KMGCORE_GEN11200] フィールドの取得に失敗しました。フィールド名=[test.yaml]、対象のクラス=[TestClass]、最後に取得したフィールド=[testField]";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11100;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException testCause           = new RuntimeException("テスト原因");
        final Object[]         testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException  testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "ドメインメッセージが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgMsgExceptionメッセージタイプが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_semiMessageTypesMismatch() {

        /* 期待値の定義 */
        final Class<?>          expectedCauseClass    = RuntimeException.class;
        final String            expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11200;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final RuntimeException testCause           = new RuntimeException("テスト原因");
        final Object[]         testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException  testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs, testCause);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedCauseClass, expectedDomainMessage,
                expectedMessageTypes);

        }, "メッセージタイプが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgMsgException原因クラスなしでドメインメッセージが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_semiWithoutCauseDomainMessageMismatch() {

        /* 期待値の定義 */
        final String            expectedDomainMessage
                                                      = "[KMGCORE_GEN11200] フィールドの取得に失敗しました。フィールド名=[test.yaml]、対象のクラス=[TestClass]、最後に取得したフィールド=[testField]";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11100;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]        testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        }, "原因クラスなしでドメインメッセージが一致しない場合はAssertionErrorが発生すること");

    }

    /**
     * verifyKmgMsgException メソッドのテスト - 準正常系:KmgMsgException原因クラスなしでメッセージタイプが一致しない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testVerifyKmgMsgExceptionKmgMsg_semiWithoutCauseMessageTypesMismatch() {

        /* 期待値の定義 */
        final String            expectedDomainMessage = "[KMGFUND_GEN24000] 該当するYAMLファイルがありません。ファイルパス=[test.yaml]";
        final KmgCmnGenMsgTypes expectedMessageTypes  = KmgCoreGenMsgTypes.KMGCORE_GEN11200;

        /* 準備 */
        // Springのアプリケーションコンテキストを初期化
        @SuppressWarnings({
            "resource", "unused"
        })
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        final Object[]        testMessageArgs     = {
            "test.yaml"
        };
        final KmgMsgException testActualException = new KmgMsgException(KmgCoreGenMsgTypes.KMGCORE_GEN11100,
            testMessageArgs);

        /* テスト対象の実行 */
        // verifyKmgMsgExceptionメソッドを実行（AssertionErrorが発生することを期待）
        Assertions.assertThrows(AssertionError.class, () -> {

            this.verifyKmgMsgException(testActualException, expectedDomainMessage, expectedMessageTypes);

        }, "原因クラスなしでメッセージタイプが一致しない場合はAssertionErrorが発生すること");

    }

}
