/*
 * Copyright (c) 2014, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.project.aoyolo.cooperation_v1.utils;

import org.kymjs.kjframe.http.HttpParams;
import java.lang.reflect.Field;

/**
 * Http请求的参数集合
 * 继承HttpParams
 * @author Myy
 */
public class HttpParamsObject extends HttpParams{
    public HttpParamsObject(Object object) {
        super();
        if(object == null)
        return;
        //得到类对象
        Class _class = object.getClass();
       /*
        * 得到类中的所有属性集合
        */
        Field[] fs = _class.getDeclaredFields();
        for(Field f :fs){
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = null;//得到此属性的值
            try {
                val = f.get(object);
                if(val != null)
                {
                    put(f.getName(),val.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
        }
    }
}
