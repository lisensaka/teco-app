package com.taco.security.jwtFilter;

public class JwtProperties {
    // kjo eshte Secret qe do perdoret nga e cila Tokeni do te behet hash(encryptohet)
    public static final String SECRET = "Atis123";

    public static final int EXPIRATION_TIME = 8640000;
    // prefixi i tokenit, pasi te gjenerohet ne na duhet qe ta dergojme tokenin mbrapa me ane te header te autorizimit dhe ka prefixin Barer
    public static final String TOKEN_PREFIX = "Bearer ";
    // kjo do jet headeri qe do dergojme tokenin Bearer
    public static final String HEADER_STRING = "Authorization";
}
