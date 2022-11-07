package Example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import lombok.extern.log4j.Log4j;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hi Admin");
        return "hello";
    }

    /* MVC 방식 */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
    @ResponseBody 를 사용한 API 방식

        1. 웹 브라우저에서 요청이 들어오면 내장 톰켓 서버가 스프링 컨테이너로 넘김
        2. 매핑된 함수가 실행될 때 @ResponseBody를 만나면 함수의 리턴 값이
           HttpMessageConverter 에 의해 다음 중 하나로 변환되어 HTTP 응답 바디에 실림

            - StringConverter(문자열) : 그대로 HTTP 응답 바디에 실어서 넘김
            - JsonConverter(객체)     : JSON(Default)으로 변환해서 넘김
     */

    /* API 방식: viewResolver를 거치지 않고 다이렉트로 응답 */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam String name) {
        return name;
    }

    /* API 방식으로 객체 넘기기 => JSON 방식으로 넘겨짐 */
    @GetMapping("hello-api")
    @ResponseBody
    public Seungsu helloApi(@RequestParam String name, @RequestParam int age) {
        Seungsu seungsu = new Seungsu();
        seungsu.setName(name);
        seungsu.setAge(age);

        return seungsu;
    }

    static class Seungsu {
        private String name;
        private int age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }

    /*
    @GetMapping("/doA")
    public void doA() {
        log.info("doA call");
        log.info("--------------------");
    }

    */
}
