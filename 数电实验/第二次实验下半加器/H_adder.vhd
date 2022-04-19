LIBRARY IEEE;       --���ļ�
USE IEEE.STD_LOGIC_1164.ALL;
ENTITY h_adder IS   --ʵ��
   PORT(            --�˿ڶ���
        in_a  : IN  STD_LOGIC;
        in_b  : IN  STD_LOGIC;
        out_S : OUT STD_LOGIC;
        out_co : OUT STD_LOGIC
        );
END ENTITY h_adder;

--�����ǰ�����ṹ����Ϊ����
ARCHITECTURE Dataflow OF H_adder IS
BEGIN 
	out_s<= '1' WHEN (in_a= '0' AND in_b= '1') ELSE
	            '1' WHEN (in_a= '1' AND in_b= '0') ELSE
				'0';
	out_co<= '1' WHEN (in_a= '1' AND in_b= '1') ELSE
	            '0';
END Dataflow; 