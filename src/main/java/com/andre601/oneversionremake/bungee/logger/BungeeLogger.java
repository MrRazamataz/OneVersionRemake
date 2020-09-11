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

package com.andre601.oneversionremake.bungee.logger;

import com.andre601.oneversionremake.core.Logger;

import java.util.logging.Level;

public class BungeeLogger implements Logger{
    
    private final java.util.logging.Logger logger;
    
    public BungeeLogger(java.util.logging.Logger logger){
        this.logger = logger;
    }
    
    @Override
    public void info(String msg){
        logger.log(Level.INFO, msg);
    }
    
    @Override
    public void warn(String msg){
        logger.log(Level.WARNING, msg);
    }
    
    @Override
    public void warn(String msg, Throwable throwable){
        logger.log(Level.WARNING, msg, throwable);
    }
}
