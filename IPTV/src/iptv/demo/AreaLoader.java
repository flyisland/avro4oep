package iptv.demo;

import au.com.bytecode.opencsv.CSVReader;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

public class AreaLoader {
    public AreaLoader() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {       
        if(args.length==2){
            AreaLoader cacheLoader = new AreaLoader();
            String datafile=args[0];
            String cohfile=args[1];
            cacheLoader.load(datafile, cohfile);
        }else
        {
            System.out.println("arg usage: java iptv.demo.Loader data_filename coherence-config-filename ");
        }
    }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    private void load(String datafileName, String cohfileName) throws FileNotFoundException, IOException {
        System.out.println("==> checking files....");
        File f= new File(datafileName);
        if(!f.exists()) {
            System.out.println(datafileName+"doesnot exits");
            return;
        }
        
        f= new File(cohfileName);
        if(!f.exists()){
            System.out.println(cohfileName+"doesnot exits");
            return;
        }
        
        System.setProperty("tangosol.coherence.cacheconfig", cohfileName);
        NamedCache cache= CacheFactory.getCache("urCache");
        
        System.out.println("==> begin to load data into coherence...");
        System.out.println("==> cache size = "+cache.size());
        int i=0;
        long begin=System.currentTimeMillis();
        CSVReader csv = new CSVReader(new BufferedReader(new FileReader(datafileName)), '\t');
        String [] nextLine = null;
        HashMap<String, UserRegionBean> tempMap = new HashMap<String, UserRegionBean>();
        while ((nextLine = csv.readNext()) != null) {
            tempMap.put(nextLine[0], UserRegionBean.newInstance(nextLine[0], nextLine[1]));
            i ++;
            if (i%1000 == 0) {
                cache.putAll(tempMap);
                System.out.println(i);
                tempMap.clear();
            }
        }
        csv.close();
        cache.putAll(tempMap);
        long end=System.currentTimeMillis();
        System.out.println("==> cache size:"+cache.size()+", cost time is "+(end-begin)/1000+ " s\n");
    }
}
