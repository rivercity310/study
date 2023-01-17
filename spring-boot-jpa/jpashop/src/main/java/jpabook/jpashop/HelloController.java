package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {      /* Model: 데이터를 뷰에 넘길 때 사용 */
        model.addAttribute("data", "hi");
        return "hello";     /* 관례: 리턴은 화면 이름 -> resources/templates/hello.html, 렌더링 필요없는 정적인 페이지 -> static */
    }
}
