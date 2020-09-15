import com.alibaba.fastjson.JSON;
import org.microblog.dbconnect.blog.voBlog.Blog;

import java.util.ArrayList;
import java.util.List;


public class test {
    public static void main(String[] args) {
        Blog blog=new Blog();
        blog.setUser_id(1234);
        blog.setBlog_content("efghjgfdssdfg");
        blog.setBlog_id(12366);
        String s=JSON.toJSONString(blog);
        System.out.println(s);
    }
}
