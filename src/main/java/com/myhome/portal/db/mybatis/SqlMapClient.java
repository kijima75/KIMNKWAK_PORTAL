package com.myhome.portal.db.mybatis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;

/*
 * MyBatis
 */
public class SqlMapClient {
	
	private static SqlSessionFactory mariaDBSessionFactory;
	
	static {
		try{
			//"src/main/java/com/myhome/portal/db/mybatis/sqlmapper/myBatisConfig.xml"
			String xmlPath = "com/myhome/portal/db/mybatis/sqlmapper/myBatisConfig.xml";
			Reader reader = Resources.getResourceAsReader(xmlPath);
			Properties prop = getCommonProperties(xmlPath);
			mariaDBSessionFactory = new SqlSessionFactoryBuilder().build(reader, prop);
			
		} catch(Exception e ){
			e.printStackTrace();
			
		}
		
	}
	
	public static SqlSession getSqlSession(){
		return getSqlSession(ExecutorType.SIMPLE, false);	
	}
	
	public static SqlSession getSqlSession(boolean isAutoCommit ){
		return getSqlSession(ExecutorType.SIMPLE,isAutoCommit);
	}
	
	public static SqlSession getSqlSession(ExecutorType executorType, boolean isAutoCommit ){
		return mariaDBSessionFactory.openSession(executorType, isAutoCommit);
	}
	
	public static Properties getEnvVars() throws Exception {
        Process p = null;
        Properties envVars = new Properties();
        Runtime r = Runtime.getRuntime();
		String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if (OS.indexOf("windows 9") > -1) {
            p = r.exec("command.com /c set");
        } else if ((OS.indexOf("nt") > -1) || (OS.indexOf("win") > -1)) {
            p = r.exec("cmd.exe /c set");
        } else {
            // our last hope, we assume Unix (thanks to H. Ware for the fix)
            p = r.exec("env");
        }
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
		try {
			String line;
			while ((line = br.readLine()) != null) {
				int idx = line.indexOf('=');
				String key = line.substring(0, idx);
				String value = line.substring(idx + 1);
				envVars.setProperty(key, value);
				// System.out.println( key + " = " + value );
			}
		}
		finally {
			br.close();
		}
        return envVars;
    }
	
	
	public static synchronized String getHomeDirectory() {
		String home = "/home/kimnkwak";
		Properties p;
		try {
			p = getEnvVars();
			home = p.getProperty("kimnkwak.properties");
			if (home == null || home.length() == 0) {
				home = "/home/kimnkwak";
			}
			File d = new File(home);
			if (d.exists() == false) {
				d.mkdirs();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return home;
	}
	
	private static Properties getCommonProperties(String xmlPath){
		Properties prop = new Properties();
		String home =  getHomeDirectory();
		String dbPath = home;
		if (home.endsWith(File.separator) == false) {
			dbPath += File.separator;
		}
		dbPath += "config" + File.separator + xmlPath;
		InputStreamReader in;
		try {
			in = new InputStreamReader(new FileInputStream(dbPath), "UTF8");
			prop.load(in);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
}
