package com.example.bulletinBoard.controller;
import com.example.bulletinBoard.dto.BulletinForm;
import com.example.bulletinBoard.entity.Bulletin;
import com.example.bulletinBoard.service.BulletinService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 布告欄控制器。
 * <p>
 * 作用：
 * 1. 接收頁面請求。
 * 2. 呼叫 Service 處理公告資料。
 * 3. 將資料交給 Thymeleaf 畫面。
 */
@Controller
@RequestMapping("/bulletins")
public class BulletinController {
    /**
     * 布告欄服務物件。
     */
    private final BulletinService bulletinService;

    /**
     * 建構式。
     *
     * @param bulletinService 布告欄服務物件。
     * @return 無回傳值。
     */
    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    /**
     * 顯示公告列表頁。
     *
     * @param page 頁碼，從 1 開始。
     * @param model 畫面模型。
     * @return 頁面名稱。
     */
    @GetMapping
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Bulletin> bulletinList = bulletinService.findPage(page);
        int totalPages = bulletinService.getTotalPages();

        model.addAttribute("bulletinList", bulletinList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "bulletin-list";
    }

    /**
     * 顯示新增頁。
     *
     * @param model 畫面模型。
     * @return 頁面名稱。
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("bulletinForm", new BulletinForm());
        model.addAttribute("formAction", "/bulletins");
        model.addAttribute("pageTitle", "新增公布訊息");
        return "bulletin-form";
    }

    /**
     * 執行新增。
     *
     * @param bulletinForm 表單資料。
     * @param bindingResult 驗證結果。
     * @param model 畫面模型。
     * @return 成功導回列表頁，失敗返回表單頁。
     */
    @PostMapping
    public String create(@Valid @ModelAttribute("bulletinForm") BulletinForm bulletinForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/bulletins");
            model.addAttribute("pageTitle", "新增公布訊息");
            return "bulletin-form";
        }

        try {
            bulletinService.create(bulletinForm);
            return "redirect:/bulletins";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("formAction", "/bulletins");
            model.addAttribute("pageTitle", "新增公布訊息");
            model.addAttribute("dateError", ex.getMessage());
            return "bulletin-form";
        }
    }

    /**
     * 顯示修改頁。
     *
     * @param id 公告 ID。
     * @param model 畫面模型。
     * @return 頁面名稱。
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Bulletin bulletin = bulletinService.getById(id);
        BulletinForm form = bulletinService.toForm(bulletin);

        model.addAttribute("bulletinForm", form);
        model.addAttribute("formAction", "/bulletins/" + id);
        model.addAttribute("pageTitle", "修改公布訊息");
        return "bulletin-form";
    }

    /**
     * 執行修改。
     *
     * @param id 公告 ID。
     * @param bulletinForm 表單資料。
     * @param bindingResult 驗證結果。
     * @param model 畫面模型。
     * @return 成功導回列表頁，失敗返回表單頁。
     */
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("bulletinForm") BulletinForm bulletinForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/bulletins/" + id);
            model.addAttribute("pageTitle", "修改公布訊息");
            return "bulletin-form";
        }

        try {
            bulletinService.update(id, bulletinForm);
            return "redirect:/bulletins";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("formAction", "/bulletins/" + id);
            model.addAttribute("pageTitle", "修改公布訊息");
            model.addAttribute("dateError", ex.getMessage());
            return "bulletin-form";
        }
    }

    /**
     * 執行刪除。
     *
     * @param id 公告 ID。
     * @return 導回列表頁。
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        bulletinService.delete(id);
        return "redirect:/bulletins";
    }
}
