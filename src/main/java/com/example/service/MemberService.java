//package com.example.service;
//
//import com.example.entity.Authority;
//import com.example.entity.Member;
//import com.example.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//@Service
//public class MemberService {
//    private MemberRepository memberRepository;
//
//    @Autowired
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//    public Member create(Member member) {
//        return memberRepository.save(member); // memberRepository.save(member)로 수정
//    }
//
//    public Optional<Member> read(Long id) {
//        return memberRepository.findById(id); // memberRepository.findById(id)로 수정
//    }
//
//    public Member update(Long id, String address){
//        Optional<Member> optionalMember = read(id);
//        if (optionalMember.isPresent()) {
//            Member member = optionalMember.get();
////            member.setAddress(address); // member.setAddress(address)로 수정
//            return memberRepository.save(member); // memberRepository.save(member)로 수정
//        }
//        return null; // 처리 실패 시 null을 반환하거나 예외를 던지는 것이 좋습니다.
//    }
//
//    public void delete(Long id){
//        memberRepository.deleteById(id); // memberRepository.deleteById(id)로 수정
//    }
//}
////@Service
////public class MemberService {
////    private final MemberRepository memberRepository;
////    private final AuthorityService authorityService;
////
////    public MemberService(MemberRepository memberRepository, AuthorityService authorityService) {
////        this.memberRepository = memberRepository;
////        this.authorityService = authorityService;
////    }
////
////    @Transactional
////    public Member createMemberWithAuthorities(Member member, List<String> authorityNames) {
////        List<Authority> authorities = authorityNames.stream()
////                .map(authorityName -> authorityService.createOrGetAuthorityByName(authorityName))
////                .collect(Collectors.toList());
////
////        member.setAuthorities(authorities);
////
////        return memberRepository.save(member);
////    }
////}
