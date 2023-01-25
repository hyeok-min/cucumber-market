package UsedMarket.demo.springsecurity;

import UsedMarket.demo.domain.Member;
import UsedMarket.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginOauth2Service extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder getpasswordEncoder=new BCryptPasswordEncoder();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = oauth2User.getAttribute("sub");
        String username = provider + "_" + providerId;

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = getpasswordEncoder.encode("비밀번호암호화" + uuid);
        String email = oauth2User.getAttribute("email");
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            member = Member.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .oauthCode(1l)
                    .nickname(username)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberRepository.save(member);
        }
        return new LoginSecurityMember(member, oauth2User.getAttributes());
    }
}
