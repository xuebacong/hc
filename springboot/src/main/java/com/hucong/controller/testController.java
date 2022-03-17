package com.hucong.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hucong.entity.*;
import com.hucong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Controller
public class testController {
    UserService userService;
    @Autowired
    public testController(UserService userService) {
        this.userService = userService;
    }
//    根据前台传入的题号进入考试页面
    @RequestMapping("enterTest")
    public String test2(HttpServletRequest httpServletRequest,Model model , @RequestParam(defaultValue = "30") int testId){

        SingleTest singleTest = new SingleTest();
        singleTest.setTestId(testId);
        httpServletRequest.getSession().setAttribute("testId",testId);

        List<Single> singleList=userService.getSingleByTestId(testId);
        for(int i=0;i<singleList.size();i++){
            singleList.get(i).setSingleAnswer(null);
        }
        singleTest.setSingleList(singleList);

        List<Multiple>multipleList=userService.getMultipleByTestId(testId);
        for(int i=0;i<multipleList.size();i++){
           multipleList.get(i).setMultipleAnswer(null);
        }
        singleTest.setMultipleList(multipleList);

        List<Fill> fillList=userService.getFillByTestId(testId);
        for(int i=0;i<fillList.size();i++){
            fillList.get(i).setFillAnswer(null);
        }
        singleTest.setFillList(fillList);

        model.addAttribute("form",singleTest);

        return "user-exam";
    }

    @RequestMapping("browseTest")
    public String browseTest(HttpServletRequest httpServletRequest,Model model , @RequestParam(defaultValue = "30") int testId){
        SingleTest singleTest = new SingleTest();
        singleTest.setTestId(testId);
        httpServletRequest.getSession().setAttribute("testId",testId);

        List<Single> singleList=userService.getSingleByTestId(testId);

        singleTest.setSingleList(singleList);

        List<Multiple>multipleList=userService.getMultipleByTestId(testId);

        singleTest.setMultipleList(multipleList);

        List<Fill> fillList=userService.getFillByTestId(testId);

        singleTest.setFillList(fillList);

        model.addAttribute("form",singleTest);

        return "user-normal";
    }

    @RequestMapping("failList")
    public String failList(HttpSession httpSession,Model model){
        Integer uid=(Integer) httpSession.getAttribute("uid");
        SingleTest singleTest = new SingleTest();
        singleTest.setTestId(0);
        //System.out.println(uid);
        List<Single> singleList=userService.findFailSingleByUid(uid);
        singleTest.setSingleList(singleList);

        List<Multiple> multipleList=userService.findFailMultipleByUid(uid);
        singleTest.setMultipleList(multipleList);

        List<Fill> fillList =userService.findFailFillByUid(uid);
        singleTest.setFillList(fillList);

        model.addAttribute("form",singleTest);
        return "failList";
    }

