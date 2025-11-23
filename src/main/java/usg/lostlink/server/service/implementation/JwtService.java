package usg.lostlink.server.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final Key key =
      Keys.hmacShaKeyFor(
          "nWrWSruKB97A1PmVTgjSauHnbuUtVT202rvsYl310kHe5jo59H1ZnV4ZU5sJL9UIo6KFfXMxqw8Ix6d4FqyRZ1n3VWmmpkW4ZhGp".getBytes());

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 10000L * 60 * 60 * 24 * 30))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims =
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    return claimsResolver.apply(claims);
  }
}
