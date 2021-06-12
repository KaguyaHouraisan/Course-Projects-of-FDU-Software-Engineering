package SQAT.controller;

import SQAT.controller.request.*;
import SQAT.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    private CheckService checkService;
    private LoanRepaymentService loanRepaymentService;
    private GetFlowInformationService getFlowInformationService;
    private FinancialPurchaseService financialPurchaseService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(CheckService checkService, LoanRepaymentService loanRepaymentService, GetFlowInformationService getFlowInformationService, FinancialPurchaseService financialPurchaseService) {
        this.checkService = checkService;
        this.loanRepaymentService = loanRepaymentService;
        this.getFlowInformationService = getFlowInformationService;
        this.financialPurchaseService = financialPurchaseService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.debug("Login Form: " + request.toString());
        return ResponseEntity.ok(checkService.login(request));
    }

    @PostMapping("/checkId")
    public ResponseEntity<?> checkId(@RequestBody CheckIdRequest request) {
        logger.debug("Check ID Form: " + request.toString());
        return ResponseEntity.ok(loanRepaymentService.checkId(request));
    }

    @PostMapping("/getLoanAccountList")
    public ResponseEntity<?> getLoanAccountList(@RequestBody GetLoanAccountListRequest request) {
        logger.debug("Get Loan Account List Form: " + request.toString());
        return ResponseEntity.ok(loanRepaymentService.getLoanAccountList(request));
    }

    @PostMapping("/getLoanDetail")
    public ResponseEntity<?> getLoanDetail(@RequestBody GetLoanDetailRequest request) {
        logger.debug("Get Loan Detail Form: " + request.toString());
        return ResponseEntity.ok(loanRepaymentService.getLoanDetail(request));
    }

    @PostMapping("/loanPlan")
    public ResponseEntity<?> loanPlan(@RequestBody LoanPlanRequest request) {
        logger.debug("Loan Plan Form: " + request.toString());
        return ResponseEntity.ok(loanRepaymentService.loanPlan(request));
    }

    @PostMapping("/payLoan")
    public ResponseEntity<?> payLoan(@RequestBody PayLoanRequest request) {
        logger.debug("Pay Loan Form: " + request.toString());
        return ResponseEntity.ok(loanRepaymentService.payLoan(request));
    }

    @PostMapping("/payLoanPart")
    public ResponseEntity<?> payLoanPart(@RequestBody PartPayLoanRequest request) {
        logger.debug("Pay Loan Part Form: " + request.toString());
        return ResponseEntity.ok(loanRepaymentService.partPayLoan(request));
    }

    @PostMapping("/getFlow")
    public ResponseEntity<?> getFlow(@RequestBody GetFlowRequest request) {
        logger.debug("Get Flow Form: " + request.toString());
        return ResponseEntity.ok(getFlowInformationService.getFlow(request));
    }

    @PostMapping("/evaluateCreditRating")
    public ResponseEntity<?> evaluateCreditRating(@RequestBody EvaluateUserCreditRatingRequest request) {
        logger.debug("Evaluate User Credit Rating Form: " + request.toString());
        return ResponseEntity.ok(financialPurchaseService.evaluateUserCreditRating(request));
    }

    @PostMapping("/getProduct")
    public ResponseEntity<?> getProduct(@RequestBody GetProductRequest request) {
        logger.debug("Get Product Form: " + request.toString());
        return ResponseEntity.ok(financialPurchaseService.getProduct(request));
    }

    @PostMapping("/getPenalty")
    public ResponseEntity<?> getPenalty(@RequestBody GetPenaltyRequest request) {
        logger.debug("Get Penalty Form: " + request.toString());
        return ResponseEntity.ok(financialPurchaseService.getPenalty(request.getToken(), request.getAccountNum()));
    }

    @PostMapping("/payPenalty")
    public ResponseEntity<?> payPenalty(@RequestBody PayPenaltyRequest request) {
        logger.debug("Pay Penalty Form: " + request.toString());
        return ResponseEntity.ok(financialPurchaseService.payPenalty(request.getToken(), request.getAccountNum()));
    }

    @PostMapping("/buyProduct")
    public ResponseEntity<?> buyProduct(@RequestBody BuyProductRequest request) {
        logger.debug("Buy Product Form: " + request.toString());
        return ResponseEntity.ok(financialPurchaseService.buyProduct(request.getToken(), request.getAccountNum(), request.getProductNum(), request.getAmount(), request.getPurchaseTime(), request.getShareAmount()));
    }

    @PostMapping("/getPurchaseRecord")
    public ResponseEntity<?> getPurchaseRecord(@RequestBody GetPurchaseRecordRequest request) {
        logger.debug("Get Purchase Record Form: " + request.toString());
        return ResponseEntity.ok(financialPurchaseService.getPurchaseRecord(request.getToken(), request.getAccountNum()));
    }

    @PostMapping("/autoPayLoanPlan")
    public ResponseEntity<?> autoPayLoanPlan(@RequestBody AutoPayLoanRequest request){
        logger.debug("Auto Pay Loan Part Form: " + request.toString());
        return ResponseEntity.ok(loanRepaymentService.autoPayLoan(request));
    }

    /**
     * This is a function to test your connectivity.
     */
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        Map<String, String> response = new HashMap<>();
        String message = "2021 Software Quality Assurance And Testing Lab1. ";
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}



