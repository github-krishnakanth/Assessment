package info.influx.Nearpod.service.impl.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import info.influx.Nearpod.model.user.TokenPayLoadInfo;
import info.influx.Nearpod.model.user.UserTokenSession;
import info.influx.Nearpod.repository.UserRepository;
import info.influx.Nearpod.repository.UserTokenSessionRepository;

@Service
public class UserTokenSessionServiceImpl {
	
	private static final Logger LOGGER = Logger.getLogger(UserTokenSessionServiceImpl.class);

    @Value("${config.oauth2.tokenTimeout}")
    private long tokenExpiryTime;

    @Autowired
    private UserTokenSessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    private BiPredicate<TokenPayLoadInfo, UserTokenSession> isTokenIdNotSame = (tPayloadInfo, uTokenSession) -> !tPayloadInfo.getTokenId().equals(uTokenSession.getToken());

    private Predicate<UserTokenSession> isTokenTimeExpired =
            userSession -> {
                long currentTimeInMillis = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                ZonedDateTime dataBaseZonedDateTime = userSession.getCreatedTime().atZone(ZoneId.systemDefault());
                long tokenTimeInMillis = dataBaseZonedDateTime.toInstant().toEpochMilli() + (userSession.getExpiryTime() * 1000);

                return currentTimeInMillis >= tokenTimeInMillis;
            };

    public TokenPayLoadInfo saveUserTokenSessionMapping(final TokenPayLoadInfo tokenPayload) {

        return userRepository.findOneByUsername(tokenPayload.getUsername())
                .map(user -> validateTokenandDeleteIfnotValid(tokenPayload))
                .filter(tokenPayloadInfo -> !tokenPayloadInfo.getValid() && !tokenPayloadInfo.getMessage().equalsIgnoreCase("User Doesnot Exists"))
                .map(tokenPayloadInfo -> saveTokenSessionandReturnTokenPayload().apply(tokenPayload))
                .orElse(TokenPayLoadInfo.builder().with(TokenPayLoadInfo::getMessage, "UserId and Password Doesnot Matches").with(TokenPayLoadInfo::getValid, false).build());

    }

    private Function<TokenPayLoadInfo, TokenPayLoadInfo> saveTokenSessionandReturnTokenPayload() {
        return tokenPayload -> {
            UserTokenSession userTokenSession = sessionRepository.save(new UserTokenSession(tokenPayload.getUsername(), generateToken(), "Sessionid-" + generateToken(), tokenExpiryTime));
            System.out.println(userTokenSession);
            return TokenPayLoadInfo.builder().on(TokenPayLoadInfo::getUsername).set(userTokenSession.getUsername())
                    .on(TokenPayLoadInfo::getTokenId).set(userTokenSession.getToken())
                    .on(TokenPayLoadInfo::getValid).set(true)
                    .on(TokenPayLoadInfo::getMessage).set("Token is Valid").build();
        };
    }

    public TokenPayLoadInfo validateTokenandDeleteIfnotValid(final TokenPayLoadInfo tokenPayload) {
        return sessionRepository.findOneByUsername(tokenPayload.getUsername())
                .filter(us -> isTokenIdNotSame.test(tokenPayload, us) && isTokenTimeExpired.test(us))
                .map(uts -> deleteExpiredTokenThenReturnStatus().apply(uts))
                .orElseGet(() -> isValidTokenOrFindUserExists().apply(tokenPayload));
    }

    private Function<TokenPayLoadInfo, TokenPayLoadInfo> isValidTokenOrFindUserExists() {
        return tokenPayload -> sessionRepository.findOneByUsername(tokenPayload.getUsername())
                .filter(us -> !isTokenIdNotSame.test(tokenPayload, us) && !isTokenTimeExpired.test(us))
                .map(us -> TokenPayLoadInfo.builder().with(TokenPayLoadInfo::getMessage, "Token is Valid").with(TokenPayLoadInfo::getValid, true).build())
                .orElseGet(() -> findUserExists().apply(tokenPayload));
    }

    private Function<TokenPayLoadInfo, TokenPayLoadInfo> findUserExists() {
        return tokenPayload -> userRepository.findOneByUsername(tokenPayload.getUsername())
                .map(user -> TokenPayLoadInfo.builder().with(TokenPayLoadInfo::getMessage, "Token is Not Valid Expired").with(TokenPayLoadInfo::getValid, false).build())
                .orElseGet(() -> TokenPayLoadInfo.builder().with(TokenPayLoadInfo::getMessage, "User Doesnot Exists").with(TokenPayLoadInfo::getValid, false).build());
    }

    private Function<UserTokenSession, TokenPayLoadInfo> deleteExpiredTokenThenReturnStatus() {
        return userTokenSession -> {
            sessionRepository.delete(userTokenSession);
            return TokenPayLoadInfo.builder().on(TokenPayLoadInfo::getMessage).set("Token is Not Valid Expired")
                    .on(TokenPayLoadInfo::getValid).set(false).build();
        };
    }

    private String generateToken() {
        int lowerLimit = 97;
        int upperLimit = 122;
        Random random = new Random();
        StringBuffer r = new StringBuffer(10);
        for (int i = 0; i < 8; i++) {
            int nextRandomChar = lowerLimit
                    + (int) (random.nextFloat()
                    * (upperLimit - lowerLimit + 1));
            r.append((char) nextRandomChar);
        }
        return r.toString();
    }
}
