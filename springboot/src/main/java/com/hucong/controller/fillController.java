package com.hucong.controller;

import com.hucong.entity.Fill;
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
public class fillController {
    private UserService userService;
    @Autowired
    public fillController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("findFill")
    public String allSingle(Model model, HttpSession httpSession, @RequestParam(required = false,defaultValue = "0") Integer current){
        int PageSize = 5;
        int totalRows = userService.getFillTotal();
        int totalPages=(int) Math.ceil((double)totalRows/PageSize);
        if(current <=0){
            current =1;
        }else if(current>totalPages){
            current=totalPages;
        }

        int startRow = (current-1)*PageSize;
        List<Fill> rows = userService.findFill(startRow,PageSize);


        MyPage<Fill> myPage = new MyPage<Fill>(totalRows,current,totalPages,PageSize,rows);
        myPage.setAttribute();
        model.addAttribute("myPage", myPage);
        return "fillList";
    }
    @ResponseBody
    @RequestMapping("findFillById")
    public Fill findFillById(Integer fillId)
    {
        return userService.findFillById(fillId);
    }
    @ResponseBody
    @RequestMapping("updateFill")
    public Integer updateFill(Fill fill)
    {
        return userService.updateFill(fill);
    }
    @ResponseBody
    @RequestMapping("deleteFill")
    public Integer deleteFill(Integer fillId)
    {
        int flag=0;
        try {
            flag=userService.deleteFillById(fillId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(flag==0)
            {

                return userService.fillBeUsed(fillId);
            }
            return -1;
        }
    }
    @ResponseBody
    @RequestMapping("insertFill")
    public Integer insertFill(Fill fill)
    {
        return userService.insertFill(fill);
    }
}
