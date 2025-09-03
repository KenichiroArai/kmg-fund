package kmg.fund.infrastructure.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import kmg.fund.infrastructure.exception.KmgFundMsgException;
import kmg.fund.infrastructure.types.msg.KmgFundGenMsgTypes;

/**
 * KMG YAMLユーティリティのテスト<br>
 *
 * @author KenichiroArai
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 */
@SpringBootTest
@ActiveProfiles("test")
@SuppressWarnings({
    "nls", "static-method",
})
public class KmgYamlUtilsTest {

    /**
     * 一時ディレクトリ
     *
     * @since 1.0.0
     */
    @TempDir
    private Path tempDir;

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 1.0.0
     */
    public KmgYamlUtilsTest() {

        // 処理無し
    }

    /**
     * load メソッドのテスト - 正常系:複合構造のYAMLファイルの読み込み
     *
     * @since 1.0.0
     *
     * @throws KmgFundMsgException
     *                             KMG基盤メッセージ例外
     * @throws IOException
     *                             入出力例外
     */
    @Test
    public void testLoad_normalComplexYamlFile() throws KmgFundMsgException, IOException {

        /* 期待値の定義 */
        final String expectedName    = "test application";
        final String expectedVersion = "1.0.0";

        /* 準備 */
        final String yamlContent  = "app:\n  name: test application\n  version: 1.0.0\nlist:\n  - item1\n  - item2\n";
        final Path   testYamlFile = this.tempDir.resolve("complex.yaml");
        Files.write(testYamlFile, yamlContent.getBytes());

        /* テスト対象の実行 */
        final Map<String, Object> testResult = KmgYamlUtils.load(testYamlFile);

        /* 検証の準備 */
        final Map<String, Object> actualResult = testResult;
        @SuppressWarnings("unchecked")
        final Map<String, Object> actualApp    = (Map<String, Object>) actualResult.get("app");

        /* 検証の実施 */
        Assertions.assertNotNull(actualResult, "YAMLファイル読み込み結果がnullでないこと");
        Assertions.assertTrue(actualResult.containsKey("app"), "appキーが存在すること");
        Assertions.assertEquals(expectedName, actualApp.get("name"), "期待するアプリケーション名が取得できること");
        Assertions.assertEquals(expectedVersion, actualApp.get("version"), "期待するバージョンが取得できること");

    }

