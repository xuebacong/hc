package com.hucong.service;

import com.hucong.dao.UserDao;
import com.hucong.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserServiceImp implements UserService{
    private UserDao userDao;
    @Autowired
    public UserServiceImp(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public Integer enter(User user) {
       List<User> resultUser= userDao.enter(user);
       if(resultUser.size()==0){
           return 0;
       }else if(resultUser.get(0).getRole()==0){
               return 1;
       }
       return 2;
       //if(resultUser.get(0).getRole()=)
//       if(resultUser.size()==0) return false;
//       return true;
    }

    @Override
    public List<Single> findSingle(Integer startRow,Integer pageSize) {
        return userDao.findSingle(startRow,pageSize);
    }

    @Override
    public Integer getSingleTotal() {
        return userDao.getSingleTotal();
    }

    @Override
    public Single findSingleById(Integer id) {
        return userDao.findSingleById(id);
    }

    @Override
    public Integer updateSingle(Single single) {
        return userDao.updateSingle(single);
    }

    @Override
    public int deleteSingleById(Integer singleId) {
        return userDao.deleteSingleById(singleId);
    }

    @Override
    public int insertSingle(Single single) {
        return userDao.insertSingle(single);
    }

    @Override
    public List<Multiple> findMultiple(Integer startRow, Integer pageSize) {
        return userDao.findMultiple(startRow,pageSize);
    }

    @Override
    public Integer getMultipleTotal() {
        return userDao.getMultipleTotal();
    }

    @Override
    public Multiple findMultipleById(Integer multipleId) {
        return userDao.findMultipleById(multipleId);
    }

    @Override
    public Integer updateMultiple(Multiple multiple) {
        return userDao.updateMultiple(multiple);
    }

    @Override
    public Integer deleteMultiple(Integer multipleId) {
        return userDao.deleteMultiple(multipleId);
    }

    @Override
    public Integer insertMultiple(Multiple multiple) {
        return userDao.insertMultiple(multiple);
    }

    @Override
    public Integer createTestByName(String testName) {
        return userDao.createTestByName(testName);
    }

    @Override
    public Integer getTestIdByName(String testName) {
        return userDao.getTestIdByName(testName);
    }

    @Override
    public List<Single> getSingleByRand() {
        return userDao.getSingleByRand();
    }

    @Override
    public Integer insertTestSingle(Integer testId, Integer singleId) {
        return userDao.insertTestSingle(testId, singleId);
    }

    @Override
    public List<Multiple> getMultipleByRand() {
        return userDao.getMultipleByRand();
    }

    @Override
    public Integer insertTestMultiple(Integer testId, Integer multipleId) {
        return userDao.insertTestMultiple(testId,multipleId);
    }

    @Override
    public List<Fill> getFillByRand() {
        return userDao.getFillByRand();
    }

    @Override
    public Integer insertTestFill(Integer testId, Integer fillId) {
        return userDao.insertTestFill(testId,fillId);
    }

    @Override
    public List<Multiple> getMultipleByTestId(Integer testId) {
        return userDao.getMultipleByTestId(testId);
    }

    @Override
    public List<Fill> getFillByTestId(Integer testId) {
        return userDao.getFillByTestId(testId);
    }

    @Override
    public List<Single> getSingleByTestId(Integer testId) {
        return userDao.getSingleByTestId(testId);
    }

    @Override
    public String getSingleAnswerById(Integer singleId) {
        return userDao.getSingleAnswerById(singleId);
    }

    @Override
    public String getMultipleAnswerById(Integer multipleId) {
        return userDao.getMultipleAnswerById(multipleId);
    }

    @Override
    public String getFillAnswerById(Integer fillId) {
        return userDao.getFillAnswerById(fillId);
    }

    @Override
    public Integer insertGrade(Integer testId, Integer uid, String testDay, Integer grade) {
        return userDao.insertGrade(testId,uid,testDay,grade);
    }

    @Override
    public Integer getUidByUname(String uname) {
        return userDao.getUidByUname(uname);
    }

    @Override
    public List<WrongQues> getWrongQuesByUid(Integer uid,Integer startRow,Integer pageSize) {
        return userDao.getWrongQuesByUid(uid,startRow,pageSize);
    }

    @Override
    public Integer getWrongQuesTotalByUid(Integer uid) {
        return userDao.getWrongQuesTotalByUid(uid);
    }

    @Override
    public List<Test> getAllTest() {
        return userDao.getAllTest();
    }

    @Override
    public UserInfo getUserInfoByUid(Integer uid) {
        return userDao.getUserInfoByUid(uid);
    }

    @Override
    public String getUnameByUid(Integer uid) {
        return userDao.getUnameByUid(uid);
    }

    @Override
    public String getPasswordByUname(String uname) {
        return userDao.getPasswordByUname(uname);
    }

    @Override
    public Integer updatePasswordByUname(String uname, String newPassword) {
        return userDao.updatePasswordByUname(uname,newPassword);
    }

    @Override
    public Integer updateUserInfo(UserInfo userInfo) {
        return userDao.updateUserInfo(userInfo);
    }

    @Override
    public Integer createOnlineTest1(String testName, String startTime, String endTime) {
        return userDao.createOnlineTest1(testName,startTime,endTime);
    }

    @Override
    public Integer createNormalTest1(String testName) {
        return userDao.createNormalTest1(testName);
    }

    @Override
    public Integer insertFailSingle(Integer singleId, Integer uid) {
        return userDao.insertFailSingle(singleId,uid);
    }

    @Override
    public Integer insertFailMultiple(Integer multipleId, Integer uid) {
        return userDao.insertFailMultiple(multipleId,uid);
    }

    @Override
    public Integer insertFailFill(Integer fillId, Integer uid) {
        return userDao.insertFailFill(fillId,uid);
    }

    @Override
    public List<Grade> checkHasTest(Integer testId, Integer uid) {
        return userDao.checkHasTest(testId,uid);
    }

    @Override
    public List<Single> findFailSingleByUid(Integer uid) {
        return userDao.findFailSingleByUid(uid);
    }

    @Override
    public List<Multiple> findFailMultipleByUid(Integer uid) {
        return userDao.findFailMultipleByUid(uid);
    }

    @Override
    public List<Fill> findFailFillByUid(Integer uid) {
        return userDao.findFailFillByUid(uid);
    }

    @Override
    public List<GradePro> findGradeByTestId(Integer testId) {
        return userDao.findGradeByTestId(testId);
    }

    @Override
    public Integer getGradeByTestIdTotal(Integer testId) {
        return userDao.getGradeByTestIdTotal(testId);
    }

    @Override
    public Integer setInfoByUid(Integer uid) {
        return userDao.setInfoByUid(uid);
    }

    @Override
    public List<Single> findSingleByQues(String Ques) {
        return userDao.findSingleByQues(Ques);
    }

    @Override
    public List<Multiple> findMultipleByQues(String Ques) {
        return userDao.findMultipleByQues(Ques);
    }

    @Override
    public List<Fill> findFillByQues(String Ques) {
        return userDao.findFillByQues(Ques);
    }

    @Override
    public Integer getGradeByTestNameTotal(String testName) {
        return userDao.getGradeByTestNameTotal(testName);
    }

    @Override
    public List<GradePro> findGradeByTestName(Integer startRow, Integer PageSize, String testName) {
        return userDao.findGradeByTestName(startRow,PageSize,testName);
    }

    @Override
    public GradePro findGradeByTestNameAndUname(String testName, String uname) {
        return userDao.findGradeByTestNameAndUname(testName,uname);
    }

    @Override
    public Integer updateGrade(String testName, String uname, Integer grade) {
        return userDao.updateGrade(testName,uname,grade);
    }

    @Override
    public Integer deleteGrade(String testName, String uname) {
        return userDao.deleteGrade(testName,uname);
    }

    @Override
    public Integer getFillTotal() {
        return userDao.getFillTotal();
    }

    @Override
    public List<Fill> findFill(Integer startRow, Integer PageSize) {
        return userDao.findFill(startRow,PageSize);
    }

    @Override
    public Fill findFillById(Integer fillId) {
        return userDao.findFillById(fillId);
    }

    @Override
    public Integer updateFill(Fill fill) {
        return userDao.updateFill(fill);
    }

    @Override
    public Integer deleteFillById(Integer fillId) {
        return userDao.deleteFillById(fillId);
    }

    @Override
    public Integer insertFill(Fill fill) {
        return userDao.insertFill(fill);
    }

    @Override
    public Integer findTestTotal() {
        return userDao.findTestTotal();
    }

    @Override
    public List<Test> findTest(Integer startRow, Integer PageSize) {
        return userDao.findTest(startRow,PageSize);
    }

    @Override
    public Integer deleteTestById(Integer testId) {
        return userDao.deleteTestById(testId);
    }

    @Override
    public Integer singleBeUsed(Integer singleId) {
        return userDao.singleBeUsed(singleId);
    }

    @Override
    public Integer multipleBeUsed(Integer multipleId) {
        return userDao.multipleBeUsed(multipleId);
    }

    @Override
    public Integer fillBeUsed(Integer fillId) {
        return userDao.fillBeUsed(fillId);
    }

    @Override
    public Integer changeTestStatus1(Integer testId) {
        return userDao.changeTestStatus1(testId);
    }

    @Override
    public Integer changeTestStatus2(Integer testId) {
        return userDao.changeTestStatus2(testId);
    }

    @Override
    public Integer checkTestBeUsed(Integer testId) {
        return userDao.checkTestBeUsed(testId);
    }


}
