package iptv.demo;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CacheLoader {
    public CacheLoader() {
        super();
    }

    public static void main(String[] args) {       
        if(args.length==2){
            CacheLoader cacheLoader = new CacheLoader();
            String datafile=args[0];
            String cohfile=args[1];
            cacheLoader.load(datafile, cohfile);
        }else
        {
            System.out.println("arg usage: java iptv.demo.Loader data_filename coherence-config-filename ");
        }
    }
    
    private void load(String datafile, String cohfile) {
        System.out.println("==> checking files....");
        File f= new File(datafile);
        if(!f.exists()) {
            System.out.println(datafile+"doesnot exits");
            return;
        }
        
        f= new File(cohfile);
        if(!f.exists()){
            System.out.println(cohfile+"doesnot exits");
            return;
        }
        
        System.out.println("==> beging to load data into coherence...");
        System.setProperty("tangosol.coherence.cacheconfig", cohfile);
        NamedCache cache= CacheFactory.getCache("urCache");
        
        int i=0;
        long begin=System.currentTimeMillis();
        for (i=0;i<10;i++){
            String uid = String.valueOf(i);
            String rid;
            if (i<5){
                rid = "1";
            }else{
                rid = "2";
            }
            UserRegionBean urb = UserRegionBean.newInstance(uid, rid);
            System.out.println(urb);
            cache.put(uid, urb);
        }
        long end=System.currentTimeMillis();
        System.out.println("==> cache size:"+cache.size()+", cost time is "+(end-begin)/1000+ " s\n");
        for (i=0;i<10;i++){
            String uid = String.valueOf(i);
            UserRegionBean urb = (UserRegionBean)cache.get(uid);
            System.out.println(urb);
        }        
    }
}
