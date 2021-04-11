package DataBase1;

import DataBase1.constant.Score;
import DataBase1.domain.Expert;
import DataBase1.domain.FarmProductMarket;
import DataBase1.domain.FarmProductMarketChief;
import DataBase1.domain.RegulatoryTasks;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.*;
import DataBase1.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Util {
    public static ArrayList<Integer> randomNumber(int min, int max, int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        //判断是否已经达到索要输出随机数的个数
        if (n > (max - min + 1) || max < min) {
            return null;
        }

        while (arrayList.size() < n) {
            int num = (int)(Math.random() * (max - min)) + min;

            if (!arrayList.contains(num)) {
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    public static void registerResultsForASingleCategory(RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                                         RegulatorySingleTask regulatorySingleTask,
                                                         String category,
                                                         int totalNumOfInspect,
                                                         int unqualifiedNumOfInspect,
                                                         FarmProductMarketRepository farmProductMarketRepository,
                                                         NotificationRepository notificationRepository,
                                                         SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                                         MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
                                                         RegulatoryTasksRepository regulatoryTasksRepository) {
        Set<SingleCategoryResult> singleCategoryResultSet = regulatorySingleTask.getRegulatorySingleTaskResult();
        SingleCategoryResult singleCategoryResult = null;
        for (SingleCategoryResult temp : singleCategoryResultSet) {
            if (temp.getCategory().equals(category)) {
                singleCategoryResult = temp;
                break;
            }
        }

        if (singleCategoryResult == null) {
            return;
        }

        singleCategoryResult.setWhetherDone(true);
        singleCategoryResult.setTotalNumOfInspect(totalNumOfInspect);
        singleCategoryResult.setUnqualifiedNumOfInspect(unqualifiedNumOfInspect);
        singleCategoryResultSet.add(singleCategoryResult);
        regulatorySingleTask.setRegulatorySingleTaskResult(singleCategoryResultSet);
        regulatorySingleTaskRepository.save(regulatorySingleTask);

        checkIfRegulatorySingleTaskIsCompleted(regulatorySingleTask, farmProductMarketRepository, regulatorySingleTaskRepository, notificationRepository, samplingInspectionExpertRelationRepository, mainTaskExpertRelationRepository, regulatoryTasksRepository);
    }

    public static void checkIfRegulatorySingleTaskIsCompleted(RegulatorySingleTask regulatorySingleTask,
                                                              FarmProductMarketRepository farmProductMarketRepository,
                                                              RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                                              NotificationRepository notificationRepository,
                                                              SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                                              MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
                                                              RegulatoryTasksRepository regulatoryTasksRepository) {
        boolean isDone = true;
        Set<SingleCategoryResult> singleCategoryResultSet = regulatorySingleTask.getRegulatorySingleTaskResult();
        for (SingleCategoryResult temp : singleCategoryResultSet) {
            if (!temp.isWhetherDone()) {
                isDone = false;
                break;
            }
        }

        FarmProductMarket farmProductMarket = farmProductMarketRepository.findById((long)regulatorySingleTask.getFarmProductMarketId());
        if (farmProductMarket == null) {
            throw new ErrorCode(ErrorCode.MARKET_CHIEF_NOT_FOUND_FAILURE);
        }

        if (isDone) {
            regulatorySingleTask.setWhetherDone(true);
            regulatorySingleTaskRepository.save(regulatorySingleTask);

            Notification notification = notificationRepository.findByRegulatorySingleTaskId(regulatorySingleTask.getId());
            notificationRepository.delete(notification);

            System.out.println("----------------------------------------");
            System.out.println("All tasks of farm market " + farmProductMarket.getName() + " have been completed. ");
            System.out.println("----------------------------------------");

            if (regulatorySingleTask.isIfSampling()) {
                checkIfAllRegulatorySingleTasksOfExpertAreCompleted(regulatorySingleTask.getRegulatoryTaskId(), regulatorySingleTaskRepository, samplingInspectionExpertRelationRepository, mainTaskExpertRelationRepository, regulatoryTasksRepository);
            } else {
                checkIfAllRegulatorySingleTasksOfOriginalAreCompleted(regulatorySingleTask.getRegulatoryTaskId(), regulatorySingleTaskRepository, mainTaskExpertRelationRepository, samplingInspectionExpertRelationRepository, regulatoryTasksRepository);
            }
        } else {
            System.out.println("----------------------------------------");
            System.out.println("Some tasks of farm market " + farmProductMarket.getName() + " have not been completed. ");
            System.out.println("----------------------------------------");
        }
    }

    public static void checkIfAllRegulatorySingleTasksOfExpertAreCompleted(long regulatoryTaskId,
                                                                           RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                                                           SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                                                           MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
                                                                           RegulatoryTasksRepository regulatoryTasksRepository) {
        List<RegulatorySingleTask> regulatorySingleTasks = regulatorySingleTaskRepository.findByRegulatoryTaskIdAndIfSampling(regulatoryTaskId, true);
        boolean isDone = true;
        Long regulatorySingleTaskId = (long)0;
        for (RegulatorySingleTask temp : regulatorySingleTasks) {
            regulatorySingleTaskId = temp.getId();
            if (!temp.isWhetherDone()) {
                isDone = false;
                break;
            }
        }

        SamplingInspectionExpertRelation samplingInspectionExpertRelations = samplingInspectionExpertRelationRepository.findByRegulatorySingleTaskId(regulatorySingleTaskId);
        if (samplingInspectionExpertRelations == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        }

        if (isDone) {
            MainTaskExpertRelation mainTaskExpertRelation = mainTaskExpertRelationRepository.findByExpertIdAndMainTaskId(samplingInspectionExpertRelations.getExpertId(), regulatoryTaskId);
            mainTaskExpertRelation.setDone(true);
            mainTaskExpertRelationRepository.save(mainTaskExpertRelation);

            System.out.println("----------------------------------------");
            System.out.println("All tasks of expert " + samplingInspectionExpertRelations.getExpertId() + " have been completed. ");
            System.out.println("----------------------------------------");

            checkMainTaskIsCompleted(regulatoryTaskId,
                    mainTaskExpertRelationRepository,
                    samplingInspectionExpertRelationRepository,
                    regulatorySingleTaskRepository,
                    regulatoryTasksRepository);
        } else {
            System.out.println("----------------------------------------");
            System.out.println("Some tasks of expert " + samplingInspectionExpertRelations.getExpertId() + " have not been completed. ");
            System.out.println("----------------------------------------");
        }
    }

    public static void checkIfAllRegulatorySingleTasksOfOriginalAreCompleted(long regulatoryTaskId,
                                                                             RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                                                             MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
                                                                             SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                                                             RegulatoryTasksRepository regulatoryTasksRepository) {
        List<RegulatorySingleTask> regulatorySingleTasks = regulatorySingleTaskRepository.findByRegulatoryTaskIdAndIfSampling(regulatoryTaskId, false);
        boolean isDone = true;
        for (RegulatorySingleTask temp : regulatorySingleTasks) {
            if (!temp.isWhetherDone()) {
                isDone = false;
                break;
            }
        }

        if (isDone) {
            System.out.println("----------------------------------------");
            System.out.println("All routine regulatory tasks of regulatory task No." + regulatoryTaskId + " have been completed. ");
            System.out.println("----------------------------------------");

            checkMainTaskIsCompleted(regulatoryTaskId,
                    mainTaskExpertRelationRepository,
                    samplingInspectionExpertRelationRepository,
                    regulatorySingleTaskRepository,
                    regulatoryTasksRepository);
        } else {
            System.out.println("----------------------------------------");
            System.out.println("Some routine regulatory tasks of regulatory task No." + regulatoryTaskId + " have not been completed. ");
            System.out.println("----------------------------------------");
        }
    }

    public static void checkMainTaskIsCompleted(long regulatoryTaskId,
                                                MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
                                                SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository,
                                                RegulatorySingleTaskRepository regulatorySingleTaskRepository,
                                                RegulatoryTasksRepository regulatoryTasksRepository) {
        boolean isDone = true;

        List<RegulatorySingleTask> regulatorySingleTasks1 = regulatorySingleTaskRepository.findByRegulatoryTaskIdAndIfSampling(regulatoryTaskId, false);
        for (RegulatorySingleTask temp : regulatorySingleTasks1) {
            if (!temp.isWhetherDone()) {
                isDone = false;
                break;
            }
        }

        List<RegulatorySingleTask> regulatorySingleTasks2 = regulatorySingleTaskRepository.findByRegulatoryTaskIdAndIfSampling(regulatoryTaskId, true);
        for (RegulatorySingleTask temp : regulatorySingleTasks2) {
            if (!temp.isWhetherDone()) {
                isDone = false;
                break;
            }
        }

        RegulatoryTasks regulatoryTask = regulatoryTasksRepository.findById(regulatoryTaskId);
        if (regulatoryTask == null) {
            throw new ErrorCode(ErrorCode.TASK_NOT_FOUND);
        }

        if (isDone) {
            regulatoryTask.setWhetherDone(true);
            regulatoryTasksRepository.save(regulatoryTask);

            System.out.println("----------------------------------------");
            System.out.println("All tasks of regulatory task " + regulatoryTask.getName() + " have been completed. ");
            System.out.println("----------------------------------------");
        } else {
            System.out.println("----------------------------------------");
            System.out.println("Some tasks of regulatory task " + regulatoryTask.getName() + " have not been completed. ");
            System.out.println("----------------------------------------");
        }
    }

    public static void dailyCheck(
            ExpertRepository expertRepository,
            FarmProductMarketChiefRepository farmProductMarketChiefRepository,
            FarmProductMarketRepository farmProductMarketRepository,
            RegulatoryTasksRepository regulatoryTasksRepository,
            RegulatorySingleTaskRepository regulatorySingleTaskRepository,
            MainTaskExpertRelationRepository mainTaskExpertRelationRepository,
            ScoreChangeRecordsRepository scoreChangeRecordsRepository
    ){
        //check for expert
        Iterable<Expert> experts = expertRepository.findAll();
        for (Expert expert: experts){

            List<MainTaskExpertRelation> mainTaskExpertRelations = mainTaskExpertRelationRepository.findByExpertId(expert.getId());
            for (MainTaskExpertRelation mainTaskExpertRelation:mainTaskExpertRelations){
                int days = IndicatorService.minusDate(mainTaskExpertRelation.getDeadline(), IndicatorService.getTime());
                int scoreChange = 0;
                ScoreChangeRecords scoreChangeRecords = new ScoreChangeRecords(expert.getId(),IndicatorService.getTime(),scoreChange,"");

                if (mainTaskExpertRelation.getScore()==0 && mainTaskExpertRelation.isDone()){
                    mainTaskExpertRelation.setScore(Score.ONTIME);
                    scoreChange = Score.ONTIME;
                    String reason = "Sampling inspect for Main Task "
                            + mainTaskExpertRelation.getMainTaskName()
                            + " is Done at "
                            + IndicatorService.getTime();
                    scoreChangeRecords.setReason(reason);
                    scoreChangeRecords.setScoreChange(scoreChange);
                    scoreChangeRecordsRepository.save(scoreChangeRecords);
                }
                else if (mainTaskExpertRelation.getScore()==0 && days>=10){
                    mainTaskExpertRelation.setScore(Score.OUTTIME);
                    scoreChange = Score.OUTTIME;
                    String reason = "Sampling inspect for Main Task "
                            + mainTaskExpertRelation.getMainTaskName()
                            + " is over deadline 10 days at "
                            + IndicatorService.getTime();
                    scoreChangeRecords.setReason(reason);
                    scoreChangeRecords.setScoreChange(scoreChange);
                    scoreChangeRecordsRepository.save(scoreChangeRecords);
                }
                else if (mainTaskExpertRelation.getScore()==Score.OUTTIME && days>=20 && !mainTaskExpertRelation.isDone()){
                    mainTaskExpertRelation.setScore(mainTaskExpertRelation.getScore()+Score.OUTTIME_OVER_20_DAYS);
                    scoreChange = Score.OUTTIME_OVER_20_DAYS;
                    String reason = "Sampling inspect for Main Task "
                            + mainTaskExpertRelation.getMainTaskName()
                            + " is over deadline 20 days at "
                            + IndicatorService.getTime();
                    scoreChangeRecords.setReason(reason);
                    scoreChangeRecords.setScoreChange(scoreChange);
                    scoreChangeRecordsRepository.save(scoreChangeRecords);
                }

                mainTaskExpertRelationRepository.save(mainTaskExpertRelation);

                expert.setTotalScore(expert.getTotalScore()+scoreChange);
                expertRepository.save(expert);
            }
        }


        //check for marketchief
        Iterable<FarmProductMarketChief> farmProductMarketChiefs = farmProductMarketChiefRepository.findAll();
        for (FarmProductMarketChief farmProductMarketChief:farmProductMarketChiefs){
            FarmProductMarket farmProductMarket = farmProductMarketRepository.findById((long)farmProductMarketChief.getFarmProductMarketId());
            List<RegulatorySingleTask> regulatorySingleTasks = regulatorySingleTaskRepository.findByFarmProductMarketIdAndIfSampling(farmProductMarketChief.getFarmProductMarketId(),false);
            for (RegulatorySingleTask regulatorySingleTask:regulatorySingleTasks){
                RegulatoryTasks regulatoryTasks = regulatoryTasksRepository.findById((long)regulatorySingleTask.getRegulatoryTaskId());
                String deadline = regulatoryTasks.getDeadLine();

                int days = IndicatorService.minusDate(deadline,IndicatorService.getTime());
                int scoreChange = 0;

                ScoreChangeRecords scoreChangeRecords = new ScoreChangeRecords(farmProductMarket.getId(),IndicatorService.getTime(),scoreChange,"");

                if (regulatorySingleTask.getScore()==0 && regulatorySingleTask.isWhetherDone()){
                    regulatorySingleTask.setScore(Score.ONTIME);
                    scoreChange = Score.ONTIME;
                    String reason = "Self inspect for Main Task "
                            + regulatoryTasks.getName()
                            + " is Done at "
                            + IndicatorService.getTime();
                    scoreChangeRecords.setReason(reason);
                    scoreChangeRecords.setScoreChange(scoreChange);
                    scoreChangeRecordsRepository.save(scoreChangeRecords);
                }
                else if(regulatorySingleTask.getScore()==0 && days>=10){
                    regulatorySingleTask.setScore(Score.OUTTIME);
                    scoreChange = Score.OUTTIME;
                    String reason = "Self inspect for Main Task "
                            + regulatoryTasks.getName()
                            + " is over deadline 10 days at "
                            + IndicatorService.getTime();
                    scoreChangeRecords.setReason(reason);
                    scoreChangeRecords.setScoreChange(scoreChange);
                    scoreChangeRecordsRepository.save(scoreChangeRecords);
                }
                else if(regulatorySingleTask.getScore()==Score.OUTTIME && days>=20 && !regulatorySingleTask.isWhetherDone()){
                    regulatorySingleTask.setScore(regulatorySingleTask.getScore()+Score.OUTTIME_OVER_20_DAYS);
                    scoreChange = Score.OUTTIME_OVER_20_DAYS;
                    String reason = "Self inspect for Main Task "
                            + regulatoryTasks.getName()
                            + " is over deadline 20 days at "
                            + IndicatorService.getTime();
                    scoreChangeRecords.setReason(reason);
                    scoreChangeRecords.setScoreChange(scoreChange);
                    scoreChangeRecordsRepository.save(scoreChangeRecords);
                }

                regulatorySingleTaskRepository.save(regulatorySingleTask);

                farmProductMarket.setTotalScore(farmProductMarket.getTotalScore()+scoreChange);
                farmProductMarketRepository.save(farmProductMarket);
            }

        }


    }
}
