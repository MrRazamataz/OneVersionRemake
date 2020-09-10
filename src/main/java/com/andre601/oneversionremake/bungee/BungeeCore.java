/*
 * Copyright 2020 Andre601
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.andre601.oneversionremake.bungee;

import com.andre601.oneversionremake.bungee.commands.CmdOneVersionRemake;
import com.andre601.oneversionremake.bungee.listener.LoginListener;
import com.andre601.oneversionremake.bungee.listener.PingListener;
import com.andre601.oneversionremake.core.ConfigHandler;
import com.andre601.oneversionremake.core.OneVersionRemake;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

public class BungeeCore extends Plugin implements OneVersionRemake.Core{
    
    private OneVersionRemake core;
    private final Logger logger = LoggerFactory.getLogger(BungeeCore.class);
    
    @Override
    public void onEnable(){
        this.core = new OneVersionRemake(this);
    }
    
    @Override
    public void enable(){
        logger.info("Loading command...");
        new CmdOneVersionRemake(this);
        logger.info("Command loaded!");
        
        logger.info("Loading listener...");
        new LoginListener(this);
        new PingListener(this);
        logger.info("Listener loaded!");
        
        logger.info("OneVersionRemake is ready!");
    }
    
    @Override
    public boolean reloadConfig(){
        return core.reloadConfig();
    }
    
    @Override
    public Path getPath(){
        return getDataFolder().toPath();
    }
    
    @Override
    public OneVersionRemake.Platform getPlatform(){
        try{
            Class.forName("io.github.waterfallmc.waterfall.conf.WaterfallConfiguration");
            return OneVersionRemake.Platform.WATERFALL;
        }catch(ClassNotFoundException ex){
            return OneVersionRemake.Platform.BUNGEE;
        }
    }
    
    @Override
    public Logger getProxyLogger(){
        return logger;
    }
    
    public ConfigHandler getConfigHandler(){
        return core.getConfigHandler();
    }
    
    public String getVersion(){
        return core.getVersion();
    }
    
    public TextComponent getComponent(List<String> lines, List<Integer> serverProtocols, int userProtocol){
        return new TextComponent(getText(String.join("\n", lines), serverProtocols, userProtocol));
    }
    
    public String getText(String text, List<Integer> serverProtocols, int userProtocol){
        return ChatColor.translateAlternateColorCodes('&', text)
                .replace("{version}", OneVersionRemake.Versions.getFriendlyName(serverProtocols))
                .replace("{userVersion}", OneVersionRemake.Versions.getFriendlyName(userProtocol));
    }
}
