package com.example.config.oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.config.auth.PrincipalDetails;
import com.example.config.oauth.provider.*;
import com.example.entity.Member;
import com.example.entity.SocialUser;
import com.example.repository.MemberRepository;
import com.example.repository.SocialUserRepository;
import java.util.Map;
import java.util.Optional;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private MemberRepository memberRepository;



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("userRequest :" + userRequest.getClientRegistration());          //registrationId로 어떤 Oauth로 로그인 했는지 확인 가능
        System.out.println("userRequest :" + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("userRequest :" + oAuth2User.getAttributes());


        OAuth2UserInfo oAuth2UserInfo = null;
//        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
//            System.out.println("구글 로그인 요청");
//
//            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
//
//        }else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
//            System.out.println("페이스북 로그인 요청");
//
//            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
//
//        }else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
//            System.out.println("네이버 로그인 요청");
//
//            oAuth2UserInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
//
//        }else
        if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            System.out.println("카카오 로그인 요청");

            oAuth2UserInfo = new KakaoUserInfo((Map) oAuth2User.getAttributes());

        }else{
            System.out.println("카카오로그인만 지원");
        }


        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String email = oAuth2UserInfo.getEmail();

        SocialUser userEntity = socialUserRepository.findByMembername(username);

        //동일한 이메일을 가진 Member가 있는지 확인
        if(userEntity == null){

            Optional<Member> exinstingMember = memberRepository.findByEmail(email);
            if (exinstingMember == null) {
                //새 Member 엔티티 생성
                Member newMember = new Member();
                newMember.setEmail(email);


                //새로운 Member 저장
                memberRepository.save(newMember);
            } else {
                System.out.println("동일한 이메일로 이미 가입한 회원이 있습니다.");
            }

            System.out.println("oauth 로그인 최초");
            userEntity = SocialUser.builder()
                    .membername(username)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            socialUserRepository.save(userEntity);

        }else{
            System.out.println("이미 가입된 아이디");
        }

        //회원 가입을 강제로 진행해볼 예정
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }

}
