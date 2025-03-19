package kmg.foundation.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * KMG基盤設定クラス
 */
@Configuration
@PropertySource("classpath:kmg-fund-application.properties")
public class KmgFoundationConfig {
    // 処理なし
}
