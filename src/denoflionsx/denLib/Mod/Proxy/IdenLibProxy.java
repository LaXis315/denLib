package denoflionsx.denLib.Mod.Proxy;

import java.io.File;

public interface IdenLibProxy {
    
    public void print(String msg);
    
    public void warning(String warning);
    
    public void registerForgeSubscribe(Object o);
    
    public void sendMessageToPlayer(String msg);
    
    public void registerChangelogHandler(Object o);
    
    public void makeDirs(File configDir);
    
}
