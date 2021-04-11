function [a_quan]=ula_pcm(a,n,u)
%ULA_PCM 	u-law PCM encoding of a sequence
%       	[A_QUAN]=MULA_PCM(X,N,U).
%       	X=input sequence.
%       	n=number of quantization levels (even).     	
%		a_quan=quantized output before encoding.
%       U the parameter of the u-law

% todo: 

% 利用μ律函数对输入量化器的信号进行压缩处理
compression_signal = ulaw(a,u);

% 对压缩处理后的信号进行均匀量化
quantized_signal = u_pcm(compression_signal,n);

% 利用μ律函数的反函数对均匀量化后的信号进行扩张处理
a_quan = inv_ulaw(quantized_signal,u);

end