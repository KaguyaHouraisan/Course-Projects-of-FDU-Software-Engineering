from typing import List
import torch
from Lab2Part2.CRF.CRF import CRF
from Lab2Part2.BiLSTM_CRF.BiLSTM_CRF import BiLSTM_CRF


def setup_seed(seed):
    torch.manual_seed(seed)
    torch.cuda.manual_seed_all(seed)


class Solution:
    def crf_predict(self, sentences: List[str]) -> List[str]:
        crf = CRF('train_dataset/dataset1/template.utf8')
        crf.try_load_model(True)
        results = []
        for sent in sentences:
            results.append(crf.segment(sent))
        return results

    def dnn_predict(self, sentences: List[str]) -> List[str]:
        setup_seed(7)
        model = torch.load('model_dataset/lstm_model.pkl')
        results = []
        for sent in sentences:
            results.append(model(sent))
        return results
