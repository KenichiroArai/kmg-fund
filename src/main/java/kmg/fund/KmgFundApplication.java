package kmg.fund;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import kmg.core.infrastructure.type.KmgString;

/**
 * KMG基盤アプリケーション
 */
@SpringBootApplication
public class KmgFundApplication {

    /**
     * エントリポイント
     *
     * @param args
     *             引数
     */
    public static void main(final String[] args) {

        // SpringApplicationの設定
        final SpringApplication application = new SpringApplication(KmgFundApplication.class);
        final Properties        properties  = new Properties();
        properties.put("spring.config.name", "kmg-fund-application");
        application.setDefaultProperties(properties);

        try (final ConfigurableApplicationContext ctx = application.run(args)) {

            // テスト処理を実行
            KmgString str = new KmgString("TestAbc5Ttt");
            System.out.println(str.toCamelCase());

            System.out.println("HelloWorld");

        }

    }
}
