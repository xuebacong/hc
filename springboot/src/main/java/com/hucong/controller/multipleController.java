package com.hucong.controller;

import com.hucong.entity.Multiple;
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

@Controller
public class multipleController {
    private UserService userService;

    @Autowired
    public multipleController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("insertMultiple")
    public Integer insertMultiple(Multiple multiple){
        return userService.insertMultiple(multiple);
    }
    @ResponseBody
    @RequestMapping("deleteMultiple")
    public Integer deleteMultiple(Integer multipleId){
        int flag=0;
        try {
            flag=userService.deleteMultiple(multipleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag==0) return userService.multipleBeUsed(multipleId);
        return -1;
    }
    @ResponseBody
    @RequestMapping("updateMultiple")
    public Integer updateMultiple(Multiple multiple){
        return userService.updateMultiple(multiple);
    }
    @RequestMapping("findMultiple")
    public String findMultiple(Model model, HttpSession httpSession, @RequestParam(required = false,defaultValue = "0") Integer current){
        int PageSize = 5;
        int totalRows = userService.getMultipleTotal();
        int totalPages=(int) Math.ceil((double)totalRows/PageSize);
        if(current <=0){
            current =1;
        }else if(current>totalPages){
            current=totalPages;
        }

        int startRow = (current-1)*PageSize;
        List<Multiple> rows = userService.findMultiple(startRow,PageSize);

        MyPage<Multiple> myPage = new MyPage<Multiple>(totalRows,current,totalPages,PageSize,rows);
        myPage.setAttribute();
        model.addAttribute("myPage", myPage);
        return "multipleList";
    }
    @ResponseBody
    @RequestMapping("findMultipleById")
    public Multiple findMultipleById(Integer multipleId){
        return userService.findMultipleById(multipleId);
    }

}

