package com.tangv.feature.config;

import org.springframework.http.MediaType;

public class CustomMediaType extends MediaType {
    public CustomMediaType(String type, String subtype) {
        super(type, subtype);
    }

    /**
     * Public constant media type for {@code application/underscore}.
     * @see #APPLICATION_UNDERSCORE_UTF8
     */
    public final static MediaType APPLICATION_UNDERSCORE;

    /**
     * Public constant media type for {@code application/underscore;charset=UTF-8}.
     */
    public final static MediaType APPLICATION_UNDERSCORE_UTF8;

    /**
     * A String equivalent of {@link CustomMediaType#APPLICATION_UNDERSCORE}.
     * @see #APPLICATION_UNDERSCORE_UTF8_VALUE
     */
    public final static String APPLICATION_UNDERSCORE_VALUE = "application/underscore";

    /**
     * A String equivalent of {@link CustomMediaType#APPLICATION_UNDERSCORE_UTF8}.
     * @see #APPLICATION_UNDERSCORE_VALUE
     */
    public final static String APPLICATION_UNDERSCORE_UTF8_VALUE = "application/underscore;charset=UTF-8";

    static {
        APPLICATION_UNDERSCORE = valueOf(APPLICATION_UNDERSCORE_VALUE);
        APPLICATION_UNDERSCORE_UTF8 = valueOf(APPLICATION_UNDERSCORE_UTF8_VALUE);
    }
}

