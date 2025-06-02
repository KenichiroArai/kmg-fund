package kmg.fund.infrastructure.model.val.impl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import kmg.core.infrastructure.model.val.KmgValDataModel;
import kmg.core.infrastructure.model.val.KmgValsModel;
import kmg.fund.infrastructure.context.KmgMessageSource;
import kmg.fund.infrastructure.context.SpringApplicationContextHelper;
import kmg.fund.infrastructure.types.msg.KmgFundValMsgTypes;

/**
 * KMG基盤バリデーション集合モデルテスト<br>
 * <p>
 * Fundは、Foundationの略。<br>
 * Valは、Validationの略。
 * </p>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings({
    "nls", "static-method"
})
public class KmgFundValsModelImplTest {

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
    public KmgFundValsModelImplTest() {

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
        final boolean expectedIsEmpty    = true;
        final boolean expectedIsNotEmpty = false;
        final int     expectedSize       = 0;

        /* 準備 */
        // 処理なし

        /* テスト対象の実行 */
        final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();

        /* 検証の準備 */
        final boolean               actualIsEmpty    = testTarget.isEmpty();
        final boolean               actualIsNotEmpty = testTarget.isNotEmpty();
        final List<KmgValDataModel> actualDatas      = testTarget.getDatas();

        /* 検証の実施 */
        Assertions.assertNotNull(testTarget, "インスタンスが作成されていません");
        Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");
        Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");
        Assertions.assertNotNull(actualDatas, "データリストがnullです");
        Assertions.assertEquals(expectedSize, actualDatas.size(), "データサイズが一致しません");

    }

    /**
     * addData メソッドのテスト - 正常系:データが正常に追加される場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testAddData_normalSingleData() {

        /* 期待値の定義 */
        final boolean expectedIsEmpty    = false;
        final boolean expectedIsNotEmpty = true;
        final int     expectedSize       = 1;

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ"
            })).thenReturn("テストメッセージ");

            final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
            final KmgValDataModel      testData   = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ"
            });

            /* テスト対象の実行 */
            testTarget.addData(testData);

            /* 検証の準備 */
            final boolean               actualIsEmpty    = testTarget.isEmpty();
            final boolean               actualIsNotEmpty = testTarget.isNotEmpty();
            final List<KmgValDataModel> actualDatas      = testTarget.getDatas();

            /* 検証の実施 */
            Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedSize, actualDatas.size(), "データサイズが一致しません");
            Assertions.assertEquals(testData, actualDatas.get(0), "追加されたデータが一致しません");

        }

    }

    /**
     * addData メソッドのテスト - 正常系:複数のデータが追加される場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testAddData_normalMultipleData() {

        /* 期待値の定義 */
        final boolean expectedIsEmpty    = false;
        final boolean expectedIsNotEmpty = true;
        final int     expectedSize       = 3;

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(any(), any())).thenReturn("テストメッセージ");

            final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
            final KmgValDataModel      testData1  = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ1"
            });
            final KmgValDataModel      testData2  = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ2"
            });
            final KmgValDataModel      testData3  = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ3"
            });

            /* テスト対象の実行 */
            testTarget.addData(testData1);
            testTarget.addData(testData2);
            testTarget.addData(testData3);

            /* 検証の準備 */
            final boolean               actualIsEmpty    = testTarget.isEmpty();
            final boolean               actualIsNotEmpty = testTarget.isNotEmpty();
            final List<KmgValDataModel> actualDatas      = testTarget.getDatas();

            /* 検証の実施 */
            Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedSize, actualDatas.size(), "データサイズが一致しません");
            Assertions.assertEquals(testData1, actualDatas.get(0), "1番目のデータが一致しません");
            Assertions.assertEquals(testData2, actualDatas.get(1), "2番目のデータが一致しません");
            Assertions.assertEquals(testData3, actualDatas.get(2), "3番目のデータが一致しません");

        }

    }

    /**
     * addData メソッドのテスト - 準正常系:nullデータが追加される場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testAddData_semiNullData() {

        /* 期待値の定義 */
        final boolean expectedIsEmpty    = true;
        final boolean expectedIsNotEmpty = false;
        final int     expectedSize       = 0;

        /* 準備 */
        final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
        final KmgValDataModel      testData   = null;

        /* テスト対象の実行 */
        testTarget.addData(testData);

        /* 検証の準備 */
        final boolean               actualIsEmpty    = testTarget.isEmpty();
        final boolean               actualIsNotEmpty = testTarget.isNotEmpty();
        final List<KmgValDataModel> actualDatas      = testTarget.getDatas();

        /* 検証の実施 */
        Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");
        Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");
        Assertions.assertEquals(expectedSize, actualDatas.size(), "データサイズが一致しません");

    }

    /**
     * getDatas メソッドのテスト - 正常系:データリストが正常に取得される場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testGetDatas_normal() {

        /* 期待値の定義 */
        final int expectedSize = 2;

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(any(), any())).thenReturn("テストメッセージ");

            final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
            final KmgValDataModel      testData1  = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ1"
            });
            final KmgValDataModel      testData2  = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ2"
            });
            testTarget.addData(testData1);
            testTarget.addData(testData2);

            /* テスト対象の実行 */
            final List<KmgValDataModel> testResult = testTarget.getDatas();

            /* 検証の準備 */
            final List<KmgValDataModel> actualDatas = testResult;

            /* 検証の実施 */
            Assertions.assertNotNull(actualDatas, "データリストがnullです");
            Assertions.assertEquals(expectedSize, actualDatas.size(), "データサイズが一致しません");
            Assertions.assertEquals(testData1, actualDatas.get(0), "1番目のデータが一致しません");
            Assertions.assertEquals(testData2, actualDatas.get(1), "2番目のデータが一致しません");

        }

    }

    /**
     * isEmpty メソッドのテスト - 正常系:空の場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testIsEmpty_normalEmpty() {

        /* 期待値の定義 */
        final boolean expectedIsEmpty = true;

        /* 準備 */
        final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();

        /* テスト対象の実行 */
        final boolean testResult = testTarget.isEmpty();

        /* 検証の準備 */
        final boolean actualIsEmpty = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");

    }

    /**
     * isEmpty メソッドのテスト - 正常系:空ではない場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testIsEmpty_normalNotEmpty() {

        /* 期待値の定義 */
        final boolean expectedIsEmpty = false;

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(any(), any())).thenReturn("テストメッセージ");

            final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
            final KmgValDataModel      testData   = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ"
            });
            testTarget.addData(testData);

            /* テスト対象の実行 */
            final boolean testResult = testTarget.isEmpty();

            /* 検証の準備 */
            final boolean actualIsEmpty = testResult;

            /* 検証の実施 */
            Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");

        }

    }

    /**
     * isNotEmpty メソッドのテスト - 正常系:空ではない場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testIsNotEmpty_normalNotEmpty() {

        /* 期待値の定義 */
        final boolean expectedIsNotEmpty = true;

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(any(), any())).thenReturn("テストメッセージ");

            final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
            final KmgValDataModel      testData   = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ"
            });
            testTarget.addData(testData);

            /* テスト対象の実行 */
            final boolean testResult = testTarget.isNotEmpty();

            /* 検証の準備 */
            final boolean actualIsNotEmpty = testResult;

            /* 検証の実施 */
            Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");

        }

    }

    /**
     * isNotEmpty メソッドのテスト - 正常系:空の場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testIsNotEmpty_normalEmpty() {

        /* 期待値の定義 */
        final boolean expectedIsNotEmpty = false;

        /* 準備 */
        final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();

        /* テスト対象の実行 */
        final boolean testResult = testTarget.isNotEmpty();

        /* 検証の準備 */
        final boolean actualIsNotEmpty = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");

    }

    /**
     * merge メソッドのテスト - 正常系:別のValsModelがマージされる場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testMerge_normal() {

        /* 期待値の定義 */
        final boolean expectedIsEmpty    = false;
        final boolean expectedIsNotEmpty = true;
        final int     expectedSize       = 3;

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(any(), any())).thenReturn("テストメッセージ");

            final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
            final KmgValDataModel      testData1  = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ1"
            });
            testTarget.addData(testData1);

            final KmgValsModel    testMergeModel = new KmgFundValsModelImpl();
            final KmgValDataModel testData2      = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ2"
            });
            final KmgValDataModel testData3      = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ3"
            });
            testMergeModel.addData(testData2);
            testMergeModel.addData(testData3);

            /* テスト対象の実行 */
            testTarget.merge(testMergeModel);

            /* 検証の準備 */
            final boolean               actualIsEmpty    = testTarget.isEmpty();
            final boolean               actualIsNotEmpty = testTarget.isNotEmpty();
            final List<KmgValDataModel> actualDatas      = testTarget.getDatas();

            /* 検証の実施 */
            Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedSize, actualDatas.size(), "データサイズが一致しません");
            Assertions.assertEquals(testData1, actualDatas.get(0), "1番目のデータが一致しません");
            Assertions.assertEquals(testData2, actualDatas.get(1), "2番目のデータが一致しません");
            Assertions.assertEquals(testData3, actualDatas.get(2), "3番目のデータが一致しません");

        }

    }

    /**
     * merge メソッドのテスト - 正常系:空のValsModelがマージされる場合<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testMerge_normalEmptyModel() {

        /* 期待値の定義 */
        final boolean expectedIsEmpty    = false;
        final boolean expectedIsNotEmpty = true;
        final int     expectedSize       = 1;

        /* 準備 */
        // SpringApplicationContextHelperのモック化
        try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
            = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

            mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                .thenReturn(this.mockMessageSource);

            // モックメッセージソースの設定
            Mockito.when(this.mockMessageSource.getExcMessage(any(), any())).thenReturn("テストメッセージ");

            final KmgFundValsModelImpl testTarget = new KmgFundValsModelImpl();
            final KmgValDataModel      testData1  = new KmgFundValDataModelImpl(KmgFundValMsgTypes.NONE, new Object[] {
                "テストデータ1"
            });
            testTarget.addData(testData1);

            final KmgValsModel testMergeModel = new KmgFundValsModelImpl();

            /* テスト対象の実行 */
            testTarget.merge(testMergeModel);

            /* 検証の準備 */
            final boolean               actualIsEmpty    = testTarget.isEmpty();
            final boolean               actualIsNotEmpty = testTarget.isNotEmpty();
            final List<KmgValDataModel> actualDatas      = testTarget.getDatas();

            /* 検証の実施 */
            Assertions.assertEquals(expectedIsEmpty, actualIsEmpty, "isEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedIsNotEmpty, actualIsNotEmpty, "isNotEmpty()の結果が一致しません");
            Assertions.assertEquals(expectedSize, actualDatas.size(), "データサイズが一致しません");
            Assertions.assertEquals(testData1, actualDatas.get(0), "1番目のデータが一致しません");

        }

    }

}
