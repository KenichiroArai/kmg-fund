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
 * KMG基盤プロパティローダー
 * <p>
 * Spring Boot起動時にプロパティファイルを読み込み、環境設定を行うクラスです。 以下の順序でプロパティを読み込み、統合します：
 * <ol>
 * <li>メインプロパティ（application.properties）</li>
 * <li>KMG基盤プロパティ（kmg-fund-application.properties）</li>
 * <li>追加プロパティ（サブクラスで定義可能）</li>
 * </ol>
 * </p>
 *
 * @author KenichiroArai
 *
 * @version 0.2.0
 *
 * @since 0.1.0
 */
@Component
@Order(10)
public class KmgFundPropertiesLoader implements EnvironmentPostProcessor {

    /** 統合プロパティのマップ：全てのプロパティを統合して保持 */
    private final Map<String, Object> integratedPropertieMap;

    /** メインプロパティのマップ：application.propertiesの内容を保持 */
    private final Map<String, Object> mainPropertieMap;

    /** KMG基盤プロパティのマップ：kmg-fund-application.propertiesの内容を保持 */
    private final Map<String, Object> kmgFundPropertieMap;

    /** 追加プロパティのマップ：サブクラスで追加されるプロパティを保持 */
    private final Map<String, Object> additionalPropertieMap;

    /**
     * デフォルトコンストラクタ
     * <p>
     * 各プロパティマップを初期化します。
     * </p>
     */
    public KmgFundPropertiesLoader() {

        this.integratedPropertieMap = new HashMap<>();
        this.mainPropertieMap = new HashMap<>();
        this.kmgFundPropertieMap = new HashMap<>();
        this.additionalPropertieMap = new HashMap<>();

    }

    /**
     * 環境後処理を実行
     * <p>
     * Spring Boot起動時に呼び出され、以下の処理を行います：
     * <ol>
     * <li>メインプロパティの読み込み</li>
     * <li>KMG基盤プロパティの読み込み</li>
     * <li>追加プロパティの読み込み</li>
     * <li>全プロパティの統合</li>
     * <li>環境への統合プロパティの追加</li>
     * </ol>
     * </p>
     *
     * @param environment
     *                    Spring環境設定オブジェクト
     * @param application
     *                    Springアプリケーションオブジェクト
     */
    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, final SpringApplication application) {

        /* メインプロパティファイルの読み込み */
        KmgFundPropertiesLoader.fromPropertieMap(KmgApplicationPropertyFileTypes.APPLICATION_PROPERTIES.get(),
            this.mainPropertieMap);

        /* KMG基盤プロパティファイルの読み込み */
        KmgFundPropertiesLoader.fromPropertieMap(KmgApplicationPropertyFileTypes.KMG_FUND_APPLICATION_PROPERTIES.get(),
            this.kmgFundPropertieMap);

        /* 追加プロパティの読み込み */
        this.fromAdditionalProperties(this.additionalPropertieMap);

        /* プロパティの統合 */
        this.integrateProperties(this.integratedPropertieMap);

        /* 統合したプロパティを環境に追加 */
        final PropertySource<?> propertySource = new MapPropertySource(
            KmgApplicationPropertyFileTypes.KMG_APPLICATION_PROPERTIES.get(), this.integratedPropertieMap);
        environment.getPropertySources().addFirst(propertySource);

    }

    /**
     * 追加プロパティを読み込む
     * <p>
     * サブクラスでオーバーライドして、独自のプロパティファイルを読み込むことができます。 デフォルトの実装では何も行いません。
     * </p>
     *
     * @param additionalPropertieMap
     *                               追加プロパティを格納するマップ
     */
    @SuppressWarnings("hiding")
    protected void fromAdditionalProperties(final Map<String, Object> additionalPropertieMap) {

        // デフォルトでは処理なし
    }

    /**
     * プロパティを統合する
     * <p>
     * メインプロパティを基準とし、spring.messages.basenameプロパティのみを統合します。 サブクラスでオーバーライドして、独自の統合ロジックを実装できます。
     * </p>
     *
     * @param integratedPropertieMap
     *                               統合結果を格納するマップ
     */
    @SuppressWarnings("hiding")
    protected void integrateProperties(final Map<String, Object> integratedPropertieMap) {

        /* メインプロパティを統合マップにコピー */
        integratedPropertieMap.putAll(this.mainPropertieMap);

        /* spring.messages.basenameの値を統合 */
        this.integrateMessageBasename(this.kmgFundPropertieMap,
            KmgApplicationPropertyKeyTypes.SPRING_MESSAGES_BASENAME.get());

    }

    /**
     * メッセージベース名を統合する
     * <p>
     * 指定されたプロパティキーに対応する値を、カンマ区切りで統合します。 統合されたプロパティは統合マップに保存されます。
     * </p>
     *
     * @param propertyMap
     *                    メッセージベース名を含むプロパティマップ
     * @param propertyKey
     *                    統合対象のプロパティキー
     */
    private void integrateMessageBasename(final Map<String, Object> propertyMap, final String propertyKey) {

        /* プロパティマップの各エントリーを処理 */
        for (final String destKey : propertyMap.keySet()) {

            /* 指定されたキーと一致しない場合はスキップ */
            if (!KmgString.equals(destKey, propertyKey)) {

                continue;

            }

            /* 統合マップから既存の値を取得 */
            final String srcValue = (String) this.integratedPropertieMap.get(destKey);

            if (KmgString.isEmpty(srcValue)) {

                continue;

            }

            /* プロパティマップから新しい値を取得 */
            final String destValue = (String) propertyMap.get(destKey);

            /* 既存の値と新しい値をカンマで結合 */
            final String intergratedValue = KmgDelimiterTypes.COMMA.join(srcValue, destValue);

            /* 統合した値を保存 */
            this.integratedPropertieMap.put(destKey, intergratedValue);

        }

    }

    /**
     * リソースパスからプロパティを読み込み、マップに設定する
     * <p>
     * 指定されたリソースパスのプロパティファイルを読み込み、 その内容を指定されたマップに設定します。 ファイルが存在しない場合や読み込みに失敗した場合は処理をスキップします。
     * </p>
     *
     * @param resourcePath
     *                     プロパティファイルのリソースパス
     * @param propertieMap
     *                     プロパティを格納するマップ
     */
    protected static void fromPropertieMap(final String resourcePath, final Map<String, Object> propertieMap) {

        /* リソースの取得 */
        final Resource resource = new ClassPathResource(resourcePath);

        /* リソースが存在しない場合は処理終了 */
        if (!resource.exists()) {

            return;

        }

        /* プロパティの読み込み */
        final Properties properties = new Properties();

        try (InputStream inputStream = resource.getInputStream()) {

            properties.load(inputStream);

        } catch (final IOException e) {

            // TODO KenichiroArai 2025/03/20 ログ出力の実装
            e.printStackTrace();
            return;

        }

        /* プロパティをマップに設定 */
        for (final Object key : properties.keySet()) {

            propertieMap.put(key.toString(), properties.get(key));

        }

    }
}
