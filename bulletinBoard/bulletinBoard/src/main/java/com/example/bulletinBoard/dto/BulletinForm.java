package com.example.bulletinBoard.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 布告欄表單資料物件。
 * <p>
 * 作用：
 * 1. 接收新增與修改頁面的輸入資料。
 * 2. 提供欄位驗證。
 */
public class BulletinForm {
    /**
     * 公告標題。
     */
    @NotBlank(message = "標題不可為空")
    @Size(max = 200, message = "標題長度不可超過 200 字")
    private String title;

    /**
     * 公布者。
     */
    @NotBlank(message = "公布者不可為空")
    @Size(max = 100, message = "公布者長度不可超過 100 字")
    private String publisher;

    /**
     * 發佈日期。
     */
    @NotNull(message = "發佈日期不可為空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    /**
     * 截止日期。
     */
    @NotNull(message = "截止日期不可為空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;

    /**
     * 公告內容。
     */
    @NotBlank(message = "公布內容不可為空")
    private String content;

    /**
     * 取得公告標題。
     * @return 公告標題。
     */
    public String getTitle() {
        return title;
    }

    /**
     * 設定公告標題。
     *
     * @param title 公告標題。
     * @return 無回傳值。
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 取得公布者。
     * @return 公布者。
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 設定公布者。
     *
     * @param publisher 公布者。
     * @return 無回傳值。
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 取得發佈日期。
     * @return 發佈日期。
     */
    public LocalDate getPublishDate() {
        return publishDate;
    }

    /**
     * 設定發佈日期。
     *
     * @param publishDate 發佈日期。
     * @return 無回傳值。
     */
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 取得截止日期。
     * @return 截止日期。
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * 設定截止日期。
     *
     * @param expireDate 截止日期。
     * @return 無回傳值。
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 取得公告內容。
     * @return 公告內容。
     */
    public String getContent() {
        return content;
    }

    /**
     * 設定公告內容。
     *
     * @param content 公告內容。
     * @return 無回傳值。
     */
    public void setContent(String content) {
        this.content = content;
    }
}
