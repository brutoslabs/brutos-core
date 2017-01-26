

package org.brandao.brutos.web.util;

import java.net.MalformedURLException;
import java.net.URI;
import org.brandao.brutos.BrutosException;
import org.brandao.brutos.mapping.StringUtil;
import org.brandao.brutos.web.URIMapping;


public class WebUtil {
    
    public static String fixURI(String uri){
        
        if(StringUtil.isEmpty(uri))
            return null;
        
        uri = uri.replace("\\+", "\\");
        uri = uri.replaceAll("/+", "/");
        uri = uri.startsWith("/")? uri : "/" + uri;
        uri = uri.replace("/$", "");
        return uri;
    }
    
    public static void checkURI(String value, boolean required){
        try{  
            if(StringUtil.isEmpty(value)){
                if(required)
                    throw new MalformedURLException("is null or empty: " + value);
                else
                    return;
            }
            
            if(!value.startsWith("/"))
                throw new MalformedURLException("expected starts with \"/\": " + value);

            URIMapping map = new URIMapping( value );
            
            String uri = map.getURI(new Object[]{});
            URI prefix = new URI("http://serverName");
            prefix.resolve(uri);
        }
        catch(Exception e){
            throw new BrutosException(e);
        }
    }
    
}
