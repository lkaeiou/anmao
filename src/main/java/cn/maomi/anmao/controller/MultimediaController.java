package cn.maomi.anmao.controller;
import cn.maomi.anmao.domain.Multimedia;
import cn.maomi.anmao.domain.User;
import cn.maomi.anmao.mapper.MultimediaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;




@Controller
public class MultimediaController {
    static int vid = 1;
    static int iid = 1;
    @Autowired
    private Multimedia multimedia;
    @Autowired
    private MultimediaMapper multimediaMapper;
    @Autowired
    tool tool;

    /*
     * 获取file.html页面
     */
    @RequestMapping("file")
    public String file() {
        return "file/file";
    }

    @RequestMapping("multifile")
    public String multifile() {
        return "file/multifile";
    }

    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multifileUpload(HttpServletRequest request,
                                  HttpSession session,
                                  @RequestParam("title") String title,
                                  @RequestParam("brief") String brief) {
        multimedia.setTitle(title);
        multimedia.setBrief(brief);
        multimedia.setUid(tool.sessionTool(session).getId());
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        if (files.isEmpty()) {
            return "false";
        }
//        String path1 = "C:\\Users\\Administrator.SC-202006062107\\IdeaProjects\\anmao\\src\\main\\resources\\static\\Multimedia\\video";
//        String path2 = "C:\\Users\\Administrator.SC-202006062107\\IdeaProjects\\anmao\\src\\main\\resources\\static\\Multimedia\\image";

        String path1 = "\\usr\\local\\project\\anmao\\src\\main\\resources\\static\\Multimedia\\video";
        String path2 = "\\usr\\local\\project\\anmao\\src\\main\\resources\\static\\Multimedia\\image";
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            if (file.isEmpty()) {
                return "false";//上传文件为空
            } else {
                String[] strArray = fileName.split("\\.");
                int suffixIndex = strArray.length - 1;
                String s = strArray[suffixIndex];
                if (s.equals("mp4") ||s.equals("qlv") || s.equals("avi") || s.equals("empg") || s.equals("rm") || s.equals("rmvb")) {
                    File dest = new File(path1 + "/" + vid + fileName);
                    if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                        dest.getParentFile().mkdir();
                    }
                    try {
                        file.transferTo(dest);
                        String vaddress = dest.getName();
                        multimedia.setVaddress("/Multimedia/video/" + vaddress);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return "false";
                    }
                }
                if (s.equals("jpg") || s.equals("bmp") || s.equals("png") ||
                        s.equals("tif") || s.equals("gif") || s.equals("pcx") ||
                        s.equals("fpx") || s.equals("svg") || s.equals("ufo")) {
                    File dest = new File(path2 + "/" + iid + fileName);
                    if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                        dest.getParentFile().mkdir();
                    }
                    try {
                        file.transferTo(dest);
                        String iaddress = dest.getName();
                        multimedia.setIaddress("/Multimedia/image/" + iaddress);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return "false";
                    }
                }
            }
        }
        multimediaMapper.multifileUpload(multimedia);
        vid++;
        iid++;
        return "上传成功！";
    }

    //    根据视频标题模糊查询
/*
    @RequestMapping("/findVideoByTitle")

    public String findVideoByTitle(){
        List<Multimedia> m=multimediaMapper.findVideoByTitle("q");

        return "main/findVideo";
    }
*/



    /*@RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = "新建文件夹 (10).rar";
        String filePath = "F:/test";
        File file = new File(filePath + "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(filename, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }*/
}

