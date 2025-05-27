package kmg.fund.infrastructure.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.env.MockEnvironment;

import kmg.fund.domain.types.KmgApplicationPropertyFileTypes;
import kmg.fund.domain.types.KmgApplicationPropertyKeyTypes;

/**
 * KMG基盤プロパティローダーのテスト
 *
 * @author KenichiroArai
 *
 * @version 0.1.0
 *
 * @since 0.1.0
 */
@SuppressWarnings({
    "static-method",
})
@DisplayName("KmgFundPropertiesLoaderのテスト")
public class KmgFundPropertiesLoaderTest {

    /** テスト対象のプロパティローダー */
    private KmgFundPropertiesLoader loader;

    /** テスト用の環境設定 */
    private ConfigurableEnvironment environment;

    /** テスト用のSpringアプリケーション */
    private SpringApplication application;

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public KmgFundPropertiesLoaderTest() {

        // 処理なし
    }

    /**
     * テストの前準備
     * <p>
     * 各テストメソッドの実行前に呼び出され、テストに必要なオブジェクトを初期化します。
     * </p>
     *
     * @since 0.1.0
     */
    @BeforeEach
    public void setUp() {

        this.loader = new KmgFundPropertiesLoader();
        this.environment = new MockEnvironment();
        this.application = new SpringApplication();

    }

    /**
     * postProcessEnvironmentメソッドのテスト - 正常系:プロパティの統合
     *
     * @since 0.1.0
     */
    @Test
    @DisplayName("プロパティが正しく統合されること")
    public void testPostProcessEnvironment_normalIntegration() {

        /* 期待値の定義 */
        final String expectedPropertySourceName = KmgApplicationPropertyFileTypes.KMG_APPLICATION_PROPERTIES.get();

        /* 実行 */
        this.loader.postProcessEnvironment(this.environment, this.application);

        /* 検証 */
        final MutablePropertySources propertySources = this.environment.getPropertySources();
        Assertions.assertEquals(true, propertySources.contains(expectedPropertySourceName), "プロパティソースが存在しません");

        final MapPropertySource propertySource = (MapPropertySource) propertySources.get(expectedPropertySourceName);
        Assertions.assertNotNull(propertySource, "プロパティソースがnullです");

    }

    /**
     * fromPropertieMapメソッドのテスト - 準正常系:存在しないリソースパス
     *
     * @since 0.1.0
     */
    @Test
    @DisplayName("存在しないリソースパスの場合、マップは空のままであること")
    public void testFromPropertieMap_semiNonExistentResource() {

        /* 期待値の定義 */
        final int expectedSize = 0;

        /* 準備 */
        final Map<String, Object> propertieMap = new HashMap<>();

        /* 実行 */
        KmgFundPropertiesLoader.fromPropertieMap(new ClassPathResource("non-existent.properties"), propertieMap);

        /* 検証 */
        Assertions.assertEquals(expectedSize, propertieMap.size(), "マップのサイズが一致しません");

    }

    /**
     * integrateMessageBasenameメソッドのテスト - 正常系:メッセージベース名の統合
     *
     * @since 0.1.0
     */
    @SuppressWarnings("null")
    @Test
    @DisplayName("メッセージベース名が正しく統合されること")
    public void testIntegrateMessageBasename_normalIntegration() {

        /* 期待値の定義 */
        final String expectedMessage1 = "kmg-fund-messages";
        final String expectedMessage2 = "kmg-fund-messages-log";

        /* 準備 */
        final Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put(KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME.get(), expectedMessage2);

        final Map<String, Object> integratedMap = new HashMap<>();
        integratedMap.put(KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME.get(), expectedMessage1);

        /* 実行 */
        this.loader.postProcessEnvironment(this.environment, this.application);

        /* 検証 */
        final MutablePropertySources propertySources = this.environment.getPropertySources();
        final MapPropertySource      propertySource  = (MapPropertySource) propertySources
            .get(KmgApplicationPropertyFileTypes.KMG_APPLICATION_PROPERTIES.get());

        final String basename
            = propertySource.getProperty(KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME.get()).toString();
        Assertions.assertTrue(basename.contains(expectedMessage1), "messages1が含まれていません");
        Assertions.assertTrue(basename.contains(expectedMessage2), "messages2が含まれていません");

    }

    /**
     * fromPropertieMapメソッドのテスト - 異常系:IOException発生
     *
     * @throws IOException
     *                     テスト用の例外
     *
     * @since 0.1.0
     */
    @Test
    @DisplayName("IOExceptionが発生した場合、マップは空のままであること")
    public void testFromPropertieMap_IOException() throws IOException {

        /* 準備 */
        final Map<String, Object> propertieMap = new HashMap<>();
        final Resource            resource     = Mockito.mock(Resource.class);
        Mockito.when(resource.exists()).thenReturn(true);
        Mockito.when(resource.getInputStream()).thenThrow(new IOException("テスト用の例外"));

        /* 実行 */
        KmgFundPropertiesLoader.fromPropertieMap(resource, propertieMap);

        /* 検証 */
        Assertions.assertEquals(0, propertieMap.size(), "マップのサイズが一致しません");

    }
}