    /**
     * load メソッドのテスト - 正常系:空のYAMLファイルの読み込み
     *
     * @since 1.0.0
     *
     * @throws KmgFundMsgException
     *                             KMG基盤メッセージ例外
     * @throws IOException
     *                             入出力例外
     */
    @Test
    public void testLoad_normalEmptyYamlFile() throws KmgFundMsgException, IOException {

        /* 期待値の定義 */
        final Map<String, Object> expectedResult = null;

        /* 準備 */
        final String yamlContent  = "";
        final Path   testYamlFile = this.tempDir.resolve("empty.yaml");
        Files.write(testYamlFile, yamlContent.getBytes());

        /* テスト対象の実行 */
        final Map<String, Object> testResult = KmgYamlUtils.load(testYamlFile);

        /* 検証の準備 */
        final Map<String, Object> actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "空のYAMLファイル読み込み結果がnullであること");

    }

    /**
     * load メソッドのテスト - 正常系:有効なYAMLファイルの読み込み
     *
     * @since 1.0.0
     *
     * @throws KmgFundMsgException
     *                             KMG基盤メッセージ例外
     * @throws IOException
     *                             入出力例外
     */
    @Test
    public void testLoad_normalValidYamlFile() throws KmgFundMsgException, IOException {

        /* 期待値の定義 */
        final String expectedKey   = "test";
        final String expectedValue = "value";

        /* 準備 */
        final String yamlContent  = "test: value\nversion: 1.0.0\n";
        final Path   testYamlFile = this.tempDir.resolve("test.yaml");
        Files.write(testYamlFile, yamlContent.getBytes());

        /* テスト対象の実行 */
        final Map<String, Object> testResult = KmgYamlUtils.load(testYamlFile);

        /* 検証の準備 */
        final Map<String, Object> actualResult = testResult;

        /* 検証の実施 */
        Assertions.assertNotNull(actualResult, "YAMLファイル読み込み結果がnullでないこと");
        Assertions.assertTrue(actualResult.containsKey(expectedKey), "期待するキーが存在すること");
        Assertions.assertEquals(expectedValue, actualResult.get(expectedKey), "期待する値が取得できること");

    }

    /**
     * load メソッドのテスト - 準正常系:ディレクトリをファイルとして読み込む場合
     *
     * @since 1.0.0
     *
     * @throws IOException
     *                     入出力例外
     */
    @Test
    public void testLoad_semiDirectoryAsFile() throws IOException {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedMessageType = KmgFundGenMsgTypes.KMGFUND_GEN24001;

        /* 準備 */
        final Path testDirectory = this.tempDir.resolve("testDirectory");
        Files.createDirectory(testDirectory);
        final String expectedDirectoryPath = testDirectory.toString();

        /* テスト対象の実行 */
        final KmgFundMsgException actualException
            = Assertions.assertThrows(KmgFundMsgException.class, () -> KmgYamlUtils.load(testDirectory));

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualMessageType = (KmgFundGenMsgTypes) actualException.getMessageTypes();
        final Object[]           actualMessageArgs = actualException.getMessageArgs();
        final Throwable          actualCause       = actualException.getCause();

        /* 検証の実施 */
        Assertions.assertEquals(expectedMessageType, actualMessageType, "期待するメッセージタイプが設定されていること");
        Assertions.assertEquals(expectedDirectoryPath, actualMessageArgs[0], "期待するディレクトリパスがメッセージ引数に設定されていること");
        Assertions.assertInstanceOf(IOException.class, actualCause, "原因がIOExceptionであること");

    }

    /**
     * load メソッドのテスト - 準正常系:不正なYAML形式のファイル
     *
     * @since 1.0.0
     *
     * @throws IOException
     *                     入出力例外
     */
    @Test
    public void testLoad_semiInvalidYamlFormat() throws IOException {

        /* 期待値の定義 */
        // 不正なYAML形式の場合、ScannerExceptionが発生することを確認

        /* 準備 */
        final String invalidYamlContent  = "key1: value1\nkey2: value2: invalid";
        final Path   testInvalidYamlFile = this.tempDir.resolve("invalid.yaml");
        Files.write(testInvalidYamlFile, invalidYamlContent.getBytes());

        /* テスト対象の実行 */
        // 不正なYAML形式の場合、ScannerExceptionが発生することを確認
        Assertions.assertThrows(Exception.class, () -> {

            KmgYamlUtils.load(testInvalidYamlFile);

        });

    }

    /**
     * load メソッドのテスト - 準正常系:存在しないファイルパスの場合
     *
     * @since 1.0.0
     */
    @Test
    public void testLoad_semiNonExistentFile() {

        /* 期待値の定義 */
        final KmgFundGenMsgTypes expectedMessageType = KmgFundGenMsgTypes.KMGFUND_GEN24000;

        /* 準備 */
        final Path   testNonExistentFile = Paths.get("/non/existent/file.yaml");
        final String expectedFilePath    = testNonExistentFile.toString();

        /* テスト対象の実行 */
        final KmgFundMsgException actualException
            = Assertions.assertThrows(KmgFundMsgException.class, () -> KmgYamlUtils.load(testNonExistentFile));

        /* 検証の準備 */
        final KmgFundGenMsgTypes actualMessageType = (KmgFundGenMsgTypes) actualException.getMessageTypes();
        final Object[]           actualMessageArgs = actualException.getMessageArgs();
        final Throwable          actualCause       = actualException.getCause();

        /* 検証の実施 */
        Assertions.assertEquals(expectedMessageType, actualMessageType, "期待するメッセージタイプが設定されていること");
        Assertions.assertEquals(expectedFilePath, actualMessageArgs[0], "期待するファイルパスがメッセージ引数に設定されていること");
        Assertions.assertInstanceOf(NoSuchFileException.class, actualCause, "原因がNoSuchFileExceptionであること");

    }
}
