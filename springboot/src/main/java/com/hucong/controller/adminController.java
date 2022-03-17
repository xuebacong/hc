package com.hucong.controller;

import com.hucong.entity.*;
import com.hucong.service.UserService;
import com.hucong.utils.MyPage;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class adminController {
    private UserService userService;
    @Autowired
    public adminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("findTest")
    public String findTest(Model model,@RequestParam(defaultValue = "0") Integer current)
    {
        int PageSize = 5;
        int totalRows = userService.findTestTotal();
        int totalPages=(int) Math.ceil((double)totalRows/PageSize);
        if(current <=0){
            current =1;
        }else if(current>totalPages){
            current=totalPages;
        }

        int startRow = (current-1)*PageSize;
        List<Test> rows = userService.findTest(startRow,PageSize);


        MyPage<Test> myPage = new MyPage<Test>(totalRows,current,totalPages,PageSize,rows);
        myPage.setAttribute();
        model.addAttribute("myPage", myPage);
        return "manageExam";
    }
    @ResponseBody
    @RequestMapping("deleteTest")
    public Integer deleteTest(Integer testId)
    {
        if(userService.checkTestBeUsed(testId)==1)
        {
            return -1;
        }
        try {
            return userService.deleteTestById(testId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @RequestMapping("exitAdmin")
    public String exit(Model model, HttpSession httpSession)
    {
        httpSession.removeAttribute("rootId");
        return "main";
    }
    @ResponseBody
    @RequestMapping("deleteGrade")
    public Integer deleteGrade(String testName,String uname)
    {
        System.out.println("nnhh");
        return userService.deleteGrade(testName,uname);
    }
    @ResponseBody
    @RequestMapping("updateGrade")
    public Integer updateGrade(String testName,String uname,Integer grade)
    {
        return userService.updateGrade(testName,uname,grade);
    }
    @ResponseBody
    @RequestMapping("findOneGrade")
    public GradePro findOneGrade(String testName,String uname)
    {
        return userService.findGradeByTestNameAndUname(testName,uname);
    }
    @RequestMapping("findGrade")
    public String findGrade(Model model,@RequestParam(defaultValue = "0") Integer current,@RequestParam(defaultValue = "在线考试一") String testName)
    {

            int PageSize = 5;
            int totalRows = userService.getGradeByTestNameTotal(testName);
            int totalPages=(int) Math.ceil((double)totalRows/PageSize);
            if(current <=0){
                current =1;
            }else if(current>totalPages){
                current=totalPages;
            }

            int startRow = (current-1)*PageSize;
            List<GradePro> rows = userService.findGradeByTestName(startRow,PageSize,testName);


            MyPage<GradePro> myPage = new MyPage<GradePro>(totalRows,current,totalPages,PageSize,rows);
            myPage.setAttribute();
            model.addAttribute("myPage", myPage);
            model.addAttribute("testName",testName);

        return "manageGrade";
    }
    @RequestMapping("searchQuestion")
    public String searchQuestion(Model model, @RequestParam(defaultValue = "\"笲\"") String Ques)
    {
        SingleTest singleTest = new SingleTest();
        singleTest.setTestId(0);

        List<Single> singleList=userService.findSingleByQues(Ques);
        singleTest.setSingleList(singleList);

        List<Multiple> multipleList=userService.findMultipleByQues(Ques);
        singleTest.setMultipleList(multipleList);

        List<Fill> fillList =userService.findFillByQues(Ques);
        singleTest.setFillList(fillList);
        model.addAttribute("singleList",singleList);
        model.addAttribute("multipleList",multipleList);
        model.addAttribute("fillList",fillList);
        return "admin-search";
    }
    @RequestMapping("toAdmin")
    public String toFront(){
        return "admin";
    }
    @RequestMapping("postExam")
    public String postExam(){
        return "postExam";
    }
    @RequestMapping("manageExam")
    public String manageExam(){
        return "manageExam";
    }
    @RequestMapping("addTest")
    public String addTest(){
        return "admin-title-add";
    }
//    @RequestMapping("manageGrade")
//    public String manageGrade(Model model, @RequestParam(defaultValue = "0") Integer testId,@RequestParam(defaultValue = "0") Integer current){
//        int PageSize = 5;
//        int totalRows = userService.getGradeByTestIdTotal(testId);
//        int totalPages=(int) Math.ceil((double)totalRows/PageSize);
//        if(current <=0){
//            current =1;
//        }else if(current>totalPages){
//            current=totalPages;
//        }
//
//        int startRow = (current-1)*PageSize;
//        List<Single> rows = userService.findSingle(startRow,PageSize);
//
//        MyPage<Single> myPage = new MyPage<Single>(totalRows,current,totalPages,PageSize,rows);
//        myPage.setAttribute();
//        model.addAttribute("myPage", myPage);
//        return "manageGrade";
//    }
}
