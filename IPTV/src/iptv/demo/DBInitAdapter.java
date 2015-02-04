package iptv.demo;

import com.bea.wlevs.ede.api.StreamSender;
import com.bea.wlevs.ede.api.StreamSource;

import java.sql.SQLException;

import org.h2.tools.Server;

public class DBInitAdapter implements StreamSource {
    public DBInitAdapter() {
        super();
    }

    @Override
    public void setEventSender(StreamSender streamSender) {
        String  tcpport = "9999";
        String  tcppwd = "tcppwd";
        try {
            Server.shutdownTcpServer("tcp://localhost:" + tcpport, tcppwd, true, true);
        } catch (SQLException e) {
            log("There is no existing servers to shutdown!");
        }
        try {
            Server tcp = Server.createTcpServer("-tcpPort", tcpport, "-tcpAllowOthers", "-tcpPassword", tcppwd).start();
            log("Start the H2 Tcp Server at port["+tcpport+"] successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void log(String msg){
        System.out.println("***** "+this.toString()+"->"+msg);
    }
}
