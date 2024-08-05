package com.oven.controller;

import com.oven.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(HttpServletRequest req) throws Exception {
        String ip1 = IPUtils.getClientIPAddr(req);
        String ip2 = req.getRemoteAddr();
        String ip3 = getIpAddr(req);

        String ip4 = req.getHeader("x-forwarded-for");
        String ip5 = req.getHeader("Proxy-Client-IP");
        String ip6 = req.getHeader("WL-Proxy-Client-IP");

        log.info("ip1:{}", ip1);
        log.info("ip2:{}", ip2);
        log.info("ip3:{}", ip3);
        log.info("ip4:{}", ip4);
        log.info("ip5:{}", ip5);
        log.info("ip6:{}", ip6);
        return ip1 + "_" + ip2 + "_" + ip3 + "_" + ip4 + "_" + ip5 + "_" + ip6;
    }

    /**
     * 获取访问用户的客户端IP（适用于公网与局域网）.
     */
    public static String getIpAddr(final HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ip = request.getHeader("x-forwarded-for");
        if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }

        return ip;
    }

    /**
     * @return 如果<tt>s</tt>为<tt>null</tt>或空白字符串返回<tt>true</tt>
     */
    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

}
