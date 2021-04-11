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
public class FarmProductMarketChief {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long farmProductMarketId;

    @Transactional
    public void getTotalAndScoreChangingRecord(
            FarmProductMarketRepository farmProductMarketRepository,
            ScoreChangeRecordsRepository scoreChangeRecordsRepository
    ){

        FarmProductMarket farmProductMarket = farmProductMarketRepository.findById((long)farmProductMarketId);

        System.out.println(
                "-------------------------------------\nScore Records For Market "
                        + farmProductMarket.getName()
                        + " :");
        System.out.println("Total Score: " + Integer.valueOf(farmProductMarket.getTotalScore()).toString());

        List<ScoreChangeRecords> scoreChangeRecords = scoreChangeRecordsRepository.findByTargetId(farmProductMarketId);

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
    public void getNotifications(RegulatoryTasksRepository regulatoryTasksRepository,
                                 NotificationRepository notificationRepository,
                                 RegulatorySingleTaskRepository regulatorySingleTaskRepository){
        List<Notification> results = notificationRepository.findByTargetPersonId(id);
        if (results.size() == 0) {
            System.out.println("No notifications.");
        }

        for (Notification temp: results) {
            RegulatorySingleTask regulatorySingleTask = regulatorySingleTaskRepository.findById((long)temp.getRegulatorySingleTaskId());
            RegulatoryTasks regulatoryTasks = regulatoryTasksRepository.findById((long)regulatorySingleTask.getRegulatoryTaskId());
            String retStr = "--------------\nMainTask ID: "+regulatorySingleTask.getRegulatoryTaskId().toString()
                    + "\nMainTask Name: " + regulatoryTasks.getName()
                    + "\nDeadline: " + regulatoryTasks.getDeadLine()
                    + "\nCategories of Inspecting: \n";
            for (SingleCategoryResult categoryResult : regulatorySingleTask.getRegulatorySingleTaskResult()) {
                retStr += categoryResult.getCategory() + "  ";
            }

            System.out.println(retStr);
        }
    }

    @Transactional
    public void completeSingleCategoryTask(RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                           RegulatorySingleTask regulatorySingleTask,
                                           String category,
                                           int totalNumOfInspect,
                                           int unqualifiedNumOfInspect,
                                           FarmProductMarketRepository farmProductMarketRepository,
                                           NotificationRepository notificationRepository,
                                           SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                           MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
                                           RegulatoryTasksRepository regulatoryTasksRepository) {
        Util.registerResultsForASingleCategory(regulatorySingleTaskRepository,
                regulatorySingleTask,
                category,
                totalNumOfInspect,
                unqualifiedNumOfInspect,
                farmProductMarketRepository,
                notificationRepository,
                samplingInspectionExpertRelationRepository,
                mainTaskExpertRelationRepository,
                regulatoryTasksRepository);
    }

    @Transactional
    public void getTotalAndScoreChangingRecord(Mask mask){
        getTotalAndScoreChangingRecord(mask.getFarmProductMarketRepository(),
                mask.getScoreChangeRecordsRepository());
    }

    @Transactional
    public void getNotifications(Mask mask){
        getNotifications(
                mask.getRegulatoryTasksRepository(),
                mask.getNotificationRepository(),
                mask.getRegulatorySingleTaskRepository()
        );
    }

    @Transactional
    public void completeSingleCategoryTask(
            RegulatorySingleTask regulatorySingleTask,
            String category,
            int totalNumOfInspect,
            int unqualifiedNumOfInspect,
            Mask mask
    ){
        completeSingleCategoryTask(
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




    public FarmProductMarketChief() {
    }

    public FarmProductMarketChief(Long farmProductMarketId) {
        this.farmProductMarketId = farmProductMarketId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmProductMarketId() {
        return farmProductMarketId;
    }

    public void setFarmProductMarketId(Long farmProductMarketId) {
        this.farmProductMarketId = farmProductMarketId;
    }
}
