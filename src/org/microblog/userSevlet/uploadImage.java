package org.microblog.userSevlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class uploadImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置上传文件保存路径
        String filePath = getServletContext().getRealPath("/") + "avatar";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        String userId=null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        try {
            List<FileItem> list = sfu.parseRequest(req);
            userId = list.get(0).getString("utf-8");
            String name = userId + ".jpg";
            File f = new File(filePath, name);
            File originFile = new File(filePath + "\\" + name);
            System.out.println(originFile);
            if (originFile.exists()) {
                try {
                    originFile.delete();
                    list.get(1).write(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                list.get(1).write(f);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*System.out.println(userId);
        //实例化上传组件
        SmartUpload su = new SmartUpload();
        //初始化SmartUpload
        su.initialize(getServletConfig(), req, resp);
        //设置上传文件对象10M
        su.setMaxFileSize(1024*1024*10);
        //设置所有文件大小100M
        su.setTotalMaxFileSize(1024*1024*100);
        //设置允许上传文件类型
        su.setAllowedFilesList("txt,jpg,gif,png");
        String result = "上传成功！";
        try {
            //设置禁止上传文件类型
            su.setDeniedFilesList("rar,jsp,js");
            //上传文件
            su.upload();
            //保存文件
            su.save(filePath);
            for (int i=0;i<su.getFiles().getCount();i++)
            {
                com.jspsmart.upload.File fil = su.getFiles().getFile(i);
                if (fil.isMissing()) continue;
                fil.saveAs("/avatar/" + userId+".jpg");
            }
        } catch (Exception e) {
            System.out.println(result);
            e.printStackTrace();
        }
*/
        req.setAttribute("smartResult",2);
        req.getRequestDispatcher("home.html").forward(req, resp);
    }

}