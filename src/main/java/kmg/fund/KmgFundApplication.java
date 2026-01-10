package kmg.fund;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * KMG基盤アプリケーション<br>
 * <p>
 * Fundは、Foundationの略。
 * </p>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SpringBootApplication
public class KmgFundApplication {

    /**
     * エントリポイント
     *
     * @since 0.1.0
     *
     * @param args
     *             引数
     */
    @SuppressWarnings({
        "resource", "nls"
    })
    public static void main(final String[] args) {

        // SpringApplicationの設定
        final SpringApplication application = new SpringApplication(KmgFundApplication.class);
        final Properties        properties  = new Properties();
        properties.put("spring.config.name", "kmg-fund-application");
        application.setDefaultProperties(properties);

        application.run(args);

    }

    /**
     * コンストラクタ
     *
     * @since 0.1.0
     */
    public KmgFundApplication() {

        // 処理なし
    }
}
