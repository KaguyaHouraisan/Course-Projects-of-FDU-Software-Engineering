package DataBase1.domain;

import DataBase1.IndicatorService;
import DataBase1.Mask;
import DataBase1.Util;
import DataBase1.constant.SystemConstant;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.*;
import DataBase1.repository.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

@Entity
public class Inspector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transactional
    public void launchRegulatoryTask(String taskName, ArrayList<Long> marketsId, String date, String deadline, ArrayList<String> categories,
                                     RegulatoryTasksRepository regulatoryTasksRepository ,
                                     SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                     RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                     FarmProductMarketRepository farmProductMarketRepository,
                                     FarmProductMarketChiefRepository farmProductMarketChiefRepository,
                                     NotificationRepository notificationRepository,
                                     ExpertRepository expertRepository,
                                     MainTaskExpertRelationRepository mainTaskExpertRelationRepository) {
        RegulatoryTasks regulatoryTasks = new RegulatoryTasks(taskName,date,deadline,false);
        regulatoryTasksRepository.save(regulatoryTasks);
        launchOriginalRegulatory(taskName,
                marketsId,
                categories,
                regulatorySingleTaskRepository,
                farmProductMarketRepository,
                regulatoryTasks,
                farmProductMarketChiefRepository,
                notificationRepository
        );

        ArrayList<Integer> randomArray1 = Util.randomNumber(1, marketsId.size() + 1, (int)(Math.ceil(marketsId.size() * SystemConstant.samplingRate)));
        ArrayList<Integer> randomArray2 = Util.randomNumber(1, categories.size() + 1, (int)(Math.ceil(categories.size() * SystemConstant.samplingRate)));
        ArrayList<Long> samplingMarketId = new ArrayList<>();
        ArrayList<String> samplingCategory = new ArrayList<>();
        assert randomArray1 != null;
        for (Integer i : randomArray1) {
            samplingMarketId.add(marketsId.get(i - 1));
        }
        assert randomArray2 != null;
        for (Integer i : randomArray2) {
            samplingCategory.add(categories.get(i - 1));
        }
        int i = 0;
        ArrayList<Integer> randomArray3 = Util.randomNumber(1, (int)(expertRepository.count() + 1), 1);
        assert randomArray3 != null;
        long expertId = 0;
        int randomSelectedExpertNum = randomArray3.get(0);
        for (Expert tempExpert : expertRepository.findAll()) {
            i++;
            if (i == randomSelectedExpertNum) {
                expertId = tempExpert.getId();
            }
        }

        launchSamplingRegulatory(
                taskName,
                expertId,
                samplingMarketId,
                samplingCategory,
                samplingInspectionExpertRelationRepository,
                regulatorySingleTaskRepository,
                farmProductMarketRepository,
                regulatoryTasks,
                notificationRepository,
                mainTaskExpertRelationRepository
        );
    }

    @Transactional
    void launchOriginalRegulatory(String taskName,
                                          ArrayList<Long> marketsId,
                                          ArrayList<String> categories,
                                          RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                          FarmProductMarketRepository farmProductMarketRepository,
                                          RegulatoryTasks regulatoryTasks,
                                          FarmProductMarketChiefRepository farmProductMarketChiefRepository,
                                          NotificationRepository notificationRepository) {
        System.out.println("-------------------------------------------------");
        System.out.println("Inspect Task " + taskName + " starts launching... ");

        FarmProductMarket farmProductMarket;
        for (Long marketId : marketsId){
            farmProductMarket =farmProductMarketRepository.findById((long)marketId);

            Set<SingleCategoryResult> results = new HashSet<>();
            for (String category: categories){
                SingleCategoryResult singleCategoryResult = new SingleCategoryResult(category, false, 0, 0, IndicatorService.getTime());
                if (farmProductMarket.getCategoriesOfMarket().contains(category)) {
                    results.add(singleCategoryResult);
                }
            }

            RegulatorySingleTask regulatoryResult = new RegulatorySingleTask(marketId,regulatoryTasks.getId(),false,0,null,false,results);
            regulatorySingleTaskRepository.save(regulatoryResult);

            FarmProductMarketChief farmProductMarketChief = farmProductMarketChiefRepository.findByFarmProductMarketId(marketId);
            if (farmProductMarketChief != null) {
                Notification notification = new Notification(farmProductMarketChief.getId(), regulatoryResult.getId());
                notificationRepository.save(notification);
            } else {
                return;
            }

            System.out.println("Inspect Task to market " + farmProductMarket.getName() + " is launched");
        }

        System.out.println("Inspect Task " + taskName + " is launched ");
    }

    @Transactional
    void launchSamplingRegulatory(String taskName,
                                  Long expertId,
                                  ArrayList<Long> samplingMarketsId,
                                  ArrayList<String> samplingCategories,
                                  SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                  RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                  FarmProductMarketRepository farmProductMarketRepository,
                                  RegulatoryTasks regulatoryTasks,
                                  NotificationRepository notificationRepository,
                                  MainTaskExpertRelationRepository mainTaskExpertRelationRepository
                                         ){

        System.out.println("-------------------------------------------------");
        System.out.println("Sampling Inspect for Task " + taskName + " by expert " + expertId + " starts launching... ");

        MainTaskExpertRelation mainTaskExpertRelation = new MainTaskExpertRelation(expertId,regulatoryTasks.getId(),regulatoryTasks.getName(),0,false,regulatoryTasks.getDeadLine());
        mainTaskExpertRelationRepository.save(mainTaskExpertRelation);

        for (Long marketId:samplingMarketsId){
            FarmProductMarket farmProductMarket =farmProductMarketRepository.findById((long)marketId);

            Set<SingleCategoryResult> results = new HashSet<>();
            for (String category: samplingCategories){
                SingleCategoryResult singleCategoryResult = new SingleCategoryResult(category, false, 0, 0, IndicatorService.getTime());
                if (farmProductMarket.getCategoriesOfMarket().contains(category)) {
                    results.add(singleCategoryResult);
                }
            }

            RegulatorySingleTask regulatorySingleTask = new RegulatorySingleTask(marketId, regulatoryTasks.getId(), false, 0, null, true, results);
            regulatorySingleTaskRepository.save(regulatorySingleTask);

            SamplingInspectionExpertRelation samplingInspectionExpertRelation = new SamplingInspectionExpertRelation(expertId,regulatorySingleTask.getId());
            samplingInspectionExpertRelationRepository.save(samplingInspectionExpertRelation);

            Notification notification = new Notification(expertId, regulatorySingleTask.getId());
            notificationRepository.save(notification);

            System.out.println("Sampling Inspect Task to market " + farmProductMarket.getName() + " is launched");
        }

        System.out.println("Sampling Inspect for Task " + taskName + " by expert " + expertId + " is launched ");
    }

    @Transactional
    public void getUnDoneSituation(
            RegulatoryTasksRepository regulatoryTasksRepository,
            RegulatorySingleTaskRepository regulatorySingleTaskRepository,
            MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
            FarmProductMarketRepository farmProductMarketRepository

    ){

        Iterable<RegulatoryTasks> regulatoryTasks = regulatoryTasksRepository.findAll();
        System.out.println(
                "--------------------------------------------------------\nAll Regulatory Tasks :  ");
        for (RegulatoryTasks regulatoryTasks1:regulatoryTasks){
            System.out.println("MainRegulatoryTask "
                    + regulatoryTasks1.getName()
                    + " Start Date: "
                    + regulatoryTasks1.getDate()
                    + " Deadline: "
                    + regulatoryTasks1.getDeadLine()
                    + " Status: "
                    + ((regulatoryTasks1.isWhetherDone())?"Done":"UnDone"));
            String selfInspectOut = "- - - - - - - - - - - - - - - - - - - -\n"
                    + "SelfInspect Situation: \n";

            List<RegulatorySingleTask> regulatorySingleTasks = regulatorySingleTaskRepository.findByRegulatoryTaskIdAndIfSampling(
                    regulatoryTasks1.getId(),
                    false);
            for (RegulatorySingleTask regulatorySingleTask:regulatorySingleTasks){
                FarmProductMarket farmProductMarket = farmProductMarketRepository.findById((long)regulatorySingleTask.getFarmProductMarketId());
                String singleOut = "SelfTask for Market "
                        + farmProductMarket.getName()
                        + "  Status: " + (regulatorySingleTask.isWhetherDone()?"Done":"UnDone")
                        + "\n";
                selfInspectOut += singleOut;
            }
            System.out.println(selfInspectOut);


            MainTaskExpertRelation mainTaskExpertRelation = mainTaskExpertRelationRepository.findByMainTaskId(regulatoryTasks1.getId());
            String expertSamplingInspectOut = "- - - - - - - - - - - - - - - - - - - -\n"
                    + "Expert Sampling Inspect Situation for Expert " + mainTaskExpertRelation.getExpertId() + ": "
                    + "Status: "
                    + (mainTaskExpertRelation.isDone()?"Done":"UnDone")
                    + "\n";
            System.out.println(expertSamplingInspectOut);

            System.out.println("------------------------------------------------------------");

        }
    }

    @Transactional
    Boolean ifRegulatorySingleTaskInCheckRange(
            String dateBegin,
            String dateEnd,
            RegulatorySingleTask regulatorySingleTask,
            RegulatoryTasks regulatoryTasks
    ){

        boolean ret = true;

        String taskBegin = regulatoryTasks.getDate();

        //主task开始日期晚于搜索截止日期
        if (IndicatorService.ifDateSequenceTrue(dateEnd,taskBegin)){
            ret = false;
        }
        //SingleTask完成日期早于搜索开始日期
        String doneDate = regulatorySingleTask.getDoneDate();
        if (doneDate!=null && IndicatorService.ifDateSequenceTrue(doneDate,dateBegin)) {
            ret = false;
        }

        return ret;
    }

    @Transactional
    public int checkTotalUnqualifiedNumOfInspect(
            String category,
            String dateBegin,
            String dateEnd,
            RegulatorySingleTaskRepository regulatorySingleTaskRepository,
            RegulatoryTasksRepository regulatoryTasksRepository
            ){

        int totalNum = 0;

        Iterable<RegulatorySingleTask> regulatorySingleTasks = regulatorySingleTaskRepository.findAll();

        for (RegulatorySingleTask regulatorySingleTask: regulatorySingleTasks){
            RegulatoryTasks regulatoryTasks = regulatoryTasksRepository.findById((long)regulatorySingleTask.getRegulatoryTaskId());

            if (ifRegulatorySingleTaskInCheckRange(
                    dateBegin,
                    dateEnd,
                    regulatorySingleTask,
                    regulatoryTasks
            )){
                System.out.println("-----------------------------------");

                for (SingleCategoryResult singleCategoryResult:regulatorySingleTask.getRegulatorySingleTaskResult()){

                    if (singleCategoryResult.isWhetherDone() && singleCategoryResult.getCategory().equals(category)){
                        totalNum+=singleCategoryResult.getUnqualifiedNumOfInspect();
                        String out = "Regulatory Task "
                                + regulatoryTasks.getName()
                                + " --- Single Task Id "
                                + regulatorySingleTask.getId().toString()
                                + " "
                                + category
                                + " unqualified Number:"
                                + singleCategoryResult.getUnqualifiedNumOfInspect();
                        System.out.println(out);
                    }
                }
            }
        }
        System.out.println("Total unqualified number of category " + category + " is " + totalNum + " from " + dateBegin + " to " + dateEnd);
        return totalNum;
    }

    @Transactional
    public void launchRegulatoryTask(String taskName, ArrayList<Long> marketsId, String date, String deadline, ArrayList<String> categories, Mask mask){
        launchRegulatoryTask(
                taskName,
                marketsId,
                date,
                deadline,
                categories,
                mask.getRegulatoryTasksRepository(),
                mask.getSamplingInspectionExpertRelationRepository(),
                mask.getRegulatorySingleTaskRepository(),
                mask.getFarmProductMarketRepository(),
                mask.getFarmProductMarketChiefRepository(),
                mask.getNotificationRepository(),
                mask.getExpertRepository(),
                mask.getMainTaskExpertRelationRepository()
        );
    }

    @Transactional
    public void getUnDoneSituation(Mask mask){
        getUnDoneSituation(
                mask.getRegulatoryTasksRepository(),
                mask.getRegulatorySingleTaskRepository(),
                mask.getMainTaskExpertRelationRepository(),
                mask.getFarmProductMarketRepository()
        );
    }

    @Transactional
    public int checkTotalUnqualifiedNumOfInspect(String category,
                                                 String dateBegin,
                                                 String dateEnd,
                                                 Mask mask){
        return checkTotalUnqualifiedNumOfInspect(
                category,
                dateBegin,
                dateEnd,
                mask.getRegulatorySingleTaskRepository(),
                mask.getRegulatoryTasksRepository()
        );
    }


    public Inspector() {
    }

    public Inspector(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
