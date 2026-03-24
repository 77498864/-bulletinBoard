package com.example.bulletinBoard.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首頁控制器。
 * <p>
 * 作用：
 * 1. 將根路徑導向公告列表頁。
 */
@Controller
public class HomeController {
    /**
     * 首頁導向方法。
     * @return 導向到公告列表頁。
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/bulletins";
    }
}
