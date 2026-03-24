package com.example.bulletinBoard.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 布告欄實體類別。
 * <p>
 * 作用：
 * 1. 對應資料表 bulletin。
 * 2. 儲存單筆公告資料。
 */
@Entity
@Table(name = "bulletin")
public class Bulletin {
    /**
     * 主鍵 ID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 公告標題。
     */
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    /**
     * 公布者。
     */
    @Column(name = "publisher", nullable = false, length = 100)
    private String publisher;

    /**
     * 發佈日期。
     */
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    /**
     * 截止日期。
     */
    @Column(name = "expire_date", nullable = false)
    private LocalDate expireDate;

    /**
     * 公告內容。
     */
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * 建立時間。
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 更新時間。
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 新增前自動設定建立時間與更新時間。
     * @return 無回傳值。
     */
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    /**
     * 更新前自動設定更新時間。
     * @return 無回傳值。
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 取得主鍵 ID。
     * @return 主鍵 ID。
     */
    public Long getId() {
        return id;
    }

    /**
     * 設定主鍵 ID。
     *
     * @param id 主鍵 ID。
     * @return 無回傳值。
     */
    public void setId(Long id) {
        this.id = id;
    }

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

    /**
     * 取得建立時間。
     * @return 建立時間。
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 設定建立時間。
     *
     * @param createdAt 建立時間。
     * @return 無回傳值。
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 取得更新時間。
     * @return 更新時間。
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 設定更新時間。
     *
     * @param updatedAt 更新時間。
     * @return 無回傳值。
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
