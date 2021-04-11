package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.service.AuthService;
import fudan.se.lab2.service.ConferenceService;
import fudan.se.lab2.service.InquireService;
import fudan.se.lab2.service.InviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author LBW
 */
@RestController
public class AuthController {

    private AuthService authService;
    private InquireService inquireService;
    private ConferenceService conferenceService;
    private InviteService inviteService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthService authService, InquireService inquireService, ConferenceService conferenceService, InviteService inviteService) {
        this.authService = authService;
        this.inquireService = inquireService;
        this.conferenceService = conferenceService;
        this.inviteService = inviteService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        logger.debug("RegistrationForm: " + request.toString());
        String password=request.getPassword();
        String username=request.getUsername();
        if (password.contains(username)) return ResponseEntity.ok("{\"error\": \"password contains username\"");
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        logger.debug("LoginForm: " + request.toString());
        return ResponseEntity.ok(authService.login(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/startMeeting")
    public ResponseEntity<?> startMeeting(@RequestBody StartMeetingRequest request) {
        logger.debug("StartMeetingForm: " + request.toString());
        return ResponseEntity.ok(conferenceService.startMeeting(request));
    }

    @PostMapping("/contribute")
    public ResponseEntity<?> contribute(@RequestBody ContributeRequest request) {
        logger.debug("ContributeForm: " + request.toString());
        return ResponseEntity.ok(inviteService.contribute(request));
    }

    @PostMapping("/findUserByRealname")
    public ResponseEntity<?> findUserByRealname(@RequestBody FindUserByRealnameRequest request){
        logger.debug("FindUserByRealnameForm: " + request.toString());
        return ResponseEntity.ok(inquireService.findUserByRealname(request));
    }

    @PostMapping("/findMeetingByIdentity")
    public ResponseEntity<?> findMeetingByIdentity(@RequestBody FindMeetingByIdentityRequest request){
        logger.debug("FindMeetingByIdentityForm: " + request.toString());
        return ResponseEntity.ok(inquireService.findMeetingByIdentity(request));
    }

    @PostMapping("/findInviteByIdentity")
    public ResponseEntity<?> findInviteByIdentity(@RequestBody FindInviteByIdentityRequest request){
        logger.debug("FindInviteByIdentityForm: " + request.toString());
        return ResponseEntity.ok(inquireService.findInviteByIdentity(request));
    }

    @PostMapping("/updateInvite")
    public ResponseEntity<?> updateInvite(@RequestBody UpdateInviteRequest request){
        logger.debug("UpdateInviteForm: " + request.toString());
        return ResponseEntity.ok(inviteService.updateInvite(request));
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public  ResponseEntity<?> uplodeFile(@RequestParam String meetingName, @RequestParam MultipartFile uploadfile, @RequestParam String title, @RequestParam String digest, @RequestParam String userName, @RequestParam Set<String> topics,@RequestParam List<String[]> authors,@RequestParam Long contributionID){
        return ResponseEntity.ok(conferenceService.uploadFile(meetingName,uploadfile,title,digest,userName,topics,authors,logger,contributionID));
    }

    @PostMapping("/modifyPaper")
    @ResponseBody
    public  ResponseEntity<?> uplodeFile(@RequestParam String meetingName, @RequestParam String title, @RequestParam String digest, @RequestParam String userName, @RequestParam Set<String> topics,@RequestParam List<String[]> authors,@RequestParam Long contributionID){
        return ResponseEntity.ok(conferenceService.modifyPaper(contributionID, title, digest, topics, authors,meetingName));
    }

    @PostMapping("/reviewFile")
    @ResponseBody
    public ResponseEntity<?> reviewFile(HttpServletResponse response,@RequestParam Long paperId) throws IOException {
        return ResponseEntity.ok(authService.reviewFile(response, paperId));
    }

    @PostMapping("/verifyMeetings")
    public ResponseEntity<?> verifyMeetings(@RequestBody VerifyMeetingsRequest request){
        logger.debug("VerifyMeetingsForm: " + request.toString());
        return ResponseEntity.ok(conferenceService.verifyMeetings(request));
    }

    @PostMapping("/openContribute")
    public ResponseEntity<?> openContribute(@RequestBody ContributeOperationRequest request){
        logger.debug("OpenContributeForm: " + request.toString());
        return ResponseEntity.ok(conferenceService.openContribute(request));
    }

    @PostMapping("/closeContribute")
    public ResponseEntity<?> closeContribute(@RequestBody ContributeOperationRequest request){
        logger.debug("CloseContributeForm: " + request.toString());
        return ResponseEntity.ok(conferenceService.closeContribute(request));
    }

    @PostMapping("/getTopicByMeetingID")
    public ResponseEntity<?> getTopicByMeetingID(@RequestBody GetTopicByMeetingIDRequest request){
        logger.debug("getTopicByMeetingID: " + request.toString());
        return ResponseEntity.ok(inquireService.getTopicByMeetingID(request));
    }

    @PostMapping("/getContribute")
    public ResponseEntity<?> getContribute(@RequestBody GetContributRequest request){
        logger.debug("getContribute: " + request.toString());
        return ResponseEntity.ok(inquireService.getContribute(request));
    }

    @PostMapping("/getConferenceByMeetingID")
    public ResponseEntity<?> getTopicByMeetingID(@RequestBody GetConferenceByMeetingIDRequest request){
        logger.debug("getTopicByMeetingID: " + request.toString());
        return ResponseEntity.ok(inquireService.getConferenceByMeetingID(request));
    }

    @PostMapping("/reviewMeetings")
    public ResponseEntity<?> reviewMeetings(@RequestBody ReviewMeetingsRequest request){
        logger.debug("reviewMeetings: " + request.toString());
        return ResponseEntity.ok(conferenceService.reviewMeetings(request));
    }

    @PostMapping("/distributeResults")
    public ResponseEntity<?> distributeResults(@RequestBody DistributeResultsRequest request){
        logger.debug("reviewMeetings: " + request.toString());
        return ResponseEntity.ok(conferenceService.distributeResults(request));
    }

    @PostMapping("/getContributionState")
    public ResponseEntity<?> getContributionState(@RequestBody GetContributionStateRequest request){
        logger.debug("getContributionState: " + request.toString());
        return ResponseEntity.ok(inquireService.getContributionState(request));
    }
    @PostMapping("/submitReviewIformation")
    public ResponseEntity<?> submitReviewInfoemation(@RequestBody SubmitReviewIformationRequest request){
        logger.debug("submitReviewIformation: " + request.toString());
        return ResponseEntity.ok(conferenceService.submitReviewInformation(request));
    }
    @PostMapping("/getReviewList")
    public ResponseEntity<?> getReviewList(@RequestBody GetReviewListRequest request){
        logger.debug("getReviewList: " + request.toString());
        return ResponseEntity.ok(inquireService.getReviewList(request));
    }
    @PostMapping("/getReviewResult")
    public ResponseEntity<?> getReviewResult(@RequestBody GetReviewResultRequest request){
        logger.debug("getReviewResult: " + request.toString());
        return ResponseEntity.ok(inquireService.getReviewResult(request));
    }
    @PostMapping("/preview")
    public ResponseEntity<String> prePDF(@RequestParam String filePath, HttpServletResponse response) {
        logger.debug("previewPDF: " + filePath);
        return ResponseEntity.ok(authService.prePDF(filePath,response));
    }
    @PostMapping("/sendPost")
    public ResponseEntity<?> sendPost(@RequestBody SendPostRequest request){
        logger.debug("sendPost" + request.toString());
        return ResponseEntity.ok(conferenceService.sendPost(request));
    }
    @PostMapping("/modifyEvaluation")
    public ResponseEntity<?> modifyEvaluation(@RequestBody ModifyEvaluationRequest request){
        logger.debug("modifyEvaluation: " + request.toString());
        return ResponseEntity.ok(conferenceService.modifyEvaluation(request));
    }
    @PostMapping("/getAllPosts")
    public ResponseEntity<?> getAllPosts(@RequestBody GetAllPostsRequest request){
        logger.debug("getAllPosts: " + request.toString());
        return ResponseEntity.ok(conferenceService.getAllPosts(request));
    }
    @PostMapping("/getAllReplies")
    public ResponseEntity<?> getAllReplies(@RequestBody GetAllRepliesRequest request){
        logger.debug("getAllPReplies: " + request.toString());
        return ResponseEntity.ok(conferenceService.getAllReplies(request));
    }
    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestBody ConfirmRequest request){
        logger.debug("confirm: " + request.toString());
        return ResponseEntity.ok(conferenceService.confirm(request));
    }

    @PostMapping("/sendRebuttal")
    public ResponseEntity<?> sendRebuttal(@RequestBody SendRebuttalRequest request){
        logger.debug("sendRebuttal: " + request.toString());
        return ResponseEntity.ok(conferenceService.sendRebuttal(request));
    }
    @PostMapping("/firstRelease")
    public ResponseEntity<?> firstRelease(@RequestBody ReleaseRequest request){
        logger.debug("firstRelease: " + request.toString());
        return ResponseEntity.ok(inquireService.firstRelease(request));
    }
    @PostMapping("/finalRelease")
    public ResponseEntity<?> finalRelease(@RequestBody ReleaseRequest request){
        logger.debug("finalRelease: " + request.toString());
        return ResponseEntity.ok(inquireService.finalRelease(request));
    }
    @PostMapping("/getOldResult")
    public ResponseEntity<?> getOldResult(@RequestBody GetOldResultRequest request){
        logger.debug("getOldResult: " + request.toString());
        return ResponseEntity.ok(inquireService.getOldResult(request));
    }
    @PostMapping("/setFinalRelease")
    public ResponseEntity<?> setFinalRelease(@RequestBody ReleaseRequest request){
        logger.debug("setFinalRelease: " + request.toString());
        return ResponseEntity.ok(conferenceService.setFinalRelease(request));
    }
    @PostMapping("/setFirstRelease")
    public ResponseEntity<?> setFirstRelease(@RequestBody ReleaseRequest request){
        logger.debug("setFirstRelease: " + request.toString());
        return ResponseEntity.ok(conferenceService.setFirstRelease(request));
    }
    /**
     * This is a function to test your connectivity. (健康测试时，可能会用到它）.
     */
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        Map<String, String> response = new HashMap<>();
        String message = "Welcome to 2020 Software Engineering Lab2. ";
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

}



