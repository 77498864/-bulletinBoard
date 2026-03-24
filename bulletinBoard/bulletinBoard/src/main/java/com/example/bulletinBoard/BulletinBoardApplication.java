package com.example.bulletinBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 專案啟動類別。
 * <p>
 * 作用：
 * 1. 啟動 Spring Boot 應用程式。
 * 2. 掃描 Controller、Service、Repository、Entity。
 */
@SpringBootApplication
public class BulletinBoardApplication {

    /**
     * 程式進入點。
     *
     * @param args 啟動參數。
     * @return 無回傳值。
     */
	public static void main(String[] args) {
		SpringApplication.run(BulletinBoardApplication.class, args);
	}

}
