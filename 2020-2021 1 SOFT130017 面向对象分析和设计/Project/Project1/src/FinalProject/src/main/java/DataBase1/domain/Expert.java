package DataBase1.domain;

import DataBase1.Mask;
import DataBase1.Util;
import DataBase1.relation.Notification;
import DataBase1.relation.RegulatorySingleTask;
import DataBase1.relation.ScoreChangeRecords;
import DataBase1.relation.SingleCategoryResult;
import DataBase1.repository.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int totalScore;

    @Transactional
    public void getTotalAndScoreChangingRecord(ScoreChangeRecordsRepository scoreChangeRecordsRepository){
        System.out.println(
                "-------------------------------------\nScore Records For Expert "
                        + id.toString()
                        + " :");
        System.out.println("Total Score: " + Integer.valueOf(totalScore).toString());
        List<ScoreChangeRecords> scoreChangeRecords = scoreChangeRecordsRepository.findByTargetId(id);

        for (ScoreChangeRecords scoreChangeRecords1: scoreChangeRecords){
            String out = "Date: "
                    + scoreChangeRecords1.getRecordDate()
                    + " Score: "
                    + Integer.valueOf(scoreChangeRecords1.getScoreChange()).toString()
                    + " Reason: "
                    + scoreChangeRecords1.getReason()
                    ;

            System.out.println(out);
            System.out.println("--------------------------------------------------------");
        }
    }

    @Transactional
    public void getNotifications(NotificationRepository notificationRepository,
                                 RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                 RegulatoryTasksRepository regulatoryTasksRepository,
                                 FarmProductMarketRepository farmProductMarketRepository) {
        List<Notification> notifications = notificationRepository.findByTargetPersonId(getId());
        for (Notification temp : notifications) {
            RegulatorySingleTask regulatorySingleTask = regulatorySingleTaskRepository.findById((long)temp.getRegulatorySingleTaskId());
            RegulatoryTasks regulatoryTasks = regulatoryTasksRepository.findById((long)regulatorySingleTask.getRegulatoryTaskId());
            FarmProductMarket farmProductMarket = farmProductMarketRepository.findById((long)regulatorySingleTask.getFarmProductMarketId());

            if (farmProductMarket == null) {
                return;
            }

            String retStr = "--------------\nMainTask ID: "+regulatorySingleTask.getRegulatoryTaskId().toString()
                    + "\nMainTask Name: " + regulatoryTasks.getName()
                    + "\nFarm Product Market: " + farmProductMarket.getName()
                    + "\nDeadline: " + regulatoryTasks.getDeadLine()
                    + "\nCategories of Inspecting: \n";
            for (SingleCategoryResult categoryResult : regulatorySingleTask.getRegulatorySingleTaskResult()){
                retStr += categoryResult.getCategory() + "  ";
            }

            System.out.println(retStr);
        }
    }

    @Transactional
    public void registerResults(RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                RegulatorySingleTask regulatorySingleTask,
                                String category,
                                int totalNumOfInspect,
                                int unqualifiedNumOfInspect,
                                FarmProductMarketRepository farmProductMarketRepository,
                                NotificationRepository notificationRepository,
                                SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
                                RegulatoryTasksRepository regulatoryTasksRepository){
        Util.registerResultsForASingleCategory(
                regulatorySingleTaskRepository,
                regulatorySingleTask, category,
                totalNumOfInspect,
                unqualifiedNumOfInspect,
                farmProductMarketRepository,
                notificationRepository,
                samplingInspectionExpertRelationRepository,
                mainTaskExpertRelationRepository,
                regulatoryTasksRepository);
    }


    public Expert() {
    }

    @Transactional
    public void getTotalAndScoreChangingRecord(
            Mask mask
    ){
        getTotalAndScoreChangingRecord(mask.getScoreChangeRecordsRepository());
    }

    @Transactional
    public void getNotifications(Mask mask){
        getNotifications(
                mask.getNotificationRepository(),
                mask.getRegulatorySingleTaskRepository(),
                mask.getRegulatoryTasksRepository(),
                mask.getFarmProductMarketRepository()
        );
    }

    @Transactional
    public void registerResults(
            RegulatorySingleTask regulatorySingleTask,
            String category,
            int totalNumOfInspect,
            int unqualifiedNumOfInspect,
            Mask mask
    ){
        registerResults(
                mask.getRegulatorySingleTaskRepository(),
                regulatorySingleTask,
                category,
                totalNumOfInspect,
                unqualifiedNumOfInspect,
                mask.getFarmProductMarketRepository(),
                mask.getNotificationRepository(),
                mask.getSamplingInspectionExpertRelationRepository(),
                mask.getMainTaskExpertRelationRepository(),
                mask.getRegulatoryTasksRepository()
        );
    }




    public Expert(int totalScore) {
        this.totalScore = totalScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
