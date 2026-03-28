專案正式版發布流程:
1.application-properties裡面 active選項改為prod
2.開啟cmd 切到專案目錄 執行.\mvnw.cmd clean package -DskipTests 執行完畢後會產生bulletinBoard-0.0.1-SNAPSHOT檔案
3.先執行scp target/bulletinBoard-0.0.1-SNAPSHOT.jar opc@你的IP:~
4.再由ssh連線:ssh opc@你的IP
5.再執行sudo mv ~/bulletinBoard-0.0.1-SNAPSHOT.jar /opt/bulletinBoard/
6.執行sudo chown -R opc:opc /opt/bulletinBoard
7.執行nohup java -jar bulletinBoard-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > app.log 2>&1 &
8.vm安全規則設定 開啟0.0.0.0/0 TCP 8000port
9.vm內執行:
sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --reload
sudo firewall-cmd --permanent --add-port=8000/tcp
sudo firewall-cmd --reload
開啟8000 port與http防火牆設定
10.再來是nginx設定:
先執行grep -n "conf.d" /etc/nginx/nginx.conf確認有沒有include /etc/nginx/conf.d/*.conf;
接著在/etc/nginx/conf.d目錄底下建立mainNginx.conf:
sudo vi mainNginx.conf
寫下:
server {
    listen 80 default_server;
    server_name _;

   location = /nginx-test {
        return 200 "nginx config loaded";
   }

    location = /bulletin {
        return 302 /bulletin/;
    }

    location /bulletin/ {
        proxy_pass http://127.0.0.1:8000;

        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
再來 執行sudo vi /etc/nginx/nginx.conf 觀察
    server {
        listen       80;
這一段是否有 default_server 有的話拿掉

接著避免SELinux 擋住 Nginx連上游 所以要先看getenforce 若結果為Enforcing 接著看sudo tail -n 50 /var/log/nginx/error.log 看有沒有connect() to 127.0.0.1:8000 failed (13: Permission denied) while connecting to upstream
若有 則執行
sudo setsebool -P httpd_can_network_connect 1
接著
sudo nginx -t
sudo systemctl reload nginx