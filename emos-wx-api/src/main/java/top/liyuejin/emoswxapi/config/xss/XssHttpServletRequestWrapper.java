package top.liyuejin.emoswxapi.config.xss;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        var value = super.getParameter(name);
        return HtmlEscaping(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        var values = super.getParameterValues(name);
        if (null != values) {
            for (String value : values) {
                HtmlEscaping(value);
            }
        }
        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        var parameters = super.getParameterMap();
        Map<String, String[]> map = new LinkedHashMap<>();
        if (null != parameters) {
            for (String key : parameters.keySet()) {
                String[] values = parameters.get(key);
                for (String value : values) {
                    HtmlEscaping(value);
                }
                map.put(key, values);
            }
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return HtmlEscaping(value);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        var inputStream = super.getInputStream();
        var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        var bufferedReader = new BufferedReader(inputStreamReader);

        var body = new StringBuilder();
        var line = bufferedReader.readLine();
        while (null != line) {
            body.append(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();

        var map = JSONUtil.parseObj(body.toString());
        var result = new HashMap<String, Object>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof String) {
                if (!StrUtil.hasEmpty(value.toString())) {
                    result.put(key, HtmlUtil.filter(value.toString()));
                }
            } else {
                result.put(key, value);
            }
        }
        String json = JSONUtil.toJsonStr(result);
        var bain = new ByteArrayInputStream(json.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bain.read();
            }
        };
    }

    /**
     * 将有效的脚本代码进行转义
     */
    private String HtmlEscaping(String value) {
        return !StrUtil.hasEmpty(value)? HtmlUtil.filter(value) : value;
    }
}
