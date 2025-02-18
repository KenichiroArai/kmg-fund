package kmg.foundation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import kmg.core.infrastructure.type.KmgString;

/**
 * KMG基盤アプリケーション
 */
@SpringBootApplication
public class KmgFoundationApplication {

    /**
     * エントリポイント
     *
     * @param args
     *             引数
     */
    public static void main(final String[] args) {

        try (final ConfigurableApplicationContext ctx = SpringApplication.run(KmgFoundationApplication.class, args)) {

            // テスト処理を実行
            KmgString str = new KmgString("TestAbc5Ttt");
            System.out.println(str.toCamelCase());

            System.out.println("HelloWorld");

        }

    }
}
