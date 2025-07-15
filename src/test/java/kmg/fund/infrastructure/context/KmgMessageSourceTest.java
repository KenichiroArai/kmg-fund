package kmg.fund.infrastructure.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import kmg.core.infrastructure.cmn.msg.KmgCmnExcMsgTypes;
import kmg.core.infrastructure.cmn.msg.KmgCmnGenMsgTypes;
import kmg.core.infrastructure.cmn.msg.KmgCmnLogMsgTypes;
import kmg.core.infrastructure.cmn.msg.KmgCmnMsgTypes;
import kmg.fund.infrastructure.types.msg.KmgFundGenMsgTypes;
import kmg.fund.infrastructure.types.msg.KmgFundLogMsgTypes;

/**
 * KMGメッセージリソースのテスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "nls",
})
public class KmgMessageSourceTest {

    /** テスト対象 */
    @InjectMocks
    private KmgMessageSource testTarget;

    /** モック */
    @Mock
    private MessageSource messageSource;

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public KmgMessageSourceTest() {

        // 処理なし
    }

    /**
     * 初期化<br>
     *
     * @since 0.1.0
     */
    @SuppressWarnings("resource")
    @BeforeEach
    public void initialize() {

        MockitoAnnotations.openMocks(this);

    }

    /**
     * getExcMessage メソッドのテスト - 正常系:例外メッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetExcMessage_normalBasicMessage() {

        /* 期待値の定義 */
        final String expected = "[KMGFUND_GEN24000] テストメッセージ";

        /* 準備 */
        final KmgCmnExcMsgTypes type = KmgFundGenMsgTypes.KMGFUND_GEN24000;
        Mockito.when(this.messageSource.getMessage(type.getCode(), null, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getExcMessage(type);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "例外メッセージが一致しません");

    }

    /**
     * getExcMessage メソッドのテスト - 正常系:引数付き例外メッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetExcMessage_normalMessageWithArgs() {

        /* 期待値の定義 */
        final String expected = "[KMGFUND_GEN24000] テストメッセージ: 引数1";

        /* 準備 */
        final KmgCmnExcMsgTypes type = KmgFundGenMsgTypes.KMGFUND_GEN24000;
        final Object[]          args = {
            "引数1"
        };
        Mockito.when(this.messageSource.getMessage(type.getCode(), args, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ: 引数1");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getExcMessage(type, args);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "引数付き例外メッセージが一致しません");

    }

    /**
     * getGenMessage メソッドのテスト - 正常系:一般メッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetGenMessage_normalBasicMessage() {

        /* 期待値の定義 */
        final String expected = "テストメッセージ";

        /* 準備 */
        final KmgCmnGenMsgTypes type = KmgFundGenMsgTypes.KMGFUND_GEN24000;
        Mockito.when(this.messageSource.getMessage(type.getCode(), null, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getGenMessage(type);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "一般メッセージが一致しません");

    }

    /**
     * getGenMessage メソッドのテスト - 正常系:引数付き一般メッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetGenMessage_normalMessageWithArgs() {

        /* 期待値の定義 */
        final String expected = "テストメッセージ: 引数1";

        /* 準備 */
        final KmgCmnGenMsgTypes type = KmgFundGenMsgTypes.KMGFUND_GEN24000;
        final Object[]          args = {
            "引数1"
        };
        Mockito.when(this.messageSource.getMessage(type.getCode(), args, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ: 引数1");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getGenMessage(type, args);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "引数付き一般メッセージが一致しません");

    }

    /**
     * getLogMessage メソッドのテスト - 正常系:ログメッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetLogMessage_normalBasicMessage() {

        /* 期待値の定義 */
        final String expected = "[NONE] テストメッセージ";

        /* 準備 */
        final KmgCmnLogMsgTypes type = KmgFundLogMsgTypes.NONE;
        Mockito.when(this.messageSource.getMessage(type.getCode(), null, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getLogMessage(type);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "ログメッセージが一致しません");

    }

    /**
     * getLogMessage メソッドのテスト - 正常系:引数付きログメッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetLogMessage_normalMessageWithArgs() {

        /* 期待値の定義 */
        final String expected = "[NONE] テストメッセージ: 引数1";

        /* 準備 */
        final KmgCmnLogMsgTypes type = KmgFundLogMsgTypes.NONE;
        final Object[]          args = {
            "引数1"
        };
        Mockito.when(this.messageSource.getMessage(type.getCode(), args, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ: 引数1");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getLogMessage(type, args);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "引数付きログメッセージが一致しません");

    }

    /**
     * getMessage メソッドのテスト - 正常系:コード埋め込みなしメッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetMessage_normalWithoutCodeEmbedding() {

        /* 期待値の定義 */
        final String expected = "テストメッセージ";

        /* 準備 */
        final KmgCmnMsgTypes type = KmgFundLogMsgTypes.NONE;
        Mockito.when(this.messageSource.getMessage(type.getCode(), null, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getMessage(type, false);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "コード埋め込みなしメッセージが一致しません");

    }

    /**
     * getMessage メソッドのテスト - 正常系:コード埋め込みありメッセージの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetMessage_normalWithCodeEmbedding() {

        /* 期待値の定義 */
        final String expected = "[NONE] テストメッセージ";

        /* 準備 */
        final KmgCmnMsgTypes type = KmgFundLogMsgTypes.NONE;
        Mockito.when(this.messageSource.getMessage(type.getCode(), null, java.util.Locale.JAPANESE))
            .thenReturn("テストメッセージ");

        /* テスト対象の実行 */
        final String actual = this.testTarget.getMessage(type, true);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "コード埋め込みありメッセージが一致しません");

    }
}
