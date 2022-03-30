package com.skhu.cloud.controller;

import com.skhu.cloud.service.DownloadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Controller
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/download")
public class DownloadController {

    private final DownloadService downloadService;

    @GetMapping("/download/checked")
    public void downloadChecked(@RequestParam(value = "checkedFiles",required = false)List<String> checkedFiles, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("실행 됨~~~~000~");

        Queue<String> que_checked = new LinkedList<>();
        for(String s : checkedFiles) {
            que_checked.add(s);
            System.out.println("~~~   " + s);
        }

        boolean toZip = downloadService.before(httpServletResponse,que_checked);

        if(toZip){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/zip");
        }

    }


}
