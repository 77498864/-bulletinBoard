package com.example.bulletinBoard.service;


import com.example.bulletinBoard.dto.BulletinForm;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

/*
* 共用服務類別
* */
@Service
public class ShareService {

    /**
    * 建構式。
    *
    * 這裡暫時沒有需要注入的Service
    * */
    public ShareService(){
    }

    /**
     * 對表單中的文字欄位做 HTML Encode。
     * <p>
     * 作用：
     * 1. 將 <、>、"、'、& 等特殊字元轉成 HTML Entity。
     * 2. 降低使用者輸入惡意 HTML/Script 時造成 XSS 的風險。
     *
     * @param form 原始表單資料。
     * @return Encode 後的新表單資料。
     */
    public BulletinForm encodeForm(BulletinForm form) {
        BulletinForm encodedForm = new BulletinForm();
        encodedForm.setTitle(encode(form.getTitle()));
        encodedForm.setPublisher(encode(form.getPublisher()));
        encodedForm.setPublishDate(form.getPublishDate());
        encodedForm.setExpireDate(form.getExpireDate());
        encodedForm.setContent(encode(form.getContent()));
        return encodedForm;
    }

    /**
     * 對單一字串做 HTML Encode。
     *
     * @param value 原始字串。
     * @return Encode 後字串；若原值為 null，則回傳 null。
     */
    public String encode(String value) {
        if (value == null) {
            return null;
        }
        return HtmlUtils.htmlEscape(value);
    }
}
