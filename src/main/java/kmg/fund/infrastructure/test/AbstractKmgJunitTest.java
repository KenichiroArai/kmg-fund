package kmg.fund.infrastructure.test;

import org.junit.jupiter.api.Assertions;

import kmg.core.infrastructure.cmn.msg.KmgCmnExcMsgTypes;
import kmg.core.infrastructure.cmn.msg.KmgCmnGenMsgTypes;
import kmg.core.infrastructure.exception.KmgMsgException;
import kmg.fund.infrastructure.cmn.msg.KmgFundCmnExcMsgTypes;
import kmg.fund.infrastructure.exception.KmgFundMsgException;

/**
 * KMGのJUnitテストの抽象クラス<br>
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
public class AbstractKmgJunitTest {

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public AbstractKmgJunitTest() {

        // 処理なし
    }

    /**
     * KMG例外の検証を行う<br>
     * <p>
     * 期待する原因のクラスがnullの場合は、nullであることを検証する。
     * </p>
     *
     * @since 0.1.0
     *
     * @param actualException
     *                              実際の例外
     * @param expectedCauseClass
     *                              期待する原因のクラス
     * @param expectedDomainMessage
     *                              期待するドメインメッセージ
     * @param expectedMessageTypes
     *                              期待するメッセージの種類
     */
    protected void verifyKmgMsgException(final KmgMsgException actualException, final Class<?> expectedCauseClass,
        final String expectedDomainMessage, final KmgCmnGenMsgTypes expectedMessageTypes) {

        /* 期待する原因のクラスがnullか */
        if (expectedCauseClass == null) {
            // nullの場合

            // 期待する原因のクラスがnullとして検証する
            this.verifyKmgMsgException(actualException, expectedDomainMessage, expectedMessageTypes);
            return;

        }

        /* 検証の準備 */
        final Throwable         actualCause                   = actualException.getCause();                // 実際の例外の原因
        final String            actualDomainMessage           = actualException.getMessage();              // 実際のドメインメッセージ
        final KmgCmnExcMsgTypes actualMessageTypes            = actualException.getMessageTypes();         // 実際のメッセージタイプ
        final boolean           actualIsMatchMessageArgsCount = actualException.isMatchMessageArgsCount(); // 実際のメッセージ引数の数

        /* 検証の実施 */
        Assertions.assertInstanceOf(expectedCauseClass, actualCause,
            String.format("KmgMsgExceptionの原因が%sであること", expectedCauseClass.getSimpleName()));

        Assertions.assertEquals(expectedDomainMessage, actualDomainMessage, "KmgDomainExceptionのメッセージが正しいこと");
        Assertions.assertEquals(expectedMessageTypes, actualMessageTypes, "メッセージの種類が正しいこと");
        Assertions.assertTrue(actualIsMatchMessageArgsCount, "メッセージ引数の数が一致していること");

    }

    /**
     * KMG例外の検証を行う<br>
     * <p>
     * 実際の例外の原因のクラスがnullであることを検証する。
     * </p>
     *
     * @since 0.1.0
     *
     * @param actualException
     *                              実際の例外
     * @param expectedDomainMessage
     *                              期待するドメインメッセージ
     * @param expectedMessageTypes
     *                              期待するメッセージの種類
     */
    protected void verifyKmgMsgException(final KmgMsgException actualException, final String expectedDomainMessage,
        final KmgCmnGenMsgTypes expectedMessageTypes) {

        /* 検証の準備 */
        final Throwable         actualCause                   = actualException.getCause();                // 実際の例外の原因
        final String            actualDomainMessage           = actualException.getMessage();              // 実際のドメインメッセージ
        final KmgCmnExcMsgTypes actualMessageTypes            = actualException.getMessageTypes();         // 実際のメッセージタイプ
        final boolean           actualIsMatchMessageArgsCount = actualException.isMatchMessageArgsCount(); // 実際のメッセージ引数の数

        /* 検証の実施 */
        Assertions.assertNull(actualCause, "KmgMsgExceptionの原因がnullであること");
        Assertions.assertEquals(expectedDomainMessage, actualDomainMessage, "KmgDomainExceptionのメッセージが正しいこと");
        Assertions.assertEquals(expectedMessageTypes, actualMessageTypes, "メッセージの種類が正しいこと");
        Assertions.assertTrue(actualIsMatchMessageArgsCount, "メッセージ引数の数が一致していること");

    }

    /**
     * KMG Fund例外の検証を行う<br>
     * <p>
     * 期待する原因のクラスがnullの場合は、nullであることを検証する。
     * </p>
     *
     * @since 0.1.0
     *
     * @param actualException
     *                              実際の例外
     * @param expectedCauseClass
     *                              期待する原因のクラス
     * @param expectedDomainMessage
     *                              期待するドメインメッセージ
     * @param expectedMessageTypes
     *                              期待するメッセージの種類
     */
    protected void verifyKmgMsgException(final KmgFundMsgException actualException, final Class<?> expectedCauseClass,
        final String expectedDomainMessage, final KmgFundCmnExcMsgTypes expectedMessageTypes) {

        /* 期待する原因のクラスがnullか */
        if (expectedCauseClass == null) {
            // nullの場合

            // 期待する原因のクラスがnullとして検証する
            this.verifyKmgMsgException(actualException, expectedDomainMessage, expectedMessageTypes);
            return;

        }

        /* 検証の準備 */
        final Throwable         actualCause                   = actualException.getCause();                // 実際の例外の原因
        final String            actualDomainMessage           = actualException.getMessage();              // 実際のドメインメッセージ
        final KmgCmnExcMsgTypes actualMessageTypes            = actualException.getMessageTypes();         // 実際のメッセージタイプ
        final boolean           actualIsMatchMessageArgsCount = actualException.isMatchMessageArgsCount(); // 実際のメッセージ引数の数

        /* 検証の実施 */
        Assertions.assertInstanceOf(expectedCauseClass, actualCause,
            String.format("KmgFundMsgExceptionの原因が%sであること", expectedCauseClass.getSimpleName()));

        Assertions.assertEquals(expectedDomainMessage, actualDomainMessage, "KmgFundMsgExceptionのメッセージが正しいこと");
        Assertions.assertEquals(expectedMessageTypes, actualMessageTypes, "メッセージの種類が正しいこと");
        Assertions.assertTrue(actualIsMatchMessageArgsCount, "メッセージ引数の数が一致していること");

    }

    /**
     * KMG Fund例外の検証を行う<br>
     * <p>
     * 実際の例外の原因のクラスがnullであることを検証する。
     * </p>
     *
     * @since 0.1.0
     *
     * @param actualException
     *                              実際の例外
     * @param expectedDomainMessage
     *                              期待するドメインメッセージ
     * @param expectedMessageTypes
     *                              期待するメッセージの種類
     */
    protected void verifyKmgMsgException(final KmgFundMsgException actualException, final String expectedDomainMessage,
        final KmgFundCmnExcMsgTypes expectedMessageTypes) {

        /* 検証の準備 */
        final Throwable         actualCause                   = actualException.getCause();                // 実際の例外の原因
        final String            actualDomainMessage           = actualException.getMessage();              // 実際のドメインメッセージ
        final KmgCmnExcMsgTypes actualMessageTypes            = actualException.getMessageTypes();         // 実際のメッセージタイプ
        final boolean           actualIsMatchMessageArgsCount = actualException.isMatchMessageArgsCount(); // 実際のメッセージ引数の数

        /* 検証の実施 */
        Assertions.assertNull(actualCause, "KmgFundMsgExceptionの原因がnullであること");
        Assertions.assertEquals(expectedDomainMessage, actualDomainMessage, "KmgFundMsgExceptionのメッセージが正しいこと");
        Assertions.assertEquals(expectedMessageTypes, actualMessageTypes, "メッセージの種類が正しいこと");
        Assertions.assertTrue(actualIsMatchMessageArgsCount, "メッセージ引数の数が一致していること");

    }
}