    //随机创造一张试卷
    @ResponseBody
    @RequestMapping("createTest")
    public int createTest(@RequestParam String testName){
        userService.createTestByName(testName);
       Integer testId=userService.getTestIdByName(testName);
       List<Single> singleList = userService.getSingleByRand();
       for(int i=0;i<singleList.size();i++){
           userService.insertTestSingle(testId,singleList.get(i).getSingleId());
       }

       List<Multiple> multipleList=userService.getMultipleByRand();
       for(int i=0;i<multipleList.size();i++){
           userService.insertTestMultiple(testId,multipleList.get(i).getMultipleId());
       }

       List<Fill> fillList =userService.getFillByRand();
       for(int i=0;i< fillList.size();i++){
           userService.insertTestFill(testId,fillList.get(i).getFillId());
       }
        return 1;
    }
    //随机创造一张在线试卷
    @ResponseBody
    @RequestMapping("createOnlineTest1")
    public Integer createOnlineTest1(String testName, String startTTime, String endTTime){
        char kongGe=' ';
        StringBuffer s1=new StringBuffer(startTTime);
        s1.setCharAt(10,kongGe);
        StringBuffer s2=new StringBuffer(endTTime);
        s2.setCharAt(10,kongGe);
        String startTime=s1.toString();
        String endTime=s2.toString();
        System.out.println(startTime+" "+endTime);
        userService.createOnlineTest1(testName,startTime,endTime);

        Integer testId=userService.getTestIdByName(testName);
        List<Single> singleList = userService.getSingleByRand();
        for(int i=0;i<singleList.size();i++){
            userService.insertTestSingle(testId,singleList.get(i).getSingleId());
        }

        List<Multiple> multipleList=userService.getMultipleByRand();
        for(int i=0;i<multipleList.size();i++){
            userService.insertTestMultiple(testId,multipleList.get(i).getMultipleId());
        }

        List<Fill> fillList =userService.getFillByRand();
        for(int i=0;i< fillList.size();i++){
            userService.insertTestFill(testId,fillList.get(i).getFillId());
        }
        return 1;
    }
//    随机创造一张普通试卷
    @ResponseBody
    @RequestMapping("createNormalTest1")
    public Integer createNormalTest1(String testName){
        userService.createNormalTest1(testName);
        Integer testId=userService.getTestIdByName(testName);
        List<Single> singleList = userService.getSingleByRand();
        for(int i=0;i<singleList.size();i++){
            userService.insertTestSingle(testId,singleList.get(i).getSingleId());
        }

        List<Multiple> multipleList=userService.getMultipleByRand();
        for(int i=0;i<multipleList.size();i++){
            userService.insertTestMultiple(testId,multipleList.get(i).getMultipleId());
        }

        List<Fill> fillList =userService.getFillByRand();
        for(int i=0;i< fillList.size();i++){
            userService.insertTestFill(testId,fillList.get(i).getFillId());
        }
        return 1;
    }
    @RequestMapping("exam")
    public String exam(HttpServletRequest httpServletRequest,SingleTest singleTest){
        int grade=0;

        System.out.println(httpServletRequest.getSession().getAttribute("testId"));

        Integer uid=(Integer) httpServletRequest.getSession().getAttribute("uid");
        Iterator<Single>iter1 =singleTest.getSingleList().iterator();
        while(iter1.hasNext()){
            Single single= (Single) iter1.next();
            System.out.println(single.getSingleAnswer()+" "+userService.getSingleAnswerById(single.getSingleId()));
            if(userService.getSingleAnswerById(single.getSingleId()).equals(single.getSingleAnswer()))
            {
                grade+=5;
            }else
            {
                userService.insertFailSingle(single.getSingleId(),uid);
            }
        }

        Iterator<Multiple>iter2=singleTest.getMultipleList().iterator();
        while(iter2.hasNext()){
            Multiple multiple=(Multiple) iter2.next();
            System.out.println(multiple.getMultipleAnswer()+"  "+userService.getMultipleAnswerById(multiple.getMultipleId()));
            if(userService.getMultipleAnswerById(multiple.getMultipleId()).equals(multiple.getMultipleAnswer()))
            {
                grade+=10;
            }else
            {
                userService.insertFailMultiple(multiple.getMultipleId(),uid);
            }
        }

        Iterator<Fill>iter3=singleTest.getFillList().iterator();
        while(iter3.hasNext()){
            Fill fill=(Fill) iter3.next();
            System.out.println(fill.getFillAnswer()+" "+userService.getFillAnswerById(fill.getFillId()));
            if(userService.getFillAnswerById(fill.getFillId()).equals(fill.getFillAnswer()))
            {
                grade+=5;
            }else
            {
                userService.insertFailFill(fill.getFillId(),uid);
            }
        }



        System.out.println(grade);
        System.out.println((Integer) httpServletRequest.getSession().getAttribute("uid"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        userService.insertGrade((Integer) httpServletRequest.getSession().getAttribute("testId"),
                (Integer) httpServletRequest.getSession().getAttribute("uid"),
                (String)dateFormat.format(cal.getTime()),
        grade);
        httpServletRequest.getSession().removeAttribute("testId");
        //return "user-main";
        return "redirect:/userMain";
    }

  //  @ResponseBody
    @RequestMapping("hucong")
    public String hucong(String singleQuestion){
//        System.out.println(userService.findSingleByQues(singleQuestion));
//        return userService.findSingleByQues(singleQuestion);
        //return userService.findFillByQues(singleQuestion);
        return "manageGrade";
    }
}
