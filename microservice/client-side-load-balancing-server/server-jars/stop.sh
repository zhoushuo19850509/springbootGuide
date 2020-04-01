kill -9 $(ps -ef|grep client-side-load-balancing-server-8090|grep -v grep|awk '{print $2}')
kill -9 $(ps -ef|grep client-side-load-balancing-server-9092|grep -v grep|awk '{print $2}')
kill -9 $(ps -ef|grep client-side-load-balancing-server-9999|grep -v grep|awk '{print $2}')
