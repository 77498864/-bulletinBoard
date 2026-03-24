package com.example.bulletinBoard.repository;
import com.example.bulletinBoard.entity.Bulletin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 布告欄資料存取類別。
 * <p>
 * 作用：
 * 1. 使用 EntityManager 操作資料庫。
 * 2. 提供公告資料的新增、查詢、修改、刪除。
 */
@Repository
public class BulletinRepository {
    /**
     * JPA / Hibernate 實體管理器。
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 新增公告資料。
     *
     * @param bulletin 公告實體物件。
     * @return 無回傳值。
     */
    public void save(Bulletin bulletin) {
        entityManager.persist(bulletin);
    }

    /**
     * 修改公告資料。
     *
     * @param bulletin 公告實體物件。
     * @return 更新後的公告實體物件。
     */
    public Bulletin update(Bulletin bulletin) {
        return entityManager.merge(bulletin);
    }

    /**
     * 依主鍵查詢單筆公告。
     *
     * @param id 公告 ID。
     * @return 單筆公告資料；若查無則回傳 null。
     */
    public Bulletin findById(Long id) {
        return entityManager.find(Bulletin.class, id);
    }

    /**
     * 查詢分頁資料。
     *
     * @param offset 起始筆數。
     * @param pageSize 每頁筆數。
     * @return 公告列表。
     */
    public List<Bulletin> findPage(int offset, int pageSize) {
        String jpql = "SELECT b FROM Bulletin b ORDER BY b.publishDate DESC, b.id DESC";
        return entityManager.createQuery(jpql, Bulletin.class)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }

    /**
     * 查詢總筆數。
     * @return 總筆數。
     */
    public long count() {
        String jpql = "SELECT COUNT(b) FROM Bulletin b";
        return entityManager.createQuery(jpql, Long.class).getSingleResult();
    }

    /**
     * 刪除公告資料。
     *
     * @param bulletin 公告實體物件。
     * @return 無回傳值。
     */
    public void delete(Bulletin bulletin) {
        entityManager.remove(bulletin);
    }
}
