package com.hucong.dao;

import com.hucong.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
@Mapper
public interface UserDao {
     List<User> findAll() ;
     int save(User user);
     List<User> enter(User user);
     List<Single> findSingle(Integer startRow,Integer pageSize);
     Integer getSingleTotal();
     Single findSingleById(Integer id);
     Integer updateSingle(Single single);
     int deleteSingleById(Integer singleId);
     int insertSingle(Single single);

     List<Multiple> findMultiple(Integer startRow,Integer pageSize);
     Integer getMultipleTotal();
     Multiple findMultipleById(Integer multipleId);
     Integer updateMultiple(Multiple multiple);
     Integer deleteMultiple(Integer multipleId);
     Integer insertMultiple(Multiple multiple);



     //试卷相关的接口
     Integer createTestByName(String testName);
     Integer getTestIdByName(String testName);
     List<Single> getSingleByRand();
     Integer insertTestSingle(Integer testId,Integer singleId);
     List<Multiple> getMultipleByRand();
     Integer insertTestMultiple(Integer testId,Integer multipleId);
     List<Fill> getFillByRand();
     Integer insertTestFill(Integer testId,Integer fillId);

     List<Single> getSingleByTestId(Integer testId);
     List<Multiple> getMultipleByTestId(Integer testId);
     List<Fill> getFillByTestId(Integer testId);





     //提交试卷之后给试卷打分
     String getSingleAnswerById(Integer singleId);
     String getMultipleAnswerById(Integer multipleId);
     String getFillAnswerById(Integer fillId);
     Integer insertGrade(Integer testId,Integer uid,String testDay,Integer grade);
//     根据用户名找到用户id
     Integer getUidByUname(String uname);
//     根据uid找到考试成绩集
     List<WrongQues> getWrongQuesByUid(Integer uid,Integer startRow,Integer pageSize);
     Integer getWrongQuesTotalByUid(Integer uid);
//     找到试题
     List<Test> getAllTest();

//     通过uid找到user详细信息
     UserInfo getUserInfoByUid(Integer uid);
//     通过uid找到uname
     String getUnameByUid(Integer uid);
//     通过uname找到password
     String getPasswordByUname(String uname);
//     根据uname更新密码
     Integer updatePasswordByUname(String uname,String newPassword);

     Integer updateUserInfo(UserInfo userInfo);
     Integer createOnlineTest1(String testName,String startTime,String endTime);
     Integer createNormalTest1(String testName);

//     错题集相关功能
     Integer insertFailSingle(Integer singleId,Integer uid);
     Integer insertFailMultiple(Integer multipleId,Integer uid);
     Integer insertFailFill(Integer fillId,Integer uid);

     List<Grade> checkHasTest(Integer testId,Integer uid);

     List<Single> findFailSingleByUid(Integer uid);
     List<Multiple> findFailMultipleByUid(Integer uid);
     List<Fill> findFailFillByUid(Integer uid);

     List<GradePro> findGradeByTestId(Integer testId);
     Integer getGradeByTestIdTotal(Integer testId);

//     在注册账号的时候往个人信息里面加一条带uid的空语句
     Integer setInfoByUid(Integer uid);

     List<Single> findSingleByQues(String Ques);

     List<Multiple> findMultipleByQues(String Ques);

     List<Fill> findFillByQues(String Ques);

     Integer getGradeByTestNameTotal(String testName);
     List<GradePro> findGradeByTestName(Integer startRow,Integer PageSize,String testName);

     GradePro findGradeByTestNameAndUname(String testName,String uname);
     Integer updateGrade(String testName,String uname,Integer grade);
     Integer deleteGrade(String testName,String uname);

     Integer getFillTotal();
     List<Fill> findFill(Integer startRow,Integer PageSize);

     Fill findFillById(Integer fillId);
     Integer updateFill(Fill fill);
     Integer deleteFillById(Integer fillId);
     Integer insertFill(Fill fill);

     Integer findTestTotal();
     List<Test> findTest(Integer startRow,Integer PageSize);
     Integer deleteTestById(Integer testId);

     Integer singleBeUsed(Integer singleId);
     Integer multipleBeUsed(Integer multipleId);
     Integer fillBeUsed(Integer fillId);

     Integer changeTestStatus1(Integer testId);
     Integer changeTestStatus2(Integer testId);

     Integer checkTestBeUsed(Integer testId);
}
