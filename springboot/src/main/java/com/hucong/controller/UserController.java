package com.hucong.controller;

import com.hucong.entity.*;
import com.hucong.service.UserService;
import com.hucong.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@RestController
@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ResponseBody
    @RequestMapping("updateUserInfo")
    public Integer updateUserInfo(String uname,String userDivision,String userClass,String userSchool,String newPassword){
        System.out.println("kkkkkkkkkkkkkkkk"+newPassword);
        //更新密码
//       userService.updatePasswordByUname(uname,newPassword);
        //更新个人信息
        Integer uid=(Integer) userService.getUidByUname(uname);
        UserInfo userInfoTest = new UserInfo(uid,userDivision,userClass,userSchool);
//        userService.updateUserInfo(userInfoTest);
        if(userService.updatePasswordByUname(uname,newPassword)==1&&userService.updateUserInfo(userInfoTest)==1)
        {
            return 1;
        }

        return 0;
    }
    @ResponseBody
    @RequestMapping("checkPassword")
    public Integer checkPassword(String uname,String password){
        if(userService.getPasswordByUname(uname).equals(password))return 1;
        return 0;
    }
    @RequestMapping("updateInfo")
    public String examMain(Model model,HttpSession httpSession){
        model.addAttribute("userInfo",userService.getUserInfoByUid((Integer) httpSession.getAttribute("uid")));
        model.addAttribute("uname",userService.getUnameByUid((Integer) httpSession.getAttribute("uid")));
        return "user-update-information";
    }

    @RequestMapping("userExamMain")
    public String userExamMain(Model model){
        List<Test> testList=new ArrayList<>();
         testList=userService.getAllTest();
        List<Test> myTestList=new ArrayList<>();
        System.out.println(testList.size());


        for(Test test:testList){
            if(test.getIsOnline()==1){
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
                String str=sdf.format(new Date());
            if(str.compareTo(test.getStartTime())>0&&str.compareTo(test.getEndTime())<0)
            {
                myTestList.add(test);
            }
            }
        }


        model.addAttribute("onlineTest",myTestList);
        return "user-exam-main";
    }
    @ResponseBody
    @RequestMapping("checkHasTest")
    public Integer checkIsTest(Integer testId,Integer uid){
        System.out.println(testId+"  "+uid);
        System.out.println(userService.checkHasTest(testId,uid));
        if(userService.checkHasTest(testId,uid).size()==1)return 1;
        return 0;
    }

    @RequestMapping("userNormalMain")
    public String userNormalMain(Model model){
        List<Test> testList=new ArrayList<>();
        testList=userService.getAllTest();
        List<Test> myTestList=new ArrayList<>();
        System.out.println(testList.size());

        for(Test test:testList){
            if(test.getIsOnline()==0){
                myTestList.add(test);
            }
        }
        model.addAttribute("onlineTest",myTestList);

        return"user-normal-main";
    }


    @RequestMapping("userMain")
    public String userMain(){
        return "user-main";
    }
    //这里是成绩表
    @RequestMapping("wrongQues")
    public String wrongQues(Model model, HttpSession httpSession, @RequestParam(required = false,defaultValue = "0") Integer current){
        int PageSize = 5;
        int totalRows = userService.getWrongQuesTotalByUid((Integer)httpSession.getAttribute("uid"));
        int totalPages=(int) Math.ceil((double)totalRows/PageSize);
        if(current <=0){
            current =1;
        }else if(current>totalPages){
           // System.out.println("666");
            current=totalPages;
        }

        int startRow = (current-1)*PageSize;
        //List<Single> rows = userService.findSingle(startRow,PageSize);
        List<WrongQues> rows =userService.getWrongQuesByUid((Integer)httpSession.getAttribute("uid"),startRow,PageSize);
        MyPage<WrongQues> myPage = new MyPage<WrongQues>(totalRows,current,totalPages,PageSize,rows);
        myPage.setAttribute();
        model.addAttribute("myPage", myPage);
        return "user-score-query";
    }

    @RequestMapping("enter")
    public String enter(HttpServletRequest httpServletRequest,User user){
 //       System.out.println(userService.enter(user));
//        if(userService.enter(user))
//        {
//            httpServletRequest.getSession().setAttribute("uid",userService.getUidByUname(user.getUname()));
//            return "main";
//        }
        if(userService.enter(user)==0){
            return "main";
        }else if(userService.enter(user)==1){
            httpServletRequest.getSession().setAttribute("uid",userService.getUidByUname(user.getUname()));
            return "user-main";
        }
        httpServletRequest.getSession().setAttribute("rootId",userService.getUidByUname(user.getUname()));
        return "admin";
    }

    @RequestMapping("reg")
    public String reg(User user){
        //System.out.println("ppp");
        //System.out.println(user);
        //userService.save(user);
        return "main";
    }
    @RequestMapping("save")
    public String save(User user){
        userService.save(user);
        userService.setInfoByUid(user.getUid());
        return "main";
 //       System.out.println(user);
//        System.out.println("save....");
 //       return "index";
//        return userService.save(user);
    }

    @RequestMapping("findAll")
    public List<User>  findAll(){
        //System.out.println("findall");
        return userService.findAll();
    }

    @RequestMapping("exitUser")
    public String exit(HttpSession httpSession)
    {
        httpSession.removeAttribute("uid");
        return "main";
    }
    @ResponseBody
    @RequestMapping("changeTestStatus1")
    public Integer changeTestStatus1(Integer testId){
        System.out.println("666");
        return userService.changeTestStatus1(testId);
    }

    @ResponseBody
    @RequestMapping("changeTestStatus2")
    public Integer changeTestStatus2(String testId){
        Integer in = Integer.parseInt(testId);
        return userService.changeTestStatus2(in);
    }
}
