package kmg.fund.infrastructure.types.msg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * KmgFundGenMsgTypes のテストクラス<br>
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
public class KmgFundGenMsgTypesTest {

    /**
     * get メソッドのテスト - 正常系:KMGFUND_GEN24000のキー取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGet_normalKmgFund24000Key() {

        /* 期待値の定義 */
        final String expectedResult = "KMGFUND_GEN24000";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* テスト対象の実行 */
        final String testResult = testTarget.get();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "KMGFUND_GEN24000のキーが一致しません");

    }

    /**
     * get メソッドのテスト - 正常系:NONEのキー取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGet_normalNoneKey() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.get();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEのキーが一致しません");

    }

    /**
     * getCode メソッドのテスト - 正常系:NONEのコード取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetCode_normalNoneCode() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getCode();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEのコードが一致しません");

    }

    /**
     * getDefault メソッドのテスト - 正常系:デフォルト値の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetDefault_normalDefault() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedResult = KmgFundGenMsgTypes.NONE;

        /* 準備 */

        /* テスト対象の実行 */
        final KmgFundGenMsgTypes testResult = KmgFundGenMsgTypes.getDefault();

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "デフォルト値が一致しません");

    }

    /**
     * getDetail メソッドのテスト - 正常系:NONEの詳細情報取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetDetail_normalNoneDetail() {

        /* 期待値の定義 */
        final String expectedResult = "指定無し";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getDetail();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの詳細情報が一致しません");

    }

    /**
     * getDisplayName メソッドのテスト - 正常系:NONEの表示名取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetDisplayName_normalNoneDisplayName() {

        /* 期待値の定義 */
        final String expectedResult = "指定無し";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getDisplayName();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの表示名が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 正常系:存在するキーの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testGetEnum_normalExistingKey() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedResult = KmgFundGenMsgTypes.NONE;

        /* 準備 */
        final String testKey = "NONE";

        /* テスト対象の実行 */
        final KmgFundGenMsgTypes testResult = KmgFundGenMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "存在するキーに対応する値が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 正常系:KMGFUND_GEN24000のキーの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testGetEnum_normalKmgFund24000Key() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedResult = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        final String testKey = "KMGFUND_GEN24000";

        /* テスト対象の実行 */
        final KmgFundGenMsgTypes testResult = KmgFundGenMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "KMGFUND_GEN24000キーに対応する値が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 正常系:KMGFUND_GEN24001のキーの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testGetEnum_normalKmgFund24001Key() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedResult = KmgFundGenMsgTypes.KMGFUND_GEN24001;

        /* 準備 */
        final String testKey = "KMGFUND_GEN24001";

        /* テスト対象の実行 */
        final KmgFundGenMsgTypes testResult = KmgFundGenMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "KMGFUND_GEN24001キーに対応する値が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 準正常系:存在しないキーの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testGetEnum_semiNonExistingKey() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedResult = KmgFundGenMsgTypes.NONE;

        /* 準備 */
        final String testKey = "NON_EXISTING_KEY";

        /* テスト対象の実行 */
        final KmgFundGenMsgTypes testResult = KmgFundGenMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "存在しないキーの場合はNONEが返されません");

    }

    /**
     * getEnum メソッドのテスト - 準正常系:nullキーの場合
     *
     * @since 0.1.0
     */
    @Test
    public void testGetEnum_semiNullKey() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedResult = KmgFundGenMsgTypes.NONE;

        /* 準備 */
        final String testKey = null;

        /* テスト対象の実行 */
        final KmgFundGenMsgTypes testResult = KmgFundGenMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "nullキーの場合はNONEが返されません");

    }

    /**
     * getInitValue メソッドのテスト - 正常系:初期値の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetInitValue_normalInitValue() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedResult = KmgFundGenMsgTypes.NONE;

        /* 準備 */

        /* テスト対象の実行 */
        final KmgFundGenMsgTypes testResult = KmgFundGenMsgTypes.getInitValue();

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "初期値が一致しません");

    }

    /**
     * getKey メソッドのテスト - 正常系:NONEのキー取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetKey_normalNoneKey() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getKey();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEのキーが一致しません");

    }

    /**
     * getValue メソッドのテスト - 正常系:KMGFUND_GEN24000の値取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetValue_normalKmgFund24000Value() {

        /* 期待値の定義 */
        final String expectedResult = "該当するYAMLファイルがありません。ファイルパス=[{0}]";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* テスト対象の実行 */
        final String testResult = testTarget.getValue();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "KMGFUND_GEN24000の値が一致しません");

    }

    /**
     * getValue メソッドのテスト - 正常系:KMGFUND_GEN24001の値取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetValue_normalKmgFund24001Value() {

        /* 期待値の定義 */
        final String expectedResult = "YAMLにロードするファイルの読み込みに失敗しました。ファイルパス=[{0}]";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.KMGFUND_GEN24001;

        /* テスト対象の実行 */
        final String testResult = testTarget.getValue();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "KMGFUND_GEN24001の値が一致しません");

    }

    /**
     * getValue メソッドのテスト - 正常系:NONEの値取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetValue_normalNoneValue() {

        /* 期待値の定義 */
        final String expectedResult = "指定無し";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getValue();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの値が一致しません");

    }

    /**
     * toString メソッドのテスト - 正常系:NONEの文字列変換
     *
     * @since 0.1.0
     */
    @Test
    public void testToString_normalNoneToString() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundGenMsgTypes testTarget = KmgFundGenMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.toString();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの文字列変換が一致しません");

    }

}
