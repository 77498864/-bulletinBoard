/**
 * 布告欄前端腳本。
 * 作用：
 * 1. 刪除前顯示確認訊息。
 */
$(document).ready(function () {
    /**
     * 綁定刪除確認事件。
     */
    $('.delete-form').on('submit', function () {
        return confirm('確定要刪除此公布項目嗎？');
    });
});