package com.chats.config;

public class JwtConstant {

    private final static String JWT_HEADER = "Authorization";
    private final static String SECRET_KEY = "slfknlnglgngdlktdnajbsfjdbvskrjvjebsbvkrkjrvb";

    public static String getJwtHeader() {
        return JWT_HEADER;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

}
