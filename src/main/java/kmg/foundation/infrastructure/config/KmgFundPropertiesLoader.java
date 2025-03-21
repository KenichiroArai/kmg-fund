package kmg.foundation.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import kmg.core.infrastructure.type.KmgString;
import kmg.core.infrastructure.types.KmgDelimiterTypes;
import kmg.foundation.domain.types.KmgApplicationPropertyFileTypes;
import kmg.foundation.domain.types.KmgApplicationPropertyKeyTypes;

/**
 * KMG 基盤プロパティローダー Spring Bootの起動時にプロパティファイルを読み込むためのクラス
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.2.0
 */
@Component
@Order(10)
public class KmgFundPropertiesLoader implements EnvironmentPostProcessor {

    /** 統合プロパティのマップ */
    private final Map<String, Object> integratedPropertieMap;

    /** メインプロパティのマップ */
    private final Map<String, Object> mainPropertieMap;

    /** KMG基盤プロパティのマップ */
    private final Map<String, Object> kmgFundPropertieMap;

    /** 追加プロパティのマップ */
    private final Map<String, Object> additionalPropertieMap;

    /**
     * デフォルトコンストラクタ
     */
    public KmgFundPropertiesLoader() {

        this.integratedPropertieMap = new HashMap<>();
        this.mainPropertieMap = new HashMap<>();
        this.kmgFundPropertieMap = new HashMap<>();
        this.additionalPropertieMap = new HashMap<>();

    }

    /**
     * 環境後処理
     *
     * @param environment
     *                    環境
     * @param application
     *                    アプリケーション
     */
    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, final SpringApplication application) {

        KmgFundPropertiesLoader.fromPropertieMap(KmgApplicationPropertyFileTypes.APPLICATION_PROPERTIES.get(),
            this.mainPropertieMap);
        KmgFundPropertiesLoader.fromPropertieMap(KmgApplicationPropertyFileTypes.KMG_FUND_APPLICATION_PROPERTIES.get(),
            this.kmgFundPropertieMap);

        // 追加プロパティの読み込み
        this.fromAdditionalProperties(this.additionalPropertieMap);

        // プロパティの統合
        this.integrateProperties(this.integratedPropertieMap);

        // プロパティを環境に追加
        final PropertySource<?> propertySource = new MapPropertySource(
            KmgApplicationPropertyFileTypes.KMG_APPLICATION_PROPERTIES.get(), this.integratedPropertieMap);
        environment.getPropertySources().addFirst(propertySource);

    }

    /**
     * 追加プロパティを読み込む<br>
     * <p>
     * 子クラスはこのメソッドをオーバーライドして独自のプロパティファイルを読み込み、追加プロパティのマップに設定してください。
     * </p>
     *
     * @param additionalPropertieMap
     *                               追加プロパティのマップ
     */
    @SuppressWarnings("hiding")
    protected void fromAdditionalProperties(final Map<String, Object> additionalPropertieMap) {

        // 処理なし

    }

    /**
     * プロパティを統合する<br>
     * <p>
     * メインプロパティ、KMG基盤プロパティ、追加プロパティを統合します。 子クラスはこのメソッドをオーバーライドして独自の統合ロジックを実装できます。
     * </p>
     *
     * @param integratedPropertieMap
     *                               統合プロパティのマップ
     */
    @SuppressWarnings("hiding")
    protected void integrateProperties(final Map<String, Object> integratedPropertieMap) {

        /*
         * メインプロパティを基準にし、"spring.messages.basename"のみ統合する。
         */

        /* メインプロパティを統合 */
        integratedPropertieMap.putAll(this.mainPropertieMap);

        /* spring.messages.basenameの値を統合する */
        this.integrateMessageBasename(this.kmgFundPropertieMap,
            KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME.get());

    }

    /**
     * メッセージベース名を統合する
     *
     * @param propertyMap
     *                    メッセージベース名を含むプロパティマップ
     * @param propertyKey
     *                    統合するプロパティキー
     */
    private void integrateMessageBasename(final Map<String, Object> propertyMap, final String propertyKey) {

        for (final String destKey : propertyMap.keySet()) {

            if (!KmgString.equals(destKey, propertyKey)) {

                continue;

            }

            final String srcValue = (String) this.integratedPropertieMap.get(destKey);

            if (KmgString.isEmpty(srcValue)) {

                continue;

            }

            final String destValue = (String) propertyMap.get(destKey);

            final String intergratedValue = KmgDelimiterTypes.COMMA.join(srcValue, destValue);

            this.integratedPropertieMap.put(destKey, intergratedValue);

        }

    }

    /**
     * リソースパスからプロパティを読み込み、マップに設定する
     *
     * @param resourcePath
     *                     リソースパス
     * @param propertieMap
     *                     設定先のプロパティマップ
     */
    protected static void fromPropertieMap(final String resourcePath, final Map<String, Object> propertieMap) {

        final Resource resource = new ClassPathResource(resourcePath);

        if (!resource.exists()) {

            return;

        }

        final Properties properties = new Properties();

        try (InputStream inputStream = resource.getInputStream()) {

            properties.load(inputStream);

        } catch (final IOException e) {

            // TODO KenichiroArai 2025/03/20 ログ
            e.printStackTrace();
            return;

        }

        for (final Object key : properties.keySet()) {

            propertieMap.put(key.toString(), properties.get(key));

        }

    }
}
