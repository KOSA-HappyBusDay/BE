//package com.example.controller;
//
//import com.example.entity.Member;
//import com.example.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins="http://localhost:8761")
//@RestController
//@RequestMapping("/account")
//public class MemberController {
//    private final MemberService memberService;
//
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<Member> create(@RequestBody Member member) {
//        return ResponseEntity.ok().
//                body(memberService.create(member));
//    }
//
//
//    @GetMapping("/member/{id}")
//    public ResponseEntity<Member> read(@PathVariable Long id) {
//        return ResponseEntity.ok()
//                .body(memberService.read(id).get());
//    }
//
//    @PutMapping("/member/{id}")
//    public ResponseEntity<Member> read(@RequestParam Long id, @RequestParam String address) {
//        return ResponseEntity.ok().body(memberService.update(id,address));
//    }
//
//    @DeleteMapping("/member/{id}")
//    public void delete(@PathVariable Long id) {
//        memberService.delete(id);
//    }
//}
//
