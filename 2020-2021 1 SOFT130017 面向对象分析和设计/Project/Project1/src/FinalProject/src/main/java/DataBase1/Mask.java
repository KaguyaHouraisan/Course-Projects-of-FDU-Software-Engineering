package DataBase1;

import DataBase1.domain.*;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.Notification;
import DataBase1.relation.RegulatorySingleTask;
import DataBase1.relation.SamplingInspectionExpertRelation;
import DataBase1.relation.SingleCategoryResult;
import DataBase1.repository.*;
import DataBase1.constant.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Mask {
    private ExpertRepository expertRepository;
    private FarmProductMarketChiefRepository farmProductMarketChiefRepository;
    private FarmProductMarketRepository farmProductMarketRepository;
    private InspectorRepository inspectorRepository;
    private NotificationRepository notificationRepository;
    private RegulatorySingleTaskRepository regulatorySingleTaskRepository;
    private RegulatoryTasksRepository regulatoryTasksRepository;
    private SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository;
    private ProductCategoryRepository productCategoryRepository;
    private MainTaskExpertRelationRepository mainTaskExpertRelationRepository;
    private ScoreChangeRecordsRepository scoreChangeRecordsRepository;
    private ArrayList<Long> markerId;
    private ArrayList<String> categories;
    private ArrayList<Inspector> inspectors;
    private ArrayList<FarmProductMarket> farmProductMarkets;
    private ArrayList<FarmProductMarketChief> farmProductMarketChiefs;
    private ArrayList<Expert> experts;

    @Autowired
    public Mask(ExpertRepository expertRepository, FarmProductMarketChiefRepository farmProductMarketChiefRepository, FarmProductMarketRepository farmProductMarketRepository, InspectorRepository inspectorRepository, NotificationRepository notificationRepository, RegulatorySingleTaskRepository regulatorySingleTaskRepository, RegulatoryTasksRepository regulatoryTasksRepository, SamplingInspectionExpertRelationRepository samplingInspectionExpertRelationRepository, ProductCategoryRepository productCategoryRepository, MainTaskExpertRelationRepository mainTaskExpertRelationRepository, ScoreChangeRecordsRepository scoreChangeRecordsRepository) {
        this.expertRepository = expertRepository;
        this.farmProductMarketChiefRepository = farmProductMarketChiefRepository;
        this.farmProductMarketRepository = farmProductMarketRepository;
        this.inspectorRepository = inspectorRepository;
        this.notificationRepository = notificationRepository;
        this.regulatorySingleTaskRepository = regulatorySingleTaskRepository;
        this.regulatoryTasksRepository = regulatoryTasksRepository;
        this.samplingInspectionExpertRelationRepository = samplingInspectionExpertRelationRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.mainTaskExpertRelationRepository = mainTaskExpertRelationRepository;
        this.scoreChangeRecordsRepository = scoreChangeRecordsRepository;
        markerId = new ArrayList<>();
        categories = new ArrayList<>();
        inspectors = new ArrayList<>();
        farmProductMarkets = new ArrayList<>();
        farmProductMarketChiefs = new ArrayList<>();
        experts = new ArrayList<>();
        init();
    }

    @Transactional
    public void runTest() {
        //发起监管任务“Test”
        RegulatoryTasks regulatoryTasks = launchTask(inspectors.get(0), "Test");

        //专家查看收到的通知
        showNotification(experts.get(0));
        showNotification(experts.get(1));
        showNotification(experts.get(2));

        //农贸市场负责人查看收到的通知
        showNotification(farmProductMarketChiefs.get(0));
        showNotification(farmProductMarketChiefs.get(1));
        showNotification(farmProductMarketChiefs.get(2));
        showNotification(farmProductMarketChiefs.get(3));
        showNotification(farmProductMarketChiefs.get(4));

        //农产品市场负责人分别完成各自的待完成任务
        nextDay();
        register(farmProductMarketChiefs.get(0), regulatoryTasks);
        nextDay();
        register(farmProductMarketChiefs.get(1), regulatoryTasks);
        nextDay();
        register(farmProductMarketChiefs.get(2), regulatoryTasks);
        for (int i = 0; i < 17; i++) {
            nextDay();
        }
        register(farmProductMarketChiefs.get(3), regulatoryTasks);

        //检查监管任务的整体完成情况
        getUnDoneSituation(inspectors.get(0));

        for (int i = 0; i < 14; i++) {
            nextDay();
        }
        register(farmProductMarketChiefs.get(4), regulatoryTasks);

        //专家完成其待完成的抽检任务
        nextDay();
        register(experts.get(0));
        nextDay();
        register(experts.get(1));
        nextDay();
        register(experts.get(2));

        //农产品市场负责人查看其分数
        showScores(farmProductMarketChiefs.get(0));
        showScores(farmProductMarketChiefs.get(1));
        showScores(farmProductMarketChiefs.get(2));
        showScores(farmProductMarketChiefs.get(3));
        showScores(farmProductMarketChiefs.get(4));

        //专家查看其分数
        showScores(experts.get(0));
        showScores(experts.get(1));
        showScores(experts.get(2));

        //查看各类农产品总不合格数
        checkTotalUnqualifiedNumOfInspect(inspectors.get(0), categories.get(0), regulatoryTasks.getDate(), regulatoryTasks.getDeadLine());
        checkTotalUnqualifiedNumOfInspect(inspectors.get(0), categories.get(1), regulatoryTasks.getDate(), regulatoryTasks.getDeadLine());
        checkTotalUnqualifiedNumOfInspect(inspectors.get(0), categories.get(2), regulatoryTasks.getDate(), regulatoryTasks.getDeadLine());

        //检查监管任务的整体完成情况
        getUnDoneSituation(inspectors.get(0));
    }

    @Transactional
    RegulatoryTasks launchTask(Inspector inspector, String name) {
        String startTime = IndicatorService.getTime();
        Date deadlineDate;
        SimpleDateFormat format = IndicatorService.formatter;
        try {
            deadlineDate = format.parse(startTime);
        } catch (ParseException e) {
            throw new ErrorCode(ErrorCode.INVALID_DATE_STRING);
        }
        deadlineDate.setTime(deadlineDate.getTime() + 10 * 1000 * 60 * 60 * 24);
        String deadline = format.format(deadlineDate);

        inspector.launchRegulatoryTask(name, markerId, startTime, deadline, categories, this);
        RegulatoryTasks regulatoryTasks = getRegulatoryTasksRepository().findByName(name);

        System.out.println("\n\n-------------------------------------");
        System.out.println("Regulatory Task " + regulatoryTasks.getName() + " has been launched. ");
        return regulatoryTasks;
    }

    @Transactional
    void showNotification(Expert expert) {
        System.out.println("\n\n-------------------------------------");
        System.out.println("Notification of Expert : " + expert.getId());
        expert.getNotifications(this);
    }

    @Transactional
    void showNotification(FarmProductMarketChief farmProductMarketChief) {
        System.out.println("\n\n-------------------------------------");
        System.out.println("Notification of Market Chief : " + farmProductMarketChief.getId());
        farmProductMarketChief.getNotifications(this);
    }

    @Transactional
    void register(FarmProductMarketChief farmProductMarketChief, RegulatoryTasks regulatoryTasks) {
        RegulatorySingleTask regulatorySingleTask = getRegulatorySingleTaskRepository().findByFarmProductMarketIdAndRegulatoryTaskIdAndIfSampling(farmProductMarketChief.getFarmProductMarketId(), regulatoryTasks.getId(), false);
        if (regulatorySingleTask == null) {
            return;
        }
        FarmProductMarket farmProductMarket = getFarmProductMarketRepository().findById((long)farmProductMarketChief.getFarmProductMarketId());
        if (farmProductMarket == null) {
            return;
        }

        for (String category : categories) {
            System.out.println("\n\nThe chief of farm market " + farmProductMarket.getName() + " has registered category " + category);
            farmProductMarketChief.completeSingleCategoryTask(regulatorySingleTask, category, 10, 2, this);
            System.out.println(farmProductMarket.getName() + " : ");
            Set<SingleCategoryResult> singleCategoryResultSet1;
            singleCategoryResultSet1 = regulatorySingleTask.getRegulatorySingleTaskResult();
            for (SingleCategoryResult temp : singleCategoryResultSet1) {
                System.out.println("Category: " + temp.getCategory() + "  IsWhetherDone: " + temp.isWhetherDone()
                        + "  TotalNumOfInspect: " + temp.getTotalNumOfInspect() + "  UnqualifiedNumOfInspect: "
                        + temp.getUnqualifiedNumOfInspect());
            }
        }
    }

    @Transactional
    void register(Expert expert) {
        List<Notification> notifications1 = notificationRepository.findByTargetPersonId(expert.getId());
        if (notifications1.size() == 0) {
            return;
        }
        for (Notification temp : notifications1) {
            RegulatorySingleTask regulatorySingleTask = regulatorySingleTaskRepository.findById((long) temp.getRegulatorySingleTaskId());
            Set<SingleCategoryResult> singleCategoryResultSet = regulatorySingleTask.getRegulatorySingleTaskResult();
            FarmProductMarket farmProductMarket = farmProductMarketRepository.findById((long) regulatorySingleTask.getFarmProductMarketId());
            ArrayList<String> names = new ArrayList<>();
            for (SingleCategoryResult singleCategoryResult : singleCategoryResultSet) {
                names.add(singleCategoryResult.getCategory());
            }
            int i;
            for (i = 0; i < singleCategoryResultSet.size(); i++) {
                System.out.println("\n\nThe expert " + expert.getId() + " has registered category " + names.get(i) + " for farm market " + farmProductMarket.getName());
                expert.registerResults(regulatorySingleTask, names.get(i), i + 10, i, this);
            }
            for (SingleCategoryResult singleCategoryResult : singleCategoryResultSet) {
                System.out.println("Category: " + singleCategoryResult.getCategory() + "  IsWhetherDone: " + singleCategoryResult.isWhetherDone()
                        + "  TotalNumOfInspect: " + singleCategoryResult.getTotalNumOfInspect() + "  UnqualifiedNumOfInspect: " + singleCategoryResult.getUnqualifiedNumOfInspect());
            }
        }
    }

    @Transactional
    void showScores(FarmProductMarketChief farmProductMarketChief) {
        System.out.println("\n\n");
        farmProductMarketChief.getTotalAndScoreChangingRecord(this);
    }

    @Transactional
    void showScores(Expert expert) {
        System.out.println("\n\n");
        Expert tempExpert = expertRepository.findById((long)expert.getId());
        tempExpert.getTotalAndScoreChangingRecord(this);
    }

    @Transactional
    void checkTotalUnqualifiedNumOfInspect(Inspector inspector, String category, String dateBegin, String dateEnd) {
        System.out.println("\n\n");
        inspector.checkTotalUnqualifiedNumOfInspect(category, dateBegin, dateEnd, this);
    }

    @Transactional
    void getUnDoneSituation(Inspector inspector) {
        System.out.println("\n\n");
        inspector.getUnDoneSituation(this);
    }

    @Transactional
    void nextDay() {
        IndicatorService.update();
        System.out.println("\n\n");
        System.out.println(IndicatorService.getTime());
        System.out.println("\n\n");
        Util.dailyCheck(expertRepository,
                farmProductMarketChiefRepository,
                farmProductMarketRepository,
                regulatoryTasksRepository,
                regulatorySingleTaskRepository,
                mainTaskExpertRelationRepository,
                scoreChangeRecordsRepository);
    }

    @Transactional
    void init() {
        //清空全部数据表
        getExpertRepository().deleteAll();
        getFarmProductMarketChiefRepository().deleteAll();
        getFarmProductMarketRepository().deleteAll();
        getInspectorRepository().deleteAll();
        getNotificationRepository().deleteAll();
        getRegulatorySingleTaskRepository().deleteAll();
        getRegulatoryTasksRepository().deleteAll();
        getSamplingInspectionExpertRelationRepository().deleteAll();
        getProductCategoryRepository().deleteAll();
        getMainTaskExpertRelationRepository().deleteAll();
        getScoreChangeRecordsRepository().deleteAll();

        //初始化Inspector表
        Inspector inspector = new Inspector();
        getInspectorRepository().save(inspector);
        inspectors.add(inspector);

        //初始化ProductCategory表
        getProductCategoryRepository().save(new ProductCategory(Category.AQUATIC_PRODUCTS));
        getProductCategoryRepository().save(new ProductCategory(Category.LIVE_STOCK));
        getProductCategoryRepository().save(new ProductCategory(Category.VEGETABLES));

        //初始化FarmProductMarket表
        Set<String> categoriesOfMarkets = new HashSet<>();
        categoriesOfMarkets.add(Category.VEGETABLES);
        categoriesOfMarkets.add(Category.LIVE_STOCK);
        categoriesOfMarkets.add(Category.AQUATIC_PRODUCTS);
        FarmProductMarket farmProductMarket1 = new FarmProductMarket("TestMarket1", 0, categoriesOfMarkets);
        getFarmProductMarketRepository().save(farmProductMarket1);
        FarmProductMarket farmProductMarket2 = new FarmProductMarket("TestMarket2", 0, categoriesOfMarkets);
        getFarmProductMarketRepository().save(farmProductMarket2);
        FarmProductMarket farmProductMarket3 = new FarmProductMarket("TestMarket3", 0, categoriesOfMarkets);
        getFarmProductMarketRepository().save(farmProductMarket3);
        FarmProductMarket farmProductMarket4 = new FarmProductMarket("TestMarket4", 0, categoriesOfMarkets);
        getFarmProductMarketRepository().save(farmProductMarket4);
        FarmProductMarket farmProductMarket5 = new FarmProductMarket("TestMarket5", 0, categoriesOfMarkets);
        getFarmProductMarketRepository().save(farmProductMarket5);
        farmProductMarkets.add(farmProductMarket1);
        farmProductMarkets.add(farmProductMarket2);
        farmProductMarkets.add(farmProductMarket3);
        farmProductMarkets.add(farmProductMarket4);
        farmProductMarkets.add(farmProductMarket5);

        //初始化FarmProductMarketChief表
        FarmProductMarketChief farmProductMarketChief1 = new FarmProductMarketChief(farmProductMarket1.getId());
        getFarmProductMarketChiefRepository().save(farmProductMarketChief1);
        FarmProductMarketChief farmProductMarketChief2 = new FarmProductMarketChief(farmProductMarket2.getId());
        getFarmProductMarketChiefRepository().save(farmProductMarketChief2);
        FarmProductMarketChief farmProductMarketChief3 = new FarmProductMarketChief(farmProductMarket3.getId());
        getFarmProductMarketChiefRepository().save(farmProductMarketChief3);
        FarmProductMarketChief farmProductMarketChief4 = new FarmProductMarketChief(farmProductMarket4.getId());
        getFarmProductMarketChiefRepository().save(farmProductMarketChief4);
        FarmProductMarketChief farmProductMarketChief5 = new FarmProductMarketChief(farmProductMarket5.getId());
        getFarmProductMarketChiefRepository().save(farmProductMarketChief5);
        farmProductMarketChiefs.add(farmProductMarketChief1);
        farmProductMarketChiefs.add(farmProductMarketChief2);
        farmProductMarketChiefs.add(farmProductMarketChief3);
        farmProductMarketChiefs.add(farmProductMarketChief4);
        farmProductMarketChiefs.add(farmProductMarketChief5);

        //初始化Expert表
        Expert expert1 = new Expert(0);
        getExpertRepository().save(expert1);
        Expert expert2 = new Expert(0);
        getExpertRepository().save(expert2);
        Expert expert3 = new Expert(0);
        getExpertRepository().save(expert3);
        experts.add(expert1);
        experts.add(expert2);
        experts.add(expert3);

        markerId.add(farmProductMarket1.getId());
        markerId.add(farmProductMarket2.getId());
        markerId.add(farmProductMarket3.getId());
        markerId.add(farmProductMarket4.getId());
        markerId.add(farmProductMarket5.getId());
        categories.add(Category.AQUATIC_PRODUCTS);
        categories.add(Category.LIVE_STOCK);
        categories.add(Category.VEGETABLES);
    }

    public ExpertRepository getExpertRepository() {
        return expertRepository;
    }

    public FarmProductMarketChiefRepository getFarmProductMarketChiefRepository() {
        return farmProductMarketChiefRepository;
    }

    public FarmProductMarketRepository getFarmProductMarketRepository() {
        return farmProductMarketRepository;
    }

    public InspectorRepository getInspectorRepository() {
        return inspectorRepository;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public RegulatorySingleTaskRepository getRegulatorySingleTaskRepository() {
        return regulatorySingleTaskRepository;
    }

    public RegulatoryTasksRepository getRegulatoryTasksRepository() {
        return regulatoryTasksRepository;
    }

    public SamplingInspectionExpertRelationRepository getSamplingInspectionExpertRelationRepository() {
        return samplingInspectionExpertRelationRepository;
    }

    public ProductCategoryRepository getProductCategoryRepository() {
        return productCategoryRepository;
    }

    public MainTaskExpertRelationRepository getMainTaskExpertRelationRepository() {
        return mainTaskExpertRelationRepository;
    }

    public ScoreChangeRecordsRepository getScoreChangeRecordsRepository() {
        return scoreChangeRecordsRepository;
    }
}
