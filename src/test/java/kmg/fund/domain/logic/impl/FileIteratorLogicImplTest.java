package kmg.fund.domain.logic.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import kmg.core.infrastructure.cmn.msg.KmgCmnExcMsgTypes;
import kmg.core.infrastructure.test.AbstractKmgTest;
import kmg.core.infrastructure.type.KmgString;
import kmg.core.infrastructure.utils.KmgMessageUtils;
import kmg.fund.infrastructure.context.KmgMessageSource;
import kmg.fund.infrastructure.context.SpringApplicationContextHelper;
import kmg.fund.infrastructure.exception.KmgFundMsgException;

/**
 * ファイルイテレータロジック実装のテスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.2.3
 *
 * @version 0.2.3
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SuppressWarnings({
    "nls", "static-method"
})
public class FileIteratorLogicImplTest extends AbstractKmgTest {

    /**
     * テスト対象
     *
     * @since 0.2.3
     */
    private FileIteratorLogicImpl testTarget;

    /**
     * テスト用一時ディレクトリ
     *
     * @since 0.2.3
     */
    private Path testTempDir;

    /**
     * テスト用Javaファイル
     *
     * @since 0.2.3
     */
    private Path testJavaFile;

    /**
     * テスト用空のディレクトリ
     *
     * @since 0.2.3
     */
    private Path testEmptyDir;

    /**
     * テスト前処理<br>
     *
     * @since 0.2.3
     *
     * @throws IOException
     *                     入出力例外
     */
    @BeforeEach
    public void setUp() throws IOException {

        this.testTarget = new FileIteratorLogicImpl();

        /* テスト用一時ディレクトリの作成 */
        this.testTempDir = Files.createTempDirectory("file_iterator_test");
        this.testEmptyDir = Files.createTempDirectory("file_iterator_empty");

        /* テスト用Javaファイルの作成 */
        this.testJavaFile = this.testTempDir.resolve("TestFile.java");
        Files.writeString(this.testJavaFile, "public class TestFile {\n}\n");

        /* テスト用の非Javaファイル */
        final Path testTxtFile = this.testTempDir.resolve("TestFile.txt");
        Files.writeString(testTxtFile, "This is a text file.");

    }

    /**
     * テスト後処理<br>
     *
     * @since 0.2.3
     *
     * @throws IOException
     *                     入出力例外
     */
    @AfterEach
    public void tearDown() throws IOException {

        /* テストファイルとディレクトリの削除を再帰的に実行 */
        this.deleteDirectoryRecursively(this.testTempDir);
        this.deleteDirectoryRecursively(this.testEmptyDir);

    }

    /**
     * getCurrentFilePath メソッドのテスト - 正常系:初期化後
     *
     * @since 0.2.3
     */
    @Test
    public void testGetCurrentFilePath_normalAfterInitialize() {

        /* 期待値の定義 */
        final Path expectedCurrentFilePath = null;

        /* テスト対象の実行 */
        final Path actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedCurrentFilePath, actualCurrentFilePath, "初期化後のカレントファイルパスがnullであること");

    }

    /**
     * getCurrentFilePath メソッドのテスト - 正常系:ロード後
     *
     * @since 0.2.3
     */
    @Test
    public void testGetCurrentFilePath_normalAfterLoad() {

        /* 期待値の定義 */
        final Path expectedCurrentFilePath = this.testJavaFile;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        final Path actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedCurrentFilePath, actualCurrentFilePath, "ロード後のカレントファイルパスが設定されていること");

    }

    /**
     * getFilePathList メソッドのテスト - 正常系:初期化後
     *
     * @since 0.2.3
     */
    @Test
    public void testGetFilePathList_normalAfterInitialize() {

        /* 期待値の定義 */
        final int expectedSize = 0;

        /* テスト対象の実行 */
        final List<Path> actualFilePathList = this.testTarget.getFilePathList();

        /* 検証の実施 */
        Assertions.assertEquals(expectedSize, actualFilePathList.size(), "初期化後のファイルパスリストが空であること");

    }

    /**
     * getFilePathList メソッドのテスト - 正常系:ロード後
     *
     * @since 0.2.3
     */
    @Test
    public void testGetFilePathList_normalAfterLoad() {

        /* 期待値の定義 */
        final int expectedSize = 1;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        final List<Path> actualFilePathList = this.testTarget.getFilePathList();

        /* 検証の実施 */
        Assertions.assertEquals(expectedSize, actualFilePathList.size(), "ロード後のファイルパスリストにJavaファイルが含まれること");
        Assertions.assertTrue(actualFilePathList.contains(this.testJavaFile), "Javaファイルがリストに含まれること");

    }

    /**
     * getReadContent メソッドのテスト - 正常系:初期化後
     *
     * @since 0.2.3
     */
    @Test
    public void testGetReadContent_normalAfterInitialize() {

        /* 期待値の定義 */
        final String expectedReadContent = KmgString.EMPTY;

        /* テスト対象の実行 */
        final String actualReadContent = this.testTarget.getReadContent();

        /* 検証の実施 */
        Assertions.assertEquals(expectedReadContent, actualReadContent, "初期化後の読み込み内容が空文字列であること");

    }

    /**
     * getReadContent メソッドのテスト - 正常系:コンテンツロード後
     *
     * @since 0.2.3
     */
    @Test
    public void testGetReadContent_normalAfterLoadContent() {

        /* 期待値の定義 */
        final String expectedReadContent = "public class TestFile {\n}\n";

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();
            this.testTarget.loadContent();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        final String actualReadContent = this.testTarget.getReadContent();

        /* 検証の実施 */
        Assertions.assertEquals(expectedReadContent, actualReadContent, "コンテンツロード後に正しい内容が読み込まれること");

    }

    /**
     * getTargetPath メソッドのテスト - 正常系:初期化後
     *
     * @since 0.2.3
     */
    @Test
    public void testGetTargetPath_normalAfterInitialize() {

        /* 期待値の定義 */
        final Path expectedTargetPath = this.testTempDir;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        final Path actualTargetPath = this.testTarget.getTargetPath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedTargetPath, actualTargetPath, "初期化後に対象パスが設定されること");

    }

    /**
     * initialize メソッドのテスト - 正常系:初期化成功
     *
     * @since 0.2.3
     */
    @Test
    public void testInitialize_normalSuccess() {

        /* 期待値の定義 */
        final boolean expectedResult = true;

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.initialize(this.testTempDir);

        } catch (final Exception e) {

            Assertions.fail("初期化で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final Path       actualTargetPath      = this.testTarget.getTargetPath();
        final List<Path> actualFilePathList    = this.testTarget.getFilePathList();
        final Path       actualCurrentFilePath = this.testTarget.getCurrentFilePath();
        final String     actualReadContent     = this.testTarget.getReadContent();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "初期化が成功すること");
        Assertions.assertEquals(this.testTempDir, actualTargetPath, "対象パスが設定されること");
        Assertions.assertEquals(0, actualFilePathList.size(), "ファイルパスリストがクリアされること");
        Assertions.assertEquals(null, actualCurrentFilePath, "カレントファイルパスがnullに設定されること");
        Assertions.assertEquals(KmgString.EMPTY, actualReadContent, "読み込み内容が空文字列に設定されること");

    }

    /**
     * initialize メソッドのテスト - 正常系:拡張子を指定して初期化
     *
     * @since 0.2.3
     */
    @Test
    public void testInitialize_normalWithExtension() {

        /* 期待値の定義 */
        final boolean expectedResult = true;

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.initialize(this.testTempDir, ".java");

        } catch (final Exception e) {

            Assertions.fail("初期化で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final Path actualTargetPath = this.testTarget.getTargetPath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "初期化が成功すること");
        Assertions.assertEquals(this.testTempDir, actualTargetPath, "対象パスが設定されること");

    }

    /**
     * initialize メソッドのテスト - 正常系:拡張子をnullにして全ファイル対象
     *
     * @since 0.2.3
     */
    @Test
    public void testInitialize_normalWithNullExtension() {

        /* 期待値の定義 */
        final boolean expectedResult = true;

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.initialize(this.testTempDir, null);

        } catch (final Exception e) {

            Assertions.fail("初期化で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final Path actualTargetPath = this.testTarget.getTargetPath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "初期化が成功すること");
        Assertions.assertEquals(this.testTempDir, actualTargetPath, "対象パスが設定されること");

    }

    /**
     * load メソッドのテスト - 異常系:IOException発生
     *
     * @since 0.2.3
     */
    @Test
    public void testLoad_errorIOException() {

        /* 準備 */
        final Path nonExistentPath = Paths.get("non/existent/path");

        try {

            this.testTarget.initialize(nonExistentPath);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* KmgMessageUtilsの静的メソッドをモック化 */
        try (final MockedStatic<KmgMessageUtils> mockedKmgMessageUtils = this.setupKmgMessageUtilsMock()) {

            /* SpringApplicationContextHelperのモック化 */
            try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
                = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

                final KmgMessageSource mockMessageSource = Mockito.mock(KmgMessageSource.class);
                mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                    .thenReturn(mockMessageSource);

                /* モックメッセージソースの設定 */
                Mockito.when(mockMessageSource.getExcMessage(ArgumentMatchers.any(), ArgumentMatchers.any()))
                    .thenReturn("");

                /* Files.walkをstaticモックしてIOExceptionをスローさせる */
                try (final MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

                    filesMock.when(() -> Files.walk(nonExistentPath)).thenThrow(new IOException("Test IOException"));

                    /* テスト対象の実行・検証の実施 */
                    final KmgFundMsgException actualException
                        = Assertions.assertThrows(KmgFundMsgException.class, () -> {

                            this.testTarget.load();

                        }, "IOExceptionでKmgFundMsgExceptionがスローされること");

                    /* 検証の実施 */
                    Assertions.assertNotNull(actualException, "例外が発生すること");
                    Assertions.assertInstanceOf(IOException.class, actualException.getCause(), "原因がIOExceptionであること");

                }

            }

        }

    }

    /**
     * load メソッドのテスト - 異常系:NoSuchFileException発生
     *
     * @since 0.2.3
     */
    @Test
    public void testLoad_errorNoSuchFileException() {

        /* 準備 */
        final Path testNoSuchFilePath = Paths.get("nosuchfile/path");

        try {

            this.testTarget.initialize(testNoSuchFilePath);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* KmgMessageUtilsの静的メソッドをモック化 */
        try (final MockedStatic<KmgMessageUtils> mockedKmgMessageUtils = this.setupKmgMessageUtilsMock()) {

            /* SpringApplicationContextHelperのモック化 */
            try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
                = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

                final KmgMessageSource mockMessageSource = Mockito.mock(KmgMessageSource.class);
                mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                    .thenReturn(mockMessageSource);

                /* モックメッセージソースの設定 */
                Mockito.when(mockMessageSource.getExcMessage(ArgumentMatchers.any(), ArgumentMatchers.any()))
                    .thenReturn("");

                /* Files.walkをstaticモックしてNoSuchFileExceptionをスローさせる */
                try (final MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

                    filesMock.when(() -> Files.walk(testNoSuchFilePath))
                        .thenThrow(new NoSuchFileException("nosuchfile/path"));

                    /* テスト対象の実行・検証の実施 */
                    final KmgFundMsgException actualException
                        = Assertions.assertThrows(KmgFundMsgException.class, () -> {

                            this.testTarget.load();

                        }, "NoSuchFileExceptionでKmgFundMsgExceptionがスローされること");

                    /* 検証の実施 */
                    Assertions.assertNotNull(actualException, "例外が発生すること");
                    Assertions.assertInstanceOf(NoSuchFileException.class, actualException.getCause(),
                        "原因がNoSuchFileExceptionであること");

                }

            }

        }

    }

    /**
     * load メソッドのテスト - 正常系:Javaファイルなし
     *
     * @since 0.2.3
     */
    @Test
    public void testLoad_normalNoJavaFiles() {

        /* 期待値の定義 */
        final boolean expectedResult    = true;
        final int     expectedFileCount = 0;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testEmptyDir);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("ロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final List<Path> actualFilePathList    = this.testTarget.getFilePathList();
        final Path       actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "ロードが成功すること");
        Assertions.assertEquals(expectedFileCount, actualFilePathList.size(), "Javaファイルがない場合はリストが空であること");
        Assertions.assertEquals(null, actualCurrentFilePath, "Javaファイルがない場合はカレントファイルがnullであること");

    }

    /**
     * load メソッドのテスト - 正常系:異なる拡張子を指定してロード
     *
     * @since 0.2.3
     */
    @Test
    public void testLoad_normalWithDifferentExtension() {

        /* 期待値の定義 */
        final boolean expectedResult    = true;
        final int     expectedFileCount = 1;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir, ".txt");

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("ロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final List<Path> actualFilePathList    = this.testTarget.getFilePathList();
        final Path       actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "ロードが成功すること");
        Assertions.assertEquals(expectedFileCount, actualFilePathList.size(), "txtファイルがロードされること");
        Assertions.assertNotNull(actualCurrentFilePath, "カレントファイルが設定されること");
        Assertions.assertTrue(actualCurrentFilePath.toString().endsWith(".txt"), "txtファイルがカレントファイルに設定されること");

    }

    /**
     * load メソッドのテスト - 正常系:拡張子を指定してロード
     *
     * @since 0.2.3
     */
    @Test
    public void testLoad_normalWithExtension() {

        /* 期待値の定義 */
        final boolean expectedResult    = true;
        final int     expectedFileCount = 1;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir, ".java");

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("ロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final List<Path> actualFilePathList    = this.testTarget.getFilePathList();
        final Path       actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "ロードが成功すること");
        Assertions.assertEquals(expectedFileCount, actualFilePathList.size(), "Javaファイルがロードされること");
        Assertions.assertEquals(this.testJavaFile, actualCurrentFilePath, "最初のJavaファイルがカレントファイルに設定されること");

    }

    /**
     * load メソッドのテスト - 正常系:Javaファイルあり
     *
     * @since 0.2.3
     */
    @Test
    public void testLoad_normalWithJavaFiles() {

        /* 期待値の定義 */
        final boolean expectedResult    = true;
        final int     expectedFileCount = 1;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("ロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final List<Path> actualFilePathList    = this.testTarget.getFilePathList();
        final Path       actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "ロードが成功すること");
        Assertions.assertEquals(expectedFileCount, actualFilePathList.size(), "Javaファイルがロードされること");
        Assertions.assertEquals(this.testJavaFile, actualCurrentFilePath, "最初のJavaファイルがカレントファイルに設定されること");

    }

    /**
     * load メソッドのテスト - 正常系:拡張子をnullにして全ファイルをロード
     *
     * @since 0.2.3
     */
    @Test
    public void testLoad_normalWithNullExtension() {

        /* 期待値の定義 */
        final boolean expectedResult    = true;
        final int     expectedFileCount = 2;   // .javaファイルと.txtファイル

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir, null);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("ロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final List<Path> actualFilePathList    = this.testTarget.getFilePathList();
        final Path       actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "ロードが成功すること");
        Assertions.assertEquals(expectedFileCount, actualFilePathList.size(), "全ファイルがロードされること");
        Assertions.assertNotNull(actualCurrentFilePath, "カレントファイルが設定されること");

    }

    /**
     * loadContent メソッドのテスト - 異常系:ファイル読み込みエラー
     *
     * @since 0.2.3
     */
    @Test
    public void testLoadContent_errorFileReadError() {

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* KmgMessageUtilsの静的メソッドをモック化 */
        try (final MockedStatic<KmgMessageUtils> mockedKmgMessageUtils = this.setupKmgMessageUtilsMock()) {

            /* SpringApplicationContextHelperのモック化 */
            try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
                = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

                final KmgMessageSource mockMessageSource = Mockito.mock(KmgMessageSource.class);
                mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                    .thenReturn(mockMessageSource);

                /* モックメッセージソースの設定 */
                Mockito.when(mockMessageSource.getExcMessage(ArgumentMatchers.any(), ArgumentMatchers.any()))
                    .thenReturn("");

                /* Files.readStringをstaticモックしてIOExceptionをスローさせる */
                try (final MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

                    filesMock.when(() -> Files.readString(ArgumentMatchers.any(Path.class)))
                        .thenThrow(new IOException("Test IOException"));

                    /* テスト対象の実行・検証の実施 */
                    final KmgFundMsgException actualException
                        = Assertions.assertThrows(KmgFundMsgException.class, () -> {

                            this.testTarget.loadContent();

                        }, "ファイル読み込みエラーでKmgFundMsgExceptionがスローされること");

                    /* 検証の実施 */
                    Assertions.assertNotNull(actualException, "例外が発生すること");
                    Assertions.assertInstanceOf(IOException.class, actualException.getCause(), "原因がIOExceptionであること");

                }

            }

        }

    }

    /**
     * loadContent メソッドのテスト - 正常系:内容あり
     *
     * @since 0.2.3
     */
    @Test
    public void testLoadContent_normalWithContent() {

        /* 期待値の定義 */
        final boolean expectedResult  = true;
        final String  expectedContent = "public class TestFile {\n}\n";

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.loadContent();

        } catch (final Exception e) {

            Assertions.fail("コンテンツロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final String actualReadContent = this.testTarget.getReadContent();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "コンテンツロードが成功すること");
        Assertions.assertEquals(expectedContent, actualReadContent, "正しい内容が読み込まれること");

    }

    /**
     * loadContent メソッドのテスト - 準正常系:内容がブランク
     *
     * @since 0.2.3
     */
    @Test
    public void testLoadContent_semiBlankContent() {

        /* 期待値の定義 */
        final boolean expectedResult = false;

        /* 準備 */
        Path blankJavaFile = null;

        try {

            /* 既存のファイルを削除してブランクファイルのみにする */
            Files.delete(this.testJavaFile);
            blankJavaFile = this.testTempDir.resolve("BlankFile.java");
            Files.writeString(blankJavaFile, "   \n  \t  ");
            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = true;

        try {

            actualResult = this.testTarget.loadContent();

        } catch (final Exception e) {

            Assertions.fail("コンテンツロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final String actualReadContent = this.testTarget.getReadContent();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "ブランクのコンテンツの場合falseが返されること");
        Assertions.assertEquals("   \n  \t  ", actualReadContent, "ブランクの内容が読み込まれること");

    }

    /**
     * loadContent メソッドのテスト - 準正常系:内容が空
     *
     * @since 0.2.3
     */
    @Test
    public void testLoadContent_semiEmptyContent() {

        /* 期待値の定義 */
        final boolean expectedResult = false;

        /* 準備 */
        Path emptyJavaFile = null;

        try {

            /* 既存のファイルを削除して空のファイルのみにする */
            Files.delete(this.testJavaFile);
            emptyJavaFile = this.testTempDir.resolve("EmptyFile.java");
            Files.writeString(emptyJavaFile, "");
            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = true;

        try {

            actualResult = this.testTarget.loadContent();

        } catch (final Exception e) {

            Assertions.fail("コンテンツロード処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final String actualReadContent = this.testTarget.getReadContent();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "空のコンテンツの場合falseが返されること");
        Assertions.assertEquals("", actualReadContent, "空の内容が読み込まれること");

    }

    /**
     * nextFile メソッドのテスト - 正常系:次のファイルあり
     *
     * @since 0.2.3
     */
    @Test
    public void testNextFile_normalHasNextFile() {

        /* 期待値の定義 */
        final boolean expectedResult = true;

        /* 準備 */
        Path secondJavaFile = null;

        try {

            secondJavaFile = this.testTempDir.resolve("SecondFile.java");
            Files.writeString(secondJavaFile, "public class SecondFile {}");
            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.nextFile();

        } catch (final Exception e) {

            Assertions.fail("nextFile処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final Path actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "次のファイルが存在する場合trueが返されること");
        Assertions.assertTrue(actualCurrentFilePath.getFileName().toString().endsWith(".java"),
            "次のJavaファイルがカレントファイルに設定されること");

    }

    /**
     * nextFile メソッドのテスト - 準正常系:次のファイルなし
     *
     * @since 0.2.3
     */
    @Test
    public void testNextFile_semiNoNextFile() {

        /* 期待値の定義 */
        final boolean expectedResult = false;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = true;

        try {

            actualResult = this.testTarget.nextFile();

        } catch (final Exception e) {

            Assertions.fail("nextFile処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "次のファイルが存在しない場合falseが返されること");

    }

    /**
     * resetFileIndex メソッドのテスト - 正常系:ファイルリストなし
     *
     * @since 0.2.3
     */
    @Test
    public void testResetFileIndex_normalNoFileList() {

        /* 期待値の定義 */
        final boolean expectedResult = true;

        /* 準備 */
        try {

            this.testTarget.initialize(this.testEmptyDir);
            this.testTarget.load();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.resetFileIndex();

        } catch (final Exception e) {

            Assertions.fail("resetFileIndex処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final Path actualCurrentFilePath = this.testTarget.getCurrentFilePath();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "リセットが成功すること");
        Assertions.assertEquals(null, actualCurrentFilePath, "ファイルリストが空の場合カレントファイルがnullに設定されること");

    }

    /**
     * resetFileIndex メソッドのテスト - 正常系:ファイルリストあり
     *
     * @since 0.2.3
     */
    @Test
    public void testResetFileIndex_normalWithFileList() {

        /* 期待値の定義 */
        final boolean expectedResult = true;

        /* 準備 */
        Path secondJavaFile = null;

        try {

            secondJavaFile = this.testTempDir.resolve("SecondFile.java");
            Files.writeString(secondJavaFile, "public class SecondFile {}");
            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();
            /* 次のファイルに進んでからリセット */
            this.testTarget.nextFile();

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.resetFileIndex();

        } catch (final Exception e) {

            Assertions.fail("resetFileIndex処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        final Path       actualCurrentFilePath = this.testTarget.getCurrentFilePath();
        final List<Path> filePathList          = this.testTarget.getFilePathList();

        /* 検証の実施 */
        Assertions.assertEquals(expectedResult, actualResult, "リセットが成功すること");
        Assertions.assertEquals(filePathList.get(0), actualCurrentFilePath, "先頭のファイルがカレントファイルに設定されること");

    }

    /**
     * setWriteContent メソッドのテスト - 正常系:書き込み内容設定
     *
     * @since 0.2.3
     */
    @Test
    public void testSetWriteContent_normalSetContent() {

        /* 期待値の定義 */
        final String expectedContent = "Modified content";

        /* テスト対象の実行 */
        this.testTarget.setWriteContent(expectedContent);

        /* 検証は writeContent で間接的に確認 */
        Assertions.assertTrue(true, "setWriteContentメソッドが正常に実行されること");

    }

    /**
     * writeContent メソッドのテスト - 異常系:書き込みエラー
     *
     * @since 0.2.3
     */
    @Test
    public void testWriteContent_errorWriteError() {

        final String testContent = "Test content";

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();
            this.testTarget.setWriteContent(testContent);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* KmgMessageUtilsの静的メソッドをモック化 */
        try (final MockedStatic<KmgMessageUtils> mockedKmgMessageUtils = this.setupKmgMessageUtilsMock()) {

            /* SpringApplicationContextHelperのモック化 */
            try (final MockedStatic<SpringApplicationContextHelper> mockedStatic
                = Mockito.mockStatic(SpringApplicationContextHelper.class)) {

                final KmgMessageSource mockMessageSource = Mockito.mock(KmgMessageSource.class);
                mockedStatic.when(() -> SpringApplicationContextHelper.getBean(KmgMessageSource.class))
                    .thenReturn(mockMessageSource);

                /* モックメッセージソースの設定 */
                Mockito.when(mockMessageSource.getExcMessage(ArgumentMatchers.any(), ArgumentMatchers.any()))
                    .thenReturn("");

                /* Files.writeStringをstaticモックしてIOExceptionをスローさせる */
                try (final MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

                    filesMock
                        .when(() -> Files.writeString(ArgumentMatchers.any(Path.class), ArgumentMatchers.anyString()))
                        .thenThrow(new IOException("Test IOException"));

                    /* テスト対象の実行・検証の実施 */
                    final KmgFundMsgException actualException
                        = Assertions.assertThrows(KmgFundMsgException.class, () -> {

                            this.testTarget.writeContent();

                        }, "書き込みエラーでKmgFundMsgExceptionがスローされること");

                    /* 検証の実施 */
                    Assertions.assertNotNull(actualException, "例外が発生すること");
                    Assertions.assertInstanceOf(IOException.class, actualException.getCause(), "原因がIOExceptionであること");

                }

            }

        }

    }

    /**
     * writeContent メソッドのテスト - 正常系:書き込み成功
     *
     * @since 0.2.3
     */
    @Test
    public void testWriteContent_normalSuccess() {

        /* 期待値の定義 */
        final String expectedContent = "Modified Java content";

        /* 準備 */
        try {

            this.testTarget.initialize(this.testTempDir);
            this.testTarget.load();
            this.testTarget.setWriteContent(expectedContent);

        } catch (final Exception e) {

            Assertions.fail("準備処理で例外が発生しました: " + e.getMessage());

        }

        /* テスト対象の実行 */
        boolean actualResult = false;

        try {

            actualResult = this.testTarget.writeContent();

        } catch (final Exception e) {

            Assertions.fail("書き込み処理で例外が発生しました: " + e.getMessage());

        }

        /* 検証の準備 */
        String actualFileContent = "";

        try {

            actualFileContent = Files.readString(this.testJavaFile);

        } catch (final Exception e) {

            Assertions.fail("ファイル読み込みで例外が発生しました: " + e.getMessage());

        }

        /* 検証の実施 */
        Assertions.assertTrue(actualResult, "書き込みが成功すること");
        Assertions.assertEquals(expectedContent, actualFileContent, "指定された内容でファイルが書き込まれること");

    }

    /**
     * KmgMessageUtilsの静的メソッドをモック化する<br>
     * <p>
     * テストでKmgFundMsgExceptionなどの例外クラスのコンストラクタが呼び出される前に、 KmgMessageUtilsの静的メソッドをモック化することで、静的初期化ブロックの失敗を回避します。
     * </p>
     *
     * @since 0.2.3
     *
     * @return MockedStatic&lt;KmgMessageUtils&gt; モック化されたKmgMessageUtils（try-with-resourcesで管理すること）
     */
    @Override
    protected MockedStatic<KmgMessageUtils> setupKmgMessageUtilsMock() {

        final MockedStatic<KmgMessageUtils> mockedKmgMessageUtils = Mockito.mockStatic(KmgMessageUtils.class);

        // getExcMessageをモック化（任意の引数で空文字列を返す）
        mockedKmgMessageUtils.when(
            () -> KmgMessageUtils.getExcMessage(ArgumentMatchers.any(KmgCmnExcMsgTypes.class), ArgumentMatchers.any()))
            .thenReturn("");

        // getMessageArgsCountをモック化（任意の文字列で0を返す）
        mockedKmgMessageUtils.when(() -> KmgMessageUtils.getMessageArgsCount(ArgumentMatchers.anyString()))
            .thenReturn(0);

        return mockedKmgMessageUtils;

    }

    /**
     * ディレクトリを再帰的に削除する<br>
     *
     * @since 0.2.3
     *
     * @param directory
     *                  削除するディレクトリ
     *
     * @throws IOException
     *                     入出力例外
     */
    private void deleteDirectoryRecursively(final Path directory) throws IOException {

        if (!Files.exists(directory)) {

            return;

        }

        try (Stream<Path> stream = Files.walk(directory)) {

            stream.sorted((a, b) -> b.compareTo(a)).forEach(path -> {

                try {

                    Files.delete(path);

                } catch (final IOException e) {

                    // テストコードのため、例外は無視

                }

            });

        }

    }

}
