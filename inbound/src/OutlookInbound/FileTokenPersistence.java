package OutlookInbound;

import Facade.ConfigurationSetting;
import com.microsoft.aad.msal4j.ITokenCacheAccessAspect;
import com.microsoft.aad.msal4j.ITokenCacheAccessContext;

import java.io.*;
import java.util.Arrays;

public class FileTokenPersistence implements ITokenCacheAccessAspect
{
    private File file;
    final private String key;

    protected FileTokenPersistence(String key)
    {
        String userHome = System.getProperty("user.home");
        file = new File(String.format("%s/.siplanner",userHome));
        if(!file.exists())
            file.mkdir();
        file = new File(String.format("%s/.siplanner/token.dat",userHome));
        this.key = key;
    }

    @Override
    public void beforeCacheAccess(ITokenCacheAccessContext iTokenCacheAccessContext)
    {
        try
        {
            file.createNewFile();
            FileInputStream stream = new FileInputStream(file);

            String data = Aes.decrypt(new String(stream.readAllBytes()),key);
            stream.close();
            if(data != null && data.length() > 2)
                iTokenCacheAccessContext.tokenCache().deserialize(data);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void afterCacheAccess(ITokenCacheAccessContext iTokenCacheAccessContext)
    {
        if(!iTokenCacheAccessContext.hasCacheChanged()) return;

        try
        {
            file.delete();
            file.createNewFile();

            FileOutputStream stream = new FileOutputStream(file);
            String data = Aes.encrypt(iTokenCacheAccessContext.tokenCache().serialize(),key);
            stream.write(data.getBytes());
            stream.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void clearCache()
    {
        file.delete();
    }
}
