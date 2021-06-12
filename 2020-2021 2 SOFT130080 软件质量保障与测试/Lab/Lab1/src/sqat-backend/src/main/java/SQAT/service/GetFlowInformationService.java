package SQAT.service;

import SQAT.constant.Constant;
import SQAT.controller.request.GetFlowRequest;
import SQAT.domain.TransactionRecordDto;
import SQAT.response.GetFlowResponse;
import SQAT.util.BackendHttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GetFlowInformationService {
    @Autowired
    public GetFlowInformationService() {

    }

    public List<TransactionRecordDto> getFlow(GetFlowRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            //Get方式请求
            String url = Constant.URL + "/transaction?" + "pageSize=1000&pageNum=1" +
                    "&params=%7B%22orderBy%22:%22order+by+updateTime+DESC%22%7D";

            //设置请求参数，获取返回的Json字符串
            String result = BackendHttpRequest.sendGet(url, "", CheckService.getToken());
            System.out.println(result);

            //解析Json字符串
            GetFlowResponse getFlowResponse = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                getFlowResponse = objectMapper.readValue(result, GetFlowResponse.class);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (getFlowResponse != null) {
                System.out.println(getFlowResponse.toString());
                return new ArrayList<>(Arrays.asList(getFlowResponse.getList()));
            }
            return null;
        }
        return null;
    }
}
