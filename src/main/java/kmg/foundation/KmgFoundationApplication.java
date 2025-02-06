package kmg.foundation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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

        @SuppressWarnings("resource")
        final ConfigurableApplicationContext ctx = SpringApplication.run(KmgFoundationApplication.class, args);
        ctx.close();

    }
}
