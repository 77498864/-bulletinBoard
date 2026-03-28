package com.example.bulletinBoard.service;
import com.example.bulletinBoard.dto.BulletinForm;
import com.example.bulletinBoard.entity.Bulletin;
import com.example.bulletinBoard.repository.BulletinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 布告欄服務類別。
 * 處理公告的操作邏輯
 */
@Service
public class BulletinService {
    /**
     * 每頁顯示筆數。
     */
    private static final int PAGE_SIZE = 5;

    /**
     * 布告欄資料存取物件。
     */
    private final BulletinRepository bulletinRepository;

    /**
     * 共用Service
     * */
    private ShareService shareService;

    /**
     * 建構式。
     *
     * @param bulletinRepository 布告欄資料存取物件。
     * @return 無回傳值。
     */
    public BulletinService(BulletinRepository bulletinRepository,  ShareService shareService) {
        this.bulletinRepository = bulletinRepository;
        this.shareService = shareService;
    }

    /**
     * 查詢指定頁數的公告列表。
     *
     * @param page 頁碼，從 1 開始。
     * @return 公告列表。
     */
    @Transactional(readOnly = true)
    public List<Bulletin> findPage(int page) {
        int safePage = Math.max(page, 1);
        int offset = (safePage - 1) * PAGE_SIZE;
        return bulletinRepository.findPage(offset, PAGE_SIZE);
    }

    /**
     * 取得總頁數。
     * @return 總頁數。
     */
    @Transactional(readOnly = true)
    public int getTotalPages() {
        long totalCount = bulletinRepository.count();
        return (int) Math.ceil((double) totalCount / PAGE_SIZE);
    }

    /**
     * 依主鍵查詢單筆公告。
     *
     * @param id 公告 ID。
     * @return 單筆公告資料。
     */
    @Transactional(readOnly = true)
    public Bulletin getById(Long id) {
        Bulletin bulletin = bulletinRepository.findById(id);
        if (bulletin == null) {
            throw new EntityNotFoundException("找不到公告資料，ID：" + id);
        }
        return bulletin;
    }

    /**
     * 新增公告資料。
     *
     * @param form 表單資料。
     * @return 無回傳值。
     */
    @Transactional
    public void create(BulletinForm form) {
        BulletinForm encodeForm = shareService.encodeForm(form);
        validateDateRange(encodeForm);

        Bulletin bulletin = new Bulletin();
        bulletin.setTitle(encodeForm.getTitle());
        bulletin.setPublisher(encodeForm.getPublisher());
        bulletin.setPublishDate(encodeForm.getPublishDate());
        bulletin.setExpireDate(encodeForm.getExpireDate());
        bulletin.setContent(encodeForm.getContent());

        bulletinRepository.save(bulletin);
    }

    /**
     * 修改公告資料。
     *
     * @param id 公告 ID。
     * @param form 表單資料。
     * @return 無回傳值。
     */
    @Transactional
    public void update(Long id, BulletinForm form) {
        BulletinForm encodeForm = shareService.encodeForm(form);
        validateDateRange(encodeForm);

        Bulletin bulletin = getById(id);
        bulletin.setTitle(encodeForm.getTitle());
        bulletin.setPublisher(encodeForm.getPublisher());
        bulletin.setPublishDate(encodeForm.getPublishDate());
        bulletin.setExpireDate(encodeForm.getExpireDate());
        bulletin.setContent(encodeForm.getContent());

        bulletinRepository.update(bulletin);
    }

    /**
     * 刪除公告資料。
     *
     * @param id 公告 ID。
     * @return 無回傳值。
     */
    @Transactional
    public void delete(Long id) {
        Bulletin bulletin = getById(id);
        bulletinRepository.delete(bulletin);
    }

    /**
     * 將實體物件轉為表單物件。
     *
     * @param bulletin 公告實體物件。
     * @return 表單物件。
     */
    @Transactional(readOnly = true)
    public BulletinForm toForm(Bulletin bulletin) {
        BulletinForm form = new BulletinForm();
        BeanUtils.copyProperties(bulletin, form);
        return form;
    }

    /**
     * 驗證截止日期不得早於發佈日期。
     *
     * @param form 表單資料。
     * @return 無回傳值。
     */
    private void validateDateRange(BulletinForm form) {
        if (form.getExpireDate().isBefore(form.getPublishDate())) {
            throw new IllegalArgumentException("截止日期不可早於發佈日期");
        }
    }
}
