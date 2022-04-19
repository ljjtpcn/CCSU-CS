--������һλȫ�����ṹ�����Ķ������
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
--������һλȫ����ʵ������
ENTITY full_adder IS
PORT (a, b, ci: IN STD_LOGIC;
         s, co: OUT STD_LOGIC);
END full_adder;
--������һλȫ�����ṹ������
ARCHITECTURE Structural OF full_adder IS
COMPONENT H_adder              --�������������
PORT (in_a, in_b    : IN STD_LOGIC;
      out_s, out_co : OUT STD_LOGIC);
END COMPONENT;
COMPONENT Or_2                 --���Ų�������
PORT (x, y: IN STD_LOGIC;
         z: OUT STD_LOGIC);
END COMPONENT;
SIGNAL u, v, w: STD_LOGIC; --�����ڲ���������
BEGIN
U1: H_adder PORT MAP (in_a=>a, in_b =>b,  
             out_s=>v, out_co=>u);  --�˿�ӳ��
U2: H_adder PORT MAP (in_a=>v, in_b=>ci, 
             out_s=>s, out_co=>w);
U3: Or_2 PORT MAP (x => u, y => w, z=>co);
END Structural;