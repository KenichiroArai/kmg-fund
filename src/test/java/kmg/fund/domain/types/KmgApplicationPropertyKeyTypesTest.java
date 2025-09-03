package kmg.fund.domain.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * KMG アプリケーションプロパティキーの種類のテスト<br>
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
public class KmgApplicationPropertyKeyTypesTest {

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public KmgApplicationPropertyKeyTypesTest() {

        // 処理なし
    }

    /**
     * get メソッドのテスト - 正常系:基本的な値の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGet_normalBasicValue() {

        /* 期待値の定義 */
        final String expected = "spring.messages.basename";

        /* 準備 */
        final KmgApplicationPropertyKeyTypes testType = KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME;

        /* テスト対象の実行 */
        final String actual = testType.get();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "取得値が一致しません");

    }

    /**
     * getDefault メソッドのテスト - 正常系:デフォルト値の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetDefault_normalDefaultValue() {

        /* 期待値の定義 */
        final KmgApplicationPropertyKeyTypes expected = KmgApplicationPropertyKeyTypes.NONE;

        /* テスト対象の実行 */
        final KmgApplicationPropertyKeyTypes actual = KmgApplicationPropertyKeyTypes.getDefault();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "デフォルト値が一致しません");

    }

    /**
     * getDetail メソッドのテスト - 正常系:詳細情報の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetDetail_normalBasicDetail() {

        /* 期待値の定義 */
        final String expected = "メッセージのベース名を指定するプロパティキー";

        /* 準備 */
        final KmgApplicationPropertyKeyTypes testType = KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME;

        /* テスト対象の実行 */
        final String actual = testType.getDetail();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "詳細情報が一致しません");

    }

    /**
     * getDisplayName メソッドのテスト - 正常系:表示名の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetDisplayName_normalBasicDisplayName() {

        /* 期待値の定義 */
        final String expected = "メッセージのベース名";

        /* 準備 */
        final KmgApplicationPropertyKeyTypes testType = KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME;

        /* テスト対象の実行 */
        final String actual = testType.getDisplayName();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "表示名が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 正常系:存在する値の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetEnum_normalExistingValue() {

        /* 期待値の定義 */
        final KmgApplicationPropertyKeyTypes expected = KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME;

        /* 準備 */
        final String testValue = "spring.messages.basename";

        /* テスト対象の実行 */
        final KmgApplicationPropertyKeyTypes actual = KmgApplicationPropertyKeyTypes.getEnum(testValue);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "値が一致しません");

    }

    /**
     * getEnum メソッドのテスト - 準正常系:存在しない値の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetEnum_semiNonExistingValue() {

        /* 期待値の定義 */
        final KmgApplicationPropertyKeyTypes expected = KmgApplicationPropertyKeyTypes.NONE;

        /* 準備 */
        final String testValue = "INVALID";

        /* テスト対象の実行 */
        final KmgApplicationPropertyKeyTypes actual = KmgApplicationPropertyKeyTypes.getEnum(testValue);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "値が一致しません");

    }

    /**
     * getInitValue メソッドのテスト - 正常系:初期値の取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetInitValue_normalInitialValue() {

        /* 期待値の定義 */
        final KmgApplicationPropertyKeyTypes expected = KmgApplicationPropertyKeyTypes.NONE;

        /* テスト対象の実行 */
        final KmgApplicationPropertyKeyTypes actual = KmgApplicationPropertyKeyTypes.getInitValue();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "初期値が一致しません");

    }

    /**
     * getKey メソッドのテスト - 正常系:キーの取得
     *
     * @since 0.1.0
     */
    @Test
    public void testGetKey_normalBasicKey() {

        /* 期待値の定義 */
        final String expected = "spring.messages.basename";

        /* 準備 */
        final KmgApplicationPropertyKeyTypes testType = KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME;

        /* テスト対象の実行 */
        final String actual = testType.getKey();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "キーが一致しません");

    }

    /**
     * toString メソッドのテスト - 正常系:NONEの文字列表現
     *
     * @since 0.1.0
     */
    @Test
    public void testToString_normalNone() {

        /* 期待値の定義 */
        final String expected = "None";

        /* 準備 */
        final KmgApplicationPropertyKeyTypes testType = KmgApplicationPropertyKeyTypes.NONE;

        /* テスト対象の実行 */
        final String actual = testType.toString();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "NONEの場合、'None'が返されること");

    }

    /**
     * toString メソッドのテスト - 正常系:SPRING_MESSAGES_BASENAMEの文字列表現
     *
     * @since 0.1.0
     */
    @Test
    public void testToString_normalSpringMessagesBasename() {

        /* 期待値の定義 */
        final String expected = "spring.messages.basename";

        /* 準備 */
        final KmgApplicationPropertyKeyTypes testType = KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME;

        /* テスト対象の実行 */
        final String actual = testType.toString();

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "SPRING_MESSAGES_BASENAMEの場合、'spring.messages.basename'が返されること");

    }
}
