package iptv.demo;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.List;

public class TestCSV {
    private ColumnPositionMappingStrategy<DemoEvent> strat;
    private CsvToBean<DemoEvent> csv;
        private String  line = "2014-11-06 15:50:53:610 075500715758    http://183.59.161.135:8082/EPG/jsp/defaulthdcctv/en/Category.jsp?directplay=0&lastchannelNo=null&isComeFromPredeal=1&joinFlag=0 http://183.59.161.135:8082/EPG/jsp/PreDealHWCTC.jsp?directPlay=0&lastChannelNo=null&FIRSTPAGE=defaulthdcctv/en/Category.jsp 635 null    null    null    null    null";      

    public TestCSV() {
        super();
        strat = new ColumnPositionMappingStrategy<>();
        strat.setType(DemoEvent.class);
        strat.setColumnMapping("trackTime", "userId", "targetURL", "referURL", "pageId");
        csv = new CsvToBean<>();
    }

    public static void main(String[] args) {
        TestCSV testCSV = new TestCSV();
        testCSV.testReader();
        testCSV.testParse();
    }
    
    public void testParse(){
        List<DemoEvent> list;
        try {
            list = csv.parse(strat, new CSVReader(new FileReader("r:\\user_iptv.txt"), '\t'));
            int i = 0;
            while(i < list.size()){
                DemoEvent ute = list.get(i);
                i ++;
                System.out.println("Line: "+i+" -> "+ute.getPageId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testReader() {
        try {
            CSVReader csv = new CSVReader(new FileReader("r:\\user_iptv.txt"), '\t');
            String [] props;
            props = csv.readNext();
            System.out.println(props.length);
            System.out.println(props[0]+", "+props[1]+", "+props[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
