package io.quarkus.logging.sentry;

import static java.lang.String.join;

import io.sentry.DefaultSentryClientFactory;
import io.sentry.config.provider.ConfigurationProvider;

public class SentryConfigProvider implements ConfigurationProvider {

    private final SentryConfig config;

    SentryConfigProvider(SentryConfig config) {
        this.config = config;
    }

    @Override
    public String getProperty(String key) {
        switch (key) {
            case DefaultSentryClientFactory.IN_APP_FRAMES_OPTION:
                return config.inAppPackages.map(p -> join(",", p)).orElse("");
            case DefaultSentryClientFactory.ENVIRONMENT_OPTION:
                return config.environment.orElse("");
            case DefaultSentryClientFactory.RELEASE_OPTION:
                return config.release.orElse("");
            // New SentryConfig options should be mapped here
            default:
                return null;
        }
    }
}
