package UsedMarket.demo.controller;

import UsedMarket.demo.service.BoardService;
import UsedMarket.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;
    private final BoardService boardService;
    @GetMapping("/home")
    public String login(Principal principal, Model model) {
        model.addAttribute("member", memberService.loadMemberByNickname(principal)); //로그인사용자 표시
        model.addAttribute("topBoard",boardService.findTopBoard());
        return "home";
    }

    @GetMapping("/oauth-home")
    public String oauthIdentify(Principal principal, Model model) {
        model.addAttribute("member", memberService.loadMemberByNickname(principal)); //로그인사용자 표시

        if (memberService.findMemberByEmail(principal).getOauthCode() == 1) {
            model.addAttribute("message", "처음으로 구글로그인하셨습니다. 개인정보를 변경해주세요.");
            model.addAttribute("searchUrl", "/member/edit");
            return "message";}
        return "home"; }}