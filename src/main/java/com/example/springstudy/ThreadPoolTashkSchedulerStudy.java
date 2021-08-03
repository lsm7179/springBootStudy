package com.example.springstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

public class ThreadPoolTashkSchedulerStudy {

    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    private Map<String, ScheduledFuture<?>> jobMap=new HashMap<>();


    public void exampleMethod(){
        scheduler.setPoolSize(10); //스레드풀의 사이즈를 결정합니다.

        //스케줄러에 실행하고 싶은 Task 넣기
        RunnableEx runnableEx=new RunnableEx();
        ScheduledFuture<?> scheduledFuture=scheduler.schedule(runnableEx,new CronTrigger("0 0 12 * *"));//매일 12시(정오)에 실행되는 Task를 스케줄에 등록함.
        jobMap.put("testId",scheduledFuture);

        scheduler.shutdown(); //작업중인 스케줄러를 중지합니다.
        shutDown=true;
        jobMap.remove("testId");
    }


    class RunnableEx implements Runnable {

        @Override
        public void run() {
            try{
                System.out.println("배치 시작");
                serviceLogic();//실행하고 싶은 서비스 로직 구현
                System.out.println("배치 종료");
            }catch (InterruptedException e){
                //배치가 강제종료되면 여기로 넘어오게됩니다.
                e.printStackTrace();
            }finally {
                //do finally Logic
            }
        }
    }

    private boolean shutDown=false;

    public void isStop(){
        scheduler.shutdown(); //작업중인 스케줄러를 중지합니다.
        shutDown=true;  // InterruptedException를 일으키는 boolean
    }

    public void serviceLogic() throws InterruptedException {
        //do service
        for(int i=0;i<10;i++){
            if(shutDown) throw new InterruptedException();
            selectDb();
            updateDb();
            insertLog();
        }
    }

    public void updateDb(){
        //updateDB
    }

    public void selectDb(){
        //selectDB
    }

    public void insertLog(){
        //insertDB
    }





}
