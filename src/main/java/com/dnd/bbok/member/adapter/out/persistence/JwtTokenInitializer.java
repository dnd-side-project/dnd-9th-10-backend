package com.dnd.bbok.member.adapter.out.persistence;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenInitializer {

  //  jwt를 생성해주는 secretKey
  private static String secretKey = "MFswDQYJKoZIhvcNAQEBBQADSgAwRwJAaJd167MUifo3ZSytpDPd9z7oSS+1KXjf\n"
      + "HJ3oMvI61Id26fdQHgWE1QMLcrhOmRzTxkCU+gesx5ANkSSIrPHNswIDAQAB";

  //accessToken 만료시간
  public static final Long ACCESS_TOKEN_EXPIRE_LENGTH_MS = 1000L * 60 * 60 * 24; //1일

  //refreshToken 만료시간
  public static final Long REFRESH_TOKEN_EXPIRE_LENGTH_MS = 1000L * 60 * 60 * 24 * 14; //2주

  private Key key;

  public JwtTokenInitializer() {
    initialize();
  }

  @PostConstruct
  protected void initialize() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    key = Keys.hmacShaKeyFor(keyBytes);
  }

  Key getKey() {
    return key;
  }

  Long getExpireAccess() {
    return ACCESS_TOKEN_EXPIRE_LENGTH_MS;
  }

  Long getExpireRefresh() {
    return REFRESH_TOKEN_EXPIRE_LENGTH_MS;
  }

}
