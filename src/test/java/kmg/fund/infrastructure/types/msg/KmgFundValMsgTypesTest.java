package kmg.fund.infrastructure.types.msg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * KmgFundValMsgTypes のテストクラス<br>
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
public class KmgFundValMsgTypesTest {

    /**
     * get メソッドのテスト - 正常系:NONEのキー取得
     */
    @Test
    public void testGet_normalNoneKey() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundValMsgTypes testTarget = KmgFundValMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.get();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEのキーが一致しません");

    }

    /**
     * getCode メソッドのテスト - 正常系:NONEのコード取得
     */
    @Test
    public void testGetCode_normalNoneCode() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundValMsgTypes testTarget = KmgFundValMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getCode();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEのコードが一致しません");

    }

    /**
     * getDefault メソッドのテスト - 正常系:デフォルト値の取得
     */
    @Test
    public void testGetDefault_normalDefault() {

        /* 期待値の定義 */
        final KmgFundValMsgTypes expectedResult = KmgFundValMsgTypes.NONE;

        /* 準備 */

        /* テスト対象の実行 */
        final KmgFundValMsgTypes testResult = KmgFundValMsgTypes.getDefault();

        /* 検証の準備 */
        final KmgFundValMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "デフォルト値が一致しません");

    }

    /**
     * getDetail メソッドのテスト - 正常系:NONEの詳細情報取得
     */
    @Test
    public void testGetDetail_normalNoneDetail() {

        /* 期待値の定義 */
        final String expectedResult = "指定無し";

        /* 準備 */
        final KmgFundValMsgTypes testTarget = KmgFundValMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getDetail();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの詳細情報が一致しません");

    }

    /**
     * getDisplayName メソッドのテスト - 正常系:NONEの表示名取得
     */
    @Test
    public void testGetDisplayName_normalNoneDisplayName() {

        /* 期待値の定義 */
        final String expectedResult = "指定無し";

        /* 準備 */
        final KmgFundValMsgTypes testTarget = KmgFundValMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getDisplayName();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの表示名が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 正常系:存在するキーの場合
     */
    @Test
    public void testGetEnum_normalExistingKey() {

        /* 期待値の定義 */
        final KmgFundValMsgTypes expectedResult = KmgFundValMsgTypes.NONE;

        /* 準備 */
        final String testKey = "NONE";

        /* テスト対象の実行 */
        final KmgFundValMsgTypes testResult = KmgFundValMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundValMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "存在するキーに対応する値が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 準正常系:存在しないキーの場合
     */
    @Test
    public void testGetEnum_semiNonExistingKey() {

        /* 期待値の定義 */
        final KmgFundValMsgTypes expectedResult = KmgFundValMsgTypes.NONE;

        /* 準備 */
        final String testKey = "NON_EXISTING_KEY";

        /* テスト対象の実行 */
        final KmgFundValMsgTypes testResult = KmgFundValMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundValMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "存在しないキーの場合はNONEが返されません");

    }

    /**
     * getEnum メソッドのテスト - 準正常系:nullキーの場合
     */
    @Test
    public void testGetEnum_semiNullKey() {

        /* 期待値の定義 */
        final KmgFundValMsgTypes expectedResult = KmgFundValMsgTypes.NONE;

        /* 準備 */
        final String testKey = null;

        /* テスト対象の実行 */
        final KmgFundValMsgTypes testResult = KmgFundValMsgTypes.getEnum(testKey);

        /* 検証の準備 */
        final KmgFundValMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "nullキーの場合はNONEが返されません");

    }

    /**
     * getInitValue メソッドのテスト - 正常系:初期値の取得
     */
    @Test
    public void testGetInitValue_normalInitValue() {

        /* 期待値の定義 */
        final KmgFundValMsgTypes expectedResult = KmgFundValMsgTypes.NONE;

        /* 準備 */

        /* テスト対象の実行 */
        final KmgFundValMsgTypes testResult = KmgFundValMsgTypes.getInitValue();

        /* 検証の準備 */
        final KmgFundValMsgTypes actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "初期値が一致しません");

    }

    /**
     * getKey メソッドのテスト - 正常系:NONEのキー取得
     */
    @Test
    public void testGetKey_normalNoneKey() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundValMsgTypes testTarget = KmgFundValMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getKey();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEのキーが一致しません");

    }

    /**
     * getValue メソッドのテスト - 正常系:NONEの値取得
     */
    @Test
    public void testGetValue_normalNoneValue() {

        /* 期待値の定義 */
        final String expectedResult = "指定無し";

        /* 準備 */
        final KmgFundValMsgTypes testTarget = KmgFundValMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.getValue();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの値が一致しません");

    }

    /**
     * toString メソッドのテスト - 正常系:NONEの文字列変換
     */
    @Test
    public void testToString_normalNoneToString() {

        /* 期待値の定義 */
        final String expectedResult = "NONE";

        /* 準備 */
        final KmgFundValMsgTypes testTarget = KmgFundValMsgTypes.NONE;

        /* テスト対象の実行 */
        final String testResult = testTarget.toString();

        /* 検証の準備 */
        final String actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "NONEの文字列変換が一致しません");

    }

}
