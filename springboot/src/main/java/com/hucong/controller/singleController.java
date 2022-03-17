package com.hucong.controller;

import com.hucong.entity.Single;
import com.hucong.service.UserService;
import com.hucong.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

//@RestController
//为什么不能跳转到返回的字符串html
@Controller
public class singleController {
    private UserService userService;
    @Autowired
    public singleController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("insertSingle")
    public int insertSingle(Single single){
        return userService.insertSingle(single);
    }

    @ResponseBody
    @RequestMapping("deleteSingle")
    public Integer deleteSingle(int singleId){

        //return userService.deleteSingleById(singleId);
        int flag=0;
        try {
            flag=userService.deleteSingleById(singleId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(flag==0)
            {
                System.out.println(userService.singleBeUsed(singleId));
                return userService.singleBeUsed(singleId);
            }
            return -1;
        }

    }

    @ResponseBody
    @RequestMapping("updateSingle")
    public int updateSingle(Single single){
        return userService.updateSingle(single);
    }
    @ResponseBody
    @RequestMapping("findSingleById")
    public Single findSingleById(Integer id){
        return userService.findSingleById(id);
    }
    //@ResponseBody
    @RequestMapping("findSingle")
    public String allSingle(Model model, HttpSession httpSession, @RequestParam(required = false,defaultValue = "0") Integer current){
        int PageSize = 5;
        int totalRows = userService.getSingleTotal();
        int totalPages=(int) Math.ceil((double)totalRows/PageSize);
        if(current <=0){
            current =1;
        }else if(current>totalPages){
            current=totalPages;
        }

        int startRow = (current-1)*PageSize;
        List<Single> rows = userService.findSingle(startRow,PageSize);


        MyPage<Single> myPage = new MyPage<Single>(totalRows,current,totalPages,PageSize,rows);
        myPage.setAttribute();
        model.addAttribute("myPage", myPage);
        return "singleList";
    }
//    @RequestMapping("test2")
//    public String test2(Model model){
//        model.addAttribute("num1",1);
//        model.addAttribute("num2",2);
//        return "test2";
//    }

    @RequestMapping("test1")
    public String test1(Model model){
//        User user1 = new User(5,"p","pp");
//        model.addAttribute("user1",user1);
 //       model.addAttribute("aString","我是一个很长的字符串");
        return "singleList";
    }
}
