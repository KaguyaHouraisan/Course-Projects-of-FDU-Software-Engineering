function [a_quan]=u_pcm(a,n)
%U_PCM  	uniform PCM encoding of a sequence
%       	[A_QUAN]=U_PCM(A,N)
%       	a=input sequence.
%       	n=number of quantization levels (even).
%		a_quan=quantized output before encoding.

% todo: 

% 将输入的信号序列归一化到[-1,1]的区间内
a_quan = a ./ max(abs(a));

% 设置量化间隔为2/n
d = 2 / n;

% 将信号的抽样值调整为对应的量化值
for i = -1: d: 1
    a_quan(a_quan >= i & a_quan < (i + d)) = (2 * i + d) / 2;
end

end
