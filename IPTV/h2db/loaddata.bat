java -cp d:\DevTools\h2\bin\h2-1.3.176.jar org.h2.tools.RunScript -url "jdbc:h2:tcp://10.10.10.10:9999/mem:iptv" -user sa -password sa -script load_mulu.sql -showResults
java -cp d:\DevTools\h2\bin\h2-1.3.176.jar org.h2.tools.RunScript -url "jdbc:h2:tcp://10.10.10.10:9999/mem:iptv" -user sa -password sa -script load_area.sql -showResults
