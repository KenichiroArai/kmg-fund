package kmg.fund.infrastructure.model.val.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import kmg.fund.infrastructure.context.KmgMessageSource;
import kmg.fund.infrastructure.context.SpringApplicationContextHelper;
import kmg.fund.infrastructure.types.msg.KmgFundValMsgTypes;

/**
 * KMG基盤バリデーションデータモデルテスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@ExtendWith(MockitoExtension.class)
public class KmgFundValDataModelImplTest {

    /**
     * モックKMGメッセージソース
     *
     * @since 0.1.0
     */
    private KmgMessageSource mockMessageSource;

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public KmgFundValDataModelImplTest() {

        // 処理なし
    }

    /**
     * テスト前の準備<br>
     *
     * @since 0.1.0
     */
    @BeforeEach
    public void setUp() {

        /* モックの初期化 */
        this.mockMessageSource = Mockito.mock(KmgMessageSource.class);

    }

    /**
     * コンストラクタのテスト - 正常系:正常にインスタンスが作成される場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testConstructor_normal() {

        /* 期待値の定義 */
        final KmgFundValMsgTypes expectedMessageTypes = KmgFundValMsgTypes.NONE;
        final Object[]           expectedMessageArgs  = {
            "テスト"
        };
        final String             expectedMessage      = "テストメッセージ";

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(expectedMessageTypes, expectedMessageArgs))
                .thenReturn(expectedMessage);

            /* テスト対象の実行 */
            final KmgFundValDataModelImpl testTarget
                = new KmgFundValDataModelImpl(expectedMessageTypes, expectedMessageArgs);

            /* 検証の準備 */
            final KmgFundValMsgTypes actualMessageTypes = (KmgFundValMsgTypes) testTarget.getMessageTypes();
            final Object[]           actualMessageArgs  = testTarget.getMessageArgs();
            final String             actualMessage      = testTarget.getMessage();

            /* 検証の実施 */
            Assertions.assertNotNull(testTarget, "インスタンスが作成されていません");
            Assertions.assertEquals(expectedMessageTypes, actualMessageTypes, "メッセージタイプが一致しません");
            Assertions.assertArrayEquals(expectedMessageArgs, actualMessageArgs, "メッセージ引数が一致しません");
            Assertions.assertEquals(expectedMessage, actualMessage, "メッセージが一致しません");

        }

    }

    /**
     * createMessage メソッドのテスト - 正常系:メッセージが正常に作成される場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testCreateMessage_normal() {

        /* 期待値の定義 */
        final KmgFundValMsgTypes expectedMessageTypes = KmgFundValMsgTypes.NONE;
        final Object[]           expectedMessageArgs  = {
            "テスト"
        };
        final String             expectedMessage      = "テストメッセージ";

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(expectedMessageTypes, expectedMessageArgs))
                .thenReturn(expectedMessage);

            /* テスト対象の実行 */
            final KmgFundValDataModelImpl testTarget
                = new KmgFundValDataModelImpl(expectedMessageTypes, expectedMessageArgs);

            /* 検証の準備 */
            final String actualMessage = testTarget.getMessage();

            /* 検証の実施 */
            Assertions.assertNotNull(actualMessage, "メッセージが作成されていません");
            Assertions.assertEquals(expectedMessage, actualMessage, "メッセージが一致しません");

            // モックの検証
            Mockito.verify(this.mockMessageSource).getExcMessage(expectedMessageTypes, expectedMessageArgs);

        }

    }
}
