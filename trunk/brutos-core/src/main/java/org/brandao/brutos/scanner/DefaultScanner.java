/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009 Afonso Brandao. (afonso.rbn@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.brandao.brutos.scanner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import org.brandao.brutos.BrutosException;
import org.brandao.brutos.scanner.vfs.Dir;
import org.brandao.brutos.scanner.vfs.File;
import org.brandao.brutos.scanner.vfs.Vfs;
/**
 *
 * @author Afonso Brandao
 */
public class DefaultScanner extends AbstractScanner{

    public DefaultScanner(){
    }

    public void scan(){
        manifest();
    }
    
    public void load( ClassLoader classLoader ){
        try{
            URLClassLoader urls = (URLClassLoader)classLoader;
            Enumeration e = urls.getResources(toResource(basePackage));
            
            while(e.hasMoreElements()){
                URL url = (URL) e.nextElement();
                scan(url, classLoader);
            }
        }
        catch( Exception e ){}
    }

    private void scan(URL url, ClassLoader classLoader){
        Dir dir = Vfs.getDir(url);
        File[] files = dir.getFiles();

        for(int i=0;i<files.length;i++){
            File file = files[i];
            String path = file.getRelativePath();

            if( path.endsWith( ".class" ) ){
                String tmp = 
                    basePackage + 
                    "." + 
                    path.replace( "/" , "." ).substring( 0, path.length()-6 );

                try{
                    checkClass( Class.forName( tmp, false, classLoader) );
                }
                catch( Throwable ex ){}
            }
        }
    }
    public void manifest(){
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Enumeration urls = classLoader.getResources( "META-INF/MANIFEST.MF" );
            Map urlMap = new HashMap();
            
            while( urls.hasMoreElements() ){
                URL url = (URL) urls.nextElement();
                InputStream in = url.openConnection().getInputStream();
                List listURL = manifest(in);
                
                for(int i=0;i<listURL.size();i++){
                    URL u = (URL)listURL.get(i);
                    urlMap.put(u.toExternalForm(), u);
                }
            }
            
            Collection collectionUrls = urlMap.values();
            Iterator iterator = collectionUrls.iterator();
            
            URL[] arrayUrls = new URL[collectionUrls.size()];
            
            int index = 0;
            while(iterator.hasNext()){
                arrayUrls[index++] = (URL)iterator.next();
            }
            
            URLClassLoader urlClassLoader = 
                new URLClassLoader(
                    arrayUrls,
                    Thread.currentThread().getContextClassLoader());
            
            load( urlClassLoader );
        }
        catch (Exception ex) {
            throw new BrutosException( ex );
        }
    }

    public List manifest(InputStream in){
        try{
            java.io.BufferedReader reader = 
                    new BufferedReader( new InputStreamReader( in ) );
            String txt = "";
            String line;

            while( (line = reader.readLine() ) != null ){
                if( line.startsWith( "Class-Path: " ) ){
                    txt = line.substring( "Class-Path: ".length(), line.length() );
                    while( (line = reader.readLine() ) != null && line.startsWith( " " ) ){
                        txt += line.substring( 1, line.length() );
                    }
                }
            }

            StringTokenizer stok = new StringTokenizer( txt, " ", false );
            List urlList = new ArrayList();
            
            while( stok.hasMoreTokens() ){
                String dirName  = System.getProperty( "user.dir" );
                String fileName = stok.nextToken();
                
                URL url;
                
                if( fileName.startsWith("file:/") )
                    url = new URL(fileName);
                else
                if (".".equals(fileName)) 
                    url = new URL("file:/" + dirName);
                else{
                    fileName = dirName + "/" + fileName;
                    url = new URL("file:/"+fileName);
                }
                
                urlList.add(url);
            }
            
            return urlList;
        }
        catch( Throwable e ){
            throw new BrutosException( e );
        }

    }
    
    private void checkClass( Class classe ){
        if(accepts(classe))
            listClass.add(classe);
    }
    
   private String toResource(String value){
        value = value
            .replace(".", "/")
            .replace("\\", "/")
            .replaceAll( "/+" , "/");
        
        if (value.startsWith("/")) 
            value = value.substring(1);
        
        return value;
    }
   
}